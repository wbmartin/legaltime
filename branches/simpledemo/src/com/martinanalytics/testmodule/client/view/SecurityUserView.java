package com.martinanalytics.testmodule.client.view;



import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.RichTextArea;
import com.martinanalytics.testmodule.client.app.AppMsg;
import com.martinanalytics.testmodule.client.app.AppNotifyObject;
import com.martinanalytics.testmodule.client.app.AppSysCode;
import com.martinanalytics.testmodule.client.model.MasterCacheManager;
import com.martinanalytics.testmodule.client.model.SecurityProfileDS;
import com.martinanalytics.testmodule.client.model.SecurityUserDS;
import com.martinanalytics.testmodule.client.model.UserPublicDS;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.types.FormItemType;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.util.ValueCallback;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.RichTextItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.grid.events.EditCompleteEvent;
import com.smartgwt.client.widgets.grid.events.EditCompleteHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.IButton;

public class SecurityUserView extends Window  {
	
	private ListGrid securityUserListGrid;
	private ListGrid userPublicListGrid;
	private AppNotifyObject notifier;
	private final DynamicForm userPublicForm;
	private final DynamicForm securityUserForm;
	private IButton cmdAddNew;
	private IButton cmdSave;
	private RichTextItem commentField;
	private VLayout formAndButtons ;
	final UserPublicDS userPublicDS;
	final SecurityUserDS securityUserDS;
	ComboBoxItem securityProfileSelectItem;
	ComboBoxItem stateSelectItem;
	MasterCacheManager masterCacheManager;
	
	public SecurityUserView(SecurityUserDS securityUserDS_, UserPublicDS userPublicDS_) {
		notifier = new AppNotifyObject();
		userPublicDS = userPublicDS_;
		securityUserDS = securityUserDS_;
		masterCacheManager = MasterCacheManager.getInstance();
		hide();

		
		 userPublicForm = new DynamicForm(); 
		 securityUserForm= new DynamicForm();		
		
		 userPublicListGrid = new ListGrid();
		 userPublicListGrid.setShowAllRecords(true);
		 userPublicListGrid.setDataSource(userPublicDS);
		 userPublicListGrid.setCanEdit(true);

		 commentField = new RichTextItem();
		 userPublicListGrid.addRecordClickHandler(new RecordClickHandler() {  
	            public void onRecordClick(RecordClickEvent event) {  
	                synchFormToSelection();
	            }  
	        });  
		 userPublicForm.setDataSource(userPublicDS);
		 securityUserForm.setDataSource(securityUserDS);
		 cmdAddNew = new IButton("Add New");
		 cmdAddNew.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				SC.askforValue("Please enter the new username",  new ValueCallback() {  
                    @Override
					public void execute(String value) {  
                        if (value != null ) {  
                        	notifier.notifyAppEvent(this, AppMsg.ADD_USER_PUBLIC,value);
                        	setAllFieldsReadOnly(false);
                        } else { }  
                    }
  
                });  
				
				
			}		 
		 });
		 cmdSave = new IButton("Save");
		 cmdSave.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					userPublicDS.setUseGridRecord(false);
					userPublicForm.saveData();
					
				}		 
			 });
