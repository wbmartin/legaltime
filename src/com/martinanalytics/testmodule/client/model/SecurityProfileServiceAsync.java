
package com.martinanalytics.testmodule.client.model;
import com.martinanalytics.testmodule.client.model.bean.SecurityProfileBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface SecurityProfileServiceAsync{
	 void insertSecurityProfileBean(UserProfile userProfile_, SecurityProfileBean securityProfileBean_, AsyncCallback<SecurityProfileBean> callback);
	 void updateSecurityProfileBean(UserProfile userProfile_, SecurityProfileBean securityProfileBean_, AsyncCallback<SecurityProfileBean> callback);
//	 SecurityProfileBean saveSecurityProfileBean(UserProfile userProfile_, SecurityProfileBean securityProfileBean_) throws GWTCustomException;
	 void deleteSecurityProfileBean(UserProfile userProfile_, SecurityProfileBean securityProfileBean_, AsyncCallback<Boolean> callback);
	 void selectSecurityProfile(UserProfile userProfile_, String whereClause_, String OrderByClause_, int rowLimit_, int startRow_, AsyncCallback<ArrayList<SecurityProfileBean>> callback);
//	 ArrayList<SecurityProfileBean> saveSecurityProfileBeanBatch(UserProfile userProfile_, ArrayList<SecurityProfileBean> securityProfileBeanList_) throws GWTCustomException;
	 void getSecurityProfileByPrKey(UserProfile userProfile_ , Integer securityProfileId_, AsyncCallback<SecurityProfileBean> callback );

}