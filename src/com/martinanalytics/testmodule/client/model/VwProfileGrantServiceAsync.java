
package com.martinanalytics.testmodule.client.model;
import com.martinanalytics.testmodule.client.model.bean.VwProfileGrantBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface VwProfileGrantServiceAsync{
	 void insertVwProfileGrantBean(UserProfile userProfile_, VwProfileGrantBean vwProfileGrantBean_, AsyncCallback<VwProfileGrantBean> callback);
	 void updateVwProfileGrantBean(UserProfile userProfile_, VwProfileGrantBean vwProfileGrantBean_, AsyncCallback<VwProfileGrantBean> callback);
//	 VwProfileGrantBean saveVwProfileGrantBean(UserProfile userProfile_, VwProfileGrantBean vwProfileGrantBean_) throws GWTCustomException;
	 void deleteVwProfileGrantBean(UserProfile userProfile_, VwProfileGrantBean vwProfileGrantBean_, AsyncCallback<Boolean> callback);
	 void selectVwProfileGrant(UserProfile userProfile_, String whereClause_, String OrderByClause_, int rowLimit_, int startRow_, AsyncCallback<ArrayList<VwProfileGrantBean>> callback);
//	 ArrayList<VwProfileGrantBean> saveVwProfileGrantBeanBatch(UserProfile userProfile_, ArrayList<VwProfileGrantBean> vwProfileGrantBeanList_) throws GWTCustomException;
//	 VwProfileGrantBean getVwProfileGrantByPrKey(UserProfile userProfile_ , Integer vwProfileGrantId_ ) throws GWTCustomException;

}