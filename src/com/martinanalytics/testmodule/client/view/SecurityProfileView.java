package com.martinanalytics.testmodule.client.view;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import com.martinanalytics.testmodule.client.app.AppMsg;
import com.martinanalytics.testmodule.client.app.AppNotifyObject;
import com.martinanalytics.testmodule.client.app.icons.AppClientBundle;
import com.martinanalytics.testmodule.client.model.SecurityPrivilegeDS;
import com.martinanalytics.testmodule.client.model.SecurityProfileDS;
import com.martinanalytics.testmodule.client.model.SecurityProfileGrantDS;
import com.martinanalytics.testmodule.client.model.VwProfileGrantDS;
import com.martinanalytics.testmodule.client.model.VwUserGrantDS;
import com.martinanalytics.testmodule.client.model.bean.SecurityPrivilegeBean;
import com.martinanalytics.testmodule.client.model.bean.SecurityProfileGrantBean;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.ResultSet;

import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.allen_sauer.gwt.log.client.Log;

import com.google.gwt.user.client.ui.Label;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.grid.events.RecordDropEvent;
import com.smartgwt.client.widgets.grid.events.RecordDropHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.IButton;

public class SecurityProfileView extends Window  {
	
	private ListGrid profileListGrid;
	private ListGrid securityGrantsListGrid;
	private ListGrid availableGrantsListGrid;
	private AppNotifyObject notifier;
	private ResultSet profileGrantsRS;
	private HashMap<Integer, String> grantDescriptions = null;
	private IButton btnDeleteGroup;
	private IButton btnAddGrant;
	private IButton btnRemoveGrant;
	private IButton btnFilterAvailableGrants;
	private IButton btnAddGroup;
	private final DynamicForm form;
	
	public SecurityProfileView(SecurityProfileDS securityProfileDS_, SecurityProfileGrantDS vwProfileGrantDS_, SecurityPrivilegeDS securityPrivilegeDS_){//SecurityProfileGrantDS securityProfileGrantDS_) {
		notifier = new AppNotifyObject();
		hide();
		form = new DynamicForm(); 
		vwProfileGrantDS_.setCacheMaxAge(100000);
		vwProfileGrantDS_.setCachePreferred(true);
		
//ListGrid	Config		
		profileListGrid = new ListGrid();
		profileListGrid.setShowAllRecords(true);
		profileListGrid.setDataSource(securityProfileDS_);
		profileListGrid.setCanEdit(true);
		profileListGrid.setAutoFetchData(false);
		profileListGrid.setCanAcceptDroppedRecords(false);
		profileListGrid.setCanDragRecordsOut(false);
		profileListGrid.addSelectionChangedHandler(new SelectionChangedHandler(){
			@Override
			public void onSelectionChanged(SelectionEvent event) {
				if(profileListGrid.getSelectedRecord() !=null && profileListGrid.getSelectedRecord().getAttribute("securityProfileId") != null){
				disable();
				  Log.debug("SecurityProfileID for selection Changed:" + profileListGrid.getSelectedRecord().getAttribute("securityProfileId"));
				  //securityGrantsListGrid.setCriteria(new Criteria("securityProfileId", listGrid.getSelectedRecord().getAttribute("securityProfileId")));
				  //securityGrantsListGrid.setData(profileGrantsRS);
				  securityGrantsListGrid.filterData(new Criteria("securityProfileId", profileListGrid.getSelectedRecord().getAttribute("securityProfileId")));
				  
				  //securityGrantsListGrid.fetchData();
				}
				
			}
			
		});
	
//securityGrantsListGrid Config		
		securityGrantsListGrid = new ListGrid();
		securityGrantsListGrid.setShowAllColumns(true);
		securityGrantsListGrid.setDataSource(vwProfileGrantDS_);
		securityGrantsListGrid.setAutoFetchData(false);
		securityGrantsListGrid.setDataPageSize(1000);
		securityGrantsListGrid.setCanAcceptDroppedRecords(true);
		securityGrantsListGrid.setCanDragRecordsOut(true);
		securityGrantsListGrid.addRecordDropHandler(new RecordDropHandler(){
			@Override
			public void onRecordDrop(RecordDropEvent event) {
				ListGridRecord[] dropRecords = event.getDropRecords();				
				for(int ndx = 0;ndx<dropRecords.length; ndx++){
					notifier.notifyAppEvent(this,
							AppMsg.ADD_SECURITY_PROFILE_GRANT,
							dropRecords[ndx], 
							profileListGrid.getSelectedRecord().getAttribute(SecurityProfileDS.SECURITY_PROFILE_ID)
							,null,null);
				}
				
				
			}
			
		});
		ListGridField grantName = new ListGridField("grantName", "Name");
		grantName.setWidth(500);
		securityGrantsListGrid.setFields(grantName);
		grantName.setCellFormatter(new CellFormatter(){
			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				
				return grantDescriptions.get(record.getAttributeAsInt(SecurityPrivilegeDS.SECURITY_PRIVILEGE_ID));
			}
			
		});
		securityGrantsListGrid.addDataArrivedHandler(new DataArrivedHandler(){
			@Override
			public void onDataArrived(DataArrivedEvent event) {
				notifier.notifyAppEvent(this, AppMsg.SECURITY_GRANTS_FILTERED);		
			}	
		});

		
		
//available grants list grid config		
		availableGrantsListGrid = new ListGrid();
		availableGrantsListGrid.setShowAllColumns(false);		
		availableGrantsListGrid.setAutoFetchData(false);
		availableGrantsListGrid.setDataSource(securityPrivilegeDS_);
		availableGrantsListGrid.setCanAcceptDroppedRecords(true);
		availableGrantsListGrid.setCanDragRecordsOut(true);
		securityPrivilegeDS_.setCachePreferred(true);
		//ListGridField grantName = new ListGridField("grantName", "Name");
		availableGrantsListGrid.setFields(grantName);
//		grantName.setCellFormatter(new CellFormatter(){
//			@Override
//			public String format(Object value, ListGridRecord record,
//					int rowNum, int colNum) {
//				
//				return grantDescriptions.get(record.getAttributeAsInt(SecurityPrivilegeDS.SECURITY_PRIVILEGE_ID));
//			}
//			
//		});
		availableGrantsListGrid.addRecordDropHandler(new RecordDropHandler(){
			@Override
			public void onRecordDrop(RecordDropEvent event) {
				ListGridRecord[] dropRecords = event.getDropRecords();				
				for(int ndx = 0;ndx<dropRecords.length; ndx++){
					notifier.notifyAppEvent(this,
							AppMsg.REMOVE_SECURITY_PROFILE_GRANT,
							dropRecords[ndx], 
							profileListGrid.getSelectedRecord().getAttribute(SecurityProfileDS.SECURITY_PROFILE_ID)
							,null,null);
				}		
			}		
		});
		availableGrantsListGrid.addDataArrivedHandler(new DataArrivedHandler(){
			@Override
			public void onDataArrived(DataArrivedEvent event) {
				notifier.notifyAppEvent(this, AppMsg.SECURITY_PRIVILEGE_CACHE_AVAIL);
				
			}
			
		});
		
//btnAddGroup      
        btnAddGroup = new IButton("Add Group");
        btnAddGroup.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				notifier.notifyAppEvent(this, AppMsg.ADD_SECURITY_PROFILE);			
			}       	
        });

		
