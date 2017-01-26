package com.spring.project.model.dao;

import java.util.List;
import java.util.Map;

import com.spring.project.model.Account;
import com.spring.project.model.AccountIndividual;
import com.spring.project.model.AccountOrganization;




public interface AccountDao {
	void create(Account m);
	void createIndividualAccount(AccountIndividual m);
	void createOrganizationAccount(AccountOrganization m);
	Account getAccount(int id);
	AccountIndividual getAccountIndividual(int id);
	AccountOrganization getAccountOrganization(int id);
	Map<String, List> getAccountDetail(int id);
	List<Account> getAllAccounts();
	void update(Account m);
	void updateIndividualAccount(AccountIndividual m);
	void updateOrganizationAccount(AccountOrganization m);
	void delete(Account m);
	

	Account getAccountByEmail(String email);
	boolean accountExists(String email);
	
}