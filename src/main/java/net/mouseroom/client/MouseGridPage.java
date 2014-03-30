package net.mouseroom.client;

import java.util.ArrayList;

import net.mouseroom.datamodel.Mouse;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MouseGridPage {

	private static final int COLUMN_NAME = 0;
	private static final int COLUMN_LINE = 1;
	private static final int COLUMN_SEX = 2;
	private static final int COLUMN_COLOR = 3;
	private static final int COLUMN_EDIT = 4;
	private static final int COLUMN_REMOVE = 5;
	
	private static final String MOUSE_GRID_BUTTON_COLUMN = "mouseGridButtonColumn";
	private static final String STYLE_MOUSE_GRID_NUMERIC_COLUMN = "mouseGridNumericColumn";
	private static final String STYLE_MOUSE_GRID = "mouseGrid";
	private static final String STYLE_MOUSE_GRID_HEADER = "mouseGridHeader";
	
	private VerticalPanel mouseGridContainerPanel = new VerticalPanel();
	private FlexTable mouseGridFlexTable = new FlexTable();
	private Button refreshButton = new Button("Refresh");
	private HorizontalPanel addPanel = new HorizontalPanel();
	private TextBox mouseNameTextBox = new TextBox();
	private Button addMouseButton = new Button("Add");
	private ArrayList<String> stocks = new ArrayList<String>();

	/**
	 * Add stock to FlexTable. Executed when the user clicks the addStockButton
	 * or presses enter in the newSymbolTextBox.
	 */
	private void addMouse() {
		final String name = mouseNameTextBox.getText().toUpperCase().trim();
		mouseNameTextBox.setFocus(true);

//		// Stock code must be between 1 and 10 chars that are numbers, letters,
//		// or dots.
//		if (!name.matches("^[0-9a-zA-Z\\.]{1,10}$")) {
//			Window.alert("'" + name + "' is not a valid symbol.");
//			mouseNameTextBox.selectAll();
//			return;
//		}

		mouseNameTextBox.setText("");

		MouseServiceAsync mouseService = GWT.create(MouseService.class);
		
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
		      public void onFailure(Throwable caught) {
		    	  Window.alert("RPC error " + caught.getMessage());
		      }

		      public void onSuccess(Void v) {
		         refreshGrid();
		      }
		    };
		    
		Mouse mouse = new Mouse();
		
		mouse.setName(name);
		
		mouseService.saveMouse(mouse, callback);

	}

	public Panel getMouseGridPanel() {
		// Create table for stock data.
		mouseGridFlexTable.setText(0, COLUMN_NAME, "Name");
		mouseGridFlexTable.setText(0, COLUMN_LINE, "Line");
		mouseGridFlexTable.setText(0, COLUMN_SEX, "Sex");
		mouseGridFlexTable.setText(0, COLUMN_COLOR, "Color");
		mouseGridFlexTable.setText(0, COLUMN_EDIT, "Edit");
		mouseGridFlexTable.setText(0, COLUMN_REMOVE, "Remove");

		// Add styles to elements in the stock list table.
		mouseGridFlexTable.setCellPadding(6);
		mouseGridFlexTable.getRowFormatter().addStyleName(0, STYLE_MOUSE_GRID_HEADER);
		mouseGridFlexTable.addStyleName(STYLE_MOUSE_GRID);
		
		mouseGridFlexTable.getCellFormatter().addStyleName(0, COLUMN_EDIT, MOUSE_GRID_BUTTON_COLUMN);
		mouseGridFlexTable.getCellFormatter().addStyleName(0, COLUMN_REMOVE, MOUSE_GRID_BUTTON_COLUMN);

		// Assemble Add Stock panel.
		addPanel.add(mouseNameTextBox);
		addPanel.add(addMouseButton);
		addPanel.addStyleName("addPanel");

		// Assemble Main panel.
		mouseGridContainerPanel.add(mouseGridFlexTable);
		mouseGridContainerPanel.add(refreshButton);
		mouseGridContainerPanel.add(addPanel);
		// mainPanel.add(lastUpdatedLabel);

		// Move cursor focus to the input box.
		mouseNameTextBox.setFocus(true);

		// Setup timer to refresh list automatically.
		// Timer refreshTimer = new Timer() {
		// @Override
		// public void run() {
		// refreshWatchList();
		// }
		// };
		// refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
		
		refreshButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				refreshGrid();
			}
		});

		// Listen for mouse events on the Add button.
		addMouseButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addMouse();
			}
		});

		// Listen for keyboard events in the input box.
		mouseNameTextBox.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					addMouse();
				}
			}
		});

		return mouseGridContainerPanel;
	}
	
	public void refreshGrid()
	{
		MouseServiceAsync mouseService = GWT.create(MouseService.class);
		
		AsyncCallback<Mouse[]> callback = new AsyncCallback<Mouse[]>() {
		      public void onFailure(Throwable caught) {
		    	  Window.alert("RPC error " + caught.getMessage());
		      }

		      public void onSuccess(Mouse[] result) {
		        updateGrid(result);
		      }
		    };
		
		mouseService.getMice(callback);
	}

	protected void updateGrid(Mouse[] result) {
		
		for(int i = 1 ; i < mouseGridFlexTable.getRowCount() ; i++)
		{
			mouseGridFlexTable.removeRow(i);
		}
		
		for(int i = 0 ; i < result.length ; i++)
		{
			final Mouse mouse = result[i];
			
			int row = i+1;
			
			mouseGridFlexTable.setText(row, COLUMN_NAME, mouse.getName());
			mouseGridFlexTable.getCellFormatter().addStyleName(row, COLUMN_EDIT,
					MOUSE_GRID_BUTTON_COLUMN);
			mouseGridFlexTable.getCellFormatter().addStyleName(row, COLUMN_REMOVE,
					MOUSE_GRID_BUTTON_COLUMN);

			Button editMouseButton = new Button("Edit");
			editMouseButton.addStyleDependentName("edit");
			editMouseButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					MouseDetailsDialog dialog = new MouseDetailsDialog(mouse);
					dialog.getDialogBox().center();
				}
			});
			mouseGridFlexTable.setWidget(row, COLUMN_EDIT, editMouseButton);

			// Add a button to remove this stock from the table.
			Button removeMouseButton = new Button("x");
			removeMouseButton.addStyleDependentName("remove");
			removeMouseButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					removeMouse(mouse);
				}
			});
			mouseGridFlexTable.setWidget(row, COLUMN_REMOVE, removeMouseButton);
		}
	}
	
	private void removeMouse(Mouse mouse) {
		MouseServiceAsync mouseService = GWT.create(MouseService.class);
		
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
		      public void onFailure(Throwable caught) {
		    	  Window.alert("RPC error " + caught.getMessage());
		      }

		      public void onSuccess(Void v) {
		         refreshGrid();
		      }
		    };
		    
		mouseService.deleteMouse(mouse.getId(), callback);
	}
}
