
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
import com.martinanalytics.testmodule.client.model.UserPublicDS;
import com.martinanalytics.testmodule.client.model.UserPublicService;
import com.martinanalytics.testmodule.client.model.UserPublicServiceAsync;
import com.martinanalytics.testmodule.client.model.bean.UserPublicBean;

import com.martinanalytics.testmodule.client.ServerExceptionHandler;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;



/**
 * UserPublicController owns the user_public View and responds to all events genereated
 * by the View and it's subcomponents.
 * @author bmartin
 *
 */
public class UserPublicController implements AppEventListener{
  private static UserPublicController instance = null; //Singleton Instance
 // private UserPublicView userPublicView; 	//The UI container
  private final UserPublicServiceAsync userPublicService = 
	GWT.create(UserPublicService.class); 		// primary GWT remote Service
  private UserProfile userProfile;  			// User Properties
  private MasterController masterController;		// Overarching Controller
  private AppNotifyObject notifier = new AppNotifyObject();
  private UserPublicDS userPublicDS; 
  private java.util.Date lastUpdateHolder;



/**
 * Primary constructor, only called by getInstance, hence protected
 * @param masterController_
 */
  protected  UserPublicController(MasterController masterController_){
	masterController =masterController_;
	userPublicDS =new UserPublicDS(masterController);
	//userPublicView = new UserPublicView(userPublicDS);	
	//userPublicView.getNotifier().addAppEventListener(this);
	//UserPublicView.getUserPublicTable().getNotifier().addAppEventListener(this);
	userProfile = UserProfile.getInstance();
  }

/**
 * Singleton getInstance
 * @param masterController_ the overarching controller
 * @return a Login Conroller reference
 */
  public static UserPublicController getInstance(MasterController masterController_){
	if(instance == null){
		instance = new UserPublicController(masterController_);
	}
	return instance;
  } 

/**
 * Used to get access to the the primary UI and subcomponents (e.g. fields and tables)
 * @return the primary UI
 */
//  public UserPublicView getUserPublicView() {
//	return userPublicView;
//  }

/**
 * Handles custom event system events, driven by the event's name.  Type-casting of payloads
 * should happen here.
 */
@Override
  public void onAppEventNotify(AppEvent e_) {
    if (e_.getName().equals(AppMsg.ADD_USER_PUBLIC)){
    	
		
	}else if(e_.getName().equals(AppMsg.DELETE_USER_PUBLIC)){
	//}else if(e_.getName().equals(AppMsg.UPDATE_USER_PUBLIC)){	
	
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


/**
 * @return the userPublicDS
 */
public UserPublicDS getUserPublicDS() {
	return userPublicDS;
}




}