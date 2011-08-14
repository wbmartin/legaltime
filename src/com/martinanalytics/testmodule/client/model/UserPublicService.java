
package com.martinanalytics.testmodule.client.model;
import com.martinanalytics.testmodule.client.model.bean.UserPublicBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("userPublic")
public interface UserPublicService extends RemoteService{
	 UserPublicBean insertUserPublicBean(UserProfile userProfile_, UserPublicBean userPublicBean_) throws GWTCustomException;
	 UserPublicBean updateUserPublicBean(UserProfile userProfile_, UserPublicBean userPublicBean_) throws GWTCustomException;
//	 UserPublicBean saveUserPublicBean(UserProfile userProfile_, UserPublicBean userPublicBean_) throws GWTCustomException;
	 Boolean deleteUserPublicBean(UserProfile userProfile_, UserPublicBean userPublicBean_) throws GWTCustomException;
	 ArrayList<UserPublicBean> selectUserPublic(UserProfile userProfile_, String whereClause_, String OrderByClause_, int rowLimit_, int startRow_) throws GWTCustomException;
//	 ArrayList<UserPublicBean> saveUserPublicBeanBatch(UserProfile userProfile_, ArrayList<UserPublicBean> userPublicBeanList_) throws GWTCustomException;
//	 UserPublicBean getUserPublicByPrKey(UserProfile userProfile_ , Integer userPublicId_ ) throws GWTCustomException;

}