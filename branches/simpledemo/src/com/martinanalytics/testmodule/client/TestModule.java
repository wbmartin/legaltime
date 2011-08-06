package com.martinanalytics.testmodule.client;

import com.allen_sauer.gwt.log.client.DivLogger;
import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.DOM;
import com.martinanalytics.testmodule.client.app.AppMsg;
import com.martinanalytics.testmodule.client.app.AppNotifyObject;
import com.martinanalytics.testmodule.client.controller.MasterController;
import com.martinanalytics.testmodule.client.view.LoginView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestModule implements EntryPoint {
	AppNotifyObject notifier;
	public void onModuleLoad()	{
		notifier = new AppNotifyObject();
		
		Log.getLogger(DivLogger.class).getWidget().setVisible(false);
		MasterController masterController = new MasterController();
		notifier.addAppEventListener(masterController);
		DOM.removeChild(RootPanel.getBodyElement(), DOM.getElementById("InitialPageLoadingId"));
		//masterController.getLoginController().getLoginView().show();
		Button loginbutton = new Button("Login");
		
		loginbutton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				String username = DOM.getElementProperty(DOM.getElementById("username"),"value");
				String password = DOM.getElementProperty(DOM.getElementById("passwd"),"value");
				Log.debug("username"+ username);
				Log.debug("passwd"+ password);
				
				notifier.notifyAppEvent(this, AppMsg.SEND_LOGIN_INFO,username,password,null,"");
				
			}

			
		});
		
		loginbutton.setStyleName("loginButtonStyle");
		RootPanel.get().add(loginbutton);
		DOM.appendChild(DOM.getElementById("loginbutton"), loginbutton.getElement());
		DOM.getElementById("loginMsg").setInnerHTML("");
		//Log.getLogger(DivLogger.class).getWidget().setVisible(false);
		//RootPanel.get().add(Log.getLogger(DivLogger.class).getWidget(),100,100);
		
	}
}