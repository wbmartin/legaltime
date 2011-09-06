
package com.martinanalytics.testmodule.client.model;
import com.martinanalytics.testmodule.client.model.bean.SysCodeBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("sysCode")
public interface SysCodeService extends RemoteService{
	 SysCodeBean insertSysCodeBean(UserProfile userProfile_, SysCodeBean sysCodeBean_) throws GWTCustomException;
	 SysCodeBean updateSysCodeBean(UserProfile userProfile_, SysCodeBean sysCodeBean_) throws GWTCustomException;
//	 SysCodeBean saveSysCodeBean(UserProfile userProfile_, SysCodeBean sysCodeBean_) throws GWTCustomException;
	 Boolean deleteSysCodeBean(UserProfile userProfile_, SysCodeBean sysCodeBean_) throws GWTCustomException;
	 ArrayList<SysCodeBean> selectSysCode(UserProfile userProfile_, String whereClause_, String OrderByClause_, int rowLimit_, int startRow_) throws GWTCustomException;
//	 ArrayList<SysCodeBean> saveSysCodeBeanBatch(UserProfile userProfile_, ArrayList<SysCodeBean> sysCodeBeanList_) throws GWTCustomException;
//	 SysCodeBean getSysCodeByPrKey(UserProfile userProfile_ , Integer sysCodeId_ ) throws GWTCustomException;

}