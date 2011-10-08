

package com.martinanalytics.testmodule.client.controller;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.martinanalytics.testmodule.client.app.AppPref;
import com.martinanalytics.testmodule.client.app.AppEvent;
import com.martinanalytics.testmodule.client.app.AppEventListener;
import com.martinanalytics.testmodule.client.app.AppMsg;
import com.martinanalytics.testmodule.client.app.AppNotifyObject;
import com.martinanalytics.testmodule.client.model.bean.SecurityPrivilegeBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import com.martinanalytics.testmodule.client.model.SecurityPrivilegeDS;
import com.martinanalytics.testmodule.client.model.SecurityProfileDS;
import com.martinanalytics.testmodule.client.model.SecurityProfileGrantDS;
import com.martinanalytics.testmodule.client.model.SecurityProfileService;
import com.martinanalytics.testmodule.client.model.SecurityProfileServiceAsync;
import com.martinanalytics.testmodule.client.model.VwProfileGrantDS;
import com.martinanalytics.testmodule.client.model.VwUserGrantDS;
import com.martinanalytics.testmodule.client.model.bean.SecurityProfileBean;
import com.martinanalytics.testmodule.client.view.SecurityProfileView;
import com.martinanalytics.testmodule.client.view.SecurityProfileView;

import com.martinanalytics.testmodule.client.ServerExceptionHandler;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.widgets.grid.ListGridRecord;
//import com.martinanalytics.testmodule.client.widget.SimpleDateFormat;
//import com.extjs.gxt.ui.client.store.Record;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;



/**
 * SecurityProfileController owns the security_profile View and responds to all events genereated
 * by the View and it's subcomponents.
 * @author bmartin
 *
 */