//btnDeleteGroup        
        btnDeleteGroup = new IButton("Delete Group");
        btnDeleteGroup.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				notifier.notifyAppEvent(this, AppMsg.DELETE_SECURITY_PROFILE);				
			}
        });
//btnAddGrant       
        btnAddGrant = new IButton();

        btnAddGrant.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				
				if(availableGrantsListGrid.getSelection().length >0){
					ListGridRecord[] dropRecords = availableGrantsListGrid.getSelection();			
					for(int ndx = 0;ndx<dropRecords.length; ndx++){
						notifier.notifyAppEvent(this,
								AppMsg.ADD_SECURITY_PROFILE_GRANT,	dropRecords[ndx], 
								profileListGrid.getSelectedRecord().getAttribute(SecurityProfileDS.SECURITY_PROFILE_ID)
								,null,null);
					}
				}else{
					SC.warn("Please select a security privilege to Grant");
				}
							
				
			}
        	
        });
//btnRemoveGrant        
        btnRemoveGrant = new IButton("");
        btnRemoveGrant.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				if (securityGrantsListGrid.getSelection().length>0){
					ListGridRecord[] dropRecords = securityGrantsListGrid.getSelection();			
					for(int ndx = 0;ndx<dropRecords.length; ndx++){
						notifier.notifyAppEvent(this,
								AppMsg.REMOVE_SECURITY_PROFILE_GRANT,	dropRecords[ndx], 
								profileListGrid.getSelectedRecord().getAttribute(SecurityProfileDS.SECURITY_PROFILE_ID)
								,null,null);
					}
				}else{
					SC.warn("Please select a security grant to revoke");
				}		
			}       	
        });
        
        
