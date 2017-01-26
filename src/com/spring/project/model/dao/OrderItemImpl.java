package com.spring.project.model.dao;

import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.project.model.OrderItem;
import com.spring.project.util.DBHandler;

@Repository("orderItemDao")
public class OrderItemImpl implements OrderItemDao {

	@Autowired
	private DBHandler dbHandler;
	
	@Override
	public void create(OrderItem m) {
		dbHandler.create(m);
	}

	@Override
	public void update(OrderItem m) {
		dbHandler.update(m);
	}

	@Override
	public void delete(OrderItem m) {
		dbHandler.delete(m);
	}

	
	
	@Override
	public OrderItem getOrderItem(int id) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from OrderItem where id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		OrderItem m = (OrderItem) query.uniqueResult();	
		
		session.close();	
	    return m;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<OrderItem> getOrderItemsByProduct(int id) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from OrderItem where product_id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("product_id", id);
		Set<OrderItem> models = (Set<OrderItem>) query.list();	
		session.close();	
	    return models;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<OrderItem> getOrderItemsByOrder(int id) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from OrderItem where order_id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("order_id", id);
		Set<OrderItem> models = (Set<OrderItem>) query.list();	
		session.close();	
	    return models;
	}
	
}
