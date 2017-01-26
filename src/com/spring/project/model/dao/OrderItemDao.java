package com.spring.project.model.dao;

import java.util.Set;

import com.spring.project.model.OrderItem;


public interface OrderItemDao {
	void create(OrderItem m);
	OrderItem getOrderItem(int id);
	void update(OrderItem m);
	void delete(OrderItem m);
	
	Set<OrderItem> getOrderItemsByOrder(int id);
	Set<OrderItem> getOrderItemsByProduct(int id);
	
	
}