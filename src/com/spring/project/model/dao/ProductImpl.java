package com.spring.project.model.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.project.model.Account;
import com.spring.project.model.Category;
import com.spring.project.model.Orders;
import com.spring.project.model.Product;
import com.spring.project.util.DBHandler;

@Repository("productDao")
public class ProductImpl implements ProductDao {
	
	@Autowired
	private DBHandler dbHandler;
	
	@Override
	public void create(Product m) {
		dbHandler.create(m);
	}

	@Override
	public void update(Product m) {
		dbHandler.update(m);
	}

	@Override
	public void delete(Product m) {
		dbHandler.delete(m);
	}

	
	
	@Override
	public Product getProduct(int id) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from Product where id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		Product m = (Product) query.uniqueResult();	
		
		session.close();	
	    return m;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from Product m order by m.id";
		Query query = session.createQuery(queryString);
		List<Product> models = (List<Product>) query.list();	
		session.close();	
	    return models;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<Product> getProductsByCategory(Category cat) {
		System.out.println("ProductImpl : getProductsByCategory : ID = ");
		
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from Product where category = :cat";
		Query query = session.createQuery(queryString);
		query.setParameter("category", cat);
		Set<Product> models = (Set<Product>) query.list();	
		session.close();	
	    return models;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductsByAccount(Account account) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from Product where account = :account";
		Query query = session.createQuery(queryString);
		query.setParameter("account", account);
		List<Product> models = (List<Product>) query.list();	
		session.close();	
	    return models;
	}
}
