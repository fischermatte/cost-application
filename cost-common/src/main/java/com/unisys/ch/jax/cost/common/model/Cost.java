package com.unisys.ch.jax.cost.common.model;

import java.io.Serializable;

public class Cost implements Serializable {
	
	/** */
	private static final long serialVersionUID = 5929153423371165203L;
	
	Long id;
	String title;
	String description;
	Long workDay;
	Double time;
	Project project;
	
	public Cost() {
	}
	
	public Cost(Long id, String title, String description, Long workDay,
			Double time, Project project) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.workDay = workDay;
		this.time = time;
		this.project = project;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}