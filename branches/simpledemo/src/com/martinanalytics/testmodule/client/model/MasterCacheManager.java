package com.martinanalytics.testmodule.client.model;



import com.allen_sauer.gwt.log.client.Log;
import com.martinanalytics.testmodule.client.app.AppEvent;
import com.martinanalytics.testmodule.client.app.AppEventListener;
import com.martinanalytics.testmodule.client.app.AppMsg;

public class MasterCacheManager implements AppEventListener{
	public static final int DEFAULT_MAX_CACHE_AGE = 100000;
	public static final boolean DEFAULT_CACHE_PREFERRED = true;
	SecurityUserDS securityUserDS;
	UserPublicDS userPublicDS ;
	VwProfileGrantDS vwProfileGrantDS;
	SecurityPrivilegeDS  securityPrivilegeDS;
	SysCodeDS sysCodeDS;
	
	public MasterCacheManager(){
		 securityUserDS = SecurityUserDS.getInstance();
		securityUserDS.setCacheMaxAge(DEFAULT_MAX_CACHE_AGE);
		securityUserDS.setCachePreferred(DEFAULT_CACHE_PREFERRED);
		
		userPublicDS = UserPublicDS.getInstance();
		userPublicDS.setCacheMaxAge(DEFAULT_MAX_CACHE_AGE);
		userPublicDS.setCachePreferred(DEFAULT_CACHE_PREFERRED);
		
		vwProfileGrantDS = VwProfileGrantDS.getInstance();
		vwProfileGrantDS.setCacheMaxAge(DEFAULT_MAX_CACHE_AGE);
		vwProfileGrantDS.setCachePreferred(DEFAULT_CACHE_PREFERRED);
		
		securityPrivilegeDS = SecurityPrivilegeDS.getInstance();
		securityPrivilegeDS.setCacheMaxAge(DEFAULT_MAX_CACHE_AGE);
		securityPrivilegeDS.setCachePreferred(DEFAULT_CACHE_PREFERRED);
		
		sysCodeDS = SysCodeDS.getInstance();
		sysCodeDS.setCacheMaxAge(DEFAULT_MAX_CACHE_AGE);
		sysCodeDS.setCachePreferred(DEFAULT_CACHE_PREFERRED);		
		
		
	}
	public void init(){
		sysCodeDS.fetchAllRowsToCache();
	}
	
	@Override
	public void onAppEventNotify(AppEvent e) {
		switch(e.getName()){
		case EVT_CACHE_UPDATED: 
				if (e.getSource() instanceof SysCodeDS){
					
				}
				break;
		}
		

		
	}

}
