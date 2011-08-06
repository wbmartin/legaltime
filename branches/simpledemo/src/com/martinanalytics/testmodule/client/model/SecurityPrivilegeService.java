
package com.martinanalytics.testmodule.client.model;
import com.martinanalytics.testmodule.client.model.bean.SecurityPrivilegeBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("securityPrivilege")
public interface SecurityPrivilegeService extends RemoteService{
	 SecurityPrivilegeBean insertSecurityPrivilegeBean(UserProfile userProfile_, SecurityPrivilegeBean securityPrivilegeBean_) throws GWTCustomException;
	 SecurityPrivilegeBean updateSecurityPrivilegeBean(UserProfile userProfile_, SecurityPrivilegeBean securityPrivilegeBean_) throws GWTCustomException;
//	 SecurityPrivilegeBean saveSecurityPrivilegeBean(UserProfile userProfile_, SecurityPrivilegeBean securityPrivilegeBean_) throws GWTCustomException;
	 Boolean deleteSecurityPrivilegeBean(UserProfile userProfile_, SecurityPrivilegeBean securityPrivilegeBean_) throws GWTCustomException;
	 ArrayList<SecurityPrivilegeBean> selectSecurityPrivilege(UserProfile userProfile_, String whereClause_, String OrderByClause_, int rowLimit_, int startRow_) throws GWTCustomException;
//	 ArrayList<SecurityPrivilegeBean> saveSecurityPrivilegeBeanBatch(UserProfile userProfile_, ArrayList<SecurityPrivilegeBean> securityPrivilegeBeanList_) throws GWTCustomException;
//	 SecurityPrivilegeBean getSecurityPrivilegeByPrKey(UserProfile userProfile_ , Integer securityPrivilegeId_ ) throws GWTCustomException;

}