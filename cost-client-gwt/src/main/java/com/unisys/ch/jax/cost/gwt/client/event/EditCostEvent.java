package com.unisys.ch.jax.cost.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.unisys.ch.jax.cost.common.model.Cost;
import com.unisys.ch.jax.cost.gwt.client.event.EditCostEvent.EditCostEventHandler;

public class EditCostEvent extends GwtEvent<EditCostEventHandler> {

	public static Type<EditCostEventHandler> TYPE = new Type<EditCostEventHandler>();

	private final Cost cost;

	@Override
	public Type<EditCostEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditCostEventHandler handler) {
		handler.onEditCost(this);
	}

	public EditCostEvent(Cost cost) {
		this.cost = cost;
	}

	public Cost getCost() {
		return cost;
	}

	public interface EditCostEventHandler extends EventHandler {
		void onEditCost(EditCostEvent event);
	}
}
