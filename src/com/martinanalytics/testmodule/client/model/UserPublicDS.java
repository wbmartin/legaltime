
package com.martinanalytics.testmodule.client.model;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.martinanalytics.testmodule.client.ServerExceptionHandler;
import com.martinanalytics.testmodule.client.app.AppEventProducer;
import com.martinanalytics.testmodule.client.app.AppPref;
import com.martinanalytics.testmodule.client.app.IApplicationController;
import com.martinanalytics.testmodule.client.model.bean.UserPublicBean;
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
 * UserPublicDataSource owns the data description for user_public * @author bmartin
 *
 */
public class UserPublicDS extends GwtRpcDataSource{


	private static UserPublicDS instance = null; 
	// primary GWT remote Service 
	private final UserPublicServiceAsync userPublicService = GWT.create(UserPublicService.class);
			 		
	private UserProfile userProfile; 
	private IApplicationController masterController;
	private AppEventProducer notifier;
  
    	public static UserPublicDS getInstance(IApplicationController masterController_) {  
          if (instance == null) {  
            instance = new UserPublicDS(masterController_);  //"userPublicDS"
          }  
          return instance;  
    	}  
	public static final String CLIENT_ID="clientId";
	public static final String USER_ID="userId";
	public static final String LAST_UPDATE="lastUpdate";
	public static final String LAST_NAME="lastName";
	public static final String FIRST_NAME="firstName";
	public static final String MIDDLE_NAME="middleName";
	public static final String OFFICE_ADDRESS1="officeAddress1";
	public static final String OFFICE_ADDRESS2="officeAddress2";
	public static final String OFFICE_CITY="officeCity";
	public static final String OFFICE_STATE="officeState";
	public static final String OFFICE_ZIP="officeZip";
	public static final String TITLE="title";
	public static final String SUFFIX="suffix";
	public static final String OFFICE_PHONE="officePhone";
	public static final String FAX="fax";
	public static final String OFFICE_CELL="officeCell";
	public static final String COMMENT="comment";
	public static final String EVT_CACHE_UPDATED ="EvtCacheUpdated";
	public static final String EVT_RECORD_ADDED ="EvtRecordAdded";
	public static final String EVT_RECORD_UPDATED ="EvtRecordUpdated";
	public static final String EVT_RECORD_REMOVED ="EvtRecordRemoved";
	public static final String EVT_SELECT_RETURNED ="EvtSelectReturned";

   	public UserPublicDS(IApplicationController masterController_) { 
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

	  field = new DataSourceField(LAST_UPDATE,FieldType.DATE, "Last Update",50); 
	  field.setHidden(true); 
	  addField(field);

	  field = new DataSourceField(LAST_NAME,FieldType.TEXT, "Last Name",50);
	  addField(field);

	  field = new DataSourceField(FIRST_NAME,FieldType.TEXT, "First Name",50);
	  addField(field);

	  field = new DataSourceField(MIDDLE_NAME,FieldType.TEXT, "Middle Name",50);
	  addField(field);

	  field = new DataSourceField(OFFICE_ADDRESS1,FieldType.TEXT, "Office Address1",50);
	  addField(field);

	  field = new DataSourceField(OFFICE_ADDRESS2,FieldType.TEXT, "Office Address2",50);
	  addField(field);

	  field = new DataSourceField(OFFICE_CITY,FieldType.TEXT, "Office City",50);
	  addField(field);

	  field = new DataSourceField(OFFICE_STATE,FieldType.TEXT, "Office State",50);
	  addField(field);

	  field = new DataSourceField(OFFICE_ZIP,FieldType.TEXT, "Office Zip",50);
	  addField(field);

	  field = new DataSourceField(TITLE,FieldType.TEXT, "Title",50);
	  addField(field);

	  field = new DataSourceField(SUFFIX,FieldType.TEXT, "Suffix",50);
	  addField(field);

	  field = new DataSourceField(OFFICE_PHONE,FieldType.TEXT, "Office Phone",50);
	  addField(field);

	  field = new DataSourceField(FAX,FieldType.TEXT, "Fax",50);
	  addField(field);

	  field = new DataSourceField(OFFICE_CELL,FieldType.TEXT, "Office Cell",50);
	  addField(field);

	  field = new DataSourceField(COMMENT,FieldType.TEXT, "Comment",50);
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
		 Log.debug("executeFetch Called - UserPublic");
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
		   Log.debug("RPC Called - UserPublic");
		   final String whereClause =tempWhereClause;
		   final String orderByClause =tempOrderByClause;
		   final java.util.Date startTime = new java.util.Date();
		   userPublicService.selectUserPublic(userProfile, whereClause, orderByClause, rowLimit, startRow,
			new AsyncCallback<ArrayList<UserPublicBean>>(){
				public void onFailure(Throwable caught) {
					masterController.setTransactionResults(
					  "Retrieving UserPublic Failed ("+ (new java.util.Date().getTime() -startTime.getTime()) + "ms)");
					Log.debug("Where Attempted: " +whereClause + " | Orderby attempted " + orderByClause);
					if(!ServerExceptionHandler.getInstance().handle(caught)){

					}
				}
		
				public void onSuccess(ArrayList<UserPublicBean> userPublicResult) {
				  Log.debug("Select UserPublic received  Where Attempted: " + whereClause + " | Orderby attempted " + orderByClause );
				  masterController.setTransactionResults("Successfully Retrieved UserPublic listing"
							+ (new java.util.Date().getTime() - startTime.getTime()));
 				  ListGridRecord[] list = new ListGridRecord[userPublicResult.size ()];
				  for (int i = 0; i < list.length; i++) {
				 	ListGridRecord record = new ListGridRecord ();
				        copyValues (userPublicResult.get (i), record);
				        list[i] = record;
				   }
				   response.setData (applyClientFilter(list));
				   processResponse (requestId, response);
				   if(getCachePreferred()){
					setCacheData(list);
					notifier.notifyAppEvent(this, EVT_CACHE_UPDATED,EVT_SELECT_RETURNED);	
				   }
				   
				   Log.debug("executeFetch passed - UserPublic");
 				   notifier.notifyAppEvent(this, EVT_SELECT_RETURNED);
				}
		});
	}else{
	     response.setData (applyClientFilter(getCacheData()));//applyClientFilter
             processResponse (requestId, response);
             Log.debug("CacheFilter - UserPublic: " + getCacheData().length+ " Records");
	}

}

