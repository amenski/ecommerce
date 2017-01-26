package com.spring.project.model.dao;

import java.util.List;
import java.util.Set;

import com.spring.project.model.Address;


public interface AddressDao {
	void create(Address m);
	Address getAddress(int id);
	List<Address> getAllAddresss();
	void update(Address m);
	void delete(Address m);
	
	Set<Address> getAccountAddresses(int id);
}