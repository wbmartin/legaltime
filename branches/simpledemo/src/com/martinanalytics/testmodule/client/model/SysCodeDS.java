
package com.martinanalytics.testmodule.client.model;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.martinanalytics.testmodule.client.ServerExceptionHandler;
import com.martinanalytics.testmodule.client.app.AppEventProducer;
import com.martinanalytics.testmodule.client.app.AppMsg;
import com.martinanalytics.testmodule.client.app.AppPref;
import com.martinanalytics.testmodule.client.app.IApplicationController;
import com.martinanalytics.testmodule.client.model.bean.SysCodeBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.form.validator.FloatPrecisionValidator;  
import com.smartgwt.client.widgets.form.validator.FloatRangeValidator; 
import java.util.ArrayList;
import java.util.List; 
import java.util.Date;



/**
 * SysCodeDataSource owns the data description for sys_code * @author bmartin
 *
 */
public class SysCodeDS extends GwtRpcDataSource{


	private static SysCodeDS instance = null; 
	// primary GWT remote Service 
	private final SysCodeServiceAsync sysCodeService = GWT.create(SysCodeService.class);
			 		
	private UserProfile userProfile; 
	private AppEventProducer notifier;
  
    	public static SysCodeDS getInstance() {  
          if (instance == null) {  
            instance = new SysCodeDS();  //"sysCodeDS"
          }  
          return instance;  
    	}  
	public static final String SYS_CODE_ID="sysCodeId";
	public static final String CLIENT_ID="clientId";
	public static final String CODE_TYPE="codeType";
	public static final String KEY="key";
	public static final String VALUE="value";
	public static final String LAST_UPDATE="lastUpdate";
	public static final String NOTES="notes";
	

   	protected SysCodeDS() { 
	  userProfile = UserProfile.getInstance();	   
	  notifier = new AppEventProducer() ;
	  DataSourceField field;
		
	  field = new DataSourceField(SYS_CODE_ID,FieldType.INTEGER, "Sys Code Id",50); 
	  field.setHidden(true); 
	  field.setPrimaryKey(true);
	  addField(field);

	  field = new DataSourceField(CLIENT_ID,FieldType.INTEGER, "Client Id",50);
	  addField(field);

	  field = new DataSourceField(CODE_TYPE,FieldType.TEXT, "Code Type",50);
	  addField(field);

	  field = new DataSourceField(KEY,FieldType.TEXT, "Key",50);
	  addField(field);

	  field = new DataSourceField(VALUE,FieldType.TEXT, "Value",50);
	  addField(field);

	  field = new DataSourceField(LAST_UPDATE,FieldType.DATE, "Last Update",50); 
	  field.setHidden(true); 
	  addField(field);

	  field = new DataSourceField(NOTES,FieldType.TEXT, "Notes",50);
	  addField(field);

}

/**
 * Provides a standard template to retrieve beans from the server.  
 * The results are handled through the onSuccess method in the AsynchCallback.
 * this function also uses the userProfile Singleton to send authorization credentials.
 */
  protected void executeFetch (final String requestId, final DSRequest request, final DSResponse response) {
	        // These can be used as parameters to create paging.
		//request.getStartRow ();
		//request.getEndRow ();
		//request.getSortBy ();
		 String tempWhereClause="";
		 boolean retrieveFromCache=false;
		 String tempOrderByClause ="";
		 Log.debug("executeFetch Called - SysCode");
		 Integer startRow=-1;
		 Integer rowLimit=-1;
		 setClientFilterCriteria( request.getCriteria());
		 setClientSortBy ( request.getSortBy());
		 if( isCacheValid() && getCachePreferred()){
		   retrieveFromCache = true;
		   startRow=-1;
		   rowLimit=-1;
		   if(request.getCriteria()!= null && request.getCriteria().getAttribute("where_clause")!=null){
			tempWhereClause = request.getCriteria().getAttribute("where_clause");
		   }
		   if(request.getAttribute("orderby_clause")!=null){
			tempOrderByClause =request.getAttribute("orderby_clause");
		   }
			
		 }else{
			 retrieveFromCache = false;
			 startRow = request.getStartRow ();
				rowLimit=(request.getEndRow() ==null || request.getStartRow () ==null)?-1: request.getEndRow() - request.getStartRow ();
				startRow=(startRow == null)?-1: startRow;
			 
		 }
		 if(!retrieveFromCache){
		   Log.debug("RPC Called - SysCode");
		   final String whereClause =tempWhereClause;
		   final String orderByClause =tempOrderByClause;
		   final java.util.Date startTime = new java.util.Date();
		   sysCodeService.selectSysCode(userProfile, whereClause, orderByClause, rowLimit, startRow,
			new AsyncCallback<ArrayList<SysCodeBean>>(){
				public void onFailure(Throwable caught) {
					notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
					  "Retrieving SysCode Failed ("+ (new java.util.Date().getTime() -startTime.getTime()) + " ms)");
					Log.debug("Where Attempted: " +whereClause + " | Orderby attempted " + orderByClause);
					if(!ServerExceptionHandler.getInstance().handle(caught)){

					}
				}
		
				public void onSuccess(ArrayList<SysCodeBean> sysCodeResult) {
				  Log.debug("Select SysCode received  Where Attempted: " + whereClause + " | Orderby attempted " + orderByClause );
				  notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
					"Successfully Retrieved SysCode listing"
					+ (new java.util.Date().getTime() - startTime.getTime()));
 				  ListGridRecord[] list = new ListGridRecord[sysCodeResult.size ()];
				  for (int i = 0; i < list.length; i++) {
				 	ListGridRecord record = new ListGridRecord ();
				        copyValues (sysCodeResult.get (i), record);
				        list[i] = record;
				   }
				   response.setData (applyClientFilter(list));
				   processResponse (requestId, response);
				   if(getCachePreferred()){
					setCacheData(list);
					notifier.notifyAppEvent(instance, AppMsg.EVT_CACHE_UPDATED,AppMsg.EVT_SELECT_RETURNED);	
				   }
				   
				   Log.debug("executeFetch passed - SysCode");
 				   notifier.notifyAppEvent(instance, AppMsg.EVT_SELECT_RETURNED);
				}
		});
	}else{
	     response.setData (applyClientFilter(getCacheData()));//applyClientFilter
             processResponse (requestId, response);
             Log.debug("CacheFilter - SysCode: " + getCacheData().length+ " Records");
	}

}

