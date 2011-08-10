
package com.martinanalytics.testmodule.client.model;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.martinanalytics.testmodule.client.ServerExceptionHandler;
import com.martinanalytics.testmodule.client.app.AppEventProducer;
import com.martinanalytics.testmodule.client.app.AppPref;
import com.martinanalytics.testmodule.client.app.IApplicationController;
import com.martinanalytics.testmodule.client.model.bean.SecurityUserBean;
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
 * SecurityUserDataSource owns the data description for security_user * @author bmartin
 *
 */
public class SecurityUserDS extends GwtRpcDataSource{


	private static SecurityUserDS instance = null; 
	// primary GWT remote Service 
	private final SecurityUserServiceAsync securityUserService = GWT.create(SecurityUserService.class);
			 		
	private UserProfile userProfile; 
	private IApplicationController masterController;
	private AppEventProducer notifier;
  
    	public static SecurityUserDS getInstance(IApplicationController masterController_) {  
          if (instance == null) {  
            instance = new SecurityUserDS(masterController_);  //"securityUserDS"
          }  
          return instance;  
    	}  
	public static final String CLIENT_ID="clientId";
	public static final String USER_ID="userId";
	//public static final String PASSWORD_ENC="passwordEnc";
	public static final String SECURITY_PROFILE_ID="securityProfileId";
	//public static final String SESSION_ID="sessionId";
	//public static final String SESSION_EXPIRE_DT="sessionExpireDt";
	public static final String ACTIVE_YN="activeYn";
	public static final String LAST_UPDATE="lastUpdate";
	
	public static final String EVT_CACHE_UPDATED ="EvtCacheUpdated";
	public static final String EVT_RECORD_ADDED ="EvtRecordAdded";
	public static final String EVT_RECORD_UPDATED ="EvtRecordUpdated";
	public static final String EVT_RECORD_REMOVED ="EvtRecordRemoved";
	public static final String EVT_SELECT_RETURNED ="EvtSelectReturned";

   	public SecurityUserDS(IApplicationController masterController_) { 
	  userProfile = UserProfile.getInstance();
	  masterController = masterController_; 
	  notifier = new AppEventProducer() ;
	  DataSourceField field;
		
	  field = new DataSourceField(CLIENT_ID,FieldType.INTEGER, "Client Id",50); 
	  field.setHidden(true); 
	  field.setPrimaryKey(true);
	  addField(field);

	  field = new DataSourceField(USER_ID,FieldType.TEXT, "User Id",50); 
	  field.setHidden(true); 
	  field.setPrimaryKey(true);
	  addField(field);

//	  field = new DataSourceField(PASSWORD_ENC,FieldType.TEXT, "Password Enc",50);
//	  addField(field);

	  field = new DataSourceField(SECURITY_PROFILE_ID,FieldType.INTEGER, "Security Profile Id",50);
	  addField(field);

//	  field = new DataSourceField(SESSION_ID,FieldType.TEXT, "Session Id",50);
//	  addField(field);

//	  field = new DataSourceField(SESSION_EXPIRE_DT,FieldType.DATE, "Session Expire Dt",50);
//	  addField(field);

	  field = new DataSourceField(ACTIVE_YN,FieldType.TEXT, "Active Yn",50);
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
		 Log.debug("executeFetch Called - SecurityUser");
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
		   Log.debug("RPC Called - VwProfileGrant");
		   final String whereClause =tempWhereClause;
		   final String orderByClause =tempOrderByClause;
		   final java.util.Date startTime = new java.util.Date();
		   securityUserService.selectSecurityUser(userProfile, whereClause, orderByClause, rowLimit, startRow,
			new AsyncCallback<ArrayList<SecurityUserBean>>(){
				public void onFailure(Throwable caught) {
					masterController.setTransactionResults(
					  "Retrieving SecurityUser Failed ("+ (new java.util.Date().getTime() -startTime.getTime()) + "ms)");
					Log.debug("Where Attempted: " +whereClause + " | Orderby attempted " + orderByClause);
					if(!ServerExceptionHandler.getInstance().handle(caught)){

					}
				}
		
				public void onSuccess(ArrayList<SecurityUserBean> securityUserResult) {
				  Log.debug("Select SecurityUser received  Where Attempted: " + whereClause + " | Orderby attempted " + orderByClause );
				  masterController.setTransactionResults("Successfully Retrieved SecurityUser listing"
							+ (new java.util.Date().getTime() - startTime.getTime()));
 				  ListGridRecord[] list = new ListGridRecord[securityUserResult.size ()];
				  for (int i = 0; i < list.length; i++) {
				 	ListGridRecord record = new ListGridRecord ();
				        copyValues (securityUserResult.get (i), record);
				        list[i] = record;
				   }
				   response.setData (applyClientFilter(list));
				   processResponse (requestId, response);
				   if(getCachePreferred()){
					setCacheData(list);
					notifier.notifyAppEvent(this, EVT_CACHE_UPDATED,EVT_SELECT_RETURNED);	
				   }
				   
				   Log.debug("executeFetch passed - SecurityUser");
 				   notifier.notifyAppEvent(this, EVT_SELECT_RETURNED);
				}
		});
	}else{
	     response.setData (applyClientFilter(getCacheData()));//applyClientFilter
             processResponse (requestId, response);
             Log.debug("CacheFilter - SecurityUser: " + getCacheData().length+ " Records");
	}

}

