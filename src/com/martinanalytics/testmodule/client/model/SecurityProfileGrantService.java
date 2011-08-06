
package com.martinanalytics.testmodule.client.model;
import com.martinanalytics.testmodule.client.model.bean.SecurityProfileGrantBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("securityProfileGrant")
public interface SecurityProfileGrantService extends RemoteService{
	 SecurityProfileGrantBean insertSecurityProfileGrantBean(UserProfile userProfile_, SecurityProfileGrantBean securityProfileGrantBean_) throws GWTCustomException;
	 SecurityProfileGrantBean updateSecurityProfileGrantBean(UserProfile userProfile_, SecurityProfileGrantBean securityProfileGrantBean_) throws GWTCustomException;
//	 SecurityProfileGrantBean saveSecurityProfileGrantBean(UserProfile userProfile_, SecurityProfileGrantBean securityProfileGrantBean_) throws GWTCustomException;
	 Boolean deleteSecurityProfileGrantBean(UserProfile userProfile_, SecurityProfileGrantBean securityProfileGrantBean_) throws GWTCustomException;
	 ArrayList<SecurityProfileGrantBean> selectSecurityProfileGrant(UserProfile userProfile_, String whereClause_, String OrderByClause_, int rowLimit_, int startRow_) throws GWTCustomException;
//	 ArrayList<SecurityProfileGrantBean> saveSecurityProfileGrantBeanBatch(UserProfile userProfile_, ArrayList<SecurityProfileGrantBean> securityProfileGrantBeanList_) throws GWTCustomException;
//	 SecurityProfileGrantBean getSecurityProfileGrantByPrKey(UserProfile userProfile_ , Integer securityProfileGrantId_ ) throws GWTCustomException;

}