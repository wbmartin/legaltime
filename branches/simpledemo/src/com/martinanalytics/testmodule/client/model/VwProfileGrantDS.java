
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
import com.martinanalytics.testmodule.client.model.bean.VwProfileGrantBean;
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
 * VwProfileGrantDataSource owns the data description for vw_profile_grant * @author bmartin
 *
 */
public class VwProfileGrantDS extends GwtRpcDataSource{


	private static VwProfileGrantDS instance = null; 
	// primary GWT remote Service 
	private final VwProfileGrantServiceAsync vwProfileGrantService = GWT.create(VwProfileGrantService.class);
			 		
	private UserProfile userProfile; 
	private AppEventProducer notifier;
  
    	public static VwProfileGrantDS getInstance() {  
          if (instance == null) {  
            instance = new VwProfileGrantDS();  //"vwProfileGrantDS"
          }  
          return instance;  
    	}  
	public static final String CLIENT_ID="clientId";
	public static final String SECURITY_PROFILE_ID="securityProfileId";
	public static final String PROFILE_NAME="profileName";
	public static final String SECURITY_PRIVILEGE_ID="securityPrivilegeId";
	public static final String PRIV_NAME="privName";
	public static final String LAST_UPDATE="lastUpdate";
	

   	protected VwProfileGrantDS() { 
	  userProfile = UserProfile.getInstance();	   
	  notifier = new AppEventProducer() ;
	  DataSourceField field;
		
	  field = new DataSourceField(CLIENT_ID,FieldType.INTEGER, "Client Id",50);
	  addField(field);

	  field = new DataSourceField(SECURITY_PROFILE_ID,FieldType.INTEGER, "Security Profile Id",50);
	  addField(field);

	  field = new DataSourceField(PROFILE_NAME,FieldType.TEXT, "Profile Name",50);
	  addField(field);

	  field = new DataSourceField(SECURITY_PRIVILEGE_ID,FieldType.INTEGER, "Security Privilege Id",50);
	  addField(field);

	  field = new DataSourceField(PRIV_NAME,FieldType.TEXT, "Priv Name",50);
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
		 Log.debug("executeFetch Called - VwProfileGrant");
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
		   vwProfileGrantService.selectVwProfileGrant(userProfile, whereClause, orderByClause, rowLimit, startRow,
			new AsyncCallback<ArrayList<VwProfileGrantBean>>(){
				public void onFailure(Throwable caught) {
					notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
					  "Retrieving VwProfileGrant Failed ("+ (new java.util.Date().getTime() -startTime.getTime()) + " ms)");
					Log.debug("Where Attempted: " +whereClause + " | Orderby attempted " + orderByClause);
					if(!ServerExceptionHandler.getInstance().handle(caught)){

					}
				}
		
				public void onSuccess(ArrayList<VwProfileGrantBean> vwProfileGrantResult) {
				  Log.debug("Select VwProfileGrant received  Where Attempted: " + whereClause + " | Orderby attempted " + orderByClause );
				  notifier.notifyAppEvent(instance, AppMsg.SET_MASTER_WINDOW_STATUS,
					"Successfully Retrieved VwProfileGrant listing"
					+ (new java.util.Date().getTime() - startTime.getTime()));
 				  ListGridRecord[] list = new ListGridRecord[vwProfileGrantResult.size ()];
				  for (int i = 0; i < list.length; i++) {
				 	ListGridRecord record = new ListGridRecord ();
				        copyValues (vwProfileGrantResult.get (i), record);
				        list[i] = record;
				   }
				   response.setData (applyClientFilter(list));
				   processResponse (requestId, response);
				   if(getCachePreferred()){
					setCacheData(list);
					notifier.notifyAppEvent(instance, AppMsg.EVT_CACHE_UPDATED,AppMsg.EVT_SELECT_RETURNED);	
				   }
				   
				   Log.debug("executeFetch passed - VwProfileGrant");
 				   notifier.notifyAppEvent(instance, AppMsg.EVT_SELECT_RETURNED);
				}
		});
	}else{
	     response.setData (applyClientFilter(getCacheData()));//applyClientFilter
             processResponse (requestId, response);
             Log.debug("CacheFilter - VwProfileGrant: " + getCacheData().length+ " Records");
	}

}

