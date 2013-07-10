package com.unisys.ch.jax.cost.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.unisys.ch.jax.cost.gwt.client.event.GoToListEvent.GoToListEventHandler;

public class GoToListEvent extends GwtEvent<GoToListEventHandler> {

	public static Type<GoToListEventHandler> TYPE = new Type<GoToListEventHandler>();

	@Override
	public Type<GoToListEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GoToListEventHandler handler) {
		handler.onGoToList(this);
	}

	public interface GoToListEventHandler extends EventHandler {
		void onGoToList(GoToListEvent event);
	}
}
