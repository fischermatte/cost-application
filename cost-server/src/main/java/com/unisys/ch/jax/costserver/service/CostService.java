package com.unisys.ch.jax.costserver.service;

import java.util.Collection;

import com.unisys.ch.jax.cost.common.model.Cost;

public interface CostService {

	public Cost findById(Long id);
	
	public Collection<Cost> findAll();
	
	public Collection<Cost> findAll(int start, int amount);
	
	public Cost save(Cost todo);

	public void delete(Long id);
}
