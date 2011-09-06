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
	public final static String TYPE_SECURITY_PROFILE ="SCPRF";
	
	private static LinkedHashMap<String, String> securityProfileLookupCache;
	private static AppSysCode instance = null;
	private final SysCodeServiceAsync sysCodeService = GWT.create(SysCodeService.class);
	
	public static AppSysCode getInstance(){
		if( instance == null){	instance = new AppSysCode();}
		return instance;
	}
	
	private AppSysCode(){
		securityProfileLookupCache = new LinkedHashMap<String, String>();

		
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
				  Log.debug("fetchAllRowsToCache succeeded for SysCode");
				 
				  securityProfileLookupCache.clear();
				  for (int i = 0; i < sysCodeResult.size(); i++) {
					  if(TYPE_SECURITY_PROFILE.equals(sysCodeResult.get(i).getCodeType())){
						  securityProfileLookupCache.put(	sysCodeResult.get(i).getKey(),
								  							sysCodeResult.get(i).getValue());
					  }else if(TYPE_STATE.equals(sysCodeResult.get(i).getCodeType())){
						  
					  }

					  
				        Log.debug("SYSCODE:" +sysCodeResult.get(i).getKey() + " " + sysCodeResult.get(i).getValue() );
				   }

				}
		});
	
	}

	
	/**
	 * @return the securityProfileLookupCache
	 */
	public static LinkedHashMap getSecurityProfileLookupCache() {
		return securityProfileLookupCache;
	}
	
	

}
