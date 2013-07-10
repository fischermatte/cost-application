package com.unisys.ch.jax.cost.common.entity;

import java.io.Serializable;

public class Cost implements Serializable {
	
	/** */
	private static final long serialVersionUID = 5929153423371165203L;
	
	Long id;
	String title;
	String description;
	Long workDay;
	Double time;
	String costLocation;
	
	public Cost() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Long workDay) {
		this.workDay = workDay;
	}

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public String getCostLocation() {
		return costLocation;
	}

	public void setCostLocation(String costLocation) {
		this.costLocation = costLocation;
	}
}