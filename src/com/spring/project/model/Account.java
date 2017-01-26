package com.spring.project.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.google.gson.annotations.Expose;

public class Account {
	@Expose
	private int id;
	
	@NotNull
    @NotEmpty
    @Email
	@Expose
	private String email;

	@NotNull
    @NotEmpty
	private String password;
	
	@Expose
	private String image;

	@NotNull
    @NotEmpty
	@Expose
	private String accountType;

	@NotNull
    @NotEmpty
	@Expose
	private String accountStatus;
	
	//@Expose
	private Set<AccountWishList> wishList = new HashSet<AccountWishList>();
	
	//@Expose
	private Set<Address> addresses = new HashSet<Address>();
	
	private Set<Product> products = new HashSet<Product>();

	public Account() {
		
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}


	public Set<AccountWishList> getWishList() {
		return wishList;
	}

	public void setWishList(Set<AccountWishList> wishList) {
		this.wishList = wishList;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + id + ", email=" + email + ", password=" + password + ", image=" + image
				+ ", accountType=" + accountType + ", accountStatus=" + accountStatus + ", wishList=" + wishList
				+ ", addresses=" + addresses + "]";
	}
	
}
