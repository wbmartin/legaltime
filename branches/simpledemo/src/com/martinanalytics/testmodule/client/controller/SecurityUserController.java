
package com.martinanalytics.testmodule.client.controller;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.martinanalytics.testmodule.client.app.AppPref;
import com.martinanalytics.testmodule.client.app.AppEvent;
import com.martinanalytics.testmodule.client.app.AppEventListener;
import com.martinanalytics.testmodule.client.app.AppMsg;
import com.martinanalytics.testmodule.client.app.AppNotifyObject;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import com.martinanalytics.testmodule.client.model.bean.UserPublicBean;
import com.martinanalytics.testmodule.client.model.SecurityUserDS;
import com.martinanalytics.testmodule.client.model.SecurityUserService;
import com.martinanalytics.testmodule.client.model.SecurityUserServiceAsync;
import com.martinanalytics.testmodule.client.model.UserPublicDS;
import com.martinanalytics.testmodule.client.model.bean.SecurityUserBean;
import com.martinanalytics.testmodule.client.view.SecurityUserView;

import com.martinanalytics.testmodule.client.ServerExceptionHandler;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.ListGridRecord;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;



/**
 * SecurityUserController owns the security_user View and responds to all events genereated
 * by the View and it's subcomponents.
 * @author bmartin
 *
 */
public class SecurityUserController implements AppEventListener{
  private static SecurityUserController instance = null; //Singleton Instance
  private SecurityUserView securityUserView; 	//The UI container
  private final SecurityUserServiceAsync securityUserService = 
	GWT.create(SecurityUserService.class); 		// primary GWT remote Service
  private UserProfile userProfile;  			// User Properties
  private MasterController masterController;		// Overarching Controller
  private AppNotifyObject notifier = new AppNotifyObject();
  private SecurityUserDS securityUserDS; 
  private UserPublicDS userPublicDS;
  private java.util.Date sessionExpireDtHolder;

  private java.util.Date lastUpdateHolder;



/**
 * Primary constructor, only called by getInstance, hence protected
 * @param masterController_
 */
  protected  SecurityUserController(MasterController masterController_){
	masterController =masterController_;
	securityUserDS =SecurityUserDS.getInstance();
	securityUserDS.getNotifier().addAppEventListener(masterController);
	securityUserDS.setCacheMaxAge(100000);
	securityUserDS.setCachePreferred(true);
	//securityUserDS.fetchAllRowsToCache();
	
	userPublicDS = new UserPublicDS(masterController);
	userPublicDS.setCacheMaxAge(100000);
	userPublicDS.setCachePreferred(true);
	securityUserView = new SecurityUserView(securityUserDS, userPublicDS);	
	securityUserView.getNotifier().addAppEventListener(this);
	//SecurityUserView.getSecurityUserTable().getNotifier().addAppEventListener(this);
	userProfile = UserProfile.getInstance();
  }

/**
 * Singleton getInstance
 * @param masterController_ the overarching controller
 * @return a Login Conroller reference
 */
  public static SecurityUserController getInstance(MasterController masterController_){
	if(instance == null){
		instance = new SecurityUserController(masterController_);
	}
	return instance;
  } 

/**
 * Used to get access to the the primary UI and subcomponents (e.g. fields and tables)
 * @return the primary UI
 */
  public SecurityUserView getSecurityUserView() {
	return securityUserView;
  }

/**
 * Handles custom event system events, driven by the event's name.  Type-casting of payloads
 * should happen here.
 */
@Override
  public void onAppEventNotify(AppEvent e_) {
	if (e_.getName().equals(AppMsg.ADD_USER_PUBLIC)){
		
		
		if(e_.getPayLoad()!=null){
			String newUserName = (String)e_.getPayLoad();
			SecurityUserBean securityUserBean = new SecurityUserBean();
			ListGridRecord securityUserRecord = new ListGridRecord();
			securityUserBean.setUserId(newUserName);
			securityUserBean.setSecurityProfileId(AppPref.DEFAULT_SECURITY_PROFILE_ID);
			
			
			SecurityUserDS.copyValues(securityUserBean, securityUserRecord);
			securityUserDS.addData(securityUserRecord);
			
			UserPublicBean userPublicBean = new UserPublicBean();
			ListGridRecord userPublicRecord = new ListGridRecord();
			userPublicBean.setLastName("User");
			userPublicBean.setFirstName("Newly Created");
			userPublicBean.setUserId(newUserName);
			UserPublicDS.copyValues(userPublicBean, userPublicRecord);
			securityUserView.getUserPublicListGrid().addData(userPublicRecord);
		}
	
	}else{
		Log.debug("Unexpected AppEvent named" +e_.getName() );
	}
	
	
  }


/**
 * @return the notifier
 */
public AppNotifyObject getNotifier() {
	return notifier;
}

public void showSecurityUserView(){
	securityUserDS.fetchAllRowsToCache();
	securityUserView.getUserPublicListGrid().fetchData();
	securityUserView.show();
	
}


}