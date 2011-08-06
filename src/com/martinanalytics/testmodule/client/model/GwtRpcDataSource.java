package com.martinanalytics.testmodule.client.model;



import java.util.ArrayList;
import java.util.Arrays;

import com.allen_sauer.gwt.log.client.Log;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.SortSpecifier;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.FieldType;

/**
 * Data source with ability to communicate with server by GWT RPC.<p/>
 * SmartClient natively supports data protocol "clientCustom". This protocol
 * means that communication with server should be implemented in
 * <code>transformRequest (DSRequest request)</code> method. Here is a few
 * things to note on <code>transformRequest</code> implementation:
 * <ul>
 * <li><code>DSResponse</code> object has to be created and
 * <code>processResponse (requestId, response)</code> must be called to finish
 * data request. <code>requestId</code> should be taken from original
 * <code>DSRequest.getRequestId ()</code>.</li>
 * <li>"clientContext" attribute from <code>DSRequest</code> should be copied to
 * <code>DSResponse</code>.</li>
 * <li>In case of failure <code>DSResponse</code> should contain at least "status"
 * attribute with error code (&lt;0).</li>
 * <li>In case of success <code>DSResponse</code> should contain at least "data"
 * attribute with operation type specific data:
 *  <ul>
 *      <li>FETCH - <code>ListGridRecord[]</code> retrieved records.</li>
 *      <li>ADD - <code>ListGridRecord[]</code> with single added record.
 *          Operation is called on every newly added record.</li>
 *      <li>UPDATE - <code>ListGridRecord[]</code> with single updated record.
 *          Operation is called on every updated record.</li>
 *      <li>REMOVE - <code>ListGridRecord[]</code> with single removed record.
 *          Operation is called on every removed record.</li>
 *  </ul>
 * </li>
 * </ul>
 *
 * @author Aleksandras Novikovas
 * @author System Tier
 * @version 1.0
 */
