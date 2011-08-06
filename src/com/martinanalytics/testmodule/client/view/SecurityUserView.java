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
	
	private ListGrid listGrid;
	private AppNotifyObject notifier;
	
	public SecurityUserView(SecurityUserDS securityUserDS_) {
		notifier = new AppNotifyObject();

		hide();
		setTitle("Groups");
		resizeTo(800, 600);
		setCanDragResize(true);
		
		 VLayout layout = new VLayout(15); 
		 layout.setSize("500", "300px");
		 layout.setTop(30);

		final DynamicForm form = new DynamicForm(); 
				
		listGrid = new ListGrid();
		listGrid.setShowAllRecords(true);
		listGrid.setDataSource(securityUserDS_);
		listGrid.setCanEdit(true);
		
		
		layout.addMember(listGrid);
                layout.addMember(form);
               	addChild(layout);
	}

	public void fetchData(){
		listGrid.fetchData();
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
	public void setListGrid(ListGrid listGrid) {
		this.listGrid = listGrid;
	}

	/**
	 * @return the listGrid
	 */
	public ListGrid getListGrid() {
		return listGrid;
	}

}

