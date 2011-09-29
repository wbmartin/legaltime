package com.martinanalytics.testmodule.client.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;



import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.martinanalytics.testmodule.client.ServerExceptionHandler;
import com.martinanalytics.testmodule.client.model.SecurityProfileDS;
import com.martinanalytics.testmodule.client.model.SysCodeService;
import com.martinanalytics.testmodule.client.model.SysCodeServiceAsync;
import com.martinanalytics.testmodule.client.model.bean.SysCodeBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class AppSysCode implements AppEventListener{
	
	public final static String TYPE_STATE ="STATE";
	private static LinkedHashMap<String, String> stateLookupCache;
	
	public final static String TYPE_SECURITY_PROFILE ="SECPROFILE";
	private static LinkedHashMap<String, String> securityProfileLookupCache;
	
	public enum SysCodeType{
		SECPROFILE, STATE;
	}
	
	
	
	private static AppSysCode instance = null;
	private final SysCodeServiceAsync sysCodeService = GWT.create(SysCodeService.class);
	
	public static AppSysCode getInstance(){
		if( instance == null){	instance = new AppSysCode();}
		return instance;
	}
	
	private AppSysCode(){
		securityProfileLookupCache = new LinkedHashMap<String, String>();
		stateLookupCache= new LinkedHashMap<String, String>();

		
		fetchAllRowsToCache();
	}
	
	@Override
	public void onAppEventNotify(AppEvent e) {
		Log.debug("Source Received" + e.getSource());
		
		
	}
	
	
	public void fetchAllRowsToCache () {
		
		   Log.debug("RPC to fetch for Cache Called - SysCode");
		   final String whereClause ="";
		   final String orderByClause ="";
		   Integer rowLimit =-1;
		   Integer startRow=1;
		   final java.util.Date startTime = new java.util.Date();
		   sysCodeService.selectSysCode(UserProfile.getInstance(), whereClause, orderByClause, rowLimit, startRow,
			new AsyncCallback<ArrayList<SysCodeBean>>(){
				public void onFailure(Throwable caught) {
					Log.debug("fetchAllRowsToCache failed for SysCode");
					if(!ServerExceptionHandler.getInstance().handle(caught)){

					}
				}
		
				public void onSuccess(ArrayList<SysCodeBean> sysCodeResult) {
				SysCodeType sysCodeType;
				  Log.debug("fetchAllRowsToCache succeeded for SysCode");
				  String key,value, type;			 
				  clearCache();
				  for (int i = 0; i < sysCodeResult.size(); i++) {
					  type = sysCodeResult.get(i).getCodeType();
					  key = sysCodeResult.get(i).getKey();
					  value=sysCodeResult.get(i).getValue();
					  sysCodeType = SysCodeType.valueOf(type);
					  switch(sysCodeType){
					    case STATE:			stateLookupCache.put(key,value); break;
					    case SECPROFILE: 	securityProfileLookupCache.put(key,value);break;
					    default: Log.debug("Unexpected SYSCODE TYPE" + sysCodeType);
					  }
//					  if(TYPE_SECURITY_PROFILE.equals(type)){
//						  securityProfileLookupCache.put(key,value);
//					  }else if(TYPE_STATE.equals(sysCodeResult.get(i).getCodeType())){
//						  stateLookupCache.put(key,value);
//					  }
					  
					  

					  
				        Log.debug("SYSCODE:" +sysCodeResult.get(i).getKey() + " " + sysCodeResult.get(i).getValue() );
				   }

				}
		});
	
	}
	
	public void clearCache(){
		securityProfileLookupCache.clear();
		stateLookupCache.clear();
	}

	
	/**
	 * @return the securityProfileLookupCache
	 */
	public static LinkedHashMap getSecurityProfileLookupCache() {
		return securityProfileLookupCache;
	}
	
	

}
