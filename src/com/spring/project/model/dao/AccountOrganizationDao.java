package com.spring.project.model.dao;

import java.util.List;

import com.spring.project.model.AccountOrganization;


public interface AccountOrganizationDao {
	void create(AccountOrganization m);
	AccountOrganization getAccountOrganization(int id);
	List<AccountOrganization> getAllAccountOrganizations();
	void update(AccountOrganization m);
	void delete(AccountOrganization m);
	
}