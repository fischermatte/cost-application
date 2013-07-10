package com.unisys.ch.jax.cost.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.unisys.ch.jax.cost.gwt.client.event.AddCostEvent.AddCostEventHandler;

public class AddCostEvent extends GwtEvent<AddCostEventHandler> {

	public static Type<AddCostEventHandler> TYPE = new Type<AddCostEventHandler>();

	@Override
	public Type<AddCostEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddCostEventHandler handler) {
		handler.onAddCost(this);
	}

	public interface AddCostEventHandler extends EventHandler {
		void onAddCost(AddCostEvent event);
	}
}
