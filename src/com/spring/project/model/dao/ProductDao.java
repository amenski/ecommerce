package com.spring.project.model.dao;

import java.util.List;
import java.util.Set;

import com.spring.project.model.Account;
import com.spring.project.model.Category;
import com.spring.project.model.Product;



public interface ProductDao {
	void create(Product m);
	Product getProduct(int id);
	List<Product> getAllProducts();
	void update(Product m);
	void delete(Product m);
	
	Set<Product> getProductsByCategory(Category cat);
	List<Product> getProductsByAccount(Account account);
}