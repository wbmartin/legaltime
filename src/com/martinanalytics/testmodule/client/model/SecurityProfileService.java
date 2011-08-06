
package com.martinanalytics.testmodule.client.model;
import com.martinanalytics.testmodule.client.model.bean.SecurityProfileBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("securityProfile")
public interface SecurityProfileService extends RemoteService{
	 SecurityProfileBean insertSecurityProfileBean(UserProfile userProfile_, SecurityProfileBean securityProfileBean_) throws GWTCustomException;
	 SecurityProfileBean updateSecurityProfileBean(UserProfile userProfile_, SecurityProfileBean securityProfileBean_) throws GWTCustomException;
//	 SecurityProfileBean saveSecurityProfileBean(UserProfile userProfile_, SecurityProfileBean securityProfileBean_) throws GWTCustomException;
	 Boolean deleteSecurityProfileBean(UserProfile userProfile_, SecurityProfileBean securityProfileBean_) throws GWTCustomException;
	 ArrayList<SecurityProfileBean> selectSecurityProfile(UserProfile userProfile_, String whereClause_, String OrderByClause_, int rowLimit_, int startRow_) throws GWTCustomException;
//	 ArrayList<SecurityProfileBean> saveSecurityProfileBeanBatch(UserProfile userProfile_, ArrayList<SecurityProfileBean> securityProfileBeanList_) throws GWTCustomException;
	 SecurityProfileBean getSecurityProfileByPrKey(UserProfile userProfile_ , Integer securityProfileId_ ) throws GWTCustomException;

}