package com.martinanalytics.testmodule.client.view;

import com.martinanalytics.testmodule.client.app.AppEventProducer;
import com.martinanalytics.testmodule.client.app.AppMsg;
import com.martinanalytics.testmodule.client.app.AppNotifyObject;
import com.martinanalytics.testmodule.client.app.AppPref;
import com.smartgwt.client.widgets.Window;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;


import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.types.FormLayoutType;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class LoginView extends Window {
	private AppEventProducer appEventProducer;
	private AppNotifyObject notifier;
	private TextItem txtUserId;
	private PasswordItem txtPassword;
	private ButtonItem cmdLogin;


	public LoginView() {
		appEventProducer = new AppEventProducer();
		notifier = new AppNotifyObject();
		setSize("400", "160");
		setShowCloseButton(false);
		setUseOpacityFilter(true);
		setShowModalMask(true);
		setShowMinimizeButton(false);
		setShowMaximizeButton(false);
		setTitle("Please Login");
		centerInPage();
		
		DynamicForm dynamicForm = new DynamicForm();
		dynamicForm.setSize("350", "125");
		dynamicForm.setWrapItemTitles(false);
		dynamicForm.setItemLayout(FormLayoutType.TABLE);
		dynamicForm.setWidth(350);
		dynamicForm.setMinColWidth(30);
		dynamicForm.setCanSubmit(true);
		txtPassword = new PasswordItem("txtPassword", "Password");
		txtPassword.setDisabled(false);
		txtPassword.setLength(20);
		cmdLogin = new ButtonItem("newButtonItem_3", "Login");
		cmdLogin.setEndRow(false);
		cmdLogin.setAlign(Alignment.LEFT);
		cmdLogin.setStartRow(false);
		cmdLogin.setTitleColSpan(1);
		cmdLogin.setAutoFit(false);
		cmdLogin.addClickHandler(new ClickHandler() {
	          
			@Override
			public void onClick(ClickEvent event) {
                //  userId = (String)getLoginView().getTxtUserId().getValue();
                //passwd = (String)getLoginView().getTxtPassword().getValue();
				notifier.notifyAppEvent(LoginView.class, AppMsg.SEND_LOGIN_INFO, getTxtUserId().getValue(), getTxtPassword().getValue(),null,"");
				
			}
	      });
		setTxtUserId(new TextItem("txtUserId", "User Name"));
		
		dynamicForm.setFields(new FormItem[] { new SpacerItem(), txtUserId, txtPassword, new SpacerItem(), cmdLogin, new SpacerItem()});
		addItem(dynamicForm);
		if(AppPref.DEBUG_MODE) {
			txtUserId.setValue("simpledemo");
			txtPassword.setValue("simpledemo");
			notifier.notifyAppEvent(this, AppMsg.SEND_LOGIN_INFO);
			
		}
//		setIsModal(true);
//		setShowModalMask(true);
//		setShowMaximizeButton(false);
//		setShowMinimizeButton(false);
//		setShowCloseButton(false);
//		centerInPage();
		
	}



	/**
	 * @return the appEventProducer
	 */
	public AppEventProducer getAppEventProducer() {
		return appEventProducer;
	}






	/**
	 * @return the notifier
	 */
	public AppNotifyObject getNotifier() {
		return notifier;
	}



	/**
	 * @param txtUserId the txtUserId to set
	 */
	private void setTxtUserId(TextItem txtUserId) {
		this.txtUserId = txtUserId;
	}



	/**
	 * @return the txtUserId
	 */
	public TextItem getTxtUserId() {
		return txtUserId;
	}



	/**
	 * @param txtPassword the txtPassword to set
	 */
	private void setTxtPassword(PasswordItem txtPassword) {
		this.txtPassword = txtPassword;
	}



	/**
	 * @return the txtPassword
	 */
	public PasswordItem getTxtPassword() {
		return txtPassword;
	}
	public ButtonItem getSendButton(){
		return cmdLogin;
	}

}
