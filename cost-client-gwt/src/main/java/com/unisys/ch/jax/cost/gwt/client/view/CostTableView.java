package com.unisys.ch.jax.cost.gwt.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.unisys.ch.jax.cost.common.model.Cost;
import com.unisys.ch.jax.cost.common.model.Project;
import com.unisys.ch.jax.cost.gwt.client.presenter.CostTablePresenter.CostTable;

public class CostTableView extends Composite implements CostTable {
	private final Button addButton, deleteButton, loadButton;
	private FlexTable costTable;
	private final FlexTable contentTable;
	private int position = 1;

	public CostTableView() {
		
		//Decorator
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
		contentTableDecorator.setStyleName("center", true);
		contentTableDecorator.setWidth("100%");
		contentTableDecorator.setWidth("18em");

		//Outer Flex Table
		contentTable = new FlexTable();
		
		//Some stylings
		contentTable.setWidth("60em");
		contentTable.setCellSpacing(5);
		contentTable.setCellPadding(3);
		contentTable.getCellFormatter().addStyleName(0, 0, "costs-ListContainer");
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0, DockPanel.ALIGN_TOP);

		//Action Menu
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setBorderWidth(0);
		hPanel.setSpacing(0);
		hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		addButton = new Button("Add");
		addButton.setStyleName("btn");
		hPanel.add(addButton);
		deleteButton = new Button("Delete");
		deleteButton.setStyleName("btn");
		hPanel.add(deleteButton);
		contentTable.getCellFormatter().addStyleName(0, 0, "costs-ListMenu");
		contentTable.setWidget(0, 0, hPanel);

		//Inner Flex Table
		costTable = new FlexTable();
		costTable.setCellSpacing(0);
		costTable.setCellPadding(0);
		costTable.setWidth("100%");
		costTable.addStyleName("costs-ListContents");
		costTable.getColumnFormatter().setWidth(0, "15px");
		contentTable.setWidget(1, 0, costTable);
		
		loadButton = new Button("Mehr laden");
		loadButton.setStyleName("btn");
		contentTable.getCellFormatter().addStyleName(2, 0, "costs-DetailMenu");
		contentTable.setWidget(2, 0, loadButton);

		contentTableDecorator.add(contentTable);
	}

	public HasClickHandlers getAddButton() {
		return addButton;
	}

	public HasClickHandlers getDeleteButton() {
		return deleteButton;
	}

	public HasClickHandlers getList() {
		return costTable;
	}

	public Button getLoadButton() {
		return loadButton;
	}

	@Override
	public void setData(List<Cost> data, boolean add) {
		if (!add) {
			costTable.removeAllRows();
			position = 1;
		}
		if (data != null) {
			costTable.setText(0, 1, "ID");
			costTable.setText(0, 2, "Title");
			costTable.setText(0, 3, "Beschreibung");
			costTable.setText(0, 4, "Datum");
			costTable.setText(0, 5, "Zeit");
			costTable.setText(0, 6, "Projekt");
			costTable.getCellFormatter().addStyleName(0, 0, "costs-TableTitle");
			costTable.getCellFormatter().addStyleName(0, 1, "costs-TableTitle");
			costTable.getCellFormatter().addStyleName(0, 2, "costs-TableTitle");
			costTable.getCellFormatter().addStyleName(0, 3, "costs-TableTitle");
			costTable.getCellFormatter().addStyleName(0, 4, "costs-TableTitle");
			costTable.getCellFormatter().addStyleName(0, 5, "costs-TableTitle");
			costTable.getCellFormatter().addStyleName(0, 6, "costs-TableTitle");
			for (int i = position; i < position + data.size(); ++i) {
				costTable.setWidget(i, 0, new CheckBox());
				costTable.setText(i, 1, data.get(i-position).getId().toString());
				costTable.setText(i, 2, data.get(i-position).getTitle());
				costTable.setText(i, 3, data.get(i-position).getDescription());
				Date workDay = data.get(i-position).getWorkDay();
				costTable.setText(i, 4, workDay == null ? "" : DateTimeFormat.getLongDateFormat().format(workDay));
				Double time = data.get(i-position).getTime();
				costTable.setText(i, 5, time == null ? "" : time.toString());
				Project project = data.get(i-position).getProject();
				costTable.setText(i, 6, project == null ? "" : project.name());
			}
			position += data.size(); 
		}
	}

	@Override
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = costTable.getCellForEvent(event);

		if (cell != null) {
			// Suppress clicks if the user is actually selecting the check box
			if (cell.getCellIndex() > 0) {
				selectedRow = cell.getRowIndex();
			}
		}
		return selectedRow;
	}

	@Override
	public List<Integer> getSelectedRows() {
		List<Integer> selectedRows = new ArrayList<Integer>();

		for (int i = 1; i < costTable.getRowCount(); ++i) {
			CheckBox checkBox = (CheckBox) costTable.getWidget(i, 0);
			if (checkBox.getValue()) {
				selectedRows.add(i);
			}
		}
		return selectedRows;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

}
