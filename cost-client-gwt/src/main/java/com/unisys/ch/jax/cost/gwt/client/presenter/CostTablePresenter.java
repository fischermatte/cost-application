package com.unisys.ch.jax.cost.gwt.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.unisys.ch.jax.cost.common.model.Cost;
import com.unisys.ch.jax.cost.gwt.client.CostServiceAsync;
import com.unisys.ch.jax.cost.gwt.client.event.AddCostEvent;
import com.unisys.ch.jax.cost.gwt.client.event.EditCostEvent;
import com.unisys.ch.jax.cost.gwt.client.view.CostTableView;

public class CostTablePresenter implements Presenter {

	private List<Cost> costs;
	private int currentPage = 1;

	public interface CostTable {
		void setData(List<Cost> data, boolean add);

		HasClickHandlers getAddButton();
		HasClickHandlers getDeleteButton();
		HasClickHandlers getLoadButton();
		HasClickHandlers getList();

		int getClickedRow(ClickEvent event);
		List<Integer> getSelectedRows();

		Widget asWidget();
	}

	private final CostServiceAsync costService;
	private final HandlerManager eventBus;
	private final CostTable costTable;

	public CostTablePresenter(CostServiceAsync costService, HandlerManager eventBus, CostTableView costTable) {
		this.costService = costService;
		this.eventBus = eventBus;
		this.costTable = costTable;
	}

	public void bind() {
		costTable.getList().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int selectedRow = costTable.getClickedRow(event);

				if (selectedRow >= 0) {
					Cost costToEdit = costs.get(selectedRow);
					eventBus.fireEvent(new EditCostEvent(costToEdit));
				}
			}
		});
		costTable.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddCostEvent());
			}
		});

		costTable.getDeleteButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deleteSelectedCosts();
			}
		});
		costTable.getLoadButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				fetchCosts(false);
			}
		});
	}

	@Override
	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(costTable.asWidget());
		fetchCosts(true);
	}
	
	private void deleteSelectedCosts() {
		List<Integer> selectedRows = costTable.getSelectedRows();
		List<Long> ids = new ArrayList<Long>();

		for (int i = 0; i < selectedRows.size(); ++i) {
			ids.add(costs.get(selectedRows.get(i)).getId());
		}

		costService.delete(ids, new AsyncCallback<Void>() {
			public void onSuccess(Void result) {
				fetchCosts(true);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error deleting selected costs");
			}
		});
	}

	private void fetchCosts(final boolean reset) {
		if (reset) {
			currentPage = 1;
		}
		costService.list(currentPage, new AsyncCallback<List<Cost>>() {
			public void onSuccess(List<Cost> result) {
				if (reset) {
					costs = result;
				} else {
					costs.addAll(result);
				}
				costTable.setData(result, !reset);
				currentPage++;
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error fetching costs");
			}
		});
	}

}