/**
*
*
*/
  @Override
  protected void executeAdd (final String requestId, final DSRequest request, final DSResponse response) {

	    }
/**
*
*
*/
  @Override
  protected void executeUpdate (final String requestId, final DSRequest request, final DSResponse response) {
	Log.debug("executeUpdate Called - VwProfileGrant");

  }

/**
*
*
*/
@Override
  protected void executeRemove (final String requestId, final DSRequest request, final DSResponse response) {
	Log.debug("executeRemove Called - VwProfileGrant");

  }

/**
*
*
*/

	    public static void copyValues (ListGridRecord from, VwProfileGrantBean to) {
			to.setClientId(from.getAttributeAsInt(CLIENT_ID));
			to.setSecurityProfileId(from.getAttributeAsInt(SECURITY_PROFILE_ID));
			to.setProfileName(from.getAttributeAsString(PROFILE_NAME));
			to.setSecurityPrivilegeId(from.getAttributeAsInt(SECURITY_PRIVILEGE_ID));
			to.setPrivName(from.getAttributeAsString(PRIV_NAME));
			to.setLastUpdate(from.getAttributeAsDate(LAST_UPDATE));
	    }

/**
*
*
*/
	    public static void copyValues (VwProfileGrantBean from, ListGridRecord to) {
			to.setAttribute(CLIENT_ID, from.getClientId());
			to.setAttribute(SECURITY_PROFILE_ID, from.getSecurityProfileId());
			to.setAttribute(PROFILE_NAME, from.getProfileName());
			to.setAttribute(SECURITY_PRIVILEGE_ID, from.getSecurityPrivilegeId());
			to.setAttribute(PRIV_NAME, from.getPrivName());
			to.setAttribute(LAST_UPDATE, from.getLastUpdate());
	    }

	public AppEventProducer getNotifier() {
			return notifier;
	}
