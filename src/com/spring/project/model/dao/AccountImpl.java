package com.spring.project.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.project.model.Account;
import com.spring.project.model.AccountIndividual;
import com.spring.project.model.AccountOrganization;
import com.spring.project.model.AccountWishList;
import com.spring.project.util.DBHandler;

@Repository("accountDao")
public class AccountImpl implements AccountDao {

	@Autowired
	private DBHandler dbHandler;

	@Autowired
	private ProductImpl productDao;
	
	@Autowired
	private OrdersImpl orderDao;
	
	@Autowired
	private AccountWishListImpl whishListDao;
	
	@Override
	public void create(Account m) {
		dbHandler.create(m);
		
	}
	
	@Override
	public void createIndividualAccount(AccountIndividual m) {
		dbHandler.create(m);
	}
	
	@Override
	public void createOrganizationAccount(AccountOrganization m) {
		dbHandler.create(m);
	}

	@Override
	public void update(Account m) {
		dbHandler.update(m);
	}


	@Override
	public void updateIndividualAccount(AccountIndividual m) {
		dbHandler.update(m);
	}
	
	@Override
	public void updateOrganizationAccount(AccountOrganization m) {
		dbHandler.update(m);
	}
	
	@Override
	public void delete(Account m) {
		dbHandler.delete(m);
	}

	@Override
	public Account getAccount(int id) {
		Session session = dbHandler.getSessionFactory().openSession();
		
		String queryString = "from Account where id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		Account m = (Account) query.uniqueResult();	
		
		session.close();	
	    return m;
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
	
	
	@Override
	public Map<String, List> getAccountDetail(int id) {
		Map<String, List> accDtl = new HashMap<String, List>();
		
		Account account = getAccount(id);
		System.out.println("getAccountDetail : "+account.toString());
		
		if(account.getAccountType().equals("Purchase products") || account.getAccountType().equals("Both")){
			// Get orders by account id
			accDtl.put("MyOrders", orderDao.getOrdersByAccount(account));
			
			// Get wish list
			accDtl.put("MyWishList", whishListDao.getWishListsByAccount(account));
		}
		
		if(account.getAccountType().equals("Sale Products") || account.getAccountType().equals("Both")){
			// Get products by product id
			accDtl.put("MyProducts", productDao.getProductsByAccount(account));
		}

	    return accDtl;
	}
	
	@Override
	public Account  getAccountByEmail(String email) {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from Account where email = :email";
		Query query = session.createQuery(queryString);
		query.setParameter("email", email);
		Account models = (Account) query.uniqueResult();	
		session.close();	
	    return models;
	}
	
	@Override
	public boolean accountExists(String email){
		if(this.getAccountByEmail(email) != null)
			return true;
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAllAccounts() {
		Session session = dbHandler.getSessionFactory().openSession();
		String queryString = "from Account m order by m.id";
		Query query = session.createQuery(queryString);
		List<Account> models = (List<Account>) query.list();	
		session.close();	
	    return models;
	}
	
	
	
}
