
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
import com.martinanalytics.testmodule.client.model.bean.SecurityPrivilegeBean;
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
 * SecurityPrivilegeDataSource owns the data description for security_privilege * @author bmartin
 *
 */
public class SecurityPrivilegeDS extends GwtRpcDataSource{


	private static SecurityPrivilegeDS instance = null; 
	// primary GWT remote Service 
	private final SecurityPrivilegeServiceAsync securityPrivilegeService = GWT.create(SecurityPrivilegeService.class);
			 		
	private UserProfile userProfile; 
	private AppEventProducer notifier;
  
    	public static SecurityPrivilegeDS getInstance() {  
          if (instance == null) {  
            instance = new SecurityPrivilegeDS();  //"securityPrivilegeDS"
          }  
          return instance;  
    	}  
	public static final String CLIENT_ID="clientId";
	public static final String SECURITY_PRIVILEGE_ID="securityPrivilegeId";
	public static final String PRIV_NAME="privName";
	public static final String DESCRIPTION="description";
	public static final String LAST_UPDATE="lastUpdate";
	

   	protected SecurityPrivilegeDS() { 
	  userProfile = UserProfile.getInstance();	   
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

	  field = new DataSourceField(PRIV_NAME,FieldType.TEXT, "Priv Name",50);
	  addField(field);

	  field = new DataSourceField(DESCRIPTION,FieldType.TEXT, "Description",50);
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
		 Log.debug("executeFetch Called - SecurityPrivilege");
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
		   Log.debug("RPC Called - SecurityPrivilege");
		   final String whereClause =tempWhereClause;
		   final String orderByClause =tempOrderByClause;
		   final java.util.Date startTime = new java.util.Date();
		   securityPrivilegeService.selectSecurityPrivilege(userProfile, whereClause, orderByClause, rowLimit, startRow,
			new AsyncCallback<ArrayList<SecurityPrivilegeBean>>(){
				public void onFailure(Throwable caught) {
					notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
					  "Retrieving SecurityPrivilege Failed ("+ (new java.util.Date().getTime() -startTime.getTime()) + " ms)");
					Log.debug("Where Attempted: " +whereClause + " | Orderby attempted " + orderByClause);
					if(!ServerExceptionHandler.getInstance().handle(caught)){

					}
				}
		
				public void onSuccess(ArrayList<SecurityPrivilegeBean> securityPrivilegeResult) {
				  Log.debug("Select SecurityPrivilege received  Where Attempted: " + whereClause + " | Orderby attempted " + orderByClause );
				  notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
					"Successfully Retrieved SecurityPrivilege listing"
					+ (new java.util.Date().getTime() - startTime.getTime()));
 				  ListGridRecord[] list = new ListGridRecord[securityPrivilegeResult.size ()];
				  for (int i = 0; i < list.length; i++) {
				 	ListGridRecord record = new ListGridRecord ();
				        copyValues (securityPrivilegeResult.get (i), record);
				        list[i] = record;
				   }
				   response.setData (applyClientFilter(list));
				   processResponse (requestId, response);
				   if(getCachePreferred()){
					setCacheData(list);
					notifier.notifyAppEvent(instance, AppMsg.EVT_CACHE_UPDATED,AppMsg.EVT_SELECT_RETURNED);	
				   }
				   
				   Log.debug("executeFetch passed - SecurityPrivilege");
 				   notifier.notifyAppEvent(instance, AppMsg.EVT_SELECT_RETURNED);
				}
		});
	}else{
	     response.setData (applyClientFilter(getCacheData()));//applyClientFilter
             processResponse (requestId, response);
             Log.debug("CacheFilter - SecurityPrivilege: " + getCacheData().length+ " Records");
	}

}

