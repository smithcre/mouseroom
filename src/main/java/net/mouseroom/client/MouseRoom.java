package net.mouseroom.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.ArrayList;
import java.util.Date;

public class MouseRoom implements EntryPoint {

	private static final String MAIN_DIV = "mainDiv";
	
	// Container Stuff
	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel navigationPanel = new HorizontalPanel();
	
	// Navigation Stuff
	private Button configureButton = new Button("Configure");
	private Button exportButton = new Button("Export Data");
	private Button signOutButton = new Button("Sign Out");
	
	// Grid Stuff
	private MouseGridPage mouseGridPage = new MouseGridPage();

	// Login Stuff
	private LoginInfo loginInfo = null;
	private LoginPage loginPage = new LoginPage();

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {
		// Check login status using login service.
		LoginServiceAsync loginService = LoginServiceAsync.Util.getInstance();
		loginService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {
					public void onFailure(Throwable caught) {
						Window.alert("RPC error " + caught.getMessage());
					}

					public void onSuccess(LoginInfo result) {
						loginInfo = result;
						if (loginInfo.isLoggedIn()) {
							loadMouseGridPage();
						} else {
							loadLoginPage();
						}
					}
				});

	}

	private void loadLoginPage() {
		RootPanel.get(MAIN_DIV).add(loginPage.getLoginPanel(loginInfo));
	}

	private void loadMouseGridPage() {
		signOutButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent arg0) {
				Window.Location.assign(loginInfo.getLogoutUrl());
			}});
		
		navigationPanel.add(configureButton);
		navigationPanel.add(exportButton);
		navigationPanel.add(signOutButton);
		navigationPanel.addStyleName("navigationPanel");
		
		mainPanel.add(navigationPanel);
		mainPanel.add(mouseGridPage.getMouseGridPanel());
		
		RootPanel.get(MAIN_DIV).add(mainPanel);
		
		mouseGridPage.refreshGrid();
	}
}
