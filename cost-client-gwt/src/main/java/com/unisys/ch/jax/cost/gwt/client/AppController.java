package com.unisys.ch.jax.cost.gwt.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.unisys.ch.jax.cost.gwt.client.event.AddCostEvent;
import com.unisys.ch.jax.cost.gwt.client.event.AddCostEvent.AddCostEventHandler;
import com.unisys.ch.jax.cost.gwt.client.event.EditCostEvent;
import com.unisys.ch.jax.cost.gwt.client.event.EditCostEvent.EditCostEventHandler;
import com.unisys.ch.jax.cost.gwt.client.event.GoToListEvent;
import com.unisys.ch.jax.cost.gwt.client.event.GoToListEvent.GoToListEventHandler;
import com.unisys.ch.jax.cost.gwt.client.presenter.CostDetailPresenter;
import com.unisys.ch.jax.cost.gwt.client.presenter.CostTablePresenter;
import com.unisys.ch.jax.cost.gwt.client.presenter.Presenter;
import com.unisys.ch.jax.cost.gwt.client.view.CostDetailView;
import com.unisys.ch.jax.cost.gwt.client.view.CostTableView;

public class AppController implements ValueChangeHandler<String> {

	CostServiceAsync costService;
	HandlerManager eventBus;
	HasWidgets container;

	public AppController(CostServiceAsync costService, HandlerManager eventBus) {
		this.costService = costService;
		this.eventBus = eventBus;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(AddCostEvent.TYPE, new AddCostEventHandler() {
			public void onAddCost(AddCostEvent event) {
				History.newItem("add");
			}
		});
		eventBus.addHandler(EditCostEvent.TYPE, new EditCostEventHandler() {
			public void onEditCost(EditCostEvent event) {
				History.newItem("edit", false);
				Presenter presenter = new CostDetailPresenter(costService, eventBus, new CostDetailView(), event.getCost());
				presenter.go(container);
			}
		});
		eventBus.addHandler(GoToListEvent.TYPE, new GoToListEventHandler() {
			public void onGoToList(GoToListEvent event) {
				History.newItem("list");
			}
		});
	}

	public void go(HasWidgets container) {
		this.container = container;
		if ("".equals(History.getToken())) {
			History.newItem("list");
		} else {
			History.fireCurrentHistoryState();
		}

	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		if (token != null) {
			Presenter presenter = null;
			if (token.equals("list")) {
				presenter = new CostTablePresenter(costService, eventBus, new CostTableView());
			} else if (token.equals("add") || token.equals("edit")) {
				presenter = new CostDetailPresenter(costService, eventBus, new CostDetailView());
			}
			if (presenter != null) {
				presenter.go(container);
			}
		}

	}

	public CostServiceAsync getCostService() {
		return costService;
	}

	public void setCostService(CostServiceAsync costService) {
		this.costService = costService;
	}

	public HandlerManager getEventBus() {
		return eventBus;
	}

	public void setEventBus(HandlerManager eventBus) {
		this.eventBus = eventBus;
	}

}
