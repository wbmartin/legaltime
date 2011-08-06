
package com.martinanalytics.testmodule.client.model;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.martinanalytics.testmodule.client.ServerExceptionHandler;
import com.martinanalytics.testmodule.client.app.AppPref;
import com.martinanalytics.testmodule.client.app.IApplicationController;
import com.martinanalytics.testmodule.client.model.bean.VwUserGrantBean;
import com.martinanalytics.testmodule.client.model.bean.UserProfile;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
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
 * VwUserGrantDataSource owns the data description for vw_user_grant * @author bmartin
 *
 */
public class VwUserGrantDS extends GwtRpcDataSource{


	private static VwUserGrantDS instance = null; 
	// primary GWT remote Service 
	private final VwUserGrantServiceAsync vwUserGrantService = GWT.create(VwUserGrantService.class);
			 		
	private UserProfile userProfile; 
	private IApplicationController masterController;
  
    	public static VwUserGrantDS getInstance(IApplicationController masterController_) {  
          if (instance == null) {  
            instance = new VwUserGrantDS(masterController_);  //"vwUserGrantDS"
          }  
          return instance;  
    	}  


   	public VwUserGrantDS(IApplicationController masterController_) { 
	  userProfile = UserProfile.getInstance();
	  masterController = masterController_; 
	  DataSourceField field;
		
	  field = new DataSourceField("userId",FieldType.TEXT, "User Id",50);
	  addField(field);

	  field = new DataSourceField("clientId",FieldType.INTEGER, "Client Id",50);
	  addField(field);

	  field = new DataSourceField("profileName",FieldType.TEXT, "Profile Name",50);
	  addField(field);

	  field = new DataSourceField("privName",FieldType.TEXT, "Priv Name",50);
	  addField(field);

}

/**
 * Provides a standard template to retrieve beans from the server.  
 * The results are handled through the onSuccess method in the AsynchCallback.
 * this function also uses the userProfile Singleton to send authorization credentials.
 * @param whereClause_  a string beginning with "where" using standard sql syntax appropriate for the table to filter the beans
 * @param orderByClause a string beginning with "order by" using standard sql syntax appropriate alter the order of the beans
 */
protected void executeFetch (final String requestId, final DSRequest request, final DSResponse response) {
	        // These can be used as parameters to create paging.
		//request.getStartRow ();
		//request.getEndRow ();
		//request.getSortBy ();
		 final String whereClause_ ="";
		 final String orderByClause_ ="";
		 Log.debug("executeFetch Called - VwUserGrant");
		Integer startRow = request.getStartRow ();
		Integer rowLimit=(request.getEndRow() ==null || request.getStartRow () ==null)?-1: request.getEndRow() - request.getStartRow ();
		startRow=(startRow == null)?-1: startRow;
		 
		final java.util.Date startTime = new java.util.Date();
		vwUserGrantService.selectVwUserGrant(userProfile, whereClause_, orderByClause_, rowLimit, startRow,
				new AsyncCallback<ArrayList<VwUserGrantBean>>(){
					public void onFailure(Throwable caught) {
						Log.debug("Retrieving VwUserGrant Failed");
						masterController.setTransactionResults(
							"Retrieving VwUserGrant Failed"
							+ (new java.util.Date().getTime() -startTime.getTime()));
						masterController.addSysLogMessage("Where Attempted: " +whereClause_ + " | Orderby attempted " + orderByClause_ );
						if(!ServerExceptionHandler.getInstance().handle(caught)){

						}
						


					}
		
					public void onSuccess(ArrayList<VwUserGrantBean> vwUserGrantResult) {
						masterController.addSysLogMessage("Select VwUserGrant received ok-  Where Attempted: " 
							+ whereClause_ + " | Orderby attempted " + orderByClause_ );
						masterController.setTransactionResults(
							"Successfully Retrieved VwUserGrant listing (" + vwUserGrantResult.size ()+ ") "+ 
							+ (new java.util.Date().getTime() - startTime.getTime()));
 						ListGridRecord[] list = new ListGridRecord[vwUserGrantResult.size ()];
				                for (int i = 0; i < list.length; i++) {
				                    ListGridRecord record = new ListGridRecord ();
				                    copyValues (vwUserGrantResult.get (i), record);
				                    list[i] = record;
				                    
				                }
				                response.setData (list);
				                processResponse (requestId, response);
				                Log.debug("executeFetch passed - VwUserGrant");
						
					}
		});

}

/**
*
*
*/
	    @Override
	    protected void executeAdd (final String requestId, final DSRequest request, final DSResponse response) {
		Log.debug("executeAdd Called - VwUserGrant");
		JavaScriptObject data = request.getData ();
      		ListGridRecord listGridRecord = new ListGridRecord(data);
        	final VwUserGrantBean vwUserGrantBean = new VwUserGrantBean();
        	copyValues (listGridRecord, vwUserGrantBean);
		final java.util.Date startTime = new java.util.Date();
//		vwUserGrantService.insertVwUserGrantBean(userProfile,vwUserGrantBean,
//			new AsyncCallback<VwUserGrantBean>(){
//				public void onFailure(Throwable caught) {
//					Log.debug("vwUserGrantService.insertVwUserGrant Failed: " + caught);
//					masterController.setTransactionResults(
//						"Adding VwUserGrant Failed"
//						+ (new java.util.Date().getTime() -startTime.getTime()));
//					masterController.addSysLogMessage("Insert Bean Attempted: " + vwUserGrantBean);
//                			response.setStatus (RPCResponse.STATUS_FAILURE);
//                			processResponse (requestId, response);
//					if(!ServerExceptionHandler.getInstance().handle(caught)){
//
//					}	
//				}
// 
//	
//				public void onSuccess(VwUserGrantBean result) {
//					Log.debug("vwUserGrantService.insertVwUserGrant onSuccess: " + result);
//					if (){
//						userProfile.incrementSessionTimeOut();
//						masterController.setTransactionResults(
//							"Successfully inserted VwUserGrant record"
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
//						+ "vwUserGrant.  This is an unexpected error, please go to Help > View Log and send "
//						+ " the entire contents to the system administrator: " + AppPref.SYS_ADMIN);
//					}
//				}
//	});
	    }
/**
*
*
*/
	    @Override
	    protected void executeUpdate (final String requestId, final DSRequest request, final DSResponse response) {
		Log.debug("executeUpdate Called - VwUserGrant");
        	// Retrieve record which should be updated.
        	JavaScriptObject data = request.getData ();
        	ListGridRecord rec = new ListGridRecord (data);
        	// Find grid
        	ListGrid grid = (ListGrid) Canvas.getById (request.getComponentId ());
        	// Get record with old and new values combined
        	int index = grid.getRecordIndex (rec);
        	rec = (ListGridRecord) grid.getEditedRecord (index);
        	final VwUserGrantBean vwUserGrantBean = new VwUserGrantBean();
        	copyValues (rec, vwUserGrantBean);
		final java.util.Date startTime = new java.util.Date();
//		vwUserGrantService.updateVwUserGrantBean(userProfile,vwUserGrantBean,
//			new AsyncCallback<VwUserGrantBean>(){
//				public void onFailure(Throwable caught) {
//					Log.debug("vwUserGrantService.updateVwUserGrant Failed: " + caught);
//					masterController.setTransactionResults(
//						"Updating VwUserGrant Failed"
//						+ (new java.util.Date().getTime() -startTime.getTime()));
//					masterController.addSysLogMessage("Update Bean Attempted: " + vwUserGrantBean);
//                			response.setStatus (RPCResponse.STATUS_FAILURE);
//                			processResponse (requestId, response);
//					if(!ServerExceptionHandler.getInstance().handle(caught)){
//
//					}
//				}
//	
//				public void onSuccess(VwUserGrantBean result) {
//					Log.debug("vwUserGrantService.updateVwUserGrant onSuccess: " + result);
//					if (){
//						userProfile.incrementSessionTimeOut();
//						masterController.setTransactionResults(
//							"Successfully updated VwUserGrant record"
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
//						+ "vwUserGrant record.  This can be caused by someone else changing the record.  Please try"
//						+ " your transaction again.  If the error persists, please go to Help > View Log and send "
//						+ " the entire contents to the system administrator: " + AppPref.SYS_ADMIN);
//					}
//				}
//	});
	    }

/**
*
*
*/
	    

