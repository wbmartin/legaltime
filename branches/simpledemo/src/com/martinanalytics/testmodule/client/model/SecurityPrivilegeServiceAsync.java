
package com.martinanalytics.testmodule.client.model;
import com.martinanalytics.testmodule.client.model.bean.SecurityPrivilegeBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface SecurityPrivilegeServiceAsync{
	 void insertSecurityPrivilegeBean(UserProfile userProfile_, SecurityPrivilegeBean securityPrivilegeBean_, AsyncCallback<SecurityPrivilegeBean> callback);
	 void updateSecurityPrivilegeBean(UserProfile userProfile_, SecurityPrivilegeBean securityPrivilegeBean_, AsyncCallback<SecurityPrivilegeBean> callback);
//	 SecurityPrivilegeBean saveSecurityPrivilegeBean(UserProfile userProfile_, SecurityPrivilegeBean securityPrivilegeBean_) throws GWTCustomException;
	 void deleteSecurityPrivilegeBean(UserProfile userProfile_, SecurityPrivilegeBean securityPrivilegeBean_, AsyncCallback<Boolean> callback);
	 void selectSecurityPrivilege(UserProfile userProfile_, String whereClause_, String OrderByClause_, int rowLimit_, int startRow_, AsyncCallback<ArrayList<SecurityPrivilegeBean>> callback);
//	 ArrayList<SecurityPrivilegeBean> saveSecurityPrivilegeBeanBatch(UserProfile userProfile_, ArrayList<SecurityPrivilegeBean> securityPrivilegeBeanList_) throws GWTCustomException;
//	 SecurityPrivilegeBean getSecurityPrivilegeByPrKey(UserProfile userProfile_ , Integer securityPrivilegeId_ ) throws GWTCustomException;

}