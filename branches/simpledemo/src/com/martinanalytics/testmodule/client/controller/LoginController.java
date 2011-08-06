package com.martinanalytics.testmodule.client.controller;


import com.allen_sauer.gwt.log.client.Log;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.martinanalytics.testmodule.client.app.AppMsg;
import com.martinanalytics.testmodule.client.app.AppPages;
import com.martinanalytics.testmodule.client.app.AppEvent;
import com.martinanalytics.testmodule.client.app.AppEventListener;
import com.martinanalytics.testmodule.client.app.AppNotifyObject;
import com.martinanalytics.testmodule.client.app.AppPref;
import com.martinanalytics.testmodule.client.ServerExceptionHandler;
import com.martinanalytics.testmodule.client.model.ApplicationSecurityService;
import com.martinanalytics.testmodule.client.model.ApplicationSecurityServiceAsync;
import com.martinanalytics.testmodule.client.model.bean.SecurityUserBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import com.martinanalytics.testmodule.client.view.LoginView;


/**
 * Login Controller owns the Login view and responds to login events upon a 
 * successful call to applicationSecurityService.authenticateUser the controller
 * will forward the browser to the first page defined in App Pages
 * @author bmartin
 *
 */
public class LoginController implements   AppEventListener{
        private LoginView loginView; //contains the View for entering login credentials
        private static LoginController instance = null; // Singleton instance
        private final ApplicationSecurityServiceAsync applicationSecurityService = 
                GWT.create(ApplicationSecurityService.class); // Remote GWT Service
        private UserProfile userProfile; //User properties, not a direct DB bean
        private MasterController masterController; //connects all the controllers
        private AppNotifyObject notifier;
        
        /**
         * Singleton getInstance
         * @param masterController_ the overarching controller
         * @return a Login Conroller reference
         */
        public static LoginController getInstance(MasterController masterController_){
                if (instance == null){
                        instance = new LoginController(masterController_);
                }
                return instance;
        }
        /**
         * Primary constructor, only called by getInstance, hence protected
         * @param masterController_
         */
        protected LoginController(MasterController masterController_){
                masterController = masterController_;
                loginView = new LoginView();
                
                getLoginView().getNotifier().addAppEventListener(this);
                //loginView.getTxtUserId().addKeyUpHandler(this);
                getLoginView().getAppEventProducer().addAppEventListener(this);
                userProfile = UserProfile.getInstance();
                notifier = new AppNotifyObject();
        }
        /**
         * Get Method for LoginView, primarily used to get members
         * @return
         */
        public  LoginView getLoginView(){
                return loginView;
        }

        

        /**
         * Retrieves login credentials from LoginView and verifies access to the system
         * Even if this is hacked, the user will have to have a valid session and a
         * valid username for each database call.  The web browser security exposes to many
         * gaps to completely rely on this setting.
         */
        public void attemptAuthorization(String userId, String passwd){
                //TODO handle different subsequent Logins
                final java.util.Date startTime = new java.util.Date();
                DOM.getElementById("loginMsg").setInnerHTML("");
        
                

                applicationSecurityService.authenticateUser( userId, passwd,
                                new AsyncCallback<SecurityUserBean>(){
                                        public void onFailure(Throwable caught) {
                                                Log.debug("authenticateUser Failed: " + caught);
                                                clearLoginFormPanel();
                                                if(!ServerExceptionHandler.getInstance().handle(caught)){

                                                }
                                                DOM.getElementById("loginMsg").setInnerHTML("<p>Sorry, those credentials don't match our records.</p>");
                                                
                                           
                                        }
                
                                        public void onSuccess(SecurityUserBean result) {
                                                Log.debug("AttemptAuthorization.onSuccess received: " + result);
                                                
                                                if (result.getClientId() !=0 && result.getSessionId() !=null){
                                                        userProfile.setUserId(result.getUserId());
                                                        userProfile.incrementSessionTimeOut();
                                                        userProfile.setSessionId(result.getSessionId());
                                                        userProfile.setClientId(result.getClientId());
                                                        Log.debug("Session Id" +result.getSessionId());
                                                        DOM.getElementById("SplashScreenId").setPropertyString("style", "display:none");
                                                        notifier.notifyAppEvent(this, AppMsg.SUCCESSFUL_LOGIN);
                                                        clearLoginFormPanel();  
                                                        
                                                                
                                                }else{
                                                        masterController.notifyUserOfSystemError("Sorry...","I couldn't validate your credentials.  Please try again.");
//                                                        if(type.equals("INITIAL")){
//                                                                loginView.getInitialLoginFormPanel().getSendButton().setEnabled(true);
//                                                        }
//                                                        masterController.getAppContainer().setTransactionResults(
//                                                                        "Login Denied"
//                                                                        , (new java.util.Date().getTime() - startTime.getTime()));
                                                }
                                        }
                });
        }
        
        public void clearLoginFormPanel(){
                getLoginView().getTxtPassword().setValue("");
                getLoginView().getTxtUserId().setValue("");
        }
        /**
         * Handles custom event system events,  all type casting of payloads should happen here.
         */
        @Override
        public void onAppEventNotify(AppEvent e_) {
                Log.debug("LoginController Recieved " + e_.getName());
//                if(e_.getName().equals(AppMsg.SEND_LOGIN_INFO)){
//                        attemptAuthorization();
//                }
        
        }
        /**
         * @param notifier the notifier to set
         */
        public void setNotifier(AppNotifyObject notifier) {
                this.notifier = notifier;
        }
        /**
         * @return the notifier
         */
        public AppNotifyObject getNotifier() {
                return notifier;
        }
		
        

}
