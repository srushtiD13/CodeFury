package com.hsbc.entity;

import java.util.List;

public class Project {
	private int projectId;
	private String projectName;
	private String description;
	private String startDate;
	private String status;
	private int testerId;
	private int managerId;
	private List<Integer> developerId;

	public Project() {
		
	}
	public Project(int projectId, String projectName, String description, String startDate, String status) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.startDate = startDate;
		this.status = status;
	}
	
	public Project(String projectName, String description, String startDate, String status, int testerId, int managerId,
			List<Integer> developerId) {
		super();
		this.projectName = projectName;
		this.description = description;
		this.startDate = startDate;
		this.status = status;
		this.testerId = testerId;
		this.managerId = managerId;
		this.developerId = developerId;
	}


	public Project(int projectId, String projectName, String description, String startDate, String status, int testerId,
			int managerId, List<Integer> developerId) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.startDate = startDate;
		this.status = status;
		this.testerId = testerId;
		this.managerId = managerId;
		this.developerId = developerId;
	}

	
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public int getTesterId() {
		return testerId;
	}


	public void setTesterId(int testerId) {
		this.testerId = testerId;
	}


	public int getManagerId() {
		return managerId;
	}


	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}


	public List<Integer> getDeveloperId() {
		return developerId;
	}


	public void setDeveloperId(List<Integer> developerId) {
		this.developerId = developerId;
	}


}
