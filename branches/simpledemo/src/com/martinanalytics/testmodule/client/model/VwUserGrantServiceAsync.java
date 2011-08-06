
package com.martinanalytics.testmodule.client.model;
import com.martinanalytics.testmodule.client.model.bean.VwUserGrantBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface VwUserGrantServiceAsync{
//	 VwUserGrantBean insertVwUserGrantBean(UserProfile userProfile_, VwUserGrantBean vwUserGrantBean_) throws GWTCustomException;
//	 VwUserGrantBean updateVwUserGrantBean(UserProfile userProfile_, VwUserGrantBean vwUserGrantBean_) throws GWTCustomException;
//	 VwUserGrantBean saveVwUserGrantBean(UserProfile userProfile_, VwUserGrantBean vwUserGrantBean_) throws GWTCustomException;
//	 Boolean deleteVwUserGrantBean(UserProfile userProfile_, VwUserGrantBean vwUserGrantBean_) throws GWTCustomException;
	 void selectVwUserGrant(UserProfile userProfile_, String whereClause_, String OrderByClause_, int rowLimit_, int startRow_, AsyncCallback<ArrayList<VwUserGrantBean>> callback);
//	 ArrayList<VwUserGrantBean> saveVwUserGrantBeanBatch(UserProfile userProfile_, ArrayList<VwUserGrantBean> vwUserGrantBeanList_) throws GWTCustomException;
//	 VwUserGrantBean getVwUserGrantByPrKey(UserProfile userProfile_ , Integer vwUserGrantId_ ) throws GWTCustomException;

}