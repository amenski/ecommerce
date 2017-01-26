package com.spring.project;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.spring.project.model.Account;
import com.spring.project.model.AccountIndividual;
import com.spring.project.model.AccountOrganization;
import com.spring.project.model.AccountWishList;
import com.spring.project.model.AccountWishListId;
import com.spring.project.model.OrderItem;
import com.spring.project.model.OrderItemId;
import com.spring.project.model.Orders;
import com.spring.project.model.Product;
import com.spring.project.model.dao.AccountImpl;
import com.spring.project.model.dao.AccountWishListImpl;
import com.spring.project.model.dao.OrderItemImpl;
import com.spring.project.model.dao.OrdersImpl;
import com.spring.project.model.dao.ProductImpl;
import com.spring.project.util.MDate;

@Controller
@RequestMapping("/")
public class CustomerController {
	private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	@Autowired
	private AccountImpl accDao;

	@Autowired
	private OrdersImpl orderDao;

	@Autowired
	private OrderItemImpl orderItemDao;

	@Autowired
	private AccountWishListImpl wishListDao;
	

	@Autowired
	private ProductImpl productDao;
	
	
	//Save the uploaded file to this folder
    private String UPLOADED_FOLDER ;
    

	@RequestMapping(value="UpdatePortfolio", method = RequestMethod.GET)
    public ResponseEntity<String> updatePortfolio(@RequestParam String data, @RequestParam String entity, HttpSession sessionObj) {
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("Status", "An account with this email address already exists.");
		String returnJson = gson.toJson(errorMap);
		
		Map<String, Object> map = gson.fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
		Account acc = accDao.getAccount((Integer)sessionObj.getAttribute("loginId"));
		
		String accountJson = "";
		if(entity.equals("Individual")){
			AccountIndividual accInd = accDao.getAccountIndividual(acc.getId());
			accInd.setName((String)map.get("name"));
			accInd.setLastName((String)map.get("lastName"));
			accInd.setEmail((String)map.get("email"));
			String img = (String) map.get("image");
			if(!img.equals("")){
				accInd.setImage(img);
			}
			
			String dob = (String) map.get("dob");
			if(!dob.equals("")){
				MDate rDate = new MDate(dob);
				accInd.setDob(rDate.getDateObject());
			}
			accInd.setGender((String)map.get("gender"));
			//if(!accDao.accountExists(accInd.getEmail())){
				accDao.updateIndividualAccount(accInd);
				accountJson = gson.toJson(accInd);
			//}
		}else{
			
			AccountOrganization accOrg = accDao.getAccountOrganization(acc.getId());
			String img = (String) map.get("image");
			if(!img.equals("")){
				accOrg.setImage(img);
			}
			accOrg.setName((String)map.get("name"));
			accOrg.setLegalStatus((String)map.get("legalStatus"));
			
			String establishedDate = (String) map.get("establishedDate");
			if(!establishedDate.equals("")){
				MDate rDate = new MDate(establishedDate);
				accOrg.setEstablishedDate(rDate.getDateObject());
			}
			accOrg.setEstablishedBy((String)map.get("establishedBy"));
			accOrg.setDescription((String)map.get("description"));
			//if(!accDao.accountExists(accOrg.getEmail())){
				accDao.updateOrganizationAccount(accOrg);
				accountJson = gson.toJson(accOrg);
			//}
		}
		

		returnJson = "{\"Status\":\"Done\", \"Data\":{\"Account\":"+accountJson+
				", \"AccountDetail\":"+gson.toJson(accDao.getAccountDetail((Integer)sessionObj.getAttribute("loginId")))+"}}";

	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new ResponseEntity<String>(returnJson, responseHeaders, HttpStatus.CREATED);
		
	}
    
	/*
	 * Manage Profile
	 * - Add and Remove Address
	 * - Change Password
	 * - Update Account Details
	 * 
	 */
	@RequestMapping(value="UploadLogo", method = RequestMethod.POST)
	public String uploadLogo(@RequestParam("logo_file") MultipartFile file,
            RedirectAttributes redirectAttributes, HttpSession sessionObj) {
		
		String dir = "//Users//Aman//Documents//workspace//spring-project-1//WebContent//webresources//file_upload//"; 
		System.out.println(dir);
        try{   
        	byte[] bytes = file.getBytes();
            Path path = Paths.get(dir + file.getOriginalFilename());
            Files.write(path, bytes);
	       // ScriptEngine js = new ScriptEngineManager().getEngineByName("JavaScript");
	        redirectAttributes.addFlashAttribute("message", file.getOriginalFilename());
        }catch(Exception e){System.out.println(e);}  

	        
        return "redirect:/uploadLogoStatus";
	}
	
	@GetMapping("/uploadLogoStatus")
    public String uploadLogoStatus() {
        return "uploadLogoStatus";
    }
	
