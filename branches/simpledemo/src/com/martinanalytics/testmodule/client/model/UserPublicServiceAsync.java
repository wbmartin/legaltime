
package com.martinanalytics.testmodule.client.model;
import com.martinanalytics.testmodule.client.model.bean.UserPublicBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface UserPublicServiceAsync{
	 void insertUserPublicBean(UserProfile userProfile_, UserPublicBean userPublicBean_, AsyncCallback<UserPublicBean> callback);
	 void updateUserPublicBean(UserProfile userProfile_, UserPublicBean userPublicBean_, AsyncCallback<UserPublicBean> callback);
//	 void saveUserPublicBean(UserProfile userProfile_, UserPublicBean userPublicBean_, AsyncCallback<UserPublicBean> callback);
	 void deleteUserPublicBean(UserProfile userProfile_, UserPublicBean userPublicBean_, AsyncCallback<Boolean> callback);
	 void selectUserPublic(UserProfile userProfile_, String whereClause_, String OrderByClause_, int rowLimit_, int startRow_, AsyncCallback<ArrayList<UserPublicBean>> callback);
//	 void saveUserPublicBeanBatch(UserProfile userProfile_, ArrayList<UserPublicBean> userPublicBeanList_, AsyncCallback<ArrayList<UserPublicBean>> callback);
//	 void getUserPublicByPrKey(UserProfile userProfile_ , Integer userPublicId_, AsyncCallback<UserPublicBean> callback );

}