/**
*
*
*/
  @Override
  protected void executeAdd (final String requestId, final DSRequest request, final DSResponse response) {

	Log.debug("executeAdd Called - SysCode");
	JavaScriptObject data = request.getData ();
      	ListGridRecord listGridRecord = new ListGridRecord(data);
        final SysCodeBean sysCodeBean = new SysCodeBean();
        copyValues (listGridRecord, sysCodeBean);
	final java.util.Date startTime = new java.util.Date();
	sysCodeService.insertSysCodeBean(userProfile,sysCodeBean,
		new AsyncCallback<SysCodeBean>(){
			public void onFailure(Throwable caught) {
				Log.debug("sysCodeService.insertSysCode Failed: " + caught);
				notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
					"Adding SysCode Failed ("
					+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Insert Bean Attempted: " + sysCodeBean);
               			response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}	
			}
 	
			public void onSuccess(SysCodeBean result) {
				Log.debug("sysCodeService.insertSysCode onSuccess: " + result);
				if (result.getSysCodeId() !=null){
					userProfile.incrementSessionTimeOut();
					notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
						"Successfully inserted SysCode record"
						+ (new java.util.Date().getTime() - startTime.getTime()));
					Log.info("Bean Added" + result.toString());
					ListGridRecord[] listGridRecordArray = new ListGridRecord[1];
					ListGridRecord listGridRecord = new ListGridRecord ();
					copyValues (result, listGridRecord);
					listGridRecordArray[0] = listGridRecord;
					response.setData (listGridRecordArray);
					processResponse (requestId, response);
					if(getCachePreferred()){
						getCacheList().add(listGridRecord);
						notifier.notifyAppEvent(instance, AppMsg.EVT_CACHE_UPDATED,AppMsg.EVT_RECORD_ADDED);
					}				
					notifier.notifyAppEvent(instance, AppMsg.EVT_RECORD_ADDED);
				}else{
					notifier.notifyAppEvent(instance, AppMsg.ALERT_USER_MSG,
				  	  "Server Error","There was an error while adding the "
					  + "sysCode.  This is an unexpected error, please go to Help > View Log and send "
					  + " the entire contents to the system administrator: " + AppPref.SYS_ADMIN);
				}
			}
	});

	    }
