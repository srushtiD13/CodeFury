package com.hsbc.entity;

import java.sql.Date;

public class Bug {
	private int uniqueId;
	private String bugName;
	private String description;
	private int projectId;
	private int createdBy;
	private Date openDate;
	private int assingedTo;
	private int closedBy;
	private Date closeDate;
	private String status;
	private String severityLevel;
	private String markedForClosing;
	public int getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getBugName() {
		return bugName;
	}
	public void setBugName(String bugName) {
		this.bugName = bugName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public int getAssingedTo() {
		return assingedTo;
	}
	public void setAssingedTo(int assingedTo) {
		this.assingedTo = assingedTo;
	}
	public int getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(int closedBy) {
		this.closedBy = closedBy;
	}
	public Date getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSeverityLevel() {
		return severityLevel;
	}
	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}
	public String getMarkedForClosing() {
		return markedForClosing;
	}
	public void setMarkedForClosing(String markedForClosing) {
		this.markedForClosing = markedForClosing;
	}
	
	
}