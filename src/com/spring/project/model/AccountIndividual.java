package com.spring.project.model;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class AccountIndividual extends Account {
	@Expose
	private String name;

	@Expose
	private String middleName;

	@Expose
	private String lastName;

	@Expose
	private Date dob;

	@Expose
	private String gender;

	public AccountIndividual() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "AccountIndividual [first_name=" + this.name + ", middle_name=" + middleName + ", last_name=" + lastName
				+ ", dob=" + dob + ", gender=" + gender + "]";
	}

}