/**
*
*
*/
  @Override
  protected void executeUpdate (final String requestId, final DSRequest request, final DSResponse response) {
	Log.debug("executeUpdate Called - SysCode");

       	// Retrieve record which should be updated.
       	JavaScriptObject data = request.getData ();
       	ListGridRecord rec = new ListGridRecord (data);
       	// Find grid
       	ListGrid grid = (ListGrid) Canvas.getById (request.getComponentId ());
       	// Get record with old and new values combined
       	int index = grid.getRecordIndex (rec);
       	rec = (ListGridRecord) grid.getEditedRecord (index);
        final SysCodeBean sysCodeBean = new SysCodeBean();
        copyValues (rec, sysCodeBean);
	final java.util.Date startTime = new java.util.Date();
	sysCodeService.updateSysCodeBean(userProfile,sysCodeBean,
		new AsyncCallback<SysCodeBean>(){
			public void onFailure(Throwable caught) {
				Log.debug("sysCodeService.updateSysCode Failed: " + caught);
				notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
					"Updating SysCode Failed ("
					+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Update Bean Attempted: " + sysCodeBean);
               			response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}
			}
			public void onSuccess(SysCodeBean result) {
				Log.debug("sysCodeService.updateSysCode onSuccess: " + result);
				if (result.getSysCodeId() !=null){
				  userProfile.incrementSessionTimeOut();
				  notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
					"Successfully updated SysCode record"
				  	+ (new java.util.Date().getTime() - startTime.getTime()));
				  Log.info("Bean Updated" + result.toString());
				  ListGridRecord[] listGridRecordArray = new ListGridRecord[1];
				  ListGridRecord listGridRecord = new ListGridRecord ();
				  copyValues (result, listGridRecord);
				  listGridRecordArray[0] = listGridRecord;
				  response.setData (listGridRecordArray);
				  response.setData (listGridRecordArray);
				  processResponse (requestId, response);
				  if(getCachePreferred()){
				    for(int ndx=0;ndx< getCacheList().size();ndx++){
					if(getCacheList().get(ndx).getAttributeAsInt(SYS_CODE_ID).equals(result.getSysCodeId())){
						   getCacheList().remove(ndx);
						   notifier.notifyAppEvent(instance, AppMsg.EVT_CACHE_UPDATED,AppMsg.EVT_RECORD_UPDATED);
					}
				    }
				  }				  
				  notifier.notifyAppEvent(instance, AppMsg.EVT_RECORD_UPDATED);
				}else{
					notifier.notifyAppEvent(instance, AppMsg.ALERT_USER_MSG,
				  	  "Server Error","There was an error while updating a "
					  + "sysCode record.  This can be caused by someone else changing the record.  Please try"
					  + " your transaction again.  If the error persists, please go to Help > View Log and send "
					  + " the entire contents to the system administrator: " + AppPref.SYS_ADMIN);
				}
			}
	});

  }

