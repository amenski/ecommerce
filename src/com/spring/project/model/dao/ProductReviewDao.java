package com.spring.project.model.dao;

import java.util.List;
import java.util.Set;

import com.spring.project.model.Account;
import com.spring.project.model.Category;
import com.spring.project.model.ProductReview;



public interface ProductReviewDao {
	void create(ProductReview m);
	ProductReview getProductReview(int id);
	List<ProductReview> getAllProductReviews();
	void update(ProductReview m);
	void delete(ProductReview m);
	
	Set<ProductReview> getProductReviewsByCategory(Category cat);
	List<ProductReview> getProductReviewsByAccount(Account account);
}