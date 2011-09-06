
package com.martinanalytics.testmodule.client.model;
import com.martinanalytics.testmodule.client.model.bean.SysCodeBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface SysCodeServiceAsync{
	 void insertSysCodeBean(UserProfile userProfile_, SysCodeBean sysCodeBean_, AsyncCallback<SysCodeBean> callback);
	 void updateSysCodeBean(UserProfile userProfile_, SysCodeBean sysCodeBean_, AsyncCallback<SysCodeBean> callback);
//	 void saveSysCodeBean(UserProfile userProfile_, SysCodeBean sysCodeBean_, AsyncCallback<SysCodeBean> callback);
	 void deleteSysCodeBean(UserProfile userProfile_, SysCodeBean sysCodeBean_, AsyncCallback<Boolean> callback);
	 void selectSysCode(UserProfile userProfile_, String whereClause_, String OrderByClause_, int rowLimit_, int startRow_, AsyncCallback<ArrayList<SysCodeBean>> callback);
//	 void saveSysCodeBeanBatch(UserProfile userProfile_, ArrayList<SysCodeBean> sysCodeBeanList_, AsyncCallback<ArrayList<SysCodeBean>> callback);
//	 void getSysCodeByPrKey(UserProfile userProfile_ , Integer sysCodeId_, AsyncCallback<SysCodeBean> callback );

}