//---------------------------
	public void fetchAllRowsToCache () {

		   Log.debug("RPC to fetch for Cache Called - VwProfileGrant");
		   final String whereClause ="";
		   final String orderByClause ="";
		   Integer rowLimit =-1;
		   Integer startRow=1;
		   final java.util.Date startTime = new java.util.Date();
		   vwProfileGrantService.selectVwProfileGrant(userProfile, whereClause, orderByClause, rowLimit, startRow,
			new AsyncCallback<ArrayList<VwProfileGrantBean>>(){
				public void onFailure(Throwable caught) {
					Log.debug("fetchAllRowsToCache failed for VwProfileGrant");
					if(!ServerExceptionHandler.getInstance().handle(caught)){

					}
				}
		
				public void onSuccess(ArrayList<VwProfileGrantBean> vwProfileGrantResult) {
				  Log.debug("fetchAllRowsToCache succeeded for VwProfileGrant");
				 
 				  ListGridRecord[] list = new ListGridRecord[vwProfileGrantResult.size ()];
				  for (int i = 0; i < list.length; i++) {
				 	ListGridRecord record = new ListGridRecord ();
				        copyValues (vwProfileGrantResult.get (i), record);
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
//import com.martinanalytics.testmodule.client.model.bean.VwProfileGrantBean;
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
//
//
//
///**
// * VwProfileGrantDataSource owns the data description for vw_profile_grant * @author bmartin
// *
// */
//public class VwProfileGrantDS extends GwtRpcDataSource{
//
//
//	private static VwProfileGrantDS instance = null; 
//	// primary GWT remote Service 
//	private final VwProfileGrantServiceAsync vwProfileGrantService = GWT.create(VwProfileGrantService.class);
//			 		
//	private UserProfile userProfile; 
//	private IApplicationController masterController;
//	
//	public static final String CLIENT_ID="clientId";
//	public static final String SECURITY_PROFILE_ID="securityProfileId";
//	public static final String PROFILE_NAME="profileName";
//	public static final String SECURITY_PRIVILEGE_ID="securityPrivilegeId";
//	public static final String PRIV_NAME="privName";
//	public static final String LAST_UPDATE="lastUpdate";
//  
//    	public static VwProfileGrantDS getInstance(IApplicationController masterController_) {  
//          if (instance == null) {  
//            instance = new VwProfileGrantDS(masterController_);  //"vwProfileGrantDS"
//          }  
//          return instance;  
//    	}  
//
//
//   	public VwProfileGrantDS(IApplicationController masterController_) { 
//	  userProfile = UserProfile.getInstance();
//	  masterController = masterController_; 
//	  DataSourceField field;
//	  
//	  
//	  
//		
//	  field = new DataSourceField("clientId",FieldType.INTEGER, "Client Id",50);
//	  field.setHidden(true);
//	  addField(field);
//
//	  field = new DataSourceField("securityProfileId",FieldType.INTEGER, "Security Profile Id",50);
//	  field.setHidden(true);
//	  addField(field);
//
//	  field = new DataSourceField("profileName",FieldType.TEXT, "Profile Name",50);
//	  field.setHidden(true);
//	  addField(field);
//
//	  field = new DataSourceField("securityPrivilegeId",FieldType.INTEGER, "Security Privilege Id",50);
//	  field.setHidden(true);
//	  addField(field);
//
//	  field = new DataSourceField("privName",FieldType.TEXT, "Priv Name",50);
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
//
//		 String tempOrderByClause ="";
//		 Log.debug("executeFetch Called - VwProfileGrant");
//		 Integer startRow=-1;
//		 Integer rowLimit=-1;
//		 //Log.debug("cache decision - iscachevalid:" +isCacheValid() + " | cache pref:"+  getCachePreferred());
//		 Log.debug("Criteria: " + request.getCriteria().getAttribute("securityProfileId"));
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
//			 final java.util.Date startTime = new java.util.Date();
//			vwProfileGrantService.selectVwProfileGrant(userProfile, whereClause, orderByClause, rowLimit, startRow,
//					new AsyncCallback<ArrayList<VwProfileGrantBean>>(){
//						public void onFailure(Throwable caught) {
//							masterController.setTransactionResults(
//								"Retrieving VwProfileGrant Failed"
//								+ (new java.util.Date().getTime() -startTime.getTime()));
//							masterController.addSysLogMessage("Where Attempted: " +whereClause + " | Orderby attempted " + orderByClause );
//							if(!ServerExceptionHandler.getInstance().handle(caught)){
//	
//							}
//							
//	
//	
//						}
//			
//						public void onSuccess(ArrayList<VwProfileGrantBean> vwProfileGrantResult) {
//							masterController.addSysLogMessage("Select VwProfileGrant received ok-  Where Attempted: " 
//								+ whereClause + " | Orderby attempted " + orderByClause );
//							masterController.setTransactionResults(
//								"Successfully Retrieved VwProfileGrant listing"
//								+ (new java.util.Date().getTime() - startTime.getTime()));
//	 						ListGridRecord[] list = new ListGridRecord[vwProfileGrantResult.size ()];
//					                for (int i = 0; i < list.length; i++) {
//					                    ListGridRecord record = new ListGridRecord ();
//					                    copyValues (vwProfileGrantResult.get (i), record);
//					                    list[i] = record;
//					                    
//					                }
//					                if(getCachePreferred()){
//					                	setCacheData(list);
//					                	
//					                }
//					                response.setData (applyClientFilter(list));//applyClientFilter
//					                processResponse (requestId, response);
//					                Log.debug("executeFetch passed - VwProfileGrant");
//							
//						}
//			});
//		 }else{
//			 response.setData (applyClientFilter(getCacheData()));//applyClientFilter
//             processResponse (requestId, response);
//             Log.debug("CacheFilter - VwProfileGrant: " + getCacheData().length+ " Records");
//		 }
//		 
//}
//
///**
//*
//*
//*/
//	    @Override
//	    protected void executeAdd (final String requestId, final DSRequest request, final DSResponse response) {
//		Log.debug("executeAdd Called - VwProfileGrant");
//		JavaScriptObject data = request.getData ();
//      		ListGridRecord listGridRecord = new ListGridRecord(data);
//        	final VwProfileGrantBean vwProfileGrantBean = new VwProfileGrantBean();
//        	copyValues (listGridRecord, vwProfileGrantBean);
//		final java.util.Date startTime = new java.util.Date();
////		vwProfileGrantService.insertVwProfileGrantBean(userProfile,vwProfileGrantBean,
////			new AsyncCallback<VwProfileGrantBean>(){
////				public void onFailure(Throwable caught) {
////					Log.debug("vwProfileGrantService.insertVwProfileGrant Failed: " + caught);
////					masterController.setTransactionResults(
////						"Adding VwProfileGrant Failed"
////						+ (new java.util.Date().getTime() -startTime.getTime()));
////					masterController.addSysLogMessage("Insert Bean Attempted: " + vwProfileGrantBean);
////                			response.setStatus (RPCResponse.STATUS_FAILURE);
////                			processResponse (requestId, response);
////					if(!ServerExceptionHandler.getInstance().handle(caught)){
////
////					}	
////				}
//// 
////	
////				public void onSuccess(VwProfileGrantBean result) {
////					Log.debug("vwProfileGrantService.insertVwProfileGrant onSuccess: " + result);
////					if (){
////						userProfile.incrementSessionTimeOut();
////						masterController.setTransactionResults(
////							"Successfully inserted VwProfileGrant record"
////							+ (new java.util.Date().getTime() - startTime.getTime()));
////						masterController.addSysLogMessage("Bean Added" + result.toString());
////						ListGridRecord[] listGridRecordArray = new ListGridRecord[1];
////						ListGridRecord listGridRecord = new ListGridRecord ();
////						copyValues (result, listGridRecord);
////						listGridRecordArray[0] = listGridRecord;
////						response.setData (listGridRecordArray);
////						processResponse (requestId, response);
////			
////					}else{
////						masterController.notifyUserOfSystemError("Server Error","There was an error while adding the "
////						+ "vwProfileGrant.  This is an unexpected error, please go to Help > View Log and send "
////						+ " the entire contents to the system administrator: " + AppPref.SYS_ADMIN);
////					}
////				}
////	});
//	    }
///**
//*
//*
//*/
//	    @Override
//	    protected void executeUpdate (final String requestId, final DSRequest request, final DSResponse response) {
//		Log.debug("executeUpdate Called - VwProfileGrant");
//        	// Retrieve record which should be updated.
//        	JavaScriptObject data = request.getData ();
//        	ListGridRecord rec = new ListGridRecord (data);
//        	// Find grid
//        	ListGrid grid = (ListGrid) Canvas.getById (request.getComponentId ());
//        	// Get record with old and new values combined
//        	int index = grid.getRecordIndex (rec);
//        	rec = (ListGridRecord) grid.getEditedRecord (index);
//        	final VwProfileGrantBean vwProfileGrantBean = new VwProfileGrantBean();
//        	copyValues (rec, vwProfileGrantBean);
//		final java.util.Date startTime = new java.util.Date();
////		vwProfileGrantService.updateVwProfileGrantBean(userProfile,vwProfileGrantBean,
////			new AsyncCallback<VwProfileGrantBean>(){
////				public void onFailure(Throwable caught) {
////					Log.debug("vwProfileGrantService.updateVwProfileGrant Failed: " + caught);
////					masterController.setTransactionResults(
////						"Updating VwProfileGrant Failed"
////						+ (new java.util.Date().getTime() -startTime.getTime()));
////					masterController.addSysLogMessage("Update Bean Attempted: " + vwProfileGrantBean);
////                			response.setStatus (RPCResponse.STATUS_FAILURE);
////                			processResponse (requestId, response);
////					if(!ServerExceptionHandler.getInstance().handle(caught)){
////
////					}
////				}
////	
////				public void onSuccess(VwProfileGrantBean result) {
////					Log.debug("vwProfileGrantService.updateVwProfileGrant onSuccess: " + result);
////					if (){
////						userProfile.incrementSessionTimeOut();
////						masterController.setTransactionResults(
////							"Successfully updated VwProfileGrant record"
////							+ (new java.util.Date().getTime() - startTime.getTime()));
////						masterController.addSysLogMessage("Bean Updated" + result.toString());
////						ListGridRecord[] listGridRecordArray = new ListGridRecord[1];
////						ListGridRecord listGridRecord = new ListGridRecord ();
////						copyValues (result, listGridRecord);
////						listGridRecordArray[0] = listGridRecord;
////						response.setData (listGridRecordArray);
////						processResponse (requestId, response);
////					}else{
////						masterController.notifyUserOfSystemError("Server Error","There was an error while updating a "
////						+ "vwProfileGrant record.  This can be caused by someone else changing the record.  Please try"
////						+ " your transaction again.  If the error persists, please go to Help > View Log and send "
////						+ " the entire contents to the system administrator: " + AppPref.SYS_ADMIN);
////					}
////				}
////	});
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
//		Log.debug("executeRemove Called - VwProfileGrant");
//        	// Retrieve record which should be removed.
//        	JavaScriptObject data = request.getData ();
//        	final ListGridRecord rec = new ListGridRecord (data);
//        	final VwProfileGrantBean vwProfileGrantBean = new VwProfileGrantBean();
//        	copyValues (rec, vwProfileGrantBean);
//		final java.util.Date startTime = new java.util.Date();
////		vwProfileGrantService.deleteVwProfileGrantBean(userProfile,vwProfileGrantBean,
////			new AsyncCallback<Boolean>(){
////				public void onFailure(Throwable caught) {
////					masterController.setTransactionResults(
////						"Deleting VwProfileGrant Failed"
////						+ (new java.util.Date().getTime() -startTime.getTime()));
////					masterController.addSysLogMessage("Delete Bean Attempted: " + vwProfileGrantBean);
////                			response.setStatus (RPCResponse.STATUS_FAILURE);
////               				processResponse (requestId, response);
////					if(!ServerExceptionHandler.getInstance().handle(caught)){
////
////					}
////				}
////	
////
////				public void onSuccess(Boolean result) {
////					Log.debug("vwProfileGrantService.deleteVwProfileGrant onSuccess: " + result);
////					if (result){
////						userProfile.incrementSessionTimeOut();
////						masterController.setTransactionResults(
////							"Successfully deleted VwProfileGrant record"
////							+ (new java.util.Date().getTime() - startTime.getTime()));
////						masterController.addSysLogMessage("Bean Deleted" +  vwProfileGrantBean.toString());
////						ListGridRecord[] list = new ListGridRecord[1];
////						// We do not receive removed record from server.
////						// Return record from request.
////						list[0] = rec;
////						response.setData (list);
////						processResponse (requestId, response);
////					}else{
////						masterController.notifyUserOfSystemError("Server Error","There was an error while deleting a "
////						+ "vwProfileGrant record.  This can be caused by someone else changing the record.  Please try"
////						+ " your transaction again.  If the error persists, please go to Help > View Log and send "
////						+ " the entire contents to the system administrator: " + AppPref.SYS_ADMIN);
////					}
////				}
////	});
//
//
//            }
//
///**
//*
//*
//*/
//
//	    public static void copyValues (ListGridRecord from, VwProfileGrantBean to) {
//		to.setClientId(from.getAttributeAsInt("clientId"));
//		to.setSecurityProfileId(from.getAttributeAsInt("securityProfileId"));
//		to.setProfileName(from.getAttributeAsString("profileName"));
//		to.setSecurityPrivilegeId(from.getAttributeAsInt("securityPrivilegeId"));
//		to.setPrivName(from.getAttributeAsString("privName"));
//		to.setLastUpdate(from.getAttributeAsDate("lastUpdate"));
//	    }
//
///**
//*
//*
//*/
//	    public static void copyValues (VwProfileGrantBean from, ListGridRecord to) {
//		to.setAttribute("clientId", from.getClientId());
//		to.setAttribute("securityProfileId", from.getSecurityProfileId());
//		to.setAttribute("profileName", from.getProfileName());
//		to.setAttribute("securityPrivilegeId", from.getSecurityPrivilegeId());
//		to.setAttribute("privName", from.getPrivName());
//		to.setAttribute("lastUpdate", from.getLastUpdate());
//	    }
//	    
//
//
//  
//}