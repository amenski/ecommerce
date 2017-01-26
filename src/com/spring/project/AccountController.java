package com.spring.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.spring.project.model.Account;
import com.spring.project.model.AccountIndividual;
import com.spring.project.model.AccountOrganization;
import com.spring.project.model.dao.AccountImpl;
import com.spring.project.model.dao.OrdersImpl;
import com.spring.project.model.dao.ProductImpl;

@Controller
@RequestMapping(value="/")
public class AccountController {
	private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	@Autowired
	private AccountImpl accDao;
	
	@Autowired
	private ProductImpl productDao;

	@Autowired
	private OrdersImpl orderDao;
	
	//
	/*
	 * Update Privacy
	 * =======
	 * - Set the return json 
	 * - Select by account by log in session id 
	 * - Compare the current password with the password in the account object
	 * 		- if the password match
	 * 			- update the account password with the new one
	 * 			- update the status of the return json to Done
	 * - Send the return json
	 * 
	 * */
	@RequestMapping(value="UpdatePrivacy", method = RequestMethod.POST)
    public ResponseEntity<String> updateAccount(@RequestParam String data, HttpSession sessionObj) {
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("Status", "Error");
		returnMap.put("Data", "An account with this password does not exist.");
		
		
		Map<String, Object> map = gson.fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
		Account acc = accDao.getAccount((Integer) sessionObj.getAttribute("loginId"));
		
		if(acc.getPassword().equals((String)map.get("currentPassword"))){
			acc.setPassword((String)map.get("newPassword"));
			accDao.update(acc);
			returnMap.put("Status", "Done");
			returnMap.put("Data", "You have successfully changed your password.");
		}
	
		String returnJson = gson.toJson(returnMap);
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new ResponseEntity<String>(returnJson, responseHeaders, HttpStatus.CREATED);
    }
	
	/*
	 * Sign Up
	 * =======
	 * - Check if the email is not used before
	 * - Encrypt password
	 * - Validate and Insert to database
	 * - Send Confirmation by email
	 * - Sign in with new account
	 * 
	 * */
	@RequestMapping(value="Register", method = RequestMethod.POST)
    public ResponseEntity<String> signUp(@RequestParam String data, @RequestParam String entity) {
		String returnJson = "{\"Status\":\"Error\", \"Data\":\"An account with this email address already exists.\"}";
		
		Map<String, Object> map = gson.fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
	
		if(entity.equals("Individual")){
			System.out.println(data);
			AccountIndividual accInd = gson.fromJson(data, AccountIndividual.class); 
			accInd.setPassword((String)map.get("password"));
			if(!accDao.accountExists(accInd.getEmail())){
				accDao.createIndividualAccount(accInd);
				returnJson = "{\"Status\":\"Done\", \"Data\":"+gson.toJson(accInd)+"}";
			}
		}else{
			AccountOrganization accOrg = gson.fromJson(data, AccountOrganization.class);
			accOrg.setPassword((String)map.get("password"));
			if(!accDao.accountExists(accOrg.getEmail())){
				accDao.createOrganizationAccount(accOrg);
				returnJson = "{\"Status\":\"Done\", \"Data\":"+gson.toJson(accOrg)+"}";
			}
		}
	
		
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new ResponseEntity<String>(returnJson, responseHeaders, HttpStatus.CREATED);
    }
	
	
	/*
	 * Sign In
	 * ========
	 * - Select account by email
	 * - Check if password match
	 * 		- Set Log_In_Status session to true
	 * 		- Set Log_Id to the selected id
	 * - Get the Account detail
	 * - return account detail in json
	 * 
	 * */
	@RequestMapping(value="LogIn", method = RequestMethod.POST)
    public ResponseEntity<String> signIn(@RequestParam String data, HttpSession sessionObj) {
		String returnJson = "{\"Status\":\"Error\", \"Data\":\"You have entered incorrect email or password.\"}";
		
		Map<String, Object> map = gson.fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
		if(accDao.accountExists((String)map.get("email"))){
			Account acc = accDao.getAccountByEmail((String)map.get("email"));
			if(acc.getPassword().equals((String)map.get("password"))){
				sessionObj.setAttribute("loginStatus" , true);
				sessionObj.setAttribute("loginId" , acc.getId());
				returnJson = "{\"Status\":\"Done\", \"Data\":"+gson.toJson(acc)+"}";
				System.out.println(returnJson);
			}
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new ResponseEntity<String>(returnJson, responseHeaders, HttpStatus.CREATED);
	}
	
	/*
	 * Sign Out
	 * =======
	*/
	@RequestMapping(value="LogOut", method = RequestMethod.GET)
	public String signOut(Model model, HttpSession sessionObj)
    {
    	sessionObj.setAttribute("loginStatus" , false);
    	sessionObj.setAttribute("loginId" , 0);
        return "home";
	}

	/*
	 * Get Account Data
	 * ================
	 * - Check for the Account type
	 * 		- if purchase or both
	 * 			- get the orders
	 * 			- get the wish lists
	 * 		- if sale or both
	 * 			- get the products
	 * 
	 * */
	@RequestMapping(value="GetAccountData", method = RequestMethod.GET)
    public ResponseEntity<String> getAccountDataData(HttpSession sessionObj) {
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("Status", "Error");
		String json = gson.toJson(errorMap);
		
		json = gson.toJson(accDao.getAccountDetail((Integer)sessionObj.getAttribute("loginId")));
		System.out.println(json);
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new ResponseEntity<String>(json, responseHeaders, HttpStatus.CREATED);
    }
	
	
}
