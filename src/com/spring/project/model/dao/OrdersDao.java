package com.spring.project.model.dao;

import java.util.List;
import java.util.Set;

import com.spring.project.model.Account;
import com.spring.project.model.Orders;



public interface OrdersDao {
	void create(Orders m);
	Orders getOrders(int id);
	List<Orders> getAllOrderss();
	void update(Orders m);
	void delete(Orders m);
	
	List<Orders> getOrdersByAccount(Account account);
}