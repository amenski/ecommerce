package com.spring.project.model.dao;

import java.util.List;
import java.util.Set;

import com.spring.project.model.Account;
import com.spring.project.model.AccountWishList;
import com.spring.project.model.AccountWishListId;



public interface AccountWishListDao {
	void create(AccountWishList m);
	AccountWishList getAccountWishList(AccountWishListId wishListId);
	Set<AccountWishList> getAccountWishLists(int id);
	List<AccountWishList> getWishListsByAccount(Account account);
	void update(AccountWishList m);
	void delete(AccountWishList m);
}