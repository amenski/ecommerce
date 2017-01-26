package com.spring.project.model.dao;

import java.util.List;

import com.spring.project.model.Category;


public interface CategoryDao {
	void create(Category m);
	Category getCategory(int id);
	List<Category> getAllCategorys();
	void update(Category m);
	void delete(Category m);
	
}