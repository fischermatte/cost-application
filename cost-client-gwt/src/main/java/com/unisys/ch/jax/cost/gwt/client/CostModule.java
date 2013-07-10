package com.unisys.ch.jax.cost.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CostModule implements EntryPoint {
	public void onModuleLoad() {
		CostServiceAsync costService = GWT.create(CostService.class);
		Messages messages = GWT.create(Messages.class);
	    HandlerManager eventBus = new HandlerManager(null);
	    AppController appViewer = new AppController(costService, eventBus, messages);
	    appViewer.go(RootPanel.get());
	}
}
