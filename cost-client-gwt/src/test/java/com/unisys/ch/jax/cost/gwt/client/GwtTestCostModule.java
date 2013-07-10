package com.unisys.ch.jax.cost.gwt.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.unisys.ch.jax.cost.common.model.Cost;

/**
 * GWT JUnit <b>integration</b> tests must extend GWTTestCase. Using
 * <code>"GwtTest*"</code> naming pattern exclude them from running with
 * surefire during the test phase.
 * 
 * If you run the tests using the Maven command line, you will have to navigate
 * with your browser to a specific url given by Maven. See
 * http://mojo.codehaus.org/gwt-maven-plugin/user-guide/testing.html for
 * details.
 */
public class GwtTestCostModule extends GWTTestCase {

	/**
	 * Must refer to a valid module that sources this class.
	 */
	public String getModuleName() {
		return "com.unisys.ch.jax.cost.gwt.CostModuleJUnit";
	}

	/**
	 * This test will send a request to the server using the greetServer method
	 * in GreetingService and verify the response.
	 */
	public void testGreetingService() {
		CostServiceAsync greetingService = GWT.create(CostService.class);
		ServiceDefTarget target = (ServiceDefTarget) greetingService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "CostModule/cost");

		// Since RPC calls are asynchronous, we will need to wait for a response.
		delayTestFinish(10000);

		// Send a request to the server.
		greetingService.list(1, new AsyncCallback<List<Cost>>() {
			public void onFailure(Throwable caught) {
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(List<Cost> result) {
				assertNotNull(result);
				assertEquals(result.size(), 15);
				
				Cost cost = result.get(0);
				assertEquals(cost.getId().longValue(), 1L);
				assertEquals(cost.getTitle(), "Todo1");
				assertEquals(cost.getDescription(), "Todo1 Description");

				// When we received an answer, we finish the test
				finishTest();
			}
		});
	}

}
