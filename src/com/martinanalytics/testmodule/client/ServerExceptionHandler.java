package com.martinanalytics.testmodule.client;



import com.allen_sauer.gwt.log.client.Log;
import com.martinanalytics.testmodule.client.app.AppMsg;
import com.martinanalytics.testmodule.client.app.AppPref;
import com.martinanalytics.testmodule.client.controller.MasterController;


public class ServerExceptionHandler {
  static ServerExceptionHandler instance= null;
  private MasterController masterController;
  protected ServerExceptionHandler(){
          
          
  }
  public static ServerExceptionHandler getInstance(){
          if(instance==null){
                  instance = new ServerExceptionHandler();
          }
          return instance;
  }
  
  public boolean handle(Throwable e){
          boolean handled = false;
          Log.debug("Server Exception Handler Recieved'" + e.getMessage() +"'.");
          if (e.getMessage().equals(AppMsg.SERVER_TIMEOUT_ERROR)){
                        masterController.promptUserForLogin();
                        handled = true;
          }else if(e.getMessage().equals("Authentication Failure")){    
                  masterController.notifyUserOfSystemError("Sorry...","I couldn't validate your credentials.  Please try again.");
                }else{
                        handled = true;
                        StackTraceElement[] exceptionList = e.getStackTrace();
                        StringBuffer result =new StringBuffer();
                        for(int ndx =0; ndx< exceptionList.length; ndx++){
                                result.append("<br>Line " +exceptionList[ndx].getLineNumber() + "|" + exceptionList[ndx].getClassName() + "--" +exceptionList[ndx].getMethodName() +"<br>"  );
                        }
                        masterController.notifyUserOfSystemError("Remote Procedure Call - Failure", 
                                        AppPref.SERVER_ERROR + result.toString());
                }
          return handled;
  }
/**
 * @param masterController the masterController to set
 */
public void setMasterController(MasterController masterController_) {
        this.masterController = masterController_;
}

}

