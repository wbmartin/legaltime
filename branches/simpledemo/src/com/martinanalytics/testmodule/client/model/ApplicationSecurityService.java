package com.martinanalytics.testmodule.client.model;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.martinanalytics.testmodule.client.model.bean.SecurityUserBean;
import com.martinanalytics.testmodule.client.model.GWTCustomException;



@RemoteServiceRelativePath("applicationSecurity")
public interface ApplicationSecurityService extends RemoteService{
        SecurityUserBean authenticateUser(String userId_, String password_) throws GWTCustomException;
}

