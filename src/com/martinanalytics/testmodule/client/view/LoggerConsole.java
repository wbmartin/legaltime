package com.martinanalytics.testmodule.client.view;

import com.allen_sauer.gwt.log.client.DivLogger;
import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Widget;


import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.FlowLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.VStack;



public class LoggerConsole extends Window {
	Widget divLogger;
	public LoggerConsole(){
		setTitle("Logger Console");
		setWidth(1250);
		setHeight(400);
		setTop(60);
	
	divLogger = Log.getLogger(DivLogger.class).getWidget();
	
	VStack layout = new VStack();
	//layout.setOverflow(Overflow.SCROLL);
	divLogger.setHeight("350px");
	divLogger.setWidth("1200px");
	layout.addChild(divLogger);
	
	//setOverflow(Overflow.VISIBLE);
	
	  addItem(layout);
	//addChild(divLogger);
	
	}
	@Override
	public void show(){
		super.show();
		//layout.addChild(divLogger);
		
		divLogger.setVisible(true);
	}
	@Override
	public void hide(){
		super.hide();
		divLogger.setVisible(false);
		
	}
	
}
