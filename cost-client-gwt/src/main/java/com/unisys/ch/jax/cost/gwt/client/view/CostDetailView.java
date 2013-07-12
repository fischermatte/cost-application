package com.unisys.ch.jax.cost.gwt.client.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.unisys.ch.jax.cost.common.model.Project;
import com.unisys.ch.jax.cost.gwt.client.presenter.CostDetailPresenter.CostForm;

public class CostDetailView extends Composite implements CostForm {
	private TextBox title;
	private TextArea description;
	private DoubleBox time;
	private DatePicker date;
	private ValueListBox<Project> project;
	private final FlexTable detailsTable, contentTable;
	private final Button saveButton, cancelButton;

	public CostDetailView() {

		// Decorator
		DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
		contentDetailsDecorator.setWidth("18em");
		contentDetailsDecorator.setStyleName("center", true);
		initWidget(contentDetailsDecorator);

		// Outer Flex Table
		contentTable = new FlexTable();
		contentTable.setWidth("30em");
		contentTable.setCellSpacing(5);
		contentTable.setCellPadding(3);
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0, DockPanel.ALIGN_TOP);

		// Inner Flex Table
		detailsTable = new FlexTable();
		detailsTable.setCellSpacing(0);
		detailsTable.setWidth("100%");
		detailsTable.addStyleName("costs-DetailContainer");
		detailsTable.getColumnFormatter().addStyleName(1, "add-cost-input");

		// Init the Cost Form
		initCostForm();
		contentTable.setWidget(0, 0, detailsTable);

		// Action Menu
		HorizontalPanel menuPanel = new HorizontalPanel();
		saveButton = new Button("Save");
		saveButton.setStyleName("btn");
		cancelButton = new Button("Cancel");
		cancelButton.setStyleName("btn");
		menuPanel.add(saveButton);
		menuPanel.add(cancelButton);
		contentTable.getCellFormatter().addStyleName(1, 0, "costs-DetailContainer");
		contentTable.getCellFormatter().addStyleName(1, 0, "costs-DetailMenu");
		contentTable.setWidget(1, 0, menuPanel);
		contentDetailsDecorator.add(contentTable);
	}

	private void initCostForm() {
		detailsTable.setWidget(0, 0, new Label("Titel"));
		title = new TextBox();
		title.setWidth("294px");
		detailsTable.setWidget(0, 1, title);

		detailsTable.setWidget(1, 0, new Label("Beschreibung"));
		description = new TextArea();
		description.setWidth("300px");
		detailsTable.setWidget(1, 1, description);
		
		detailsTable.setWidget(2, 0, new Label("Projekt"));
		project = new ValueListBox<Project>(new Renderer<Project>() {
			@Override
			public String render(Project project) {
				return project == null ? "" : project.name();
			}
			@Override
			public void render(Project project, Appendable appendable) throws IOException {
				appendable.append(render(project));
			}
		});
		project.setAcceptableValues(Arrays.asList(Project.values()));
		detailsTable.setWidget(2, 1, project);
		
		detailsTable.setWidget(3, 0, new Label("Zeit [h]"));
		time = new DoubleBox();
		time.setWidth("294px");
		detailsTable.setWidget(3, 1, time);
		
		detailsTable.setWidget(4, 0, new Label("Datum"));
		date = new DatePicker();
		date.setWidth("308px");
		detailsTable.setWidget(4, 1, date);
		
		title.setFocus(true);
	}

	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public HasValue<String> getTheTitle() {
		return title;
	}

	@Override
	public HasValue<String> getDescription() {
		return description;
	}

	@Override
	public HasValue<Project> getProject() {
		return project;
	}

	@Override
	public HasValue<Double> getTime() {
		return time;
	}

	@Override
	public HasValue<Date> getDate() {
		return date;
	}
}