public abstract class GwtRpcDataSource
    extends DataSource {
	private java.util.Date lastCacheRefresh;
	private boolean cachePreferred;
	private ArrayList<Record> clientCache;
    private int clientFilterStartRow;
    private int clientFilterPageLength;
    private Criteria clientFilterCriteria;
    private SortSpecifier[] clientSortBy;
    private boolean positiveFilterMatch;

    /**
     * Creates new data source which communicates with server by GWT RPC.
     * It is normal server side SmartClient data source with data protocol
     * set to <code>DSProtocol.CLIENTCUSTOM</code> ("clientCustom" - natively
     * supported by SmartClient but should be added to smartGWT) and with data
     * format <code>DSDataFormat.CUSTOM</code>.
     */
    public GwtRpcDataSource () {
        setDataProtocol (DSProtocol.CLIENTCUSTOM);
        setDataFormat (DSDataFormat.CUSTOM);
        setClientOnly (false);
       lastCacheRefresh = new java.util.Date();
       lastCacheRefresh.setTime(0);
       cachePreferred = false;
       positiveFilterMatch=true;
    }

    /**
     * Executes request to server.
     *
     * @param request <code>DSRequest</code> being processed.
     * @return <code>Object</code> data from original request.
     */
    @Override
    protected Object transformRequest (DSRequest request) {
        String requestId = request.getRequestId ();
        DSResponse response = new DSResponse ();
        response.setAttribute ("clientContext", request.getAttributeAsObject ("clientContext"));
        // Asume success
        response.setStatus (0);
        switch (request.getOperationType ()) {
            case FETCH:
                executeFetch (requestId, request, response);
                break;
            case ADD:
                executeAdd (requestId, request, response);
                break;
            case UPDATE:
                executeUpdate (requestId, request, response);
                break;
            case REMOVE:
                executeRemove (requestId, request, response);
                break;
            default:
                // Operation not implemented.
                break;
        }
        return request.getData ();
    }

    /**
     * Executed on <code>FETCH</code> operation. <code>processResponse (requestId, response)</code>
     * should be called when operation completes (either successful or failure).
     *
     * @param requestId <code>String</code> extracted from <code>DSRequest.getRequestId ()</code>.
     * @param request <code>DSRequest</code> being processed.
     * @param response <code>DSResponse</code>. <code>setData (list)</code> should be called on
     *      successful execution of this method. <code>setStatus (&lt;0)</code> should be called
     *      on failure.
     */
    protected abstract void executeFetch (String requestId, DSRequest request, DSResponse response);

    /**
     * Executed on <code>ADD</code> operation. <code>processResponse (requestId, response)</code>
     * should be called when operation completes (either successful or failure).
     *
     * @param requestId <code>String</code> extracted from <code>DSRequest.getRequestId ()</code>.
     * @param request <code>DSRequest</code> being processed. <code>request.getData ()</code>
     *      contains record should be added.
     * @param response <code>DSResponse</code>. <code>setData (list)</code> should be called on
     *      successful execution of this method. Array should contain single element representing
     *      added row. <code>setStatus (&lt;0)</code> should be called on failure.
     */
    protected abstract void executeAdd (String requestId, DSRequest request, DSResponse response);

    /**
     * Executed on <code>UPDATE</code> operation. <code>processResponse (requestId, response)</code>
     * should be called when operation completes (either successful or failure).
     *
     * @param requestId <code>String</code> extracted from <code>DSRequest.getRequestId ()</code>.
     * @param request <code>DSRequest</code> being processed. <code>request.getData ()</code>
     *      contains record should be updated.
     * @param response <code>DSResponse</code>. <code>setData (list)</code> should be called on
     *      successful execution of this method. Array should contain single element representing
     *      updated row. <code>setStatus (&lt;0)</code> should be called on failure.
     */
    protected abstract void executeUpdate (String requestId, DSRequest request, DSResponse response);

    /**
     * Executed on <code>REMOVE</code> operation. <code>processResponse (requestId, response)</code>
     * should be called when operation completes (either successful or failure).
     *
     * @param requestId <code>String</code> extracted from <code>DSRequest.getRequestId ()</code>.
     * @param request <code>DSRequest</code> being processed. <code>request.getData ()</code>
     *      contains record should be removed.
     * @param response <code>DSResponse</code>. <code>setData (list)</code> should be called on
     *      successful execution of this method. Array should contain single element representing
     *      removed row. <code>setStatus (&lt;0)</code> should be called on failure.
     */
    protected abstract void executeRemove (String requestId, DSRequest request, DSResponse response);
    
    public boolean isCacheValid(){
    	boolean result;
    	//Log.debug ("Cache expire: " + lastCacheRefresh.getTime() + getCacheMaxAge()*(1000));
    	//Log.debug("Now: " + new java.util.Date().getTime());
    	if(!cachePreferred){result= false;}
    	else if((lastCacheRefresh.getTime() + getCacheMaxAge()*(1000)) >new java.util.Date().getTime() ){
    		result = true;
    	}else{
    		result =false;
    	}
    	
    	return result;
    }
    @Override
    public void invalidateCache(){
    	lastCacheRefresh.setTime(0);
    }
    @Override
    public void setCacheAllData(Boolean cache_){
    	cachePreferred = cache_;
    }
    @Override
    public void setCacheData(Record... cacheData){
    	lastCacheRefresh = new java.util.Date();
    	clientCache =new ArrayList<Record>(Arrays.asList(cacheData));
    	
    	
    }
    @Override
    public Record[] getCacheData(){
    	return clientCache.toArray(new Record[0]);
	
    }
    public ArrayList<Record> getCacheList(){
    	return clientCache;
    }
    public boolean getCachePreferred(){
    	return cachePreferred;
    }
    public void setCachePreferred(boolean prefer_){
    	cachePreferred = prefer_;
    }





    public Record[] applyClientFilter(Record[] startList_){

        int recordCount=0;
    	Record[] finalList ;
    	boolean match=true;
    	recordCount = startList_.length;
    	int criteriaCount=0;
    	int subCriteriaCount=0;
    	String attribInQuestion="";
    	java.util.Date applyClientFilterStart = new java.util.Date();
    	ArrayList<Record> trialList = new ArrayList<Record>();
    	String criteriaAttribute;
    	if (clientFilterCriteria != null && clientFilterCriteria.getAttributes() !=null ) {
    		criteriaCount = clientFilterCriteria.getAttributes().length;
    		String[] criteriaList ;
    		for(int recordNdx = 0; recordNdx < recordCount; recordNdx++){
    			match=true;
    			for(int criteriaNdx = 0; criteriaNdx<criteriaCount; criteriaNdx++){
    				attribInQuestion =clientFilterCriteria.getAttributes()[criteriaNdx];
    				if (attribInQuestion.equals("__gwt_ObjectId")) continue;
    				criteriaList =clientFilterCriteria.getAttribute(attribInQuestion).split(",");
    				subCriteriaCount =  criteriaList.length;
    				for(int ndx =0; ndx<subCriteriaCount;ndx++){
    					 criteriaAttribute= criteriaList[ndx];
    					 if(positiveFilterMatch &&!startList_[recordNdx].getAttribute(attribInQuestion).equals(criteriaAttribute)){
    						 match = false;	break;		
    					 }else if(!positiveFilterMatch && startList_[recordNdx].getAttribute(attribInQuestion).equals(criteriaAttribute)){
    						 match = false; break;
    					 }
    				 }
    				
    				
    				
    				
    				if (!match){break;}
    			}
    			if(match)trialList.add(startList_[recordNdx]);
    			
    		}
            
        }
    	java.util.Date applyClientFilterAfterFilter = new java.util.Date();
        // If there is sorting
//        if (f.getSort() != null) {
//            Collections.sort(list, 
//                new SmartComparator<TownRecord>(TownRecord.class, f.getSort()));
//        }
    	//Log.debug("Triallist size before conversion" + trialList.size());


    	int startRow=0;
    	int endRow=0;
    	if (clientFilterStartRow >0){
    		startRow = Math.min(clientFilterStartRow, trialList.size());
    	}else{
    		startRow =0;
    	}
    	if (clientFilterPageLength >0){
    		if (clientFilterStartRow ==-1)clientFilterStartRow=0;
    		endRow=Math.min((clientFilterStartRow+clientFilterPageLength), trialList.size());
    	}else{
    		endRow =trialList.size();
    	}
    	java.util.Date applyClientFilterAfterRowCrops = new java.util.Date();
    	
    		
        
        finalList = (new ArrayList<Record>(trialList.subList(startRow, endRow))).toArray(new Record[0]);
        
        //Log.debug("finalList Size before Return" + finalList.length);
        java.util.Date applyClientFilterEnd = new java.util.Date();
        Log.debug("ApplyClientFilter took: " + (applyClientFilterEnd.getTime() - applyClientFilterStart.getTime()) 
        		+ " to filter from "+startList_.length + " to " + finalList.length
        		+" just start to filter: " + (applyClientFilterAfterFilter.getTime() -applyClientFilterStart.getTime())
        		+" just start to rowCrops: " + (applyClientFilterAfterRowCrops.getTime() -applyClientFilterStart.getTime()));
    	
    	return finalList;
    }


    /**
     * @param clientFilterStartRow the clientFilterStartRow to set
     */
    public void setClientFilterStartRow(int clientFilterStartRow) {
    	this.clientFilterStartRow = clientFilterStartRow;
    }


    /**
     * @return the clientFilterStartRow
     */
    public int getClientFilterStartRow() {
    	return clientFilterStartRow;
    }


    /**
     * @param clientFilterPageLength the clientFilterPageLength to set
     */
    public void setClientFilterPageLength(int clientFilterPageLength) {
    	this.clientFilterPageLength = clientFilterPageLength;
    }


    /**
     * @return the clientFilterPageLength
     */
    public int getClientFilterPageLength() {
    	return clientFilterPageLength;
    }
    public void setClientFilterCriteria( Criteria criteria_){
    	clientFilterCriteria = criteria_;
    }
	public void setClientSortBy ( SortSpecifier[] sortSpecifier_){
		clientSortBy = sortSpecifier_;
	}

	public void setPositiveFilterMatch(boolean positiveFilterMatch) {
		this.positiveFilterMatch = positiveFilterMatch;
	}

	public boolean isPositiveFilterMatch() {
		return positiveFilterMatch;
	}

	


    
}
