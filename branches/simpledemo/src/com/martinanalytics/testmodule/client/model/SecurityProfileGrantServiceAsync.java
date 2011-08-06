
package com.martinanalytics.testmodule.client.model;
import com.martinanalytics.testmodule.client.model.bean.SecurityProfileGrantBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface SecurityProfileGrantServiceAsync{
	 void insertSecurityProfileGrantBean(UserProfile userProfile_, SecurityProfileGrantBean securityProfileGrantBean_, AsyncCallback<SecurityProfileGrantBean> callback);
	 void updateSecurityProfileGrantBean(UserProfile userProfile_, SecurityProfileGrantBean securityProfileGrantBean_, AsyncCallback<SecurityProfileGrantBean> callback);
//	 SecurityProfileGrantBean saveSecurityProfileGrantBean(UserProfile userProfile_, SecurityProfileGrantBean securityProfileGrantBean_) throws GWTCustomException;
	 void deleteSecurityProfileGrantBean(UserProfile userProfile_, SecurityProfileGrantBean securityProfileGrantBean_, AsyncCallback<Boolean> callback);
	 void selectSecurityProfileGrant(UserProfile userProfile_, String whereClause_, String OrderByClause_, int rowLimit_, int startRow_, AsyncCallback<ArrayList<SecurityProfileGrantBean>> callback);
//	 ArrayList<SecurityProfileGrantBean> saveSecurityProfileGrantBeanBatch(UserProfile userProfile_, ArrayList<SecurityProfileGrantBean> securityProfileGrantBeanList_) throws GWTCustomException;
//	 SecurityProfileGrantBean getSecurityProfileGrantByPrKey(UserProfile userProfile_ , Integer securityProfileGrantId_ ) throws GWTCustomException;

}