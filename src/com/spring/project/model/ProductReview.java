package com.spring.project.model;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class ProductReview {
	@Expose
	private int id;
	@Expose
	private Date date;
	@Expose
	private String comment;

	private Product product;
	@Expose
	private Account account;

	public ProductReview() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
