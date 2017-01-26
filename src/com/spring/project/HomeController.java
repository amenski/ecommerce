package com.spring.project;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.project.model.Account;
import com.spring.project.model.dao.AccountImpl;
import com.spring.project.model.dao.CategoryImpl;

@Controller
@RequestMapping("/")
@SessionAttributes({"loginStatus", "loginId"})
public class HomeController {
	private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	
	@Autowired
	private AccountImpl accDao;
	
	@Autowired
	private CategoryImpl catDao;
	
	/*
	 * 
	 * TODO 1 Done : Orders: Add View Detail, Cancel Order, Remove Order
	 * TODO 2 : Products : Upload Image
	 * TODO 3 Done : Make Checkout : Create A confirmation message, clean the cart, 
	 * TODO 4 : Portoflio :  Styling the display, Enable Editing the profile data
	 * TODO 5 Done: Home : Display a review data
	 * 
	 * */
	
	@RequestMapping(method = RequestMethod.GET)
    public String sayHelloHome(Model model, HttpSession sessionObj)
    {
    	if(sessionObj.getAttribute("loginStatus") == null) {
    		sessionObj.setAttribute("loginStatus" , false);
    		sessionObj.setAttribute("loginId" , 0);
    	}
        return "home";
    }
	
	@RequestMapping(value="LoadLayout", method=RequestMethod.GET)
    public String getSubView( Model model ) {
        return "usr_layout";
    }
	
	/*
	 * Get Data
	 * =========
	 * - ProductCategory
	 * 		- Products
	 * - Home Adver Category Id
	 * - Get User Detail (Log in status is true)
	 * 		- Customer
	 * 			- Profile
	 * 			- Get orders
	 * 			- Get Wish List
	 * 		- Vendor
	 * 			- Profile
	 * 			- Get Products
	 * 				- Product Detail
	 * 				- Sales
	 * 
	 * */
	
	@RequestMapping(value="GetData", method = RequestMethod.GET)
    public ResponseEntity<String> provideData(HttpSession sessionObj) {
		String json = "{";
				
		json += "\"Categories\":"+gson.toJson(catDao.getAllCategorys())+", "+
				"\"LogedIn\":"+(boolean) sessionObj.getAttribute("loginStatus");
		if((boolean) sessionObj.getAttribute("loginStatus")){
			Account acc = accDao.getAccount((Integer)sessionObj.getAttribute("loginId"));
			json += ", \"AccountDetail\":"+ gson.toJson(acc);
		}
		
		json += "}";
		System.out.println(json);
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new ResponseEntity<String>(json, responseHeaders, HttpStatus.CREATED);
    }
	
	
	
}
