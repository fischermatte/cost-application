package com.unisys.ch.jax.cost.gwt.client.presenter;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.unisys.ch.jax.cost.common.model.Cost;
import com.unisys.ch.jax.cost.common.model.Project;
import com.unisys.ch.jax.cost.gwt.client.CostServiceAsync;
import com.unisys.ch.jax.cost.gwt.client.event.GoToListEvent;
import com.unisys.ch.jax.cost.gwt.client.view.CostDetailView;

public class CostDetailPresenter implements Presenter {

	private Cost cost;
	private final CostServiceAsync costService;
	private final HandlerManager eventBus;
	private final CostForm costForm;

	public interface CostForm {
		HasClickHandlers getSaveButton();
		HasClickHandlers getCancelButton();

		HasValue<String> getTheTitle();
		HasValue<String> getDescription();
		HasValue<Project> getProject();
		HasValue<Double> getTime();
		HasValue<Date> getDate();

		Widget asWidget();
	}

	public CostDetailPresenter(CostServiceAsync costService, HandlerManager eventBus, CostDetailView costForm) {
		this.cost = new Cost();
		this.costService = costService;
		this.eventBus = eventBus;
		this.costForm = costForm;
		bind();
	}

	public CostDetailPresenter(CostServiceAsync costService, HandlerManager eventBus, CostDetailView costForm, Cost cost) {
		this.cost = cost;
		this.costService = costService;
		this.eventBus = eventBus;
		this.costForm = costForm;
		bind();
	}

	public void bind() {
		this.costForm.getSaveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doSave();
			}
		});

		this.costForm.getCancelButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new GoToListEvent());
			}
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(costForm.asWidget());
		initData();
	}
	
	private void initData() {
		costForm.getTheTitle().setValue(cost.getTitle());
		costForm.getDescription().setValue(cost.getDescription());
		costForm.getProject().setValue(cost.getProject());
		costForm.getTime().setValue(cost.getTime());
		costForm.getDate().setValue(cost.getWorkDay());
	}

	private void doSave() {
		cost.setTitle(costForm.getTheTitle().getValue());
		cost.setDescription(costForm.getDescription().getValue());
		cost.setProject(costForm.getProject().getValue());
		cost.setTime(costForm.getTime().getValue());
		cost.setWorkDay(costForm.getDate().getValue());

		costService.save(cost, new AsyncCallback<Void>() {
			public void onSuccess(Void result) {
				eventBus.fireEvent(new GoToListEvent());
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error updating contact");
			}
		});
	}

}
