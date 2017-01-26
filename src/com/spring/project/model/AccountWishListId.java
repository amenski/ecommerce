package com.spring.project.model;

import java.io.Serializable;

public class AccountWishListId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int accountId;
	private int productId;
	
	
	public AccountWishListId() {
		super();
	}


	// an easy initializing constructor
	public AccountWishListId(int accountId, int productId){
	this.accountId = accountId;
	this.productId = productId;
	}
	
	
	
	public int getAccountId() {
		return accountId;
	}



	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}



	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	@Override
	public boolean equals(Object arg0) {
	if(arg0 == null) return false;
	if(!(arg0 instanceof AccountWishListId)) return false;
	AccountWishListId arg1 = (AccountWishListId) arg0;
	return (this.accountId == arg1.getAccountId()) && (this.productId == arg1.getProductId());
	
	}
	@Override
	public int hashCode() {
		int hsCode;
		Long oId = Long.parseLong(String.valueOf(accountId));
		Long pId = Long.parseLong(String.valueOf(productId));
		hsCode = oId.hashCode();
		hsCode = 19 * hsCode+ pId.hashCode();
		return hsCode;
	}

}

