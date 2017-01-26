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
import com.spring.project.model.AccountWishList;
import com.spring.project.model.AccountWishListId;
import com.spring.project.model.Orders;
import com.spring.project.model.Product;
import com.spring.project.util.DBHandler;

@Repository("accountWishListDao")
public class AccountWishListImpl implements AccountWishListDao {

	@Autowired
	private DBHandler dbHandler;
	
	@Autowired
	private ProductImpl productimpl;
	
	@Override
	public void create(AccountWishList m) {
		dbHandler.create(m);
	}

	@Override
	public void update(AccountWishList m) {
		dbHandler.update(m);
	}

	@Override
	public void delete(AccountWishList m) {
		dbHandler.delete(m);
	}

	
	
	@Override
	public AccountWishList getAccountWishList(AccountWishListId wishListId) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from AccountWishList where accountWishListId = :wishListId";
		Query query = session.createQuery(queryString);
		query.setParameter("wishListId", wishListId);
		AccountWishList m = (AccountWishList) query.uniqueResult();	
		
		session.close();	
	    return m;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<AccountWishList> getAccountWishLists(int id) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from AccountWishList where accountWishListId.accountId = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		Set<AccountWishList> models = (Set<AccountWishList>) query.list();	
		session.close();	
	    return models;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AccountWishList> getWishListsByAccount(Account account) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from AccountWishList where  accountWishListId.accountId =:id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", account.getId());

		List<AccountWishList> list = (List<AccountWishList>) query.list();

		session.close();	
	    return list;
	}
	
}
