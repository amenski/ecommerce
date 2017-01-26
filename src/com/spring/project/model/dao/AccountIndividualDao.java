package com.spring.project.model.dao;

import java.util.List;

import com.spring.project.model.AccountIndividual;



public interface AccountIndividualDao {
	void create(AccountIndividual m);
	AccountIndividual getAccountIndividual(int id);
	List<AccountIndividual> getAllAccountIndividuals();
	void update(AccountIndividual m);
	void delete(AccountIndividual m);
	
}