/**
*
*
*/
  @Override
  protected void executeAdd (final String requestId, final DSRequest request, final DSResponse response) {

	Log.debug("executeAdd Called - UserPublic");
	JavaScriptObject data = request.getData ();
      	ListGridRecord listGridRecord = new ListGridRecord(data);
        final UserPublicBean userPublicBean = new UserPublicBean();
        copyValues (listGridRecord, userPublicBean);
	final java.util.Date startTime = new java.util.Date();
	userPublicService.insertUserPublicBean(userProfile,userPublicBean,
		new AsyncCallback<UserPublicBean>(){
			public void onFailure(Throwable caught) {
				Log.debug("userPublicService.insertUserPublic Failed: " + caught);
				masterController.setTransactionResults(
					"Adding UserPublic Failed ("
						+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Insert Bean Attempted: " + userPublicBean);
               			response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}	
			}
 	
			public void onSuccess(UserPublicBean result) {
				Log.debug("userPublicService.insertUserPublic onSuccess: " + result);
				if (result.getClientId() !=null && result.getUserId() !=null){
					userProfile.incrementSessionTimeOut();
					masterController.setTransactionResults(
						"Successfully inserted UserPublic record"
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
					+ "userPublic.  This is an unexpected error, please go to Help > View Log and send "
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
	Log.debug("executeUpdate Called - UserPublic");

       	// Retrieve record which should be updated.
       	JavaScriptObject data = request.getData ();
       	ListGridRecord rec = new ListGridRecord (data);
       	// Find grid
       	ListGrid grid = (ListGrid) Canvas.getById (request.getComponentId ());
       	// Get record with old and new values combined
       	int index = grid.getRecordIndex (rec);
       	rec = (ListGridRecord) grid.getEditedRecord (index);
        final UserPublicBean userPublicBean = new UserPublicBean();
        copyValues (rec, userPublicBean);
	final java.util.Date startTime = new java.util.Date();
	userPublicService.updateUserPublicBean(userProfile,userPublicBean,
		new AsyncCallback<UserPublicBean>(){
			public void onFailure(Throwable caught) {
				Log.debug("userPublicService.updateUserPublic Failed: " + caught);
				masterController.setTransactionResults("Updating UserPublic Failed ("
					+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Update Bean Attempted: " + userPublicBean);
               			response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}
			}
			public void onSuccess(UserPublicBean result) {
				Log.debug("userPublicService.updateUserPublic onSuccess: " + result);
				if (result.getClientId() !=null && result.getUserId() !=null){
				  userProfile.incrementSessionTimeOut();
				  masterController.setTransactionResults("Successfully updated UserPublic record"
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
					+ "userPublic record.  This can be caused by someone else changing the record.  Please try"
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
	Log.debug("executeRemove Called - UserPublic");

       	// Retrieve record which should be removed.
       	JavaScriptObject data = request.getData ();
       	final ListGridRecord rec = new ListGridRecord (data);
       	final UserPublicBean userPublicBean = new UserPublicBean();
       	copyValues (rec, userPublicBean);
	final java.util.Date startTime = new java.util.Date();
	userPublicService.deleteUserPublicBean(userProfile,userPublicBean,
		new AsyncCallback<Boolean>(){
			public void onFailure(Throwable caught) {
				masterController.setTransactionResults(	"Deleting UserPublic Failed ("
						+ (new java.util.Date().getTime() -startTime.getTime())+ "ms)");
				Log.info("Delete Bean Attempted: " + userPublicBean);
                		response.setStatus (RPCResponse.STATUS_FAILURE);
               			processResponse (requestId, response);
				if(!ServerExceptionHandler.getInstance().handle(caught)){
				}
			}

			public void onSuccess(Boolean result) {
			  Log.debug("userPublicService.deleteUserPublic onSuccess: " + result);
			  if (result){
				userProfile.incrementSessionTimeOut();
				masterController.setTransactionResults("Successfully deleted UserPublic record"
				  + (new java.util.Date().getTime() - startTime.getTime()));
				Log.info("Bean Deleted" +  userPublicBean.toString());
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
				+ "userPublic record.  This can be caused by someone else changing the record.  Please try"
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

	    public static void copyValues (ListGridRecord from, UserPublicBean to) {
			to.setClientId(from.getAttributeAsInt(CLIENT_ID));
			to.setUserId(from.getAttributeAsString(USER_ID));
			to.setLastUpdate(from.getAttributeAsDate(LAST_UPDATE));
			to.setLastName(from.getAttributeAsString(LAST_NAME));
			to.setFirstName(from.getAttributeAsString(FIRST_NAME));
			to.setMiddleName(from.getAttributeAsString(MIDDLE_NAME));
			to.setOfficeAddress1(from.getAttributeAsString(OFFICE_ADDRESS1));
			to.setOfficeAddress2(from.getAttributeAsString(OFFICE_ADDRESS2));
			to.setOfficeCity(from.getAttributeAsString(OFFICE_CITY));
			to.setOfficeState(from.getAttributeAsString(OFFICE_STATE));
			to.setOfficeZip(from.getAttributeAsString(OFFICE_ZIP));
			to.setTitle(from.getAttributeAsString(TITLE));
			to.setSuffix(from.getAttributeAsString(SUFFIX));
			to.setOfficePhone(from.getAttributeAsString(OFFICE_PHONE));
			to.setFax(from.getAttributeAsString(FAX));
			to.setOfficeCell(from.getAttributeAsString(OFFICE_CELL));
			to.setComment(from.getAttributeAsString(COMMENT));
	    }

/**
*
*
*/
	    public static void copyValues (UserPublicBean from, ListGridRecord to) {
			to.setAttribute(CLIENT_ID, from.getClientId());
			to.setAttribute(USER_ID, from.getUserId());
			to.setAttribute(LAST_UPDATE, from.getLastUpdate());
			to.setAttribute(LAST_NAME, from.getLastName());
			to.setAttribute(FIRST_NAME, from.getFirstName());
			to.setAttribute(MIDDLE_NAME, from.getMiddleName());
			to.setAttribute(OFFICE_ADDRESS1, from.getOfficeAddress1());
			to.setAttribute(OFFICE_ADDRESS2, from.getOfficeAddress2());
			to.setAttribute(OFFICE_CITY, from.getOfficeCity());
			to.setAttribute(OFFICE_STATE, from.getOfficeState());
			to.setAttribute(OFFICE_ZIP, from.getOfficeZip());
			to.setAttribute(TITLE, from.getTitle());
			to.setAttribute(SUFFIX, from.getSuffix());
			to.setAttribute(OFFICE_PHONE, from.getOfficePhone());
			to.setAttribute(FAX, from.getFax());
			to.setAttribute(OFFICE_CELL, from.getOfficeCell());
			to.setAttribute(COMMENT, from.getComment());
	    }

	public AppEventProducer getNotifier() {
			return notifier;
	}
  
}