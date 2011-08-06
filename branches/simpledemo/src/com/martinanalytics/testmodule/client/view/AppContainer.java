package com.martinanalytics.testmodule.client.view;


import com.allen_sauer.gwt.log.client.DivLogger;
import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.martinanalytics.testmodule.client.app.AppMsg;
import com.martinanalytics.testmodule.client.app.AppNotifyObject;
import com.martinanalytics.testmodule.client.app.AppPref;
import com.smartgwt.client.widgets.Window;


import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;
import com.smartgwt.client.widgets.menu.*;  

import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent; 
public class AppContainer extends Window {
	private AppNotifyObject notifier;
	Widget divLogger;

	public AppContainer() {
		notifier = new AppNotifyObject() ;
		setShowMaximizeButton(false);
		setShowMinimizeButton(false);
		setShowTitle(false);
		setShowHeader(false);
		setShowFooter(true);
		setTitle("Demo Application");
		setHeight100();
		setWidth100();
		maximize();
		setCanDragResize(false);
		setCanDragReposition(false);

		
		
		
		
		
		ToolStrip toolStrip = new ToolStrip();	
		ToolStripMenuButton mnuFile = new ToolStripMenuButton("File");	
		toolStrip.addMenuButton(mnuFile);
		toolStrip.addSpacer(2);
		
		Menu mnuSecurity = new Menu();
		MenuItem itmManageGroups =  new MenuItem("Manage Groups", "", "");
		MenuItem itmManageUsers =  new MenuItem("Manage Users", "", "");
		mnuSecurity.setItems(itmManageGroups,itmManageUsers);
		itmManageGroups.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(MenuItemClickEvent event) {
				notifier.notifyAppEvent(this, AppMsg.SHOW_MANAGE_GROUPS);		
			}	
		});
		
		itmManageUsers.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(MenuItemClickEvent event) {
				notifier.notifyAppEvent(this, AppMsg.SHOW_MANAGE_USERS);		
			}	
		});
		
		ToolStripMenuButton mnuBtnSecurity = new ToolStripMenuButton("Security", mnuSecurity);
		mnuBtnSecurity.setWidth("100");
		toolStrip.addMenuButton(mnuBtnSecurity);
		addItem(toolStrip);
		toolStrip.setWidth100();
		
		Menu mnuDev = new Menu();
		MenuItem itmLoggerConsole = new MenuItem("Logger Console","","");
		mnuDev.setItems(itmLoggerConsole);
		ToolStripMenuButton mnuBtnDev = new ToolStripMenuButton("Dev", mnuDev);
		toolStrip.addMenuButton(mnuBtnDev);
		itmLoggerConsole.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(MenuItemClickEvent event) {
				notifier.notifyAppEvent(this, AppMsg.SHOW_LOGGER_CONSOLE);
				
				
				
				Log.getLogger(DivLogger.class).getWidget().setVisible(true);
			}
			
		});
		 
		
	}
	
	public void setTransactionResults(String msg_){
		setStatus(msg_);
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

}
