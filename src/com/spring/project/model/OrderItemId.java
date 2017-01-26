package com.spring.project.model;

import java.io.Serializable;

public class OrderItemId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int orderId;
	private int productId;
	
	public OrderItemId(){}
	
	// an easy initializing constructor
	public OrderItemId(int orderId, int productId){
		this.orderId = orderId;
		this.productId = productId;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	if(!(arg0 instanceof OrderItemId)) return false;
	OrderItemId arg1 = (OrderItemId) arg0;
	return (this.orderId == arg1.getOrderId()) && (this.productId == arg1.getProductId());
	
	}
	@Override
	public int hashCode() {
		int hsCode;
		Long oId = Long.parseLong(String.valueOf(orderId));
		Long pId = Long.parseLong(String.valueOf(productId));
		hsCode = oId.hashCode();
		hsCode = 19 * hsCode+ pId.hashCode();
		return hsCode;
	}

}
