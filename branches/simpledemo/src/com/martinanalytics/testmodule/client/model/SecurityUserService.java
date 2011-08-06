
package com.martinanalytics.testmodule.client.model;
import com.martinanalytics.testmodule.client.model.bean.SecurityUserBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("securityUser")
public interface SecurityUserService extends RemoteService{
	 SecurityUserBean insertSecurityUserBean(UserProfile userProfile_, SecurityUserBean securityUserBean_) throws GWTCustomException;
	 SecurityUserBean updateSecurityUserBean(UserProfile userProfile_, SecurityUserBean securityUserBean_) throws GWTCustomException;
//	 SecurityUserBean saveSecurityUserBean(UserProfile userProfile_, SecurityUserBean securityUserBean_) throws GWTCustomException;
	 Boolean deleteSecurityUserBean(UserProfile userProfile_, SecurityUserBean securityUserBean_) throws GWTCustomException;
	 ArrayList<SecurityUserBean> selectSecurityUser(UserProfile userProfile_, String whereClause_, String OrderByClause_, int rowLimit_, int startRow_) throws GWTCustomException;
//	 ArrayList<SecurityUserBean> saveSecurityUserBeanBatch(UserProfile userProfile_, ArrayList<SecurityUserBean> securityUserBeanList_) throws GWTCustomException;
//	 SecurityUserBean getSecurityUserByPrKey(UserProfile userProfile_ , Integer securityUserId_ ) throws GWTCustomException;

}