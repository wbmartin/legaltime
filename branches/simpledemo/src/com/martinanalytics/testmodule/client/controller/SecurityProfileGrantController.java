
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
import com.martinanalytics.testmodule.client.model.SecurityProfileGrantDS;
import com.martinanalytics.testmodule.client.model.SecurityProfileGrantService;
import com.martinanalytics.testmodule.client.model.SecurityProfileGrantServiceAsync;
import com.martinanalytics.testmodule.client.model.bean.SecurityProfileGrantBean;
//import com.martinanalytics.testmodule.client.view.SecurityProfileGrantView;

import com.martinanalytics.testmodule.client.ServerExceptionHandler;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;



/**
 * SecurityProfileGrantController owns the security_profile_grant View and responds to all events genereated
 * by the View and it's subcomponents.
 * @author bmartin
 *
 */
public class SecurityProfileGrantController implements AppEventListener{
  private static SecurityProfileGrantController instance = null; //Singleton Instance
//  private SecurityProfileGrantView securityProfileGrantView; 	//The UI container
  private final SecurityProfileGrantServiceAsync securityProfileGrantService = 
	GWT.create(SecurityProfileGrantService.class); 		// primary GWT remote Service
  private UserProfile userProfile;  			// User Properties
  private MasterController masterController;		// Overarching Controller
  private AppNotifyObject notifier = new AppNotifyObject();
  private SecurityProfileGrantDS securityProfileGrantDS; 
  private java.util.Date lastUpdateHolder;



/**
 * Primary constructor, only called by getInstance, hence protected
 * @param masterController_
 */
  protected  SecurityProfileGrantController(MasterController masterController_){
	masterController =masterController_;
	//securityProfileGrantDS =SecurityProfileGrantDS.getInstance(masterController);
	//securityProfileGrantView = new SecurityProfileGrantView(securityProfileGrantDS);	
	//securityProfileGrantView.getNotifier().addAppEventListener(this);
	//SecurityProfileGrantView.getSecurityProfileGrantTable().getNotifier().addAppEventListener(this);
	userProfile = UserProfile.getInstance();
  }

/**
 * Singleton getInstance
 * @param masterController_ the overarching controller
 * @return a Login Conroller reference
 */
  public static SecurityProfileGrantController getInstance(MasterController masterController_){
	if(instance == null){
		instance = new SecurityProfileGrantController(masterController_);
	}
	return instance;
  } 

///**
// * Used to get access to the the primary UI and subcomponents (e.g. fields and tables)
// * @return the primary UI
// */
//  public SecurityProfileGrantView getSecurityProfileGrantView() {
//	return securityProfileGrantView;
//  }

/**
 * Handles custom event system events, driven by the event's name.  Type-casting of payloads
 * should happen here.
 */
@Override
  public void onAppEventNotify(AppEvent e_) {
         if (e_.getName().equals("SecurityProfileGrantViewOnAttach")){
		
	}else if(e_.getName().equals("SecurityProfileGrantViewOnDetach")){
	}else if(e_.getName().equals("SecurityProfileGrantTableOnAttach")){		
	}else if(e_.getName().equals("SecurityProfileGrantTableOnDetach")){		
	
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


}