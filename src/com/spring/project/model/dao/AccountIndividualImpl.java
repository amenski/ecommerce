package com.spring.project.model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.project.model.AccountIndividual;
import com.spring.project.util.DBHandler;

@Repository("accountIndividualDao")
public class AccountIndividualImpl implements AccountIndividualDao {

	@Autowired
	private DBHandler dbHandler;
	
	@Override
	public void create(AccountIndividual m) {
		dbHandler.create(m);
	}

	@Override
	public void update(AccountIndividual m) {
		dbHandler.update(m);
	}

	@Override
	public void delete(AccountIndividual m) {
		dbHandler.delete(m);
	}
	
	@Override
	public AccountIndividual getAccountIndividual(int id) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from AccountIndividual where id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		AccountIndividual m = (AccountIndividual) query.uniqueResult();	
		
		session.close();	
	    return m;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AccountIndividual> getAllAccountIndividuals() {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from AccountIndividual m order by m.id";
		Query query = session.createQuery(queryString);
		List<AccountIndividual> models = (List<AccountIndividual>) query.list();	
		session.close();	
	    return models;
	}
	
}
