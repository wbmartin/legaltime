
package com.martinanalytics.testmodule.client.model;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.martinanalytics.testmodule.client.ServerExceptionHandler;
import com.martinanalytics.testmodule.client.app.AppPref;
import com.martinanalytics.testmodule.client.app.IApplicationController;
import com.martinanalytics.testmodule.client.model.bean.SecurityProfileBean;
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
 * SecurityProfileDataSource owns the data description for security_profile * @author bmartin
 *
 */
public class SecurityProfileDS extends GwtRpcDataSource{


	private static SecurityProfileDS instance = null; 
	// primary GWT remote Service 
	private final SecurityProfileServiceAsync securityProfileService = GWT.create(SecurityProfileService.class);
			 		
	private UserProfile userProfile; 
	private IApplicationController masterController;
	
	public static final String CLIENT_ID="clientId";
	public static final String SECURITY_PROFILE_ID="securityProfileId";
	public static final String PROFILE_NAME="profileName";
	public static final String LAST_UPDATE="lastUpdate";
  
    	public static SecurityProfileDS getInstance(IApplicationController masterController_) {  
          if (instance == null) {  
            instance = new SecurityProfileDS(masterController_);  //"securityProfileDS"
          }  
          return instance;  
    	}  


   	public SecurityProfileDS(IApplicationController masterController_) { 
	  userProfile = UserProfile.getInstance();
	  masterController = masterController_; 
	  DataSourceField field;
		
	  field = new DataSourceField("clientId",FieldType.INTEGER, "Client Id",50); 
	  field.setHidden(true); 
	  field.setPrimaryKey(true);
	  addField(field);

	  field = new DataSourceField("securityProfileId",FieldType.INTEGER, "Security Profile Id",50); 
	  field.setHidden(true); 
	  field.setPrimaryKey(true);
	  addField(field);

	  field = new DataSourceField("profileName",FieldType.TEXT, "Profile Name",50);
	  addField(field);

	  field = new DataSourceField("lastUpdate",FieldType.DATE, "Last Update",50); 
	  field.setHidden(true); 
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
		 Log.debug("executeFetch Called - SecurityProfile");
		int rowLimit = request.getEndRow() - request.getStartRow ();
		 
		final java.util.Date startTime = new java.util.Date();
		securityProfileService.selectSecurityProfile(userProfile, whereClause_, orderByClause_, rowLimit, request.getStartRow (),
				new AsyncCallback<ArrayList<SecurityProfileBean>>(){
					public void onFailure(Throwable caught) {
						masterController.setTransactionResults(
							"Retrieving SecurityProfile Failed"
							+ (new java.util.Date().getTime() -startTime.getTime()));
						masterController.addSysLogMessage("Where Attempted: " +whereClause_ + " | Orderby attempted " + orderByClause_ );
						if(!ServerExceptionHandler.getInstance().handle(caught)){

						}
						


					}
		
					public void onSuccess(ArrayList<SecurityProfileBean> securityProfileResult) {
						masterController.addSysLogMessage("Select SecurityProfile received ok-  Where Attempted: " 
							+ whereClause_ + " | Orderby attempted " + orderByClause_ );
						masterController.setTransactionResults(
							"Successfully Retrieved SecurityProfile listing"
							+ (new java.util.Date().getTime() - startTime.getTime()));
 						ListGridRecord[] list = new ListGridRecord[securityProfileResult.size ()];
				                for (int i = 0; i < list.length; i++) {
				                    ListGridRecord record = new ListGridRecord ();
				                    copyValues (securityProfileResult.get (i), record);
				                    list[i] = record;
				                    
				                }
				                response.setData (list);
				                processResponse (requestId, response);
				                Log.debug("executeFetch passed - SecurityProfile");
						
					}
		});

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
					masterController.setTransactionResults(
						"Adding SecurityProfile Failed"
						+ (new java.util.Date().getTime() -startTime.getTime()));
					masterController.addSysLogMessage("Insert Bean Attempted: " + securityProfileBean);
                			response.setStatus (RPCResponse.STATUS_FAILURE);
                			processResponse (requestId, response);
					if(!ServerExceptionHandler.getInstance().handle(caught)){

					}	
				}
 
	
				public void onSuccess(SecurityProfileBean result) {
					Log.debug("securityProfileService.insertSecurityProfile onSuccess: " + result);
					if (result.getClientId() !=null && result.getSecurityProfileId() !=null){
						userProfile.incrementSessionTimeOut();
						masterController.setTransactionResults(
							"Successfully inserted SecurityProfile record"
							+ (new java.util.Date().getTime() - startTime.getTime()));
						masterController.addSysLogMessage("Bean Added" + result.toString());
						ListGridRecord[] listGridRecordArray = new ListGridRecord[1];
						ListGridRecord listGridRecord = new ListGridRecord ();
						copyValues (result, listGridRecord);
						listGridRecordArray[0] = listGridRecord;
						response.setData (listGridRecordArray);
						processResponse (requestId, response);
			
					}else{
						masterController.notifyUserOfSystemError("Server Error","There was an error while adding the "
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
					masterController.setTransactionResults(
						"Updating SecurityProfile Failed"
						+ (new java.util.Date().getTime() -startTime.getTime()));
					masterController.addSysLogMessage("Update Bean Attempted: " + securityProfileBean);
                			response.setStatus (RPCResponse.STATUS_FAILURE);
                			processResponse (requestId, response);
					if(!ServerExceptionHandler.getInstance().handle(caught)){

					}
				}
	
				public void onSuccess(SecurityProfileBean result) {
					Log.debug("securityProfileService.updateSecurityProfile onSuccess: " + result);
					if (result.getClientId() !=null && result.getSecurityProfileId() !=null){
						userProfile.incrementSessionTimeOut();
						masterController.setTransactionResults(
							"Successfully updated SecurityProfile record"
							+ (new java.util.Date().getTime() - startTime.getTime()));
						masterController.addSysLogMessage("Bean Updated" + result.toString());
						ListGridRecord[] listGridRecordArray = new ListGridRecord[1];
						ListGridRecord listGridRecord = new ListGridRecord ();
						copyValues (result, listGridRecord);
						listGridRecordArray[0] = listGridRecord;
						response.setData (listGridRecordArray);
						processResponse (requestId, response);
					}else{
						masterController.notifyUserOfSystemError("Server Error","There was an error while updating a "
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
					masterController.setTransactionResults(
						"Deleting SecurityProfile Failed"
						+ (new java.util.Date().getTime() -startTime.getTime()));
					masterController.addSysLogMessage("Delete Bean Attempted: " + securityProfileBean);
                			response.setStatus (RPCResponse.STATUS_FAILURE);
               				processResponse (requestId, response);
					if(!ServerExceptionHandler.getInstance().handle(caught)){

					}
				}
	

				public void onSuccess(Boolean result) {
					Log.debug("securityProfileService.deleteSecurityProfile onSuccess: " + result);
					if (result){
						userProfile.incrementSessionTimeOut();
						masterController.setTransactionResults(
							"Successfully deleted SecurityProfile record"
							+ (new java.util.Date().getTime() - startTime.getTime()));
						masterController.addSysLogMessage("Bean Deleted" +  securityProfileBean.toString());
						ListGridRecord[] list = new ListGridRecord[1];
						// We do not receive removed record from server.
						// Return record from request.
						list[0] = rec;
						response.setData (list);
						processResponse (requestId, response);
					}else{
						masterController.notifyUserOfSystemError("Server Error","There was an error while deleting a "
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

	    private static void copyValues (ListGridRecord from, SecurityProfileBean to) {
		to.setClientId(from.getAttributeAsInt("clientId"));
		to.setSecurityProfileId(from.getAttributeAsInt("securityProfileId"));
		to.setProfileName(from.getAttributeAsString("profileName"));
		to.setLastUpdate(from.getAttributeAsDate("lastUpdate"));	    }

/**
*
*
*/
	    private static void copyValues (SecurityProfileBean from, ListGridRecord to) {
		to.setAttribute("clientId", from.getClientId());
		to.setAttribute("securityProfileId", from.getSecurityProfileId());
		to.setAttribute("profileName", from.getProfileName());
		to.setAttribute("lastUpdate", from.getLastUpdate());
	    }


  
}