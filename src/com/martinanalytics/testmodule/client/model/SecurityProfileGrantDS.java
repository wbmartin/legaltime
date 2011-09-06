
package com.martinanalytics.testmodule.client.model;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.martinanalytics.testmodule.client.ServerExceptionHandler;
import com.martinanalytics.testmodule.client.app.AppEventProducer;
import com.martinanalytics.testmodule.client.app.AppPref;
import com.martinanalytics.testmodule.client.app.IApplicationController;
import com.martinanalytics.testmodule.client.model.bean.SecurityProfileGrantBean;
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
 * SecurityProfileGrantDataSource owns the data description for security_profile_grant * @author bmartin
 *
 */
public class SecurityProfileGrantDS extends GwtRpcDataSource{


	private static SecurityProfileGrantDS instance = null; 
	// primary GWT remote Service 
	private final SecurityProfileGrantServiceAsync securityProfileGrantService = GWT.create(SecurityProfileGrantService.class);
			 		
	private UserProfile userProfile; 
	private IApplicationController masterController;
	private AppEventProducer notifier;
  
    	public static SecurityProfileGrantDS getInstance(IApplicationController masterController_) {  
          if (instance == null) {  
            instance = new SecurityProfileGrantDS(masterController_);  //"securityProfileGrantDS"
          }  
          return instance;  
    	}  
	public static final String CLIENT_ID="clientId";
	public static final String SECURITY_PRIVILEGE_ID="securityPrivilegeId";
	public static final String SECURITY_PROFILE_ID="securityProfileId";
	public static final String LAST_UPDATE="lastUpdate";
	public static final String EVT_CACHE_UPDATED ="EvtCacheUpdated";
	public static final String EVT_RECORD_ADDED ="EvtRecordAdded";
	public static final String EVT_RECORD_UPDATED ="EvtRecordUpdated";
	public static final String EVT_RECORD_REMOVED ="EvtRecordRemoved";
	public static final String EVT_SELECT_RETURNED ="EvtSelectReturned";

   	public SecurityProfileGrantDS(IApplicationController masterController_) { 
	  userProfile = UserProfile.getInstance();
	  masterController = masterController_; 
	  notifier = new AppEventProducer() ;
	  DataSourceField field;
		
	  field = new DataSourceField(CLIENT_ID,FieldType.INTEGER, "Client Id",50); 
	  field.setHidden(true); 
	  field.setPrimaryKey(true);
	  addField(field);

	  field = new DataSourceField(SECURITY_PRIVILEGE_ID,FieldType.INTEGER, "Security Privilege Id",50); 
	  field.setHidden(true); 
	  field.setPrimaryKey(true);
	  addField(field);

	  field = new DataSourceField(SECURITY_PROFILE_ID,FieldType.INTEGER, "Security Profile Id",50); 
	  field.setHidden(true); 
	  field.setPrimaryKey(true);
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
		 Log.debug("executeFetch Called - SecurityProfileGrant");
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
		   securityProfileGrantService.selectSecurityProfileGrant(userProfile, whereClause, orderByClause, rowLimit, startRow,
			new AsyncCallback<ArrayList<SecurityProfileGrantBean>>(){
				public void onFailure(Throwable caught) {
					masterController.setTransactionResults(
					  "Retrieving SecurityProfileGrant Failed ("+ (new java.util.Date().getTime() -startTime.getTime()) + "ms)");
					Log.debug("Where Attempted: " +whereClause + " | Orderby attempted " + orderByClause);
					if(!ServerExceptionHandler.getInstance().handle(caught)){

					}
				}
		
				public void onSuccess(ArrayList<SecurityProfileGrantBean> securityProfileGrantResult) {
				  Log.debug("Select SecurityProfileGrant received  Where Attempted: " + whereClause + " | Orderby attempted " + orderByClause );
				  masterController.setTransactionResults("Successfully Retrieved SecurityProfileGrant listing"
							+ (new java.util.Date().getTime() - startTime.getTime()));
 				  ListGridRecord[] list = new ListGridRecord[securityProfileGrantResult.size ()];
				  for (int i = 0; i < list.length; i++) {
				 	ListGridRecord record = new ListGridRecord ();
				        copyValues (securityProfileGrantResult.get (i), record);
				        list[i] = record;
				   }
				   
				   response.setData (applyClientFilter(list));
				   processResponse (requestId, response);
				   if(getCachePreferred()){
						setCacheData(list);
						notifier.notifyAppEvent(this, EVT_CACHE_UPDATED,EVT_SELECT_RETURNED);	
				   }
				   Log.debug("executeFetch passed - SecurityProfileGrant");
 				   notifier.notifyAppEvent(this, EVT_SELECT_RETURNED);
				}
		});
	}else{
	     response.setData (applyClientFilter(getCacheData()));//applyClientFilter
             processResponse (requestId, response);
             Log.debug("CacheFilter - SecurityProfileGrant: " + getCacheData().length+ " Records");
	}

}

