
package com.martinanalytics.testmodule.client.model;
import com.martinanalytics.testmodule.client.model.bean.SecurityUserBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface SecurityUserServiceAsync{
	 void insertSecurityUserBean(UserProfile userProfile_, SecurityUserBean securityUserBean_, AsyncCallback<SecurityUserBean> callback);
	 void updateSecurityUserBean(UserProfile userProfile_, SecurityUserBean securityUserBean_, AsyncCallback<SecurityUserBean> callback);
//	 void saveSecurityUserBean(UserProfile userProfile_, SecurityUserBean securityUserBean_, AsyncCallback<SecurityUserBean> callback);
	 void deleteSecurityUserBean(UserProfile userProfile_, SecurityUserBean securityUserBean_, AsyncCallback<Boolean> callback);
	 void selectSecurityUser(UserProfile userProfile_, String whereClause_, String OrderByClause_, int rowLimit_, int startRow_, AsyncCallback<ArrayList<SecurityUserBean>> callback);
//	 void saveSecurityUserBeanBatch(UserProfile userProfile_, ArrayList<SecurityUserBean> securityUserBeanList_, AsyncCallback<ArrayList<SecurityUserBean>> callback);
//	 void getSecurityUserByPrKey(UserProfile userProfile_ , Integer securityUserId_, AsyncCallback<SecurityUserBean> callback );

}