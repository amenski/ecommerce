package com.spring.project.model;

import com.google.gson.annotations.Expose;

public class Address {
	@Expose
	private int id;

	@Expose
	private String email;

	@Expose
	private String phone;

	@Expose
	private String postalCode;

	@Expose
	private String street;

	@Expose
	private String city;

	@Expose
	private String stateProvince;

	@Expose
	private String country;
	
	private Account account;


	public Address() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", email=" + email + ", phone=" + phone + ", postalCode=" + postalCode
				+ ", street=" + street + ", city=" + city + ", stateProvince=" + stateProvince + ", country=" + country
				+ ", account=" + "]";
	}

}
