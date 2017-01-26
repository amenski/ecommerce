package com.spring.project.model;

import com.google.gson.annotations.Expose;

public class OrderItem {

	private OrderItemId orderItemId;

	@Expose
	private int quantity;

	@Expose
	private double total;
	
	
	// private Orders order;

	@Expose
	private Product product; // For displaying the product name and unit price

	public OrderItem() {
	}

	public OrderItemId getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(OrderItemId orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

//	public Orders getOrder() {
//		return order;
//	}
//
//	public void setOrder(Orders order) {
//		this.order = order;
//	}
//
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
//
//	@Override
//	public String toString() {
//		return "OrderItem [orderItemId=" + orderItemId + ", quantity=" + quantity + ", total=" + total + ", order="
//				+ order + ", product=" + product + "]";
//	}

	

}
