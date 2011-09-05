
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
import com.martinanalytics.testmodule.client.model.bean.SecurityProfileBean;
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
 * SecurityProfileDataSource owns the data description for security_profile * @author bmartin
 *
 */
public class SecurityProfileDS extends GwtRpcDataSource{


	private static SecurityProfileDS instance = null; 
	// primary GWT remote Service 
	private final SecurityProfileServiceAsync securityProfileService = GWT.create(SecurityProfileService.class);
			 		
	private UserProfile userProfile; 
	private AppEventProducer notifier;
  
    	public static SecurityProfileDS getInstance() {  
          if (instance == null) {  
            instance = new SecurityProfileDS();  //"securityProfileDS"
          }  
          return instance;  
    	}  
	public static final String CLIENT_ID="clientId";
	public static final String SECURITY_PROFILE_ID="securityProfileId";
	public static final String PROFILE_NAME="profileName";
	public static final String LAST_UPDATE="lastUpdate";


   	protected SecurityProfileDS() { 
	  userProfile = UserProfile.getInstance();
	   
	  notifier = new AppEventProducer() ;
	  DataSourceField field;
		
	  field = new DataSourceField(CLIENT_ID,FieldType.INTEGER, "Client Id",50); 
	  field.setHidden(true); 
	  field.setPrimaryKey(true);
	  addField(field);

	  field = new DataSourceField(SECURITY_PROFILE_ID,FieldType.INTEGER, "Security Profile Id",50); 
	  field.setHidden(true); 
	  field.setPrimaryKey(true);
	  addField(field);

	  field = new DataSourceField(PROFILE_NAME,FieldType.TEXT, "Profile Name",50);
	  addField(field);

	  field = new DataSourceField(LAST_UPDATE,FieldType.DATE, "Last Update",50); 
	  field.setHidden(true); 
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
		 Log.debug("executeFetch Called - SecurityProfile");
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
		   Log.debug("RPC Called - SecurityProfile");
		   final String whereClause =tempWhereClause;
		   final String orderByClause =tempOrderByClause;
		   final java.util.Date startTime = new java.util.Date();
		   securityProfileService.selectSecurityProfile(userProfile, whereClause, orderByClause, rowLimit, startRow,
			new AsyncCallback<ArrayList<SecurityProfileBean>>(){
				public void onFailure(Throwable caught) {
					notifier.notifyAppEvent(this, AppMsg.SET_MASTER_WINDOW_STATUS,
					  "Retrieving SecurityProfile Failed ("+ (new java.util.Date().getTime() -startTime.getTime()) + "ms)");
					Log.debug("Where Attempted: " +whereClause + " | Orderby attempted " + orderByClause);
					if(!ServerExceptionHandler.getInstance().handle(caught)){

					}
				}
		
				public void onSuccess(ArrayList<SecurityProfileBean> securityProfileResult) {
				  Log.debug("Select SecurityProfile received  Where Attempted: " + whereClause + " | Orderby attempted " + orderByClause );
				  notifier.notifyAppEvent(this, AppMsg.SET_MASTER_WINDOW_STATUS,
							"Successfully Retrieved SecurityProfile listing"
							+ (new java.util.Date().getTime() - startTime.getTime()));
 				  ListGridRecord[] list = new ListGridRecord[securityProfileResult.size ()];
				  for (int i = 0; i < list.length; i++) {
				 	ListGridRecord record = new ListGridRecord ();
				        copyValues (securityProfileResult.get (i), record);
				        list[i] = record;
				   }
				   response.setData (applyClientFilter(list));
				   processResponse (requestId, response);
				   if(getCachePreferred()){
					setCacheData(list);
					notifier.notifyAppEvent(this, AppMsg.EVT_CACHE_UPDATED,AppMsg.EVT_SELECT_RETURNED);	
				   }
				   
				   Log.debug("executeFetch passed - SecurityProfile");
 				   notifier.notifyAppEvent(this, AppMsg.EVT_SELECT_RETURNED);
				}
		});
	}else{
	     response.setData (applyClientFilter(getCacheData()));//applyClientFilter
             processResponse (requestId, response);
             Log.debug("CacheFilter - SecurityProfile: " + getCacheData().length+ " Records");
	}

}