//btnFilterAvailableGrants
		btnFilterAvailableGrants = new IButton();
		btnFilterAvailableGrants.setTitle("filter");
		btnFilterAvailableGrants.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				int totalAvailable = availableGrantsListGrid.getRecords().length;
				int totalGranted = securityGrantsListGrid.getRecords().length;
				Criteria availGrants = new Criteria();
				ArrayList<Integer> alreadyGranted = new ArrayList<Integer>();
				for(int grantNdx = 0; grantNdx < totalGranted; grantNdx++){
					if (securityGrantsListGrid.getRecords()[grantNdx].getAttribute("securityPrivilegeId")==null) continue;
					alreadyGranted.add(Integer.parseInt(securityGrantsListGrid.getRecords()[grantNdx].getAttribute("securityPrivilegeId")));
				}
				availGrants.addCriteria("securityPrivilegeId",alreadyGranted.toArray(new Integer[0]));
				availableGrantsListGrid.filterData(availGrants);
			}
		});
		
		buildLayout();
	}
	
	private void buildLayout(){
		//------------------LAYOUT        
        setTitle("Groups");
		resizeTo(900, 550);
		setCanDragResize(true);
		
		 VLayout layout = new VLayout(15);
		 HLayout profileLayout = new HLayout(2);
		 VLayout profilebuttonsLayout= new VLayout(3);
		 HLayout grantsLayout = new HLayout(3);
		 
		 layout.setSize("850", "550px");
		 layout.setMargin(30);
	
		profileListGrid.setHeight("110px");
		profileListGrid.setWidth("400px");
		profileLayout.addMember(profileListGrid);
		profilebuttonsLayout.setAlign(Alignment.CENTER);
		profilebuttonsLayout.setAlign(VerticalAlignment.CENTER);
		profilebuttonsLayout.addMember(btnAddGroup);
		profilebuttonsLayout.addMember(btnDeleteGroup);
        profileLayout.addMember(profilebuttonsLayout);
        layout.addMember(profileLayout);
        availableGrantsListGrid.setWidth("400px");
        availableGrantsListGrid.setHeight("350px");
        
        grantsLayout.addMember(availableGrantsListGrid);
        VLayout grantChangeLayout = new VLayout();
        btnAddGrant.setIcon(AppClientBundle.INSTANCE.imgRightArrow().getURL());
        btnAddGrant.setWidth("25px");
        grantChangeLayout.setAlign(Alignment.CENTER);
        grantChangeLayout.setAlign(VerticalAlignment.CENTER);
        grantChangeLayout.addMember(btnAddGrant);
        btnRemoveGrant.setIcon(AppClientBundle.INSTANCE.imgLeftArrow().getURL());
        btnRemoveGrant.setWidth("25px");
        grantChangeLayout.addMember(btnRemoveGrant);
        grantChangeLayout.setWidth(30);
        grantsLayout.addMember(grantChangeLayout);
        securityGrantsListGrid.setWidth("400px");
        securityGrantsListGrid.setHeight("350px");
        grantsLayout.addMember(securityGrantsListGrid);	
		layout.addMember(grantsLayout);
		//layout.addMember(btnFilterAvailableGrants);
        layout.addMember(form);
        
        
        addChild(layout);
	}

	

//	/**
//	 * @param notifier the notifier to set
//	 */
//	public void setNotifier(AppNotifyObject notifier) {
//		this.notifier = notifier;
//	}

	/**
	 * @return the notifier
	 */
	public AppNotifyObject getNotifier() {
		return notifier;
	}

//	/**
//	 * @param listGrid the listGrid to set
//	 */
//	public void setListGrid(ListGrid listGrid) {
//		this.profileListGrid = listGrid;
//	}

	/**
	 * @return the listGrid
	 */
	public ListGrid getProfileListGrid() {
		return profileListGrid;
	}

//	/**
//	 * @param securityGrantsListGrid the securityGrantsListGrid to set
//	 */
//	public void setSecurityGrantsListGrid(ListGrid securityGrantsListGrid) {
//		this.securityGrantsListGrid = securityGrantsListGrid;
//	}

	/**
	 * @return the securityGrantsListGrid
	 */
	public ListGrid getSecurityGrantsListGrid() {
		return securityGrantsListGrid;
	}



	/**
	 * @param profileGrantsRS the profileGrantsRS to set
	 */
	public void setProfileGrantsRS(ResultSet profileGrantsRS) {
		this.profileGrantsRS = profileGrantsRS;
	}



	/**
	 * @return the profileGrantsRS
	 */
	public ResultSet getProfileGrantsRS() {
		return profileGrantsRS;
	}


//
//	/**
//	 * @param availableGrantsListGrid the availableGrantsListGrid to set
//	 */
//	public void setAvailableGrantsListGrid(ListGrid availableGrantsListGrid) {
//		this.availableGrantsListGrid = availableGrantsListGrid;
//	}



	/**
	 * @return the availableGrantsListGrid
	 */
	public ListGrid getAvailableGrantsListGrid() {
		return availableGrantsListGrid;
	}



	/**
	 * @param grantDescriptions the grantDescriptions to set
	 */
	public void setGrantDescriptions(HashMap<Integer, String> grantDescriptions) {
		this.grantDescriptions = grantDescriptions;
	}



	/**
	 * @return the grantDescriptions
	 */
	public HashMap<Integer, String> getGrantDescriptions() {
		return grantDescriptions;
	}

}
