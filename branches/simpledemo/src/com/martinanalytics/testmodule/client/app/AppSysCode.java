package com.martinanalytics.testmodule.client.app;

import java.util.HashMap;



import com.allen_sauer.gwt.log.client.Log;
import com.martinanalytics.testmodule.client.model.SecurityProfileDS;
import com.smartgwt.client.data.Record;

public class AppSysCode implements AppEventListener{
	
	private static HashMap securityProfileLookupCache;
	private SecurityProfileDS securityProfileDS;
	private static AppSysCode instance = null;
	
	public static AppSysCode getInstance(){
		if( instance == null){	instance = new AppSysCode();}
		return instance;
	}
	
	private AppSysCode(){
		securityProfileLookupCache = new HashMap();
		securityProfileDS = SecurityProfileDS.getInstance();
		securityProfileDS.getNotifier().addAppEventListener(this);
		
		refreshAllCacheValues();	
	}
	public void refreshAllCacheValues(){
		securityProfileDS.fetchAllRowsToCache();
	}
	@Override
	public void onAppEventNotify(AppEvent e) {
		Log.debug("Source Received" + e.getSource());
		if(AppMsg.EVT_CACHE_UPDATED.equals(e.getName())){
			Log.debug("Source Received" + e.getSource());
			
		}
		
	}
	
	public Record[] getSecurityProfileCache(){
		return securityProfileDS.getCacheData();
	}
	
	

}
