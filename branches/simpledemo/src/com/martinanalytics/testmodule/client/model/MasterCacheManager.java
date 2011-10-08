package com.martinanalytics.testmodule.client.model;



import com.allen_sauer.gwt.log.client.Log;
import com.martinanalytics.testmodule.client.app.AppEvent;
import com.martinanalytics.testmodule.client.app.AppEventListener;
import com.martinanalytics.testmodule.client.app.AppMsg;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;

public class MasterCacheManager implements AppEventListener{
	public static final int DEFAULT_MAX_CACHE_AGE = 100000;
	public static final boolean DEFAULT_CACHE_PREFERRED = true;
	private SecurityUserDS securityUserDS;
	private UserPublicDS userPublicDS ;
	private VwProfileGrantDS vwProfileGrantDS;
	private SecurityPrivilegeDS  securityPrivilegeDS;
	private SecurityProfileDS securityProfileDS;
	
	private SysCodeDS sysCodeDS;
	private DataSource statesDS;
	public static String KEY ="key";
	public static String VALUE = "value";
	enum SysCodeType{STATE}
	static MasterCacheManager instance = null;
	public static MasterCacheManager getInstance(){
		if (instance == null){
			instance = new MasterCacheManager();
		}
		return instance;
	}
	
	protected MasterCacheManager(){
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
		
		securityProfileDS = SecurityProfileDS.getInstance();
		securityProfileDS.setCacheMaxAge(DEFAULT_MAX_CACHE_AGE);
		securityProfileDS.setCachePreferred(DEFAULT_CACHE_PREFERRED);
		
		sysCodeDS = SysCodeDS.getInstance();
		sysCodeDS.setCacheMaxAge(DEFAULT_MAX_CACHE_AGE);
		sysCodeDS.setCachePreferred(DEFAULT_CACHE_PREFERRED);	
		
		statesDS = new DataSource();
		statesDS.setClientOnly(true);
		
		
		
	}
	public void init(){
		sysCodeDS.fetchAllRowsToCache();
	}
	
	@Override
	public void onAppEventNotify(AppEvent e) {
		SysCodeType codeType;
		Record record;
		switch(e.getName()){
		case EVT_CACHE_UPDATED: 
			if (e.getSource() instanceof SysCodeDS){
				statesDS.setTestData(null);
				for(int i = 0;i<sysCodeDS.getCacheList().size();i++){
					codeType =SysCodeType.valueOf(sysCodeDS.getCacheList().get(i).getAttribute(SysCodeDS.CODE_TYPE));
					record = new Record();				
					record.setAttribute(KEY, sysCodeDS.getCacheList().get(i).getAttribute(SysCodeDS.KEY));
					record.setAttribute(VALUE, sysCodeDS.getCacheList().get(i).getAttribute(SysCodeDS.VALUE));
					switch(codeType){
					  case STATE :statesDS.addData(record);	break;
				    }
						
				}
			}
			break;
		}// end switch event name
	}//end onAppEventNotify
	/**
	 * @param statesDS the statesDS to set
	 */
	public void setStatesDS(DataSource statesDS) {
		this.statesDS = statesDS;
	}
	/**
	 * @return the statesDS
	 */
	public DataSource getStatesDS() {
		return statesDS;
	}

}
