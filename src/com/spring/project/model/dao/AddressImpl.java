package com.spring.project.model.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.project.model.Address;
import com.spring.project.util.DBHandler;

@Repository("addressDao")
public class AddressImpl implements AddressDao {

	@Autowired
	private DBHandler dbHandler;
	
	@Override
	public void create(Address m) {
		dbHandler.create(m);
	}

	@Override
	public void update(Address m) {
		dbHandler.update(m);
	}

	@Override
	public void delete(Address m) {
		dbHandler.delete(m);
	}

	
	
	@Override
	public Address getAddress(int id) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from Address where id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		Address m = (Address) query.uniqueResult();	
		
		session.close();	
	    return m;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAllAddresss() {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from Address m order by m.id";
		Query query = session.createQuery(queryString);
		List<Address> models = (List<Address>) query.list();	
		session.close();	
	    return models;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<Address> getAccountAddresses(int id) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from Address where id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		Set<Address> models = (Set<Address>) query.list();	
		session.close();	
	    return models;
	}
	
	
	
}
