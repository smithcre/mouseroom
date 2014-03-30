package net.mouseroom.client;

import net.mouseroom.datamodel.Mouse;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MouseDetailsDialog {
	
	private Mouse mouse;
	
	public MouseDetailsDialog(Mouse mouse)
	{
		this.mouse = mouse;
	}
	
	public DialogBox getDialogBox()
	{
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Edit Mouse");
		dialogBox.setAnimationEnabled(true);
		Button saveButton = new Button("Save");
		Button cancelButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		//closeButton.getElement().setId("closeButton");
		
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.add(new HTML("<b>Mouse Name:</b>"));
		
		FlexTable layout = new FlexTable();
	    layout.setCellSpacing(6);
	    
	    layout.setHTML(0, 0, "Name");
	    layout.setHTML(1, 0, "Gender");
	    layout.setHTML(2, 0, "Line");
	    
	    dialogVPanel.add(layout);
		
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		
		dialogVPanel.add(buttonPanel);
		dialogBox.setWidget(dialogVPanel);

		cancelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// TODO implement save
			}
		});
		
		return dialogBox;
	}
}
