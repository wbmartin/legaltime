package com.martinanalytics.testmodule.client.view;



import java.util.LinkedHashMap;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.RichTextArea;
import com.martinanalytics.testmodule.client.app.AppMsg;
import com.martinanalytics.testmodule.client.app.AppNotifyObject;
import com.martinanalytics.testmodule.client.model.SecurityUserDS;
import com.martinanalytics.testmodule.client.model.UserPublicDS;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.types.FormItemType;
import com.smartgwt.client.types.Overflow;
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
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.IButton;

public class SecurityUserView extends Window  {
	
	private ListGrid securityUserListGrid;
	private ListGrid userPublicListGrid;
	private AppNotifyObject notifier;
	private final DynamicForm userPublicForm;
	private final DynamicForm securityUserForm;
	private IButton cmdAddNew;
	private RichTextItem commentField;
	final UserPublicDS userPublicDS;
	final SecurityUserDS securityUserDS;
	
	public SecurityUserView(SecurityUserDS securityUserDS_, UserPublicDS userPublicDS_) {
		notifier = new AppNotifyObject();
		userPublicDS = userPublicDS_;
		securityUserDS = securityUserDS_;
		hide();

		
		 userPublicForm = new DynamicForm(); 
		 securityUserForm= new DynamicForm();		
//		securityUserListGrid = new ListGrid();
//		securityUserListGrid.setShowAllRecords(true);
//		securityUserListGrid.setDataSource(securityUserDS_);
//		securityUserListGrid.setCanEdit(true);
		
		 userPublicListGrid = new ListGrid();
		 userPublicListGrid.setShowAllRecords(true);
		 userPublicListGrid.setDataSource(userPublicDS_);
		 userPublicListGrid.setCanEdit(false);
		 
		
		
		 commentField = new RichTextItem();
		 
		 
		 
		 
		 //userPublicDS_.getField(UserPublicDS.COMMENT).setType

		 userPublicListGrid.addRecordClickHandler(new RecordClickHandler() {  
	            public void onRecordClick(RecordClickEvent event) {  
	                userPublicForm.reset();  
	                userPublicForm.editSelectedData(userPublicListGrid);  
	                userPublicDS.getField(UserPublicDS.USER_ID).setAttribute("readOnly", "true");
	                Record securityUserRecord;
	                for(int ndx =0;ndx< securityUserDS.getCacheData().length;ndx++){
	                	Log.debug("Comparing: "+securityUserDS.getCacheData()[ndx].getAttribute(SecurityUserDS.USER_ID)+ "-" + userPublicListGrid.getSelectedRecord().getAttribute(UserPublicDS.USER_ID)) ;
	                	if(securityUserDS.getCacheData()[ndx].getAttribute(SecurityUserDS.USER_ID).equals(userPublicListGrid.getSelectedRecord().getAttribute(UserPublicDS.USER_ID))){
	                		securityUserRecord = securityUserDS.getCacheData()[ndx];
	                		securityUserForm.editRecord(securityUserRecord);
	                		break;
	                	}
	                }
	                
	            }  
	        });  
		 userPublicForm.setDataSource(userPublicDS_);
		 securityUserForm.setDataSource(securityUserDS_);
		 cmdAddNew = new IButton("Add New");
		 cmdAddNew.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				SC.askforValue("Please enter the new username",  new ValueCallback() {  
                    @Override
					public void execute(String value) {  
                        if (value != null ) {  
                        	notifier.notifyAppEvent(this, AppMsg.ADD_USER_PUBLIC,value); 
                        } else { }  
                    }
  
                });  
				
				
			}		 
		 });
		buildLayout();
	}

	private void buildLayout(){
		setTitle("Groups");
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
		
		 userPublicDS.getField(UserPublicDS.USER_ID).setHidden(false);
		 userPublicDS.getField(UserPublicDS.USER_ID).setAttribute("readOnly", "true");
		 userPublicDS.getField(UserPublicDS.LAST_UPDATE).setHidden(false);
		 userPublicDS.getField(UserPublicDS.LAST_UPDATE).setEditorType(new TextItem());
		 userPublicDS.getField(UserPublicDS.LAST_UPDATE).setAttribute("readOnly", "true");
		 userPublicDS.getField(UserPublicDS.LAST_UPDATE).setAttribute("displayFormat", "toUSShortDateTime");
		 
		 ComboBoxItem securityProfileComboBoxItem = new ComboBoxItem();
		 LinkedHashMap securityProfileValueMap = new LinkedHashMap();
		 securityProfileComboBoxItem.setValueMap(securityProfileValueMap);
		 securityUserDS.getField("securityProfileId").setEditorType(securityProfileComboBoxItem);
		 
		 CheckboxItem activeYnCkBxItem = new CheckboxItem();
		 activeYnCkBxItem.setLabelAsTitle(true);
		 //activeYnCkBxItem.setAttribute("value", "Y");
		 securityUserDS.getField("activeYn").setEditorType(activeYnCkBxItem);
		 
		 
		 commentField.setCellStyle("richTextItemCellStyle");
		 userPublicDS.getField(UserPublicDS.COMMENT).setEditorType(commentField);
		 ListGridField useridLgf = new ListGridField(UserPublicDS.USER_ID,"User Id",100 );
		 ListGridField lastNameLgf = new ListGridField(UserPublicDS.LAST_NAME,"Last Name",100 );
		 ListGridField firstNameLgf = new ListGridField(UserPublicDS.FIRST_NAME,"First Name",100 );
		 userPublicListGrid.setFields(useridLgf,lastNameLgf,firstNameLgf);
		
		 userPublicDS.getField("userId").setHidden(true);


		gridFormSplitLayout.addMember(userPublicListGrid);
		VLayout formAndButtons = new VLayout(2);
		formAndButtons.setWidth("700");
		formAndButtons.setOverflow(Overflow.AUTO);
		securityUserForm.setWidth("600");
		securityUserForm.setTitleWidth("100");
		formAndButtons.addMember(securityUserForm);
		securityUserForm.setWidth("600");
		userPublicForm.setTitleWidth("100");
		formAndButtons.addMember(userPublicForm);
		
		formAndButtons.addMember(cmdAddNew);
		userPublicForm.setWidth(300);
		
		gridFormSplitLayout.addMember(formAndButtons);
		layout.addMember(gridFormSplitLayout);
        addChild(layout);

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

}