/**
*
*
*/
  @Override
  protected void executeAdd (final String requestId, final DSRequest request, final DSResponse response) {

	Log.debug("executeAdd Called - SecurityProfileGrant");
	JavaScriptObject data = request.getData ();
      	ListGridRecord listGridRecord = new ListGridRecord(data);
        final SecurityProfileGrantBean securityProfileGrantBean = new SecurityProfileGrantBean();
        copyValues (listGridRecord, securityProfileGrantBean);
	final java.util.Date startTime = new java.util.Date();
	securityProfileGrantService.insertSecurityProfileGrantBean(userProfile,securityProfileGrantBean,
		new AsyncCallback<SecurityProfileGrantBean>(){
			public void onFailure(Throwable caught) {
				Log.debug("securityProfileGrantService.insertSecurityProfileGrant Failed: " + caught);
				masterController.setTransactionResults(
					"Adding SecurityProfileGrant Failed ("
						+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Insert Bean Attempted: " + securityProfileGrantBean);
               			response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}	
			}
 	
			public void onSuccess(SecurityProfileGrantBean result) {
				Log.debug("securityProfileGrantService.insertSecurityProfileGrant onSuccess: " + result);
				if (result.getClientId() !=null && result.getSecurityPrivilegeId() !=null && result.getSecurityProfileId() !=null){
					userProfile.incrementSessionTimeOut();
					masterController.setTransactionResults(
						"Successfully inserted SecurityProfileGrant record"
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
					+ "securityProfileGrant.  This is an unexpected error, please go to Help > View Log and send "
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
	Log.debug("executeUpdate Called - SecurityProfileGrant");

       	// Retrieve record which should be updated.
       	JavaScriptObject data = request.getData ();
       	ListGridRecord rec = new ListGridRecord (data);
       	// Find grid
       	ListGrid grid = (ListGrid) Canvas.getById (request.getComponentId ());
       	// Get record with old and new values combined
       	int index = grid.getRecordIndex (rec);
       	rec = (ListGridRecord) grid.getEditedRecord (index);
        final SecurityProfileGrantBean securityProfileGrantBean = new SecurityProfileGrantBean();
        copyValues (rec, securityProfileGrantBean);
	final java.util.Date startTime = new java.util.Date();
	securityProfileGrantService.updateSecurityProfileGrantBean(userProfile,securityProfileGrantBean,
		new AsyncCallback<SecurityProfileGrantBean>(){
			public void onFailure(Throwable caught) {
				Log.debug("securityProfileGrantService.updateSecurityProfileGrant Failed: " + caught);
				masterController.setTransactionResults("Updating SecurityProfileGrant Failed ("
					+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Update Bean Attempted: " + securityProfileGrantBean);
               			response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}
			}
			public void onSuccess(SecurityProfileGrantBean result) {
				Log.debug("securityProfileGrantService.updateSecurityProfileGrant onSuccess: " + result);
				if (result.getClientId() !=null && result.getSecurityPrivilegeId() !=null && result.getSecurityProfileId() !=null){
				  userProfile.incrementSessionTimeOut();
				  masterController.setTransactionResults("Successfully updated SecurityProfileGrant record"
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
					&& getCacheList().get(ndx).getAttributeAsInt(SECURITY_PRIVILEGE_ID).equals(result.getSecurityPrivilegeId())
					&& getCacheList().get(ndx).getAttributeAsInt(SECURITY_PROFILE_ID).equals(result.getSecurityProfileId())){
						   getCacheList().remove(ndx);
						   notifier.notifyAppEvent(this, EVT_CACHE_UPDATED,EVT_RECORD_UPDATED);
					}
				    }
				  }
				  
				  notifier.notifyAppEvent(this, EVT_RECORD_UPDATED);
				}else{
					masterController.notifyUserOfSystemError("Server Error","There was an error while updating a "
					+ "securityProfileGrant record.  This can be caused by someone else changing the record.  Please try"
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
	Log.debug("executeRemove Called - SecurityProfileGrant");

       	// Retrieve record which should be removed.
       	JavaScriptObject data = request.getData ();
       	final ListGridRecord rec = new ListGridRecord (data);
       	final SecurityProfileGrantBean securityProfileGrantBean = new SecurityProfileGrantBean();
       	copyValues (rec, securityProfileGrantBean);
	final java.util.Date startTime = new java.util.Date();
	securityProfileGrantService.deleteSecurityProfileGrantBean(userProfile,securityProfileGrantBean,
		new AsyncCallback<Boolean>(){
			public void onFailure(Throwable caught) {
				masterController.setTransactionResults(	"Deleting SecurityProfileGrant Failed ("
						+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Delete Bean Attempted: " + securityProfileGrantBean);
                		response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}
			}

			public void onSuccess(Boolean result) {
			  Log.debug("securityProfileGrantService.deleteSecurityProfileGrant onSuccess: " + result);
			  if (result){
				userProfile.incrementSessionTimeOut();
				masterController.setTransactionResults("Successfully deleted SecurityProfileGrant record"
				  + (new java.util.Date().getTime() - startTime.getTime()));
				Log.info("Bean Deleted" +  securityProfileGrantBean.toString());
				ListGridRecord[] list = new ListGridRecord[1];
				// We do not receive removed record from server.
				// Return record from request.
				list[0] = rec;
				response.setData (list);
				processResponse (requestId, response);
				if(getCachePreferred()){
				  for(int ndx=0;ndx< getCacheList().size();ndx++){
				    if(getCacheList().get(ndx).getAttributeAsInt(CLIENT_ID).equals(rec.getAttributeAsInt(CLIENT_ID))
					&& getCacheList().get(ndx).getAttributeAsInt(SECURITY_PRIVILEGE_ID).equals(rec.getAttributeAsInt(SECURITY_PRIVILEGE_ID))
					&& getCacheList().get(ndx).getAttributeAsInt(SECURITY_PROFILE_ID).equals(rec.getAttributeAsInt(SECURITY_PROFILE_ID))){
					  getCacheList().remove(ndx);
					  notifier.notifyAppEvent(this, EVT_CACHE_UPDATED,EVT_RECORD_REMOVED);
				      }
				    }
				}
				
				notifier.notifyAppEvent(this, EVT_RECORD_REMOVED);
			   }else{
				masterController.notifyUserOfSystemError("Server Error","There was an error while deleting a "
				+ "securityProfileGrant record.  This can be caused by someone else changing the record.  Please try"
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

	    public static void copyValues (ListGridRecord from, SecurityProfileGrantBean to) {
			to.setClientId(from.getAttributeAsInt(CLIENT_ID));
			to.setSecurityPrivilegeId(from.getAttributeAsInt(SECURITY_PRIVILEGE_ID));
			to.setSecurityProfileId(from.getAttributeAsInt(SECURITY_PROFILE_ID));
			to.setLastUpdate(from.getAttributeAsDate(LAST_UPDATE));
	    }

/**
*
*
*/
	    public static void copyValues (SecurityProfileGrantBean from, ListGridRecord to) {
			to.setAttribute(CLIENT_ID, from.getClientId());
			to.setAttribute(SECURITY_PRIVILEGE_ID, from.getSecurityPrivilegeId());
			to.setAttribute(SECURITY_PROFILE_ID, from.getSecurityProfileId());
			to.setAttribute(LAST_UPDATE, from.getLastUpdate());
	    }

	public AppEventProducer getNotifier() {
			return notifier;
	}
  
}
//
//package com.martinanalytics.testmodule.client.model;
//
//import com.allen_sauer.gwt.log.client.Log;
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.core.client.JavaScriptObject;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.martinanalytics.testmodule.client.ServerExceptionHandler;
//import com.martinanalytics.testmodule.client.app.AppEventProducer;
//import com.martinanalytics.testmodule.client.app.AppPref;
//import com.martinanalytics.testmodule.client.app.IApplicationController;
//import com.martinanalytics.testmodule.client.model.bean.SecurityProfileGrantBean;
//import com.martinanalytics.testmodule.client.model.bean.UserProfile;
//import com.smartgwt.client.data.DSRequest;
//import com.smartgwt.client.data.DSResponse;
//import com.smartgwt.client.data.DataSourceField;
//import com.smartgwt.client.types.FieldType;
//import com.smartgwt.client.widgets.Canvas;
//import com.smartgwt.client.widgets.grid.ListGrid;
//import com.smartgwt.client.widgets.grid.ListGridRecord;
//import com.smartgwt.client.widgets.form.validator.FloatPrecisionValidator;  
//import com.smartgwt.client.widgets.form.validator.FloatRangeValidator; 
//import java.util.ArrayList;
//import java.util.List; 
//import java.util.Date;
//import com.smartgwt.client.rpc.RPCResponse;
//
//
//
///**
// * SecurityProfileGrantDataSource owns the data description for security_profile_grant * @author bmartin
// *
// */
//public class SecurityProfileGrantDS extends GwtRpcDataSource{
//
//
//	private static SecurityProfileGrantDS instance = null; 
//	// primary GWT remote Service 
//	private final SecurityProfileGrantServiceAsync securityProfileGrantService = GWT.create(SecurityProfileGrantService.class);
//			 		
//	private UserProfile userProfile; 
//	private IApplicationController masterController;
//	private AppEventProducer notifier;
//  
//    	public static SecurityProfileGrantDS getInstance(IApplicationController masterController_) {  
//          if (instance == null) {  
//            instance = new SecurityProfileGrantDS(masterController_);  //"securityProfileGrantDS"
//          }  
//          return instance;  
//    	}  
//	public static final String CLIENT_ID="clientId";
//	public static final String SECURITY_PRIVILEGE_ID="securityPrivilegeId";
//	public static final String SECURITY_PROFILE_ID="securityProfileId";
//	public static final String LAST_UPDATE="lastUpdate";
//	public static final String EVT_CACHE_UPDATED ="EvtCacheUpdated";
//	public static final String EVT_RECORD_ADDED ="EvtRecordAdded";
//	public static final String EVT_RECORD_UPDATED ="EvtRecordUpdated";
//	public static final String EVT_RECORD_REMOVED ="EvtRecordRemoved";
//	public static final String EVT_SELECT_RETURNED ="EvtSelectReturned";
//   	public SecurityProfileGrantDS(IApplicationController masterController_) { 
//	  userProfile = UserProfile.getInstance();
//	  masterController = masterController_; 
//	  DataSourceField field;
//	  notifier = new AppEventProducer() ;
//		
//	  field = new DataSourceField("clientId",FieldType.INTEGER, "Client Id",50); 
//	  field.setHidden(true); 
//	  field.setPrimaryKey(true);
//	  addField(field);
//
//	  field = new DataSourceField("securityPrivilegeId",FieldType.INTEGER, "Security Privilege Id",50); 
//	  field.setHidden(true); 
//	  field.setPrimaryKey(true);
//	  addField(field);
//
//	  field = new DataSourceField("securityProfileId",FieldType.INTEGER, "Security Profile Id",50); 
//	  field.setHidden(true); 
//	  field.setPrimaryKey(true);
//	  addField(field);
//
//	  field = new DataSourceField("lastUpdate",FieldType.DATE, "Last Update",50); 
//	  field.setHidden(true); 
//	  addField(field);
//
//}
//
///**
// * Provides a standard template to retrieve beans from the server.  
// * The results are handled through the onSuccess method in the AsynchCallback.
// * this function also uses the userProfile Singleton to send authorization credentials.
// */
//protected void executeFetch (final String requestId, final DSRequest request, final DSResponse response) {
//	        // These can be used as parameters to create paging.
//		//request.getStartRow ();
//		//request.getEndRow ();
//		//request.getSortBy ();
//		 String tempWhereClause="";
//		 boolean retrieveFromCache=false;
//		 String tempOrderByClause ="";
//		 Log.debug("executeFetch Called - SecurityProfileGrant");
//		 Integer startRow=-1;
//		 Integer rowLimit=-1;
//		 setClientFilterCriteria( request.getCriteria());
//		 setClientSortBy ( request.getSortBy());
//		 if( isCacheValid() && getCachePreferred()){
//			 retrieveFromCache = true;
//			 startRow=-1;
//			 rowLimit=-1;
//			
//			if(request.getCriteria()!= null && request.getCriteria().getAttribute("where_clause")!=null){
//				tempWhereClause = request.getCriteria().getAttribute("where_clause");
//			}
//			if(request.getAttribute("orderby_clause")!=null){
//				tempOrderByClause =request.getAttribute("orderby_clause");
//			}
//			
//		 }else{
//			 retrieveFromCache = false;
//			 startRow = request.getStartRow ();
//				rowLimit=(request.getEndRow() ==null || request.getStartRow () ==null)?-1: request.getEndRow() - request.getStartRow ();
//				startRow=(startRow == null)?-1: startRow;
//			 
//		 }
//		 if(!retrieveFromCache){
//			 Log.debug("RPC Called - VwProfileGrant");
//			 final String whereClause =tempWhereClause;
//			 final String orderByClause =tempOrderByClause;
//		final java.util.Date startTime = new java.util.Date();
//		securityProfileGrantService.selectSecurityProfileGrant(userProfile, whereClause, orderByClause, rowLimit, startRow,
//				new AsyncCallback<ArrayList<SecurityProfileGrantBean>>(){
//					public void onFailure(Throwable caught) {
//						masterController.setTransactionResults(
//							"Retrieving SecurityProfileGrant Failed"
//							+ (new java.util.Date().getTime() -startTime.getTime()));
//						Log.debug("Where Attempted: " +whereClause + " | Orderby attempted " + orderByClause);
//						if(!ServerExceptionHandler.getInstance().handle(caught)){
//
//						}
//						
//
//
//					}
//		
//					public void onSuccess(ArrayList<SecurityProfileGrantBean> securityProfileGrantResult) {
//						Log.debug("Select SecurityProfileGrant received ok-  Where Attempted: " 
//							+ whereClause + " | Orderby attempted " + orderByClause );
//						masterController.setTransactionResults(
//							"Successfully Retrieved SecurityProfileGrant listing"
//							+ (new java.util.Date().getTime() - startTime.getTime()));
// 						ListGridRecord[] list = new ListGridRecord[securityProfileGrantResult.size ()];
//				                for (int i = 0; i < list.length; i++) {
//				                    ListGridRecord record = new ListGridRecord ();
//				                    copyValues (securityProfileGrantResult.get (i), record);
//				                    list[i] = record;
//				                    
//				                }
//				                if(getCachePreferred()){
//				                	setCacheData(list);	
//				                	notifier.notifyAppEvent(this, EVT_CACHE_UPDATED,EVT_SELECT_RETURNED);
//				                }
//					        response.setData (applyClientFilter(list));
//				                processResponse (requestId, response);
//				                Log.debug("executeFetch passed - SecurityProfileGrant");
//				                notifier.notifyAppEvent(this, EVT_SELECT_RETURNED);
//						
//					}
//		});
//	}else{
//	     response.setData (applyClientFilter(getCacheData()));//applyClientFilter
//             processResponse (requestId, response);
//             Log.debug("CacheFilter - SecurityProfileGrant: " + getCacheData().length+ " Records");
//	}
//
//}
//
///**
//*
//*
//*/
//	    @Override
//	    protected void executeAdd (final String requestId, final DSRequest request, final DSResponse response) {
//
//		Log.debug("executeAdd Called - SecurityProfileGrant");
//		JavaScriptObject data = request.getData ();
//      		ListGridRecord listGridRecord = new ListGridRecord(data);
//        	final SecurityProfileGrantBean securityProfileGrantBean = new SecurityProfileGrantBean();
//        	copyValues (listGridRecord, securityProfileGrantBean);
//		final java.util.Date startTime = new java.util.Date();
//		securityProfileGrantService.insertSecurityProfileGrantBean(userProfile,securityProfileGrantBean,
//			new AsyncCallback<SecurityProfileGrantBean>(){
//				public void onFailure(Throwable caught) {
//					Log.debug("securityProfileGrantService.insertSecurityProfileGrant Failed: " + caught);
//					masterController.setTransactionResults(
//						"Adding SecurityProfileGrant Failed"
//						+ (new java.util.Date().getTime() -startTime.getTime()));
//					masterController.addSysLogMessage("Insert Bean Attempted: " + securityProfileGrantBean);
//                			response.setStatus (RPCResponse.STATUS_FAILURE);
//                			processResponse (requestId, response);
//					if(!ServerExceptionHandler.getInstance().handle(caught)){
//
//					}	
//				}
// 
//	
//				public void onSuccess(SecurityProfileGrantBean result) {
//					Log.debug("securityProfileGrantService.insertSecurityProfileGrant onSuccess: " + result);
//					if (result.getClientId() !=null && result.getSecurityPrivilegeId() !=null && result.getSecurityProfileId() !=null){
//						userProfile.incrementSessionTimeOut();
//						masterController.setTransactionResults(
//							"Successfully inserted SecurityProfileGrant record"
//							+ (new java.util.Date().getTime() - startTime.getTime()));
//						masterController.addSysLogMessage("Bean Added" + result.toString());
//						ListGridRecord[] listGridRecordArray = new ListGridRecord[1];
//						ListGridRecord listGridRecord = new ListGridRecord ();
//						copyValues (result, listGridRecord);
//						listGridRecordArray[0] = listGridRecord;
//						response.setData (listGridRecordArray);
//						processResponse (requestId, response);
//						if(getCachePreferred()){
//							getCacheList().add(listGridRecord);
//							notifier.notifyAppEvent(this, EVT_CACHE_UPDATED,EVT_RECORD_ADDED);
//						}
//						notifier.notifyAppEvent(this, EVT_RECORD_ADDED);
//			
//					}else{
//						masterController.notifyUserOfSystemError("Server Error","There was an error while adding the "
//						+ "securityProfileGrant.  This is an unexpected error, please go to Help > View Log and send "
//						+ " the entire contents to the system administrator: " + AppPref.SYS_ADMIN);
//					}
//				}
//	});
//
//	    }
///**
//*
//*
//*/
//	    @Override
//	    protected void executeUpdate (final String requestId, final DSRequest request, final DSResponse response) {
//		Log.debug("executeUpdate Called - SecurityProfileGrant");
//
//        	// Retrieve record which should be updated.
//        	JavaScriptObject data = request.getData ();
//        	ListGridRecord rec = new ListGridRecord (data);
//        	// Find grid
//        	ListGrid grid = (ListGrid) Canvas.getById (request.getComponentId ());
//        	// Get record with old and new values combined
//        	int index = grid.getRecordIndex (rec);
//        	rec = (ListGridRecord) grid.getEditedRecord (index);
//        	final SecurityProfileGrantBean securityProfileGrantBean = new SecurityProfileGrantBean();
//        	copyValues (rec, securityProfileGrantBean);
//		final java.util.Date startTime = new java.util.Date();
//		securityProfileGrantService.updateSecurityProfileGrantBean(userProfile,securityProfileGrantBean,
//			new AsyncCallback<SecurityProfileGrantBean>(){
//				public void onFailure(Throwable caught) {
//					Log.debug("securityProfileGrantService.updateSecurityProfileGrant Failed: " + caught);
//					masterController.setTransactionResults(
//						"Updating SecurityProfileGrant Failed"
//						+ (new java.util.Date().getTime() -startTime.getTime()));
//					masterController.addSysLogMessage("Update Bean Attempted: " + securityProfileGrantBean);
//                			response.setStatus (RPCResponse.STATUS_FAILURE);
//                			processResponse (requestId, response);
//					if(!ServerExceptionHandler.getInstance().handle(caught)){
//
//					}
//				}
//	
//				public void onSuccess(SecurityProfileGrantBean result) {
//					Log.debug("securityProfileGrantService.updateSecurityProfileGrant onSuccess: " + result);
//					if (result.getClientId() !=null && result.getSecurityPrivilegeId() !=null && result.getSecurityProfileId() !=null){
//						userProfile.incrementSessionTimeOut();
//						masterController.setTransactionResults(
//							"Successfully updated SecurityProfileGrant record"
//							+ (new java.util.Date().getTime() - startTime.getTime()));
//						masterController.addSysLogMessage("Bean Updated" + result.toString());
//						ListGridRecord[] listGridRecordArray = new ListGridRecord[1];
//						ListGridRecord listGridRecord = new ListGridRecord ();
//						copyValues (result, listGridRecord);
//						listGridRecordArray[0] = listGridRecord;
//						if(getCachePreferred()){
//							for(int ndx=0;ndx< getCacheList().size();ndx++){
//								if(getCacheList().get(ndx).getAttributeAsInt("securityProfileId").equals(result.getSecurityProfileId())
//									&& getCacheList().get(ndx).getAttributeAsInt("securityPrivilegeId").equals(result.getSecurityPrivilegeId())
//									&& getCacheList().get(ndx).getAttributeAsInt("clientId").equals(result.getClientId())
//								){
//									getCacheList().remove(ndx);
//									notifier.notifyAppEvent(this, EVT_CACHE_UPDATED,EVT_RECORD_UPDATED);
//								}
//							}
//						}
//						notifier.notifyAppEvent(this, EVT_RECORD_UPDATED);
//						response.setData (listGridRecordArray);
//						processResponse (requestId, response);
//					}else{
//						masterController.notifyUserOfSystemError("Server Error","There was an error while updating a "
//						+ "securityProfileGrant record.  This can be caused by someone else changing the record.  Please try"
//						+ " your transaction again.  If the error persists, please go to Help > View Log and send "
//						+ " the entire contents to the system administrator: " + AppPref.SYS_ADMIN);
//					}
//				}
//	});
//
//	    }
//
///**
//*
//*
//*/
//	    
//
//	    @Override
//	    protected void executeRemove (final String requestId, final DSRequest request, final DSResponse response) {
//		Log.debug("executeRemove Called - SecurityProfileGrant");
//
//        	// Retrieve record which should be removed.
//        	JavaScriptObject data = request.getData ();
//        	final ListGridRecord rec = new ListGridRecord (data);
//        	final SecurityProfileGrantBean securityProfileGrantBean = new SecurityProfileGrantBean();
//        	copyValues (rec, securityProfileGrantBean);
//		final java.util.Date startTime = new java.util.Date();
//		Log.debug("Trying to delete timestamp: " + securityProfileGrantBean.getLastUpdate());
//		securityProfileGrantService.deleteSecurityProfileGrantBean(userProfile,securityProfileGrantBean,
//			new AsyncCallback<Boolean>(){
//				public void onFailure(Throwable caught) {
//					masterController.setTransactionResults(
//						"Deleting SecurityProfileGrant Failed"
//						+ (new java.util.Date().getTime() -startTime.getTime()));
//					masterController.addSysLogMessage("Delete Bean Attempted: " + securityProfileGrantBean);
//                			response.setStatus (RPCResponse.STATUS_FAILURE);
//               				processResponse (requestId, response);
//					if(!ServerExceptionHandler.getInstance().handle(caught)){
//
//					}
//				}
//	
//
//				public void onSuccess(Boolean result) {
//					Log.debug("securityProfileGrantService.deleteSecurityProfileGrant onSuccess: " + result);
//					if (result){
//						userProfile.incrementSessionTimeOut();
//						masterController.setTransactionResults(
//							"Successfully deleted SecurityProfileGrant record"
//							+ (new java.util.Date().getTime() - startTime.getTime()));
//						masterController.addSysLogMessage("Bean Deleted" +  securityProfileGrantBean.toString());
//						ListGridRecord[] list = new ListGridRecord[1];
//						// We do not receive removed record from server.
//						// Return record from request.
//						list[0] = rec;
//						if(getCachePreferred()){
//							for(int ndx=0;ndx< getCacheList().size();ndx++){
//								if(getCacheList().get(ndx).getAttributeAsInt("securityProfileId").equals(rec.getAttributeAsInt("securityProfileId"))
//									&& getCacheList().get(ndx).getAttributeAsInt("securityPrivilegeId").equals(rec.getAttributeAsInt("securityPrivilegeId"))
//									&& getCacheList().get(ndx).getAttributeAsInt("clientId").equals(rec.getAttributeAsInt("clientId"))
//								){
//									getCacheList().remove(ndx);
//									notifier.notifyAppEvent(this, EVT_CACHE_UPDATED,EVT_RECORD_REMOVED);
//								}
//							}
//						}
//						notifier.notifyAppEvent(this, EVT_RECORD_REMOVED);
//						response.setData (list);
//						processResponse (requestId, response);
//					}else{
//						masterController.notifyUserOfSystemError("Server Error","There was an error while deleting a "
//						+ "securityProfileGrant record.  This can be caused by someone else changing the record.  Please try"
//						+ " your transaction again.  If the error persists, please go to Help > View Log and send "
//						+ " the entire contents to the system administrator: " + AppPref.SYS_ADMIN);
//					}
//				}
//	});
//
//
//            }
//
///**
//*
//*
//*/
//
//	    public static void copyValues (ListGridRecord from, SecurityProfileGrantBean to) {
//		to.setClientId(from.getAttributeAsInt("clientId"));
//		to.setSecurityPrivilegeId(from.getAttributeAsInt("securityPrivilegeId"));
//		to.setSecurityProfileId(from.getAttributeAsInt("securityProfileId"));
//		to.setLastUpdate(from.getAttributeAsDate("lastUpdate"));
//		//Log.debug("From rec to bean date: " +from.getAttributeAsDate("lastUpdate"));
//	    }
//
///**
//*
//*
//*/
//	    public static void copyValues (SecurityProfileGrantBean from, ListGridRecord to) {
//		to.setAttribute("clientId", from.getClientId());
//		to.setAttribute("securityPrivilegeId", from.getSecurityPrivilegeId());
//		to.setAttribute("securityProfileId", from.getSecurityProfileId());
//		to.setAttribute("lastUpdate", from.getLastUpdate());
//		//Log.debug("From bean to Rec date: " +from.getLastUpdate());
//	    }
//
//
//
//		public AppEventProducer getNotifier() {
//			return notifier;
//		}
//
//
//  
//}