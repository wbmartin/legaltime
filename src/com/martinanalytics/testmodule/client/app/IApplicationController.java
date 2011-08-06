package com.martinanalytics.testmodule.client.app;

public interface IApplicationController {
 public void setTransactionResults(String results_);
 public void addSysLogMessage(String msg_);
 public void notifyUserOfSystemError(String title_, String msg_);
 
}
