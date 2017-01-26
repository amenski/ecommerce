package com.spring.project.model.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.project.model.Account;
import com.spring.project.model.Orders;
import com.spring.project.util.DBHandler;

@Repository("ordersDao")
public class OrdersImpl implements OrdersDao {

	@Autowired
	private DBHandler dbHandler;
	
	@Override
	public void create(Orders m) {
		dbHandler.create(m);
	}

	@Override
	public void update(Orders m) {
		dbHandler.update(m);
	}

	@Override
	public void delete(Orders m) {
		dbHandler.delete(m);
	}

	
	
	@Override
	public Orders getOrders(int id) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from Orders where id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		Orders m = (Orders) query.uniqueResult();	
		
		session.close();	
	    return m;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> getAllOrderss() {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from Orders m order by m.id";
		Query query = session.createQuery(queryString);
		List<Orders> models = (List<Orders>) query.list();	
		session.close();	
	    return models;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> getOrdersByAccount(Account account) {
		Session session = dbHandler.getSessionFactory().openSession();
		Criteria cr = session.createCriteria(Orders.class);
		cr.add(Restrictions.or(Restrictions.eq("remark", 0), Restrictions.eq("remark", 3)));
		cr.add(Restrictions.eq("account", account));

		List<Orders> models = cr.list();	
		System.out.println(models.size());
		session.close();	
	    return models;
	}
	
}
