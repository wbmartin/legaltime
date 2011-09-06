package com.martinanalytics.testmodule.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.martinanalytics.testmodule.client.app.AppEvent;
import com.martinanalytics.testmodule.client.app.AppEventListener;
import com.martinanalytics.testmodule.client.app.AppMsg;
import com.martinanalytics.testmodule.client.app.AppPages;
import com.martinanalytics.testmodule.client.app.AppSysCode;
import com.martinanalytics.testmodule.client.app.DatedMessage;
import com.martinanalytics.testmodule.client.app.IApplicationController;

//import com.martinanalytics.testmodule.client.widget.AppContainer;
//import com.martinanalytics.testmodule.client.widget.ReportUtil;
import com.martinanalytics.testmodule.client.ServerExceptionHandler;
//import com.martinanalytics.testmodule.client.model.UserInfoCache;
//import com.martinanalytics.testmodule.client.model.bean.EmailListBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;

import com.martinanalytics.testmodule.client.view.AppContainer;
import com.martinanalytics.testmodule.client.view.LoggerConsole;

//import com.martinanalytics.testmodule.client.view.ManageUsers;
//import com.extjs.gxt.ui.client.event.BaseEvent;
//import com.extjs.gxt.ui.client.event.Listener;
public class MasterController implements AppEventListener, IApplicationController{
         private Map<String, Composite> itemWidgets = new HashMap<String, Composite>();
         private LoginController loginController ;
         private UserProfile userProfile;
         private ArrayList<DatedMessage> sysLogMessages= new ArrayList<DatedMessage>();
         private AppContainer appContainer ;
         private LoggerConsole loggerConsole = new LoggerConsole();
         private SecurityProfileController securityProfileController;
         private SecurityUserController securityUserController;
         private UserPublicController userPublicController;
         private AppSysCode appSysCode ;
      	//ManageUsers manageUsers = new ManageUsers();
//         private EmailMsgController emailMsgController;
//         private EmailListController emailListController;
         
         public MasterController(){
                 ServerExceptionHandler.getInstance().setMasterController(this);
                 userProfile = UserProfile.getInstance();
                 loginController = LoginController.getInstance(this);
                 loginController.getNotifier().addAppEventListener(this);       

         }
         
         public Composite getPage(String page_){
                 String finalPage =page_;
                 return itemWidgets.get(finalPage);
                 
         }
         public void promptUserForLogin(){
//                if(!loginController.getLoginView().getLoginDialog().isVisible()){
//                 loginController.getLoginView().getLoginDialog().show();
//                }
         }
         public void secondaryLoginAccepted(){
        //         loginController.getLoginView().getLoginDialog().hide();
         }
        
         public void notifyUserOfSystemError(String title_, String msg_){
                 Log.debug("Calling Set Message");              
           //      appContainer.displayPopupMsg(title_, msg_);
                        
         }

        public void passValue(String sessionId_) {
                //page1Controller.passValue(sessionId_);
                
        }

        @Override
        public void onAppEventNotify(AppEvent e_) {
                Log.debug("Master Controller received AppEvent: " + e_.getName());
                if(e_.getName().equals(AppMsg.LOGOUT_COMMAND)){
                        userProfile.setSessionId("");
                        userProfile.setClientId(0);
                        userProfile.expireSession();
                        
                        //History.newItem(AppPages.LOGIN_PAGE);
                }else if(e_.getName().equals(AppMsg.SEND_LOGIN_INFO)){
                	//Log.debug("username mc:" + (String)e_.getPayLoad() );
                	//Log.debug("password mc:" + (String)e_.getPayLoad2() );
                        loginController.attemptAuthorization((String)e_.getPayLoad(),(String)e_.getPayLoad2());
                }else if(AppMsg.SUCCESSFUL_LOGIN.equals(e_.getName())){ 
                	postLoginInit();
                	
                }else if(AppMsg.SHOW_MANAGE_GROUPS.equals(e_.getName())){ 
                	securityProfileController = SecurityProfileController.getInstance(this);
                	securityProfileController.showManageGroupsView();
                }else if(AppMsg.SHOW_MANAGE_USERS.equals(e_.getName())){ 
                	securityUserController = SecurityUserController.getInstance(this);
                	//userPublicController =  UserPublicController.getInstance(this);
                	securityUserController.showSecurityUserView();                	
                }else if(AppMsg.SHOW_LOGGER_CONSOLE.equals(e_.getName())){ 
                	loggerConsole = new LoggerConsole();
                	loggerConsole.show();
                }else if(AppMsg.SET_MASTER_WINDOW_STATUS.equals(e_.getName())){
                	setTransactionResults((String)e_.getPayLoad());
                }else if(AppMsg.ALERT_USER_MSG.equals(e_.getName())){
                	//setTransactionResults((String)e_.getPayLoad());
                }else{
                        Log.debug("Warning: Unhandled App Message: " + e_.getName());
                }
                
        }
        private void postLoginInit(){
        	appSysCode = AppSysCode.getInstance();
                           
                            
            
            
            
        	loginController.getLoginView().hide();
        	appContainer = new AppContainer();
            appContainer.getNotifier().addAppEventListener(this);
        	appContainer.show();
//            emailMsgController =  EmailMsgController.getInstance(this);
//                   itemWidgets.put(AppPages.EMAIL_MSG_PAGE, emailMsgController.getEmailMsgView().getEmailMsgComposite());
//
//                   emailListController =  EmailListController.getInstance(this);
//                   itemWidgets.put(AppPages.EMAIL_LIST_PAGE, emailListController.getEmailListView().getEmailListComposite());
//                   emailListController.getNotifier().addAppEventListener(this);
//
//          customerController =  CustomerController.getInstance(this);
//          itemWidgets.put(AppPages.CUSTOMER_PAGE, customerController.getCustomerView().getCustomerComposite());
//          customerController.getNotifier().addAppEventListener(this);
        }


		public LoginController getLoginController(){
			return loginController;
		}
        

        /**
         * @return the masterFileBar2
         */
        public AppContainer getAppContainer() {
                return appContainer;
        }
        
        public void addSysLogMessage(String msg_){
        	
        	sysLogMessages.add(new DatedMessage(msg_)); 
        }

        public void setTransactionResults(String msg_){
    		appContainer.setTransactionResults(msg_);
    		sysLogMessages.add(new DatedMessage(msg_));
    	}
        
        public void clearNonUserCache(){
        	userPublicController.getUserPublicDS().invalidateCache();
        	
        }
        
                
}