/**
*
*
*/
  @Override
  protected void executeAdd (final String requestId, final DSRequest request, final DSResponse response) {

	Log.debug("executeAdd Called - SecurityPrivilege");
	JavaScriptObject data = request.getData ();
      	ListGridRecord listGridRecord = new ListGridRecord(data);
        final SecurityPrivilegeBean securityPrivilegeBean = new SecurityPrivilegeBean();
        copyValues (listGridRecord, securityPrivilegeBean);
	final java.util.Date startTime = new java.util.Date();
	securityPrivilegeService.insertSecurityPrivilegeBean(userProfile,securityPrivilegeBean,
		new AsyncCallback<SecurityPrivilegeBean>(){
			public void onFailure(Throwable caught) {
				Log.debug("securityPrivilegeService.insertSecurityPrivilege Failed: " + caught);
				notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
					"Adding SecurityPrivilege Failed ("
					+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Insert Bean Attempted: " + securityPrivilegeBean);
               			response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}	
			}
 	
			public void onSuccess(SecurityPrivilegeBean result) {
				Log.debug("securityPrivilegeService.insertSecurityPrivilege onSuccess: " + result);
				if (result.getClientId() !=null && result.getSecurityPrivilegeId() !=null){
					userProfile.incrementSessionTimeOut();
					notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
						"Successfully inserted SecurityPrivilege record"
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
					  + "securityPrivilege.  This is an unexpected error, please go to Help > View Log and send "
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
	Log.debug("executeUpdate Called - SecurityPrivilege");

       	// Retrieve record which should be updated.
       	JavaScriptObject data = request.getData ();
       	ListGridRecord rec = new ListGridRecord (data);
       	// Find grid
       	ListGrid grid = (ListGrid) Canvas.getById (request.getComponentId ());
       	// Get record with old and new values combined
       	int index = grid.getRecordIndex (rec);
       	rec = (ListGridRecord) grid.getEditedRecord (index);
        final SecurityPrivilegeBean securityPrivilegeBean = new SecurityPrivilegeBean();
        copyValues (rec, securityPrivilegeBean);
	final java.util.Date startTime = new java.util.Date();
	securityPrivilegeService.updateSecurityPrivilegeBean(userProfile,securityPrivilegeBean,
		new AsyncCallback<SecurityPrivilegeBean>(){
			public void onFailure(Throwable caught) {
				Log.debug("securityPrivilegeService.updateSecurityPrivilege Failed: " + caught);
				notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
					"Updating SecurityPrivilege Failed ("
					+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Update Bean Attempted: " + securityPrivilegeBean);
               			response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}
			}
			public void onSuccess(SecurityPrivilegeBean result) {
				Log.debug("securityPrivilegeService.updateSecurityPrivilege onSuccess: " + result);
				if (result.getClientId() !=null && result.getSecurityPrivilegeId() !=null){
				  userProfile.incrementSessionTimeOut();
				  notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
					"Successfully updated SecurityPrivilege record"
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
					&& getCacheList().get(ndx).getAttributeAsInt(SECURITY_PRIVILEGE_ID).equals(result.getSecurityPrivilegeId())){
						   getCacheList().remove(ndx);
						   notifier.notifyAppEvent(instance, AppMsg.EVT_CACHE_UPDATED,AppMsg.EVT_RECORD_UPDATED);
					}
				    }
				  }				  
				  notifier.notifyAppEvent(instance, AppMsg.EVT_RECORD_UPDATED);
				}else{
					notifier.notifyAppEvent(instance, AppMsg.ALERT_USER_MSG,
				  	  "Server Error","There was an error while updating a "
					  + "securityPrivilege record.  This can be caused by someone else changing the record.  Please try"
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
	Log.debug("executeRemove Called - SecurityPrivilege");

       	// Retrieve record which should be removed.
       	JavaScriptObject data = request.getData ();
       	final ListGridRecord rec = new ListGridRecord (data);
       	final SecurityPrivilegeBean securityPrivilegeBean = new SecurityPrivilegeBean();
       	copyValues (rec, securityPrivilegeBean);
	final java.util.Date startTime = new java.util.Date();
	securityPrivilegeService.deleteSecurityPrivilegeBean(userProfile,securityPrivilegeBean,
		new AsyncCallback<Boolean>(){
			public void onFailure(Throwable caught) {
				notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
					"Deleting SecurityPrivilege Failed ("
					+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Delete Bean Attempted: " + securityPrivilegeBean);
                		response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}
			}

			public void onSuccess(Boolean result) {
			  Log.debug("securityPrivilegeService.deleteSecurityPrivilege onSuccess: " + result);
			  if (result){
				userProfile.incrementSessionTimeOut();
				notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
				 	"Successfully deleted SecurityPrivilege record"
				  	+ (new java.util.Date().getTime() - startTime.getTime()));
				Log.info("Bean Deleted" +  securityPrivilegeBean.toString());
				ListGridRecord[] list = new ListGridRecord[1];
				// We do not receive removed record from server.
				// Return record from request.
				list[0] = rec;
				
				if(getCachePreferred()){
				  for(int ndx=0;ndx< getCacheList().size();ndx++){
				    if(getCacheList().get(ndx).getAttributeAsInt(CLIENT_ID).equals(rec.getAttributeAsInt(CLIENT_ID))
					&& getCacheList().get(ndx).getAttributeAsInt(SECURITY_PRIVILEGE_ID).equals(rec.getAttributeAsInt(SECURITY_PRIVILEGE_ID))){
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
				  + "securityPrivilege record.  This can be caused by someone else changing the record.  Please try"
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

	    public static void copyValues (ListGridRecord from, SecurityPrivilegeBean to) {
			to.setClientId(from.getAttributeAsInt(CLIENT_ID));
			to.setSecurityPrivilegeId(from.getAttributeAsInt(SECURITY_PRIVILEGE_ID));
			to.setPrivName(from.getAttributeAsString(PRIV_NAME));
			to.setDescription(from.getAttributeAsString(DESCRIPTION));
			to.setLastUpdate(from.getAttributeAsDate(LAST_UPDATE));
	    }

/**
*
*
*/
	    public static void copyValues (SecurityPrivilegeBean from, ListGridRecord to) {
			to.setAttribute(CLIENT_ID, from.getClientId());
			to.setAttribute(SECURITY_PRIVILEGE_ID, from.getSecurityPrivilegeId());
			to.setAttribute(PRIV_NAME, from.getPrivName());
			to.setAttribute(DESCRIPTION, from.getDescription());
			to.setAttribute(LAST_UPDATE, from.getLastUpdate());
	    }

	public AppEventProducer getNotifier() {
			return notifier;
	}
//---------------------------
	public void fetchAllRowsToCache () {

		   Log.debug("RPC to fetch for Cache Called - SecurityPrivilege");
		   final String whereClause ="";
		   final String orderByClause ="";
		   Integer rowLimit =-1;
		   Integer startRow=1;
		   final java.util.Date startTime = new java.util.Date();
		   securityPrivilegeService.selectSecurityPrivilege(userProfile, whereClause, orderByClause, rowLimit, startRow,
			new AsyncCallback<ArrayList<SecurityPrivilegeBean>>(){
				public void onFailure(Throwable caught) {
					Log.debug("fetchAllRowsToCache failed for SecurityPrivilege");
					if(!ServerExceptionHandler.getInstance().handle(caught)){

					}
				}
		
				public void onSuccess(ArrayList<SecurityPrivilegeBean> securityPrivilegeResult) {
				  Log.debug("fetchAllRowsToCache succeeded for SecurityPrivilege");
				 
 				  ListGridRecord[] list = new ListGridRecord[securityPrivilegeResult.size ()];
				  for (int i = 0; i < list.length; i++) {
				 	ListGridRecord record = new ListGridRecord ();
				        copyValues (securityPrivilegeResult.get (i), record);
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

//
//package com.martinanalytics.testmodule.client.model;
//
//import com.allen_sauer.gwt.log.client.Log;
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.core.client.JavaScriptObject;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.martinanalytics.testmodule.client.ServerExceptionHandler;
//import com.martinanalytics.testmodule.client.app.AppPref;
//import com.martinanalytics.testmodule.client.app.IApplicationController;
//import com.martinanalytics.testmodule.client.model.bean.SecurityPrivilegeBean;
//import com.martinanalytics.testmodule.client.model.bean.UserProfile;
//import com.smartgwt.client.data.DSRequest;
//import com.smartgwt.client.data.DSResponse;
//import com.smartgwt.client.data.DataSourceField;
//import com.smartgwt.client.rpc.RPCResponse;
//import com.smartgwt.client.types.FieldType;
//import com.smartgwt.client.widgets.Canvas;
//import com.smartgwt.client.widgets.grid.ListGrid;
//import com.smartgwt.client.widgets.grid.ListGridRecord;
//import com.smartgwt.client.widgets.form.validator.FloatPrecisionValidator;  
//import com.smartgwt.client.widgets.form.validator.FloatRangeValidator; 
//import java.util.ArrayList;
//import java.util.List; 
//import java.util.Date;
//
//
//
///**
// * SecurityPrivilegeDataSource owns the data description for security_privilege * @author bmartin
// *
// */
//public class SecurityPrivilegeDS extends GwtRpcDataSource{
//
//
//	private static SecurityPrivilegeDS instance = null; 
//	// primary GWT remote Service 
//	private final SecurityPrivilegeServiceAsync securityPrivilegeService = GWT.create(SecurityPrivilegeService.class);
//			 		
//	private UserProfile userProfile; 
//	private IApplicationController masterController;
//  
//    	public static SecurityPrivilegeDS getInstance(IApplicationController masterController_) {  
//          if (instance == null) {  
//            instance = new SecurityPrivilegeDS(masterController_);  //"securityPrivilegeDS"
//          }  
//          return instance;  
//    	}  
//
//    	public static final String CLIENT_ID="clientId";
//    	public static final String SECURITY_PRIVILEGE_ID="securityPrivilegeId";
//    	public static final String PRIV_NAME="privName";
//    	public static final String DESCRIPTION="description";
//    	public static final String LAST_UPDATE="lastUpdate";
//  	
//   	public SecurityPrivilegeDS(IApplicationController masterController_) { 
//	  userProfile = UserProfile.getInstance();
//	  masterController = masterController_; 
//	  DataSourceField field;
//	  setCacheMaxAge(100000);
//	
//	  field = new DataSourceField(CLIENT_ID,FieldType.INTEGER, "Client Id",50); 
//	  field.setHidden(true); 
//	  field.setPrimaryKey(true);
//	  addField(field);
//
//	  field = new DataSourceField(SECURITY_PRIVILEGE_ID,FieldType.INTEGER, "Security Privilege Id",50); 
//	  field.setHidden(true); 
//	  field.setPrimaryKey(true);
//	  addField(field);
//
//	  field = new DataSourceField(PRIV_NAME,FieldType.TEXT, "Priv Name",50);
//	  addField(field);
//
//	  field = new DataSourceField(DESCRIPTION,FieldType.TEXT, "Description",50);
//	  addField(field);
//
//	  field = new DataSourceField(LAST_UPDATE,FieldType.DATE, "Last Update",50); 
//	  field.setHidden(true); 
//	  addField(field);
//	  setCachePreferred(true);
//
//}
//
///**
// * Provides a standard template to retrieve beans from the server.  
// * The results are handled through the onSuccess method in the AsynchCallback.
// * this function also uses the userProfile Singleton to send authorization credentials.
// * @param whereClause_  a string beginning with "where" using standard sql syntax appropriate for the table to filter the beans
// * @param orderByClause a string beginning with "order by" using standard sql syntax appropriate alter the order of the beans
// */
//protected void executeFetch (final String requestId, final DSRequest request, final DSResponse response) {
//	        // These can be used as parameters to create paging.
//		//request.getStartRow ();
//		//request.getEndRow ();
//		//request.getSortBy ();
//		 String tempWhereClause="";
//		 boolean retrieveFromCache=false;
//		 String tempOrderByClause ="";
//		 Log.debug("executeFetch Called - SecurityPrivilege");
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
//		securityPrivilegeService.selectSecurityPrivilege(userProfile, whereClause, orderByClause, rowLimit, startRow,
//				new AsyncCallback<ArrayList<SecurityPrivilegeBean>>(){
//					public void onFailure(Throwable caught) {
//						masterController.setTransactionResults(
//							"Retrieving SecurityPrivilege Failed"
//							+ (new java.util.Date().getTime() -startTime.getTime()));
//						masterController.addSysLogMessage("Where Attempted: " +whereClause + " | Orderby attempted " + orderByClause );
//						if(!ServerExceptionHandler.getInstance().handle(caught)){
//
//						}
//						
//
//
//					}
//		
//					public void onSuccess(ArrayList<SecurityPrivilegeBean> securityPrivilegeResult) {
//						masterController.addSysLogMessage("Select SecurityPrivilege received ok-  Where Attempted: " 
//							+ whereClause + " | Orderby attempted " + orderByClause );
//						masterController.setTransactionResults(
//							"Successfully Retrieved SecurityPrivilege listing"
//							+ (new java.util.Date().getTime() - startTime.getTime()));
// 						ListGridRecord[] list = new ListGridRecord[securityPrivilegeResult.size ()];
//				                for (int i = 0; i < list.length; i++) {
//				                    ListGridRecord record = new ListGridRecord ();
//				                    copyValues (securityPrivilegeResult.get (i), record);
//				                    list[i] = record;
//				                    
//				                }
//				                if(getCachePreferred()){setCacheData(list);				        }
//					        response.setData (applyClientFilter(list));
//				                processResponse (requestId, response);
//				                Log.debug("executeFetch passed - SecurityPrivilege");
//						
//					}
//		});
//	}else{
//	     response.setData (applyClientFilter(getCacheData()));//applyClientFilter
//             processResponse (requestId, response);
//             Log.debug("CacheFilter - SecurityPrivilegeDS: " + getCacheData().length+ " Records");
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
//		Log.debug("executeAdd Called - SecurityPrivilege");
//		JavaScriptObject data = request.getData ();
//      		ListGridRecord listGridRecord = new ListGridRecord(data);
//        	final SecurityPrivilegeBean securityPrivilegeBean = new SecurityPrivilegeBean();
//        	copyValues (listGridRecord, securityPrivilegeBean);
//		final java.util.Date startTime = new java.util.Date();
//		securityPrivilegeService.insertSecurityPrivilegeBean(userProfile,securityPrivilegeBean,
//			new AsyncCallback<SecurityPrivilegeBean>(){
//				public void onFailure(Throwable caught) {
//					Log.debug("securityPrivilegeService.insertSecurityPrivilege Failed: " + caught);
//					masterController.setTransactionResults(
//						"Adding SecurityPrivilege Failed"
//						+ (new java.util.Date().getTime() -startTime.getTime()));
//					masterController.addSysLogMessage("Insert Bean Attempted: " + securityPrivilegeBean);
//                			response.setStatus (RPCResponse.STATUS_FAILURE);
//                			processResponse (requestId, response);
//					if(!ServerExceptionHandler.getInstance().handle(caught)){
//
//					}	
//				}
// 
//	
//				public void onSuccess(SecurityPrivilegeBean result) {
//					Log.debug("securityPrivilegeService.insertSecurityPrivilege onSuccess: " + result);
//					if (result.getClientId() !=null && result.getSecurityPrivilegeId() !=null){
//						userProfile.incrementSessionTimeOut();
//						masterController.setTransactionResults(
//							"Successfully inserted SecurityPrivilege record"
//							+ (new java.util.Date().getTime() - startTime.getTime()));
//						masterController.addSysLogMessage("Bean Added" + result.toString());
//						ListGridRecord[] listGridRecordArray = new ListGridRecord[1];
//						ListGridRecord listGridRecord = new ListGridRecord ();
//						copyValues (result, listGridRecord);
//						listGridRecordArray[0] = listGridRecord;
//						response.setData (listGridRecordArray);
//						processResponse (requestId, response);
//			
//					}else{
//						masterController.notifyUserOfSystemError("Server Error","There was an error while adding the "
//						+ "securityPrivilege.  This is an unexpected error, please go to Help > View Log and send "
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
//		Log.debug("executeUpdate Called - SecurityPrivilege");
//
//        	// Retrieve record which should be updated.
//        	JavaScriptObject data = request.getData ();
//        	ListGridRecord rec = new ListGridRecord (data);
//        	// Find grid
//        	ListGrid grid = (ListGrid) Canvas.getById (request.getComponentId ());
//        	// Get record with old and new values combined
//        	int index = grid.getRecordIndex (rec);
//        	rec = (ListGridRecord) grid.getEditedRecord (index);
//        	final SecurityPrivilegeBean securityPrivilegeBean = new SecurityPrivilegeBean();
//        	copyValues (rec, securityPrivilegeBean);
//		final java.util.Date startTime = new java.util.Date();
//		securityPrivilegeService.updateSecurityPrivilegeBean(userProfile,securityPrivilegeBean,
//			new AsyncCallback<SecurityPrivilegeBean>(){
//				public void onFailure(Throwable caught) {
//					Log.debug("securityPrivilegeService.updateSecurityPrivilege Failed: " + caught);
//					masterController.setTransactionResults(
//						"Updating SecurityPrivilege Failed"
//						+ (new java.util.Date().getTime() -startTime.getTime()));
//					masterController.addSysLogMessage("Update Bean Attempted: " + securityPrivilegeBean);
//                			response.setStatus (RPCResponse.STATUS_FAILURE);
//                			processResponse (requestId, response);
//					if(!ServerExceptionHandler.getInstance().handle(caught)){
//
//					}
//				}
//	
//				public void onSuccess(SecurityPrivilegeBean result) {
//					Log.debug("securityPrivilegeService.updateSecurityPrivilege onSuccess: " + result);
//					if (result.getClientId() !=null && result.getSecurityPrivilegeId() !=null){
//						userProfile.incrementSessionTimeOut();
//						masterController.setTransactionResults(
//							"Successfully updated SecurityPrivilege record"
//							+ (new java.util.Date().getTime() - startTime.getTime()));
//						masterController.addSysLogMessage("Bean Updated" + result.toString());
//						ListGridRecord[] listGridRecordArray = new ListGridRecord[1];
//						ListGridRecord listGridRecord = new ListGridRecord ();
//						copyValues (result, listGridRecord);
//						listGridRecordArray[0] = listGridRecord;
//						response.setData (listGridRecordArray);
//						processResponse (requestId, response);
//					}else{
//						masterController.notifyUserOfSystemError("Server Error","There was an error while updating a "
//						+ "securityPrivilege record.  This can be caused by someone else changing the record.  Please try"
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
//		Log.debug("executeRemove Called - SecurityPrivilege");
//
//        	// Retrieve record which should be removed.
//        	JavaScriptObject data = request.getData ();
//        	final ListGridRecord rec = new ListGridRecord (data);
//        	final SecurityPrivilegeBean securityPrivilegeBean = new SecurityPrivilegeBean();
//        	copyValues (rec, securityPrivilegeBean);
//		final java.util.Date startTime = new java.util.Date();
//		securityPrivilegeService.deleteSecurityPrivilegeBean(userProfile,securityPrivilegeBean,
//			new AsyncCallback<Boolean>(){
//				public void onFailure(Throwable caught) {
//					masterController.setTransactionResults(
//						"Deleting SecurityPrivilege Failed"
//						+ (new java.util.Date().getTime() -startTime.getTime()));
//					masterController.addSysLogMessage("Delete Bean Attempted: " + securityPrivilegeBean);
//                			response.setStatus (RPCResponse.STATUS_FAILURE);
//               				processResponse (requestId, response);
//					if(!ServerExceptionHandler.getInstance().handle(caught)){
//
//					}
//				}
//	
//
//				public void onSuccess(Boolean result) {
//					Log.debug("securityPrivilegeService.deleteSecurityPrivilege onSuccess: " + result);
//					if (result){
//						userProfile.incrementSessionTimeOut();
//						masterController.setTransactionResults(
//							"Successfully deleted SecurityPrivilege record"
//							+ (new java.util.Date().getTime() - startTime.getTime()));
//						masterController.addSysLogMessage("Bean Deleted" +  securityPrivilegeBean.toString());
//						ListGridRecord[] list = new ListGridRecord[1];
//						// We do not receive removed record from server.
//						// Return record from request.
//						list[0] = rec;
//						response.setData (list);
//						processResponse (requestId, response);
//					}else{
//						masterController.notifyUserOfSystemError("Server Error","There was an error while deleting a "
//						+ "securityPrivilege record.  This can be caused by someone else changing the record.  Please try"
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
//	    public static void copyValues (ListGridRecord from, SecurityPrivilegeBean to) {
//		to.setClientId(from.getAttributeAsInt("clientId"));
//		to.setSecurityPrivilegeId(from.getAttributeAsInt("securityPrivilegeId"));
//		to.setPrivName(from.getAttributeAsString("privName"));
//		to.setDescription(from.getAttributeAsString("description"));
//		to.setLastUpdate(from.getAttributeAsDate("lastUpdate"));
//	    }
//
///**
//*
//*
//*/
//	    public static void copyValues (SecurityPrivilegeBean from, ListGridRecord to) {
//		to.setAttribute("clientId", from.getClientId());
//		to.setAttribute("securityPrivilegeId", from.getSecurityPrivilegeId());
//		to.setAttribute("privName", from.getPrivName());
//		to.setAttribute("description", from.getDescription());
//		to.setAttribute("lastUpdate", from.getLastUpdate());
//	    }
//
//
//  
//}