//		 userPublicListGrid.addDataArrivedHandler(new DataArrivedHandler() {			
//			@Override
//			public void onDataArrived(DataArrivedEvent event) {
//				Log.debug("UserPublicListGrid Received New data");
//				
//				Log.debug("Cache data at this point" +userPublicDS.getCacheData()[0].getAttribute(UserPublicDS.FIRST_NAME));
//				//userPublicForm.add
//			}
//		 });

		 securityProfileSelectItem = new ComboBoxItem();
		 securityProfileSelectItem.setOptionDataSource( SecurityProfileDS.getInstance());
		 securityProfileSelectItem.setValueField(SecurityProfileDS.SECURITY_PROFILE_ID);
		 securityProfileSelectItem.setDisplayField(SecurityProfileDS.PROFILE_NAME);
		 
		 stateSelectItem= new ComboBoxItem();
		 stateSelectItem.setOptionDataSource(masterCacheManager.getStatesDS());
		 stateSelectItem.setValueField(MasterCacheManager.KEY);
		 stateSelectItem.setDisplayField(MasterCacheManager.VALUE);
		buildLayout();
	}
	
	private void synchFormToSelection(){
		userPublicForm.reset();  
		if(userPublicListGrid.getSelectedRecord() !=null){
	        userPublicForm.editRecord(userPublicListGrid.getSelectedRecord());  
	        //userPublicDS.getField(UserPublicDS.USER_ID).setAttribute("readOnly", "true");
	        Record securityUserRecord;
	        for(int ndx =0;ndx< securityUserDS.getCacheData().length;ndx++){
	        	Log.debug("Comparing: "+securityUserDS.getCacheData()[ndx].getAttribute(SecurityUserDS.USER_ID)+ "-" + userPublicListGrid.getSelectedRecord().getAttribute(UserPublicDS.USER_ID)) ;
	        	if(securityUserDS.getCacheData()[ndx].getAttribute(SecurityUserDS.USER_ID).equals(userPublicListGrid.getSelectedRecord().getAttribute(UserPublicDS.USER_ID))){
	        		securityUserRecord = securityUserDS.getCacheData()[ndx];
	        		securityUserForm.editRecord(securityUserRecord);
	        		break;
	        	}
	        }
	        setAllFieldsReadOnly(false);
		}
	}

	private void buildLayout(){
		setTitle("Users");
		resizeTo(1000, 650);
		setCanDragResize(true);
		VLayout layout = new VLayout(15);
		layout.setSize("100px", "650px");
		layout.setMargin(30);
		setTop(50);
		setLeft(50);
		
		HLayout gridFormSplitLayout = new HLayout(2);
		
		userPublicListGrid.setHeight("600px");
		userPublicListGrid.setWidth("350px");
		
		 
		
		securityUserDS.getField(SecurityUserDS.LAST_UPDATE).setHidden(false);
		securityUserDS.getField(SecurityUserDS.LAST_UPDATE).setEditorType(new TextItem());
		securityUserDS.getField(SecurityUserDS.LAST_UPDATE).setAttribute("readOnly", "true");
		securityUserDS.getField(SecurityUserDS.LAST_UPDATE).setAttribute("displayFormat", "toUSShortDateTime");

		
		 
		 securityUserDS.getField("securityProfileId").setEditorType(securityProfileSelectItem);
		 
		 CheckboxItem activeYnCkBxItem = new CheckboxItem();
		 activeYnCkBxItem.setLabelAsTitle(true);
		 
		 securityUserDS.getField("activeYn").setEditorType(activeYnCkBxItem);
		 securityUserDS.getField(SecurityUserDS.USER_ID).setAttribute("readOnly", "true");
		 
		 
		 commentField.setCellStyle("richTextItemCellStyle");
		 userPublicDS.getField(UserPublicDS.COMMENT).setEditorType(commentField);
		 ListGridField useridLgf = new ListGridField(UserPublicDS.USER_ID,"User Id",100 );
		 ListGridField lastNameLgf = new ListGridField(UserPublicDS.LAST_NAME,"Last Name",100 );
		 ListGridField firstNameLgf = new ListGridField(UserPublicDS.FIRST_NAME,"First Name",100 );
		 userPublicListGrid.setFields(useridLgf,lastNameLgf,firstNameLgf);
		
		 userPublicDS.getField("userId").setHidden(true);
		 userPublicDS.getField(UserPublicDS.OFFICE_STATE).setEditorType(stateSelectItem);


		gridFormSplitLayout.addMember(userPublicListGrid);
		formAndButtons = new VLayout(2);
		
		formAndButtons.setWidth("700");
		formAndButtons.setOverflow(Overflow.AUTO);
		
		securityUserForm.setWidth("600");
		securityUserForm.setTitleWidth("100");
		formAndButtons.addMember(securityUserForm);
		securityUserForm.setWidth("600");
		userPublicForm.setTitleWidth("100");
		userPublicForm.setNumCols(4);
		userPublicForm.setColWidths(100,200,100,200);
		userPublicForm.setFields(new FormItem[]{});
		
		
		
		final TabSet userInfoTabSet = new TabSet();  
		userInfoTabSet.setTabBarPosition(Side.TOP);  
		userInfoTabSet.setWidth(600);  
		userInfoTabSet.setHeight(400);
        Tab tabPublic = new Tab("Public", "");  
        
        tabPublic.setPane(userPublicForm);  
        userInfoTabSet.addTab(tabPublic);
		formAndButtons.addMember(userInfoTabSet);
		
		formAndButtons.addMember(cmdAddNew);
		formAndButtons.addMember(cmdSave);
		userPublicForm.setWidth(300);
		
		
		
		gridFormSplitLayout.addMember(formAndButtons);
		layout.addMember(gridFormSplitLayout);
        addChild(layout);
      setAllFieldsReadOnly(true);

	}


	/**
	 * @return the notifier
	 */
	public AppNotifyObject getNotifier() {
		return notifier;
	}

	/**
	 * @param listGrid the listGrid to set
	 */
	public void setSecurityUserListGrid(ListGrid securityUserListGrid_) {
		this.securityUserListGrid = securityUserListGrid_;
	}

	/**
	 * @return the listGrid
	 */
	public ListGrid getSecurityUserListGrid() {
		return securityUserListGrid;
	}

	public void setUserPublicListGrid(ListGrid userPublicListGrid) {
		this.userPublicListGrid = userPublicListGrid;
	}

	public ListGrid getUserPublicListGrid() {
		return userPublicListGrid;
	}

	/**
	 * @return the securityUserForm
	 */
	public DynamicForm getSecurityUserForm() {
		return securityUserForm;
	}
	
	private void setAllFieldsReadOnly(boolean lock_){
		if(lock_){
			formAndButtons.disable();
		}else{formAndButtons.enable();}

	
	
	}

}



