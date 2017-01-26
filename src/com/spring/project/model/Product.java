package com.spring.project.model;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

public class Product {

	@Expose
	private int id;

	@Expose
	private String name;

	@Expose
	private String images;

	@Expose
	private double unitPrice;

	@Expose
	private String atp;

	@Expose
	private String description;

	private Category category;

	//private int vendorId;
	private Account account;

	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	
	@Expose
	private Set<ProductReview> productReviews = new HashSet<ProductReview>();

	public Product() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getAtp() {
		return atp;
	}

	public void setAtp(String atp) {
		this.atp = atp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	

	public Set<ProductReview> getProductReviews() {
		return productReviews;
	}

	public void setProductReviews(Set<ProductReview> productReviews) {
		this.productReviews = productReviews;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}