/**
*
*
*/
  @Override
  protected void executeAdd (final String requestId, final DSRequest request, final DSResponse response) {

	Log.debug("executeAdd Called - SecurityUser");
	JavaScriptObject data = request.getData ();
      	ListGridRecord listGridRecord = new ListGridRecord(data);
        final SecurityUserBean securityUserBean = new SecurityUserBean();
        copyValues (listGridRecord, securityUserBean);
	final java.util.Date startTime = new java.util.Date();
	securityUserService.insertSecurityUserBean(userProfile,securityUserBean,
		new AsyncCallback<SecurityUserBean>(){
			public void onFailure(Throwable caught) {
				Log.debug("securityUserService.insertSecurityUser Failed: " + caught);
				masterController.setTransactionResults(
					"Adding SecurityUser Failed ("
						+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Insert Bean Attempted: " + securityUserBean);
               			response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}	
			}
 	
			public void onSuccess(SecurityUserBean result) {
				Log.debug("securityUserService.insertSecurityUser onSuccess: " + result);
				if (result.getClientId() !=null && result.getUserId() !=null){
					userProfile.incrementSessionTimeOut();
					masterController.setTransactionResults(
						"Successfully inserted SecurityUser record"
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
						notifier.notifyAppEvent(this, EVT_CACHE_UPDATED,EVT_RECORD_ADDED);
					}				
					notifier.notifyAppEvent(this, EVT_RECORD_ADDED);
				}else{
					masterController.notifyUserOfSystemError("Server Error","There was an error while adding the "
					+ "securityUser.  This is an unexpected error, please go to Help > View Log and send "
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
	Log.debug("executeUpdate Called - SecurityUser");

       	// Retrieve record which should be updated.
       	JavaScriptObject data = request.getData ();
       	ListGridRecord rec = new ListGridRecord (data);
       	// Find grid
       	ListGrid grid = (ListGrid) Canvas.getById (request.getComponentId ());
       	// Get record with old and new values combined
       	int index = grid.getRecordIndex (rec);
       	rec = (ListGridRecord) grid.getEditedRecord (index);
        final SecurityUserBean securityUserBean = new SecurityUserBean();
        copyValues (rec, securityUserBean);
	final java.util.Date startTime = new java.util.Date();
	securityUserService.updateSecurityUserBean(userProfile,securityUserBean,
		new AsyncCallback<SecurityUserBean>(){
			public void onFailure(Throwable caught) {
				Log.debug("securityUserService.updateSecurityUser Failed: " + caught);
				masterController.setTransactionResults("Updating SecurityUser Failed ("
					+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Update Bean Attempted: " + securityUserBean);
               			response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}
			}
			public void onSuccess(SecurityUserBean result) {
				Log.debug("securityUserService.updateSecurityUser onSuccess: " + result);
				if (result.getClientId() !=null && result.getUserId() !=null){
				  userProfile.incrementSessionTimeOut();
				  masterController.setTransactionResults("Successfully updated SecurityUser record"
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
					&& getCacheList().get(ndx).getAttributeAsString(USER_ID).equals(result.getUserId())){
						   getCacheList().remove(ndx);
						   notifier.notifyAppEvent(this, EVT_CACHE_UPDATED,EVT_RECORD_UPDATED);
					}
				    }
				  }				  
				  notifier.notifyAppEvent(this, EVT_RECORD_UPDATED);
				}else{
					masterController.notifyUserOfSystemError("Server Error","There was an error while updating a "
					+ "securityUser record.  This can be caused by someone else changing the record.  Please try"
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
	Log.debug("executeRemove Called - SecurityUser");

       	// Retrieve record which should be removed.
       	JavaScriptObject data = request.getData ();
       	final ListGridRecord rec = new ListGridRecord (data);
       	final SecurityUserBean securityUserBean = new SecurityUserBean();
       	copyValues (rec, securityUserBean);
	final java.util.Date startTime = new java.util.Date();
	securityUserService.deleteSecurityUserBean(userProfile,securityUserBean,
		new AsyncCallback<Boolean>(){
			public void onFailure(Throwable caught) {
				masterController.setTransactionResults(	"Deleting SecurityUser Failed ("
						+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Delete Bean Attempted: " + securityUserBean);
                		response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}
			}

			public void onSuccess(Boolean result) {
			  Log.debug("securityUserService.deleteSecurityUser onSuccess: " + result);
			  if (result){
				userProfile.incrementSessionTimeOut();
				masterController.setTransactionResults("Successfully deleted SecurityUser record"
				  + (new java.util.Date().getTime() - startTime.getTime()));
				Log.info("Bean Deleted" +  securityUserBean.toString());
				ListGridRecord[] list = new ListGridRecord[1];
				// We do not receive removed record from server.
				// Return record from request.
				list[0] = rec;
				
				if(getCachePreferred()){
				  for(int ndx=0;ndx< getCacheList().size();ndx++){
				    if(getCacheList().get(ndx).getAttributeAsInt(CLIENT_ID).equals(rec.getAttributeAsInt(CLIENT_ID))
					&& getCacheList().get(ndx).getAttributeAsString(USER_ID).equals(rec.getAttributeAsString(USER_ID))){
					  getCacheList().remove(ndx);
					  notifier.notifyAppEvent(this, EVT_CACHE_UPDATED,EVT_RECORD_REMOVED);
				      }
				    }
				}
				response.setData (list);
				processResponse (requestId, response);
				notifier.notifyAppEvent(this, EVT_RECORD_REMOVED);
			   }else{
				masterController.notifyUserOfSystemError("Server Error","There was an error while deleting a "
				+ "securityUser record.  This can be caused by someone else changing the record.  Please try"
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

	    public static void copyValues (ListGridRecord from, SecurityUserBean to) {
			to.setClientId(from.getAttributeAsInt(CLIENT_ID));
			to.setUserId(from.getAttributeAsString(USER_ID));
//			to.setPasswordEnc(from.getAttributeAsString(PASSWORD_ENC));
			to.setSecurityProfileId(from.getAttributeAsInt(SECURITY_PROFILE_ID));
//			to.setSessionId(from.getAttributeAsString(SESSION_ID));
//			to.setSessionExpireDt(from.getAttributeAsDate(SESSION_EXPIRE_DT));
			to.setActiveYn(from.getAttributeAsString(ACTIVE_YN));
			to.setLastUpdate(from.getAttributeAsDate(LAST_UPDATE));
	    }

/**
*
*
*/
	    public static void copyValues (SecurityUserBean from, ListGridRecord to) {
			to.setAttribute(CLIENT_ID, from.getClientId());
			to.setAttribute(USER_ID, from.getUserId());
//			to.setAttribute(PASSWORD_ENC, from.getPasswordEnc());
			to.setAttribute(SECURITY_PROFILE_ID, from.getSecurityProfileId());
//			to.setAttribute(SESSION_ID, from.getSessionId());
//			to.setAttribute(SESSION_EXPIRE_DT, from.getSessionExpireDt());
			to.setAttribute(ACTIVE_YN, from.getActiveYn());
			to.setAttribute(LAST_UPDATE, from.getLastUpdate());
	    }

	public AppEventProducer getNotifier() {
			return notifier;
	}
  
}