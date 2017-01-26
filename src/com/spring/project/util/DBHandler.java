package com.spring.project.util;


import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DBHandler {
	
	public enum Operation {CREATE, UPDATE, DELETE};
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public DBHandler() {
		
	}
	

	public void performOperation(Object object, Operation op) {
		
		Session session = sessionFactory.openSession();
		Transaction tx= null;
		try {
			tx = session.beginTransaction();
			switch (op) {
			case CREATE:
				session.save(object);
				break;
			case UPDATE:
				session.update(object);
				break;
			case DELETE:
				session.delete(object);
				break;
			default:
				break;
			}
			
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		session.close();
		
	}
	
	public void create(Object obj) {
		performOperation(obj, Operation.CREATE);
	}

	public void delete(Object obj) {
		performOperation(obj, Operation.DELETE);
	}

	public void update(Object obj) {
		performOperation(obj, Operation.UPDATE);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
