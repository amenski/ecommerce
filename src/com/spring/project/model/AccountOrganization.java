package com.spring.project.model;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class AccountOrganization extends Account {
	
	
	@Expose
	private String name;

	@Expose
	private String legalStatus;

	@Expose
	private Date establishedDate;

	@Expose
	private String establishedBy;

	@Expose
	private String description;

	
	public AccountOrganization() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLegalStatus() {
		return legalStatus;
	}

	public void setLegalStatus(String legalStatus) {
		this.legalStatus = legalStatus;
	}

	public Date getEstablishedDate() {
		return establishedDate;
	}

	public void setEstablishedDate(Date establishedDate) {
		this.establishedDate = establishedDate;
	}

	public String getEstablishedBy() {
		return establishedBy;
	}

	public void setEstablishedBy(String establishedBy) {
		this.establishedBy = establishedBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AccountOrganization [id=" + this.getId() + ", name=" + name + ", legalStatus=" + legalStatus + ", establishedDate="
				+ establishedDate + ", establishedBy=" + establishedBy + ", description=" + description + "]";
	}

}
