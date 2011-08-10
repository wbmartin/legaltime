package com.martinanalytics.testmodule.client.view;

import com.martinanalytics.testmodule.client.app.AppMsg;
import com.martinanalytics.testmodule.client.app.AppNotifyObject;
import com.martinanalytics.testmodule.client.model.SecurityUserDS;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.IButton;

public class SecurityUserView extends Window  {
	
	private ListGrid securityUserListGrid;
	private AppNotifyObject notifier;
	final DynamicForm form;
	
	public SecurityUserView(SecurityUserDS securityUserDS_) {
		notifier = new AppNotifyObject();
		hide();
		
		 form = new DynamicForm(); 
				
		securityUserListGrid = new ListGrid();
		securityUserListGrid.setShowAllRecords(true);
		securityUserListGrid.setDataSource(securityUserDS_);
		securityUserListGrid.setCanEdit(true);
		
		
		
	}

	private void buildLayout(){
		setTitle("Groups");
		resizeTo(900, 550);
		setCanDragResize(true);
		VLayout layout = new VLayout(15);
		layout.setSize("850", "550px");
		layout.setMargin(30);

		layout.addMember(securityUserListGrid);
                layout.addMember(form);
               	addChild(layout);

	}

	/**
	 * @param notifier the notifier to set
	 */
	public void setNotifier(AppNotifyObject notifier) {
		this.notifier = notifier;
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

}