	    @Override
	    protected void executeRemove (final String requestId, final DSRequest request, final DSResponse response) {
		Log.debug("executeRemove Called - VwUserGrant");
        	// Retrieve record which should be removed.
        	JavaScriptObject data = request.getData ();
        	final ListGridRecord rec = new ListGridRecord (data);
        	final VwUserGrantBean vwUserGrantBean = new VwUserGrantBean();
        	copyValues (rec, vwUserGrantBean);
		final java.util.Date startTime = new java.util.Date();
//		vwUserGrantService.deleteVwUserGrantBean(userProfile,vwUserGrantBean,
//			new AsyncCallback<Boolean>(){
//				public void onFailure(Throwable caught) {
//					masterController.setTransactionResults(
//						"Deleting VwUserGrant Failed"
//						+ (new java.util.Date().getTime() -startTime.getTime()));
//					masterController.addSysLogMessage("Delete Bean Attempted: " + vwUserGrantBean);
//                			response.setStatus (RPCResponse.STATUS_FAILURE);
//               				processResponse (requestId, response);
//					if(!ServerExceptionHandler.getInstance().handle(caught)){
//
//					}
//				}
//	
//
//				public void onSuccess(Boolean result) {
//					Log.debug("vwUserGrantService.deleteVwUserGrant onSuccess: " + result);
//					if (result){
//						userProfile.incrementSessionTimeOut();
//						masterController.setTransactionResults(
//							"Successfully deleted VwUserGrant record"
//							+ (new java.util.Date().getTime() - startTime.getTime()));
//						masterController.addSysLogMessage("Bean Deleted" +  vwUserGrantBean.toString());
//						ListGridRecord[] list = new ListGridRecord[1];
//						// We do not receive removed record from server.
//						// Return record from request.
//						list[0] = rec;
//						response.setData (list);
//						processResponse (requestId, response);
//					}else{
//						masterController.notifyUserOfSystemError("Server Error","There was an error while deleting a "
//						+ "vwUserGrant record.  This can be caused by someone else changing the record.  Please try"
//						+ " your transaction again.  If the error persists, please go to Help > View Log and send "
//						+ " the entire contents to the system administrator: " + AppPref.SYS_ADMIN);
//					}
//				}
//	});


            }

/**
*
*
*/

	    private static void copyValues (ListGridRecord from, VwUserGrantBean to) {
		to.setUserId(from.getAttributeAsString("userId"));
		to.setClientId(from.getAttributeAsInt("clientId"));
		to.setProfileName(from.getAttributeAsString("profileName"));
		to.setPrivName(from.getAttributeAsString("privName"));
	    }

/**
*
*
*/
	    private static void copyValues (VwUserGrantBean from, ListGridRecord to) {
		to.setAttribute("userId", from.getUserId());
		to.setAttribute("clientId", from.getClientId());
		to.setAttribute("profileName", from.getProfileName());
		to.setAttribute("privName", from.getPrivName());
	    }


  
}