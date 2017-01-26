package com.spring.project.model;

import java.util.Set;

import com.google.gson.annotations.Expose;

public class AccountWishList {

	
	private AccountWishListId accountWishListId;

	@Expose
	private Product product; 
	
	public AccountWishList() {
	}

	public AccountWishListId getAccountWishListId() {
		return accountWishListId;
	}

	public void setAccountWishListId(AccountWishListId accountWishListId) {
		this.accountWishListId = accountWishListId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
}
