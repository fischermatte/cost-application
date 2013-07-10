package com.unisys.ch.jax.cost.gwt.server;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;

import com.unisys.ch.jax.cost.common.model.Cost;
import com.unisys.ch.jax.cost.common.model.Project;
import com.unisys.ch.jax.cost.gwt.client.CostService;

public class CostServiceTest {

	private CostService costService;

	@Before
	public void setUp() throws Exception {
		costService = new CostServiceImpl();
	}

//	@Test
	public void testList() {
		long i = 1;
		List<Cost> list = costService.list(1);
		Assert.assertEquals(list.size(), 10);
		for (Cost cost : list) {
			Assert.assertEquals(cost.getId().longValue(), i++);
			Assert.assertEquals(cost.getProject(), Project.BAFU.name());
			Assert.assertTrue(cost.getTitle().startsWith("Todo"));
			Assert.assertTrue(cost.getDescription().startsWith("Todo"));
		}
	}

}
