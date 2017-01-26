package com.spring.project.model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.spring.project.model.AccountOrganization;
import com.spring.project.util.DBHandler;


public class AccountOrganizationImpl implements AccountOrganizationDao {

	private DBHandler dbHandler;
	
	public DBHandler getDbHandler() {
		return dbHandler;
	}

	public void setDbHandler(DBHandler dbHandler) {
		this.dbHandler = dbHandler;
	}
	
	@Override
	public void create(AccountOrganization m) {
		dbHandler.create(m);
	}

	@Override
	public void update(AccountOrganization m) {
		dbHandler.update(m);
	}

	@Override
	public void delete(AccountOrganization m) {
		dbHandler.delete(m);
	}

	
	
	@Override
	public AccountOrganization getAccountOrganization(int id) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from AccountOrganization where id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		AccountOrganization m = (AccountOrganization) query.uniqueResult();	
		
		session.close();	
	    return m;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AccountOrganization> getAllAccountOrganizations() {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from AccountOrganization m order by m.id";
		Query query = session.createQuery(queryString);
		List<AccountOrganization> models = (List<AccountOrganization>) query.list();	
		session.close();	
	    return models;
	}
	
}
