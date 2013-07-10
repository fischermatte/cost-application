package com.unisys.ch.jax.cost.gwt.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.unisys.ch.jax.cost.common.model.Cost;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("cost")
public interface CostService extends RemoteService {
	List<Cost> list(int page);
	void delete(List<Long> ids);
	void save(Cost cost);
}