/**
*
*
*/
@Override
  protected void executeRemove (final String requestId, final DSRequest request, final DSResponse response) {
	Log.debug("executeRemove Called - SysCode");

       	// Retrieve record which should be removed.
       	JavaScriptObject data = request.getData ();
       	final ListGridRecord rec = new ListGridRecord (data);
       	final SysCodeBean sysCodeBean = new SysCodeBean();
       	copyValues (rec, sysCodeBean);
	final java.util.Date startTime = new java.util.Date();
	sysCodeService.deleteSysCodeBean(userProfile,sysCodeBean,
		new AsyncCallback<Boolean>(){
			public void onFailure(Throwable caught) {
				notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
					"Deleting SysCode Failed ("
					+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Delete Bean Attempted: " + sysCodeBean);
                		response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}
			}

			public void onSuccess(Boolean result) {
			  Log.debug("sysCodeService.deleteSysCode onSuccess: " + result);
			  if (result){
				userProfile.incrementSessionTimeOut();
				notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
				 	"Successfully deleted SysCode record"
				  	+ (new java.util.Date().getTime() - startTime.getTime()));
				Log.info("Bean Deleted" +  sysCodeBean.toString());
				ListGridRecord[] list = new ListGridRecord[1];
				// We do not receive removed record from server.
				// Return record from request.
				list[0] = rec;
				
				if(getCachePreferred()){
				  for(int ndx=0;ndx< getCacheList().size();ndx++){
				    if(getCacheList().get(ndx).getAttributeAsInt(SYS_CODE_ID).equals(rec.getAttributeAsInt(SYS_CODE_ID))){
					  getCacheList().remove(ndx);
					  notifier.notifyAppEvent(instance, AppMsg.EVT_CACHE_UPDATED,AppMsg.EVT_RECORD_REMOVED);
				      }
				    }
				}
				response.setData (list);
				processResponse (requestId, response);
				notifier.notifyAppEvent(instance, AppMsg.EVT_RECORD_REMOVED);
			   }else{
				notifier.notifyAppEvent(instance, AppMsg.ALERT_USER_MSG,
				  "Server Error","There was an error while deleting a "
				  + "sysCode record.  This can be caused by someone else changing the record.  Please try"
				  + " your transaction again.  If the error persists, please go to Help > View Log and send "
				  + " the entire contents to the system administrator: " + AppPref.SYS_ADMIN);
			    }
			}
	});

  }

/**
*
*
*/

	    public static void copyValues (ListGridRecord from, SysCodeBean to) {
			to.setSysCodeId(from.getAttributeAsInt(SYS_CODE_ID));
			to.setClientId(from.getAttributeAsInt(CLIENT_ID));
			to.setCodeType(from.getAttributeAsString(CODE_TYPE));
			to.setKey(from.getAttributeAsString(KEY));
			to.setValue(from.getAttributeAsString(VALUE));
			to.setLastUpdate(from.getAttributeAsDate(LAST_UPDATE));
			to.setNotes(from.getAttributeAsString(NOTES));
	    }

/**
*
*
*/
	    public static void copyValues (SysCodeBean from, ListGridRecord to) {
			to.setAttribute(SYS_CODE_ID, from.getSysCodeId());
			to.setAttribute(CLIENT_ID, from.getClientId());
			to.setAttribute(CODE_TYPE, from.getCodeType());
			to.setAttribute(KEY, from.getKey());
			to.setAttribute(VALUE, from.getValue());
			to.setAttribute(LAST_UPDATE, from.getLastUpdate());
			to.setAttribute(NOTES, from.getNotes());
	    }

	public AppEventProducer getNotifier() {
			return notifier;
	}
//---------------------------
	public void fetchAllRowsToCache () {

		   Log.debug("RPC to fetch for Cache Called - SysCode");
		   final String whereClause ="";
		   final String orderByClause ="";
		   Integer rowLimit =-1;
		   Integer startRow=1;
		   final java.util.Date startTime = new java.util.Date();
		   sysCodeService.selectSysCode(userProfile, whereClause, orderByClause, rowLimit, startRow,
			new AsyncCallback<ArrayList<SysCodeBean>>(){
				public void onFailure(Throwable caught) {
					Log.debug("fetchAllRowsToCache failed for SysCode");
					if(!ServerExceptionHandler.getInstance().handle(caught)){

					}
				}
		
				public void onSuccess(ArrayList<SysCodeBean> sysCodeResult) {
				  Log.debug("fetchAllRowsToCache succeeded for SysCode");
				 
 				  ListGridRecord[] list = new ListGridRecord[sysCodeResult.size ()];
				  for (int i = 0; i < list.length; i++) {
				 	ListGridRecord record = new ListGridRecord ();
				        copyValues (sysCodeResult.get (i), record);
				        list[i] = record;
				   }
				   
				   if(getCachePreferred()){
					setCacheData(list);
					notifier.notifyAppEvent(instance, AppMsg.EVT_CACHE_UPDATED,AppMsg.EVT_SELECT_RETURNED);	
				   }
				   notifier.notifyAppEvent(instance, AppMsg.EVT_SELECT_RETURNED);
				   
				   
				}
		});
	
	}

  
}