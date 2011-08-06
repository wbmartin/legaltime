
package com.martinanalytics.testmodule.client.model;
import com.martinanalytics.testmodule.client.model.bean.VwProfileGrantBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("vwProfileGrant")
public interface VwProfileGrantService extends RemoteService{
	 VwProfileGrantBean insertVwProfileGrantBean(UserProfile userProfile_, VwProfileGrantBean vwProfileGrantBean_) throws GWTCustomException;
	 VwProfileGrantBean updateVwProfileGrantBean(UserProfile userProfile_, VwProfileGrantBean vwProfileGrantBean_) throws GWTCustomException;
//	 VwProfileGrantBean saveVwProfileGrantBean(UserProfile userProfile_, VwProfileGrantBean vwProfileGrantBean_) throws GWTCustomException;
	 Boolean deleteVwProfileGrantBean(UserProfile userProfile_, VwProfileGrantBean vwProfileGrantBean_) throws GWTCustomException;
	 ArrayList<VwProfileGrantBean> selectVwProfileGrant(UserProfile userProfile_, String whereClause_, String OrderByClause_, int rowLimit_, int startRow_) throws GWTCustomException;
//	 ArrayList<VwProfileGrantBean> saveVwProfileGrantBeanBatch(UserProfile userProfile_, ArrayList<VwProfileGrantBean> vwProfileGrantBeanList_) throws GWTCustomException;
//	 VwProfileGrantBean getVwProfileGrantByPrKey(UserProfile userProfile_ , Integer vwProfileGrantId_ ) throws GWTCustomException;

}