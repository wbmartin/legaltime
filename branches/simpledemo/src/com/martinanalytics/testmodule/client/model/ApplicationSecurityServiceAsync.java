package com.martinanalytics.testmodule.client.model;

import com.martinanalytics.testmodule.client.model.bean.SecurityUserBean;
import com.google.gwt.user.client.rpc.AsyncCallback;



public interface ApplicationSecurityServiceAsync{
        void authenticateUser(String userId_, String password_, AsyncCallback<SecurityUserBean> callback);
}

