package com.spring.project.model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.project.model.Category;
import com.spring.project.util.DBHandler;

@Repository("categoryDao")
public class CategoryImpl implements CategoryDao {

	@Autowired
	private DBHandler dbHandler;
	
	@Autowired
	private ProductImpl proDao;
	
	@Override
	public void create(Category m) {
		dbHandler.create(m);
	}

	@Override
	public void update(Category m) {
		dbHandler.update(m);
	}

	@Override
	public void delete(Category m) {
		dbHandler.delete(m);
	}

	
	
	@Override
	public Category getCategory(int id) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from Category where id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		Category m = (Category) query.uniqueResult();	
		
		//m.setProducts(proDao.getProductsByCategory(m));
		
		session.close();	
	    return m;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategorys() {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from Category m order by m.id";
		Query query = session.createQuery(queryString);
		List<Category> models = (List<Category>) query.list();	
		session.close();	
	    return models;
	}
	
}
