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
import com.spring.project.model.ProductReview;
import com.spring.project.util.DBHandler;

@Repository("ProductReviewDao")
public class ProductReviewImpl implements ProductReviewDao {
	
	@Autowired
	private DBHandler dbHandler;
	
	@Override
	public void create(ProductReview m) {
		dbHandler.create(m);
	}

	@Override
	public void update(ProductReview m) {
		dbHandler.update(m);
	}

	@Override
	public void delete(ProductReview m) {
		dbHandler.delete(m);
	}

	
	
	@Override
	public ProductReview getProductReview(int id) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from ProductReview where id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		ProductReview m = (ProductReview) query.uniqueResult();	
		
		session.close();	
	    return m;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductReview> getAllProductReviews() {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from ProductReview m order by m.id";
		Query query = session.createQuery(queryString);
		List<ProductReview> models = (List<ProductReview>) query.list();	
		session.close();	
	    return models;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<ProductReview> getProductReviewsByCategory(Category cat) {
		System.out.println("ProductReviewImpl : getProductReviewsByCategory : ID = ");
		
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from ProductReview where category = :cat";
		Query query = session.createQuery(queryString);
		query.setParameter("category", cat);
		Set<ProductReview> models = (Set<ProductReview>) query.list();	
		session.close();	
	    return models;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductReview> getProductReviewsByAccount(Account account) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from ProductReview where account = :account";
		Query query = session.createQuery(queryString);
		query.setParameter("account", account);
		List<ProductReview> models = (List<ProductReview>) query.list();	
		session.close();	
	    return models;
	}
}
