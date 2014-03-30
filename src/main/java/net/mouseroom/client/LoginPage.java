package net.mouseroom.client;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginPage {

	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Please sign in to your Google Account to access the MouseRoom application.");
	private Anchor signInLink = new Anchor("Sign In");
	
	public LoginPage()
	{
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
	}
	
	public Panel getLoginPanel(LoginInfo loginInfo)
	{
		signInLink.setHref(loginInfo.getLoginUrl());
		return loginPanel;
	}
}