public class SecurityProfileController implements AppEventListener{
  private static SecurityProfileController instance = null; //Singleton Instance
  private final SecurityProfileServiceAsync securityProfileService = 
	GWT.create(SecurityProfileService.class); 		// primary GWT remote Service
  private UserProfile userProfile;  			// User Properties
  private MasterController masterController;		// Overarching Controller
  private AppNotifyObject notifier = new AppNotifyObject();
  private java.util.Date lastUpdateHolder;
  SecurityProfileView securityProfileView ;
  private SecurityProfileDS securityProfileDS;
  private SecurityProfileGrantDS securityProfileGrantDS; 
  private SecurityPrivilegeDS securityPrivilegeDS;
  VwProfileGrantDS vwProfileGrantDS;



/**
 * Primary constructor, only called by getInstance, hence protected
 * @param masterController_
 */
  protected  SecurityProfileController(MasterController masterController_){
	masterController =masterController_;
	 
	 securityProfileDS =SecurityProfileDS.getInstance();
	 securityProfileGrantDS =SecurityProfileGrantDS.getInstance();
	 securityProfileGrantDS.getNotifier().addAppEventListener(this);
	 vwProfileGrantDS = VwProfileGrantDS.getInstance();
	 securityPrivilegeDS = SecurityPrivilegeDS.getInstance();
	 securityProfileView = new SecurityProfileView(securityProfileDS, securityProfileGrantDS, securityPrivilegeDS);
	 securityProfileView.getNotifier().addAppEventListener(this);
	 masterController_.getAppContainer().addChild(securityProfileView);
	 securityProfileGrantDS.getNotifier().addAppEventListener(this);
	userProfile = UserProfile.getInstance();
  }

/**
 * Singleton getInstance
 * @param masterController_ the overarching controller
 * @return a Login Conroller reference
 */
  public static SecurityProfileController getInstance(MasterController masterController_){
	if(instance == null){
		instance = new SecurityProfileController(masterController_);
	}
	return instance;
  } 

/**
 * Used to get access to the the primary UI and subcomponents (e.g. fields and tables)
 * @return the primary UI
 */
  public SecurityProfileView getSecurityProfileView() {
	return securityProfileView;
  }

/**
 * Handles custom event system events, driven by the event's name.  Type-casting of payloads
 * should happen here.
 */
@Override
  public void onAppEventNotify(AppEvent e_) {
	 Log.debug("SecurityProfileController received Appevent named:" + e_.getName());
     if (e_.getName().equals(AppMsg.ADD_SECURITY_PROFILE)){
    	 ListGridRecord rec = new ListGridRecord();
    	 rec.setAttribute("profileName", "_New Group");
    	 securityProfileView.getProfileListGrid().addData(rec);
    	 securityProfileView.getProfileListGrid().sort("profileName", SortDirection.ASCENDING);
     }else if (AppMsg.DELETE_SECURITY_PROFILE.equals(e_.getName())){	 
    	 ListGridRecord[] selectedRecords = securityProfileView.getProfileListGrid().getSelection();  
         for(ListGridRecord rec: selectedRecords) { 
        	 securityProfileView.getProfileListGrid().removeData(rec);  
         } 
         
    }else if(AppMsg.ADD_SECURITY_PROFILE_GRANT.equals(e_.getName())){
    	ListGridRecord recordToAdd = (ListGridRecord)e_.getPayLoad();
    	String profileToAddTo = (String)e_.getPayLoad2();
    	recordToAdd.setAttribute(SecurityProfileGrantDS.SECURITY_PROFILE_ID, Integer.parseInt(profileToAddTo));
    	//Log.debug("setting profile id" + profileToAddTo + " | " + e_.getPayLoad2() );
    	securityProfileGrantDS.addData(recordToAdd);
    }else if(AppMsg.SECURITY_GRANTS_FILTERED.equals(e_.getName())){
    	filterAvailableGrants();	
    
    }else if(AppMsg.SECURITY_PRIVILEGE_CACHE_AVAIL.equals(e_.getName())){
    	if(securityProfileView.getGrantDescriptions() == null){
    	HashMap<Integer, String>grantDescriptions = new HashMap<Integer, String>();
    	securityProfileView.getGrantDescriptions();
    	int privCount = securityPrivilegeDS.getCacheList().size();
    	for(int ndx =0; ndx < privCount;ndx++){
    		grantDescriptions.put(
    				securityPrivilegeDS.getCacheList().get(ndx).getAttributeAsInt(SecurityPrivilegeDS.SECURITY_PRIVILEGE_ID), 
    				securityPrivilegeDS.getCacheList().get(ndx).getAttributeAsString(SecurityPrivilegeDS.PRIV_NAME )
    						+ " " +securityPrivilegeDS.getCacheList().get(ndx).getAttributeAsString(SecurityPrivilegeDS.DESCRIPTION));
    	}
    	securityProfileView.setGrantDescriptions(grantDescriptions);
    	}
    	
    }else if(AppMsg.REMOVE_SECURITY_PROFILE_GRANT.equals(e_.getName())){
    	ListGridRecord recordToDelete = (ListGridRecord)e_.getPayLoad();
    	String profileToRemoveFrom = (String)e_.getPayLoad2();

 
    	recordToDelete.setAttribute(SecurityProfileGrantDS.SECURITY_PROFILE_ID, Integer.parseInt(profileToRemoveFrom));
    	Log.debug("record to delete timestamp" + recordToDelete.getAttribute("lastUpdate") );
    	securityProfileView.getSecurityGrantsListGrid().removeData(recordToDelete);
    }else if (AppMsg.EVT_CACHE_UPDATED.equals(e_.getName())){
    	AppMsg cacheUpdateType = (AppMsg)e_.getPayLoad();
    	if(cacheUpdateType.equals(AppMsg.EVT_RECORD_ADDED) ||cacheUpdateType.equals(AppMsg.EVT_RECORD_REMOVED)){
    		filterAvailableGrants();
    	}
    	
	}else{
		Log.debug("Unexpected AppEvent named" +e_.getName() );
	}
	
	
  }

private void filterAvailableGrants() {
	int totalAvailable = securityProfileView.getAvailableGrantsListGrid().getRecords().length;
	int totalGranted = securityProfileView.getSecurityGrantsListGrid().getRecords().length;
	Criteria availGrants = new Criteria();
	if(securityProfileView.getProfileListGrid().getSelectedRecord() !=null){
		ArrayList<Integer> alreadyGranted = new ArrayList<Integer>();
		for(int grantNdx = 0; grantNdx < totalGranted; grantNdx++){
			if (securityProfileView.getSecurityGrantsListGrid().getRecords()[grantNdx].getAttribute("securityPrivilegeId")==null) continue;
			alreadyGranted.add(Integer.parseInt(securityProfileView.getSecurityGrantsListGrid().getRecords()[grantNdx].getAttribute("securityPrivilegeId")));
		}
		Log.debug("Total Granted/Avail: " + totalGranted +"/"+ totalAvailable);
		if(totalGranted >0){
			availGrants.addCriteria("securityPrivilegeId",alreadyGranted.toArray(new Integer[0]));
			securityPrivilegeDS.setPositiveFilterMatch(false);
		}else{
			availGrants.addCriteria("securityPrivilegeId",-1);
			securityPrivilegeDS.setPositiveFilterMatch(false);
		}
		securityProfileView.getAvailableGrantsListGrid().filterData(availGrants);
	}
	securityProfileView.enable();
}



/**
 * @return the notifier
 */
public AppNotifyObject getNotifier() {
	return notifier;
}

public void showManageGroupsView(){
	//selectSecurityProfileBeans("","");
	securityProfileView.setTop(50);
	securityProfileView.show();
	securityProfileView.getProfileListGrid().fetchData();
	
	securityProfileView.getSecurityGrantsListGrid().filterData(new Criteria(VwProfileGrantDS.SECURITY_PROFILE_ID,"-1"));
	securityPrivilegeDS.setPositiveFilterMatch(true);
	securityProfileView.getAvailableGrantsListGrid().filterData(new Criteria(SecurityPrivilegeDS.SECURITY_PRIVILEGE_ID,"-1"));
	//securityProfileView.getSecurityGrantsListGrid().fetchData();
	
	
	
	
}


}