/**
*
*
*/
  @Override
  protected void executeAdd (final String requestId, final DSRequest request, final DSResponse response) {

	Log.debug("executeAdd Called - SecurityProfile");
	JavaScriptObject data = request.getData ();
      	ListGridRecord listGridRecord = new ListGridRecord(data);
        final SecurityProfileBean securityProfileBean = new SecurityProfileBean();
        copyValues (listGridRecord, securityProfileBean);
	final java.util.Date startTime = new java.util.Date();
	securityProfileService.insertSecurityProfileBean(userProfile,securityProfileBean,
		new AsyncCallback<SecurityProfileBean>(){
			public void onFailure(Throwable caught) {
				Log.debug("securityProfileService.insertSecurityProfile Failed: " + caught);
				notifier.notifyAppEvent(this, AppMsg.SET_MASTER_WINDOW_STATUS,
					"Adding SecurityProfile Failed ("
					+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Insert Bean Attempted: " + securityProfileBean);
               			response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}	
			}
 	
			public void onSuccess(SecurityProfileBean result) {
				Log.debug("securityProfileService.insertSecurityProfile onSuccess: " + result);
				if (result.getClientId() !=null && result.getSecurityProfileId() !=null){
					userProfile.incrementSessionTimeOut();
					notifier.notifyAppEvent(this, AppMsg.SET_MASTER_WINDOW_STATUS,
						"Successfully inserted SecurityProfile record"
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
						notifier.notifyAppEvent(this, AppMsg.EVT_CACHE_UPDATED,AppMsg.EVT_RECORD_ADDED);
					}				
					notifier.notifyAppEvent(this, AppMsg.EVT_RECORD_ADDED);
				}else{
					notifier.notifyAppEvent(this, AppMsg.ALERT_USER_MSG,
				  	  "Server Error","There was an error while adding the "
					  + "securityProfile.  This is an unexpected error, please go to Help > View Log and send "
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
	Log.debug("executeUpdate Called - SecurityProfile");

       	// Retrieve record which should be updated.
       	JavaScriptObject data = request.getData ();
       	ListGridRecord rec = new ListGridRecord (data);
       	// Find grid
       	ListGrid grid = (ListGrid) Canvas.getById (request.getComponentId ());
       	// Get record with old and new values combined
       	int index = grid.getRecordIndex (rec);
       	rec = (ListGridRecord) grid.getEditedRecord (index);
        final SecurityProfileBean securityProfileBean = new SecurityProfileBean();
        copyValues (rec, securityProfileBean);
	final java.util.Date startTime = new java.util.Date();
	securityProfileService.updateSecurityProfileBean(userProfile,securityProfileBean,
		new AsyncCallback<SecurityProfileBean>(){
			public void onFailure(Throwable caught) {
				Log.debug("securityProfileService.updateSecurityProfile Failed: " + caught);
				notifier.notifyAppEvent(this, AppMsg.SET_MASTER_WINDOW_STATUS,
					"Updating SecurityProfile Failed ("
					+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Update Bean Attempted: " + securityProfileBean);
               			response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}
			}
			public void onSuccess(SecurityProfileBean result) {
				Log.debug("securityProfileService.updateSecurityProfile onSuccess: " + result);
				if (result.getClientId() !=null && result.getSecurityProfileId() !=null){
				  userProfile.incrementSessionTimeOut();
				  notifier.notifyAppEvent(this, AppMsg.SET_MASTER_WINDOW_STATUS,
					"Successfully updated SecurityProfile record"
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
					if(getCacheList().get(ndx).getAttributeAsInt(CLIENT_ID).equals(result.getClientId())
					&& getCacheList().get(ndx).getAttributeAsInt(SECURITY_PROFILE_ID).equals(result.getSecurityProfileId())){
						   getCacheList().remove(ndx);
						   notifier.notifyAppEvent(this, AppMsg.EVT_CACHE_UPDATED,AppMsg.EVT_RECORD_UPDATED);
					}
				    }
				  }				  
				  notifier.notifyAppEvent(this, AppMsg.EVT_RECORD_UPDATED);
				}else{
					notifier.notifyAppEvent(this, AppMsg.ALERT_USER_MSG,
				  	  "Server Error","There was an error while updating a "
					  + "securityProfile record.  This can be caused by someone else changing the record.  Please try"
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
	Log.debug("executeRemove Called - SecurityProfile");

       	// Retrieve record which should be removed.
       	JavaScriptObject data = request.getData ();
       	final ListGridRecord rec = new ListGridRecord (data);
       	final SecurityProfileBean securityProfileBean = new SecurityProfileBean();
       	copyValues (rec, securityProfileBean);
	final java.util.Date startTime = new java.util.Date();
	securityProfileService.deleteSecurityProfileBean(userProfile,securityProfileBean,
		new AsyncCallback<Boolean>(){
			public void onFailure(Throwable caught) {
				notifier.notifyAppEvent(this, AppMsg.SET_MASTER_WINDOW_STATUS,
					"Deleting SecurityProfile Failed ("
					+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Delete Bean Attempted: " + securityProfileBean);
                		response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}
			}

			public void onSuccess(Boolean result) {
			  Log.debug("securityProfileService.deleteSecurityProfile onSuccess: " + result);
			  if (result){
				userProfile.incrementSessionTimeOut();
				notifier.notifyAppEvent(this, AppMsg.SET_MASTER_WINDOW_STATUS,
				 	"Successfully deleted SecurityProfile record"
				  	+ (new java.util.Date().getTime() - startTime.getTime()));
				Log.info("Bean Deleted" +  securityProfileBean.toString());
				ListGridRecord[] list = new ListGridRecord[1];
				// We do not receive removed record from server.
				// Return record from request.
				list[0] = rec;
				
				if(getCachePreferred()){
				  for(int ndx=0;ndx< getCacheList().size();ndx++){
				    if(getCacheList().get(ndx).getAttributeAsInt(CLIENT_ID).equals(rec.getAttributeAsInt(CLIENT_ID))
					&& getCacheList().get(ndx).getAttributeAsInt(SECURITY_PROFILE_ID).equals(rec.getAttributeAsInt(SECURITY_PROFILE_ID))){
					  getCacheList().remove(ndx);
					  notifier.notifyAppEvent(this, AppMsg.EVT_CACHE_UPDATED,AppMsg.EVT_RECORD_REMOVED);
				      }
				    }
				}
				response.setData (list);
				processResponse (requestId, response);
				notifier.notifyAppEvent(this, AppMsg.EVT_RECORD_REMOVED);
			   }else{
				notifier.notifyAppEvent(this, AppMsg.ALERT_USER_MSG,
				  "Server Error","There was an error while deleting a "
				  + "securityProfile record.  This can be caused by someone else changing the record.  Please try"
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

	    public static void copyValues (ListGridRecord from, SecurityProfileBean to) {
			to.setClientId(from.getAttributeAsInt(CLIENT_ID));
			to.setSecurityProfileId(from.getAttributeAsInt(SECURITY_PROFILE_ID));
			to.setProfileName(from.getAttributeAsString(PROFILE_NAME));
			to.setLastUpdate(from.getAttributeAsDate(LAST_UPDATE));
	    }

/**
*
*
*/
	    public static void copyValues (SecurityProfileBean from, ListGridRecord to) {
			to.setAttribute(CLIENT_ID, from.getClientId());
			to.setAttribute(SECURITY_PROFILE_ID, from.getSecurityProfileId());
			to.setAttribute(PROFILE_NAME, from.getProfileName());
			to.setAttribute(LAST_UPDATE, from.getLastUpdate());
	    }

	public AppEventProducer getNotifier() {
			return notifier;
	}
//---------------------------
	public void fetchAllRowsToCache () {

		   Log.debug("RPC to fetch for Cache Called - SecurityProfile");
		   final String whereClause ="";
		   final String orderByClause ="";
		   Integer rowLimit =-1;
		   Integer startRow=1;
		   final java.util.Date startTime = new java.util.Date();
		   securityProfileService.selectSecurityProfile(userProfile, whereClause, orderByClause, rowLimit, startRow,
			new AsyncCallback<ArrayList<SecurityProfileBean>>(){
				public void onFailure(Throwable caught) {
					Log.debug("fetchAllRowsToCache failed for SecurityProfile");
					if(!ServerExceptionHandler.getInstance().handle(caught)){

					}
				}
		
				public void onSuccess(ArrayList<SecurityProfileBean> securityProfileResult) {
				  Log.debug("fetchAllRowsToCache succeeded for SecurityProfile");
				 
 				  ListGridRecord[] list = new ListGridRecord[securityProfileResult.size ()];
				  for (int i = 0; i < list.length; i++) {
				 	ListGridRecord record = new ListGridRecord ();
				        copyValues (securityProfileResult.get (i), record);
				        list[i] = record;
				   }
				   
				   if(getCachePreferred()){
					setCacheData(list);
					notifier.notifyAppEvent(this, AppMsg.EVT_CACHE_UPDATED,AppMsg.EVT_SELECT_RETURNED);	
				   }
				   notifier.notifyAppEvent(this, AppMsg.EVT_SELECT_RETURNED);
				}
		});
	
	}

  
}