	/*
	 * Manage Orders
	 * =============
	 * - Add Order
	 * - Edit Order
	 * - Delete Order
	 * 
	 * */
	@RequestMapping(value="CreateNewOrder", method = RequestMethod.POST)
    public ResponseEntity<String> createNewOrder(@RequestParam String data, HttpSession sessionObj) {
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("Status", "An account with this email address already exists.");
		String returnJson = gson.toJson(errorMap);
		
		Map<String, Object> map = gson.fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
		Account acc = accDao.getAccount((Integer)sessionObj.getAttribute("loginId"));
			
		MDate d1 = new MDate((String) map.get("date"));
		Orders newOrder = new Orders();
		newOrder.setAccount(acc);
		newOrder.setOrderDate(d1.getDateObject());
		newOrder.setShipping((String) map.get("shipping"));
		newOrder.setTax(Integer.parseInt((String) map.get("tax")));
		newOrder.setTotal(Double.parseDouble((String) map.get("total")));
		newOrder.setStatus("Pending");
		orderDao.create(newOrder);
		
		List<Map<String, Object>> newOrderItems = (List<Map<String, Object>>) map.get("orderItems");
		for(Map<String, Object> newOrderMap : newOrderItems){
			int productId = Integer.parseInt((String) newOrderMap.get("productId"));
			int quantity = Integer.parseInt((String) newOrderMap.get("quantity"));
			double total = Double.parseDouble((String) newOrderMap.get("total"));
			OrderItemId newOrderItemId = new OrderItemId(newOrder.getId(), productId);
			OrderItem newOrderItem = new OrderItem();
			newOrderItem.setOrderItemId(newOrderItemId);
			newOrderItem.setQuantity(quantity);
			newOrderItem.setTotal(total);
			orderItemDao.create(newOrderItem);
		}
		
		returnJson = gson.toJson(accDao.getAccountDetail((Integer)sessionObj.getAttribute("loginId")));
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new ResponseEntity<String>(returnJson, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value="CancelOrder", method = RequestMethod.GET)
    public ResponseEntity<String> updateOrder(@RequestParam String data, HttpSession sessionObj) {
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("Status", "An account with this email address already exists.");
		String returnJson = gson.toJson(errorMap);
		
		Map<String, Object> map = gson.fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
		
		Orders existingOrder = orderDao.getOrders(Integer.parseInt((String) map.get("id")));
		existingOrder.setStatus("Canceled");
		orderDao.update(existingOrder);
		
		returnJson = gson.toJson(accDao.getAccountDetail((Integer)sessionObj.getAttribute("loginId")));
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new ResponseEntity<String>(returnJson, responseHeaders, HttpStatus.CREATED);
	}
	

	@RequestMapping(value="DeleteOrder", method = RequestMethod.GET)
    public ResponseEntity<String> deleteOrder(@RequestParam String data, HttpSession sessionObj) {
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("Status", "An account with this email address already exists.");
		String returnJson = gson.toJson(errorMap);
		
		Map<String, Object> map = gson.fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
			
		Orders existingOrder = orderDao.getOrders(Integer.parseInt((String) map.get("id")));
		existingOrder.setRemark(1);
		orderDao.update(existingOrder);
		
		returnJson = gson.toJson(accDao.getAccountDetail((Integer)sessionObj.getAttribute("loginId")));
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new ResponseEntity<String>(returnJson, responseHeaders, HttpStatus.CREATED);
	}
	
	
	/*
	 * Manage WishList
	 * ===============
	 * - Add Product to wish list
	 * - Remove Product from wish list
	 * 
	 * */
	@RequestMapping(value="CreateNewWishList", method = RequestMethod.GET)
    public ResponseEntity<String> createNewWishList(@RequestParam String data, HttpSession sessionObj) {
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("Status", "An account with this email address already exists.");
		String returnJson = gson.toJson(errorMap);
		
		Map<String, Object> map = gson.fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
			
		
		Product p = productDao.getProduct(Integer.parseInt((String) map.get("id")));
		Account acc = accDao.getAccount((Integer)sessionObj.getAttribute("loginId"));
		AccountWishListId wishListId = new AccountWishListId(acc.getId(), p.getId());
		AccountWishList wishList = new AccountWishList();
		wishList.setAccountWishListId(wishListId);
		wishListDao.create(wishList);
		
		returnJson = gson.toJson(accDao.getAccountDetail((Integer)sessionObj.getAttribute("loginId")));
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new ResponseEntity<String>(returnJson, responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="DeleteWishList", method = RequestMethod.GET)
    public ResponseEntity<String> deleteWishList(@RequestParam String data, HttpSession sessionObj) {
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("Status", "Error.");
		errorMap.put("Status", "Unable to remove the product from wish list");
		String returnJson = gson.toJson(errorMap);
		
		Map<String, Object> map = gson.fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
			
		Product p = productDao.getProduct(Integer.parseInt((String) map.get("id")));
		Account acc = accDao.getAccount((Integer)sessionObj.getAttribute("loginId"));
		AccountWishListId wishListId = new AccountWishListId(acc.getId(), p.getId());
		AccountWishList wishList = wishListDao.getAccountWishList(wishListId);
		wishListDao.delete(wishList);
		
		returnJson = gson.toJson(accDao.getAccountDetail((Integer)sessionObj.getAttribute("loginId")));
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new ResponseEntity<String>(returnJson, responseHeaders, HttpStatus.CREATED);
	}
}