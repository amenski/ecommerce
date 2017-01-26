package com.spring.project;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import com.spring.project.model.Category;
import com.spring.project.model.Product;
import com.spring.project.model.ProductReview;
import com.spring.project.model.dao.AccountImpl;
import com.spring.project.model.dao.CategoryImpl;
import com.spring.project.model.dao.OrdersImpl;
import com.spring.project.model.dao.ProductImpl;
import com.spring.project.model.dao.ProductReviewImpl;
import com.spring.project.util.MDate;

@Controller
@RequestMapping(value="/")
public class ProductController {
	private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	@Autowired
	private AccountImpl accDao;
	
	@Autowired
	private ProductImpl productDao;
	

	@Autowired
	private ProductReviewImpl productReviewDao;

	@Autowired
	private OrdersImpl orderDao;
	

	@Autowired
	private CategoryImpl categoryDao;
	
	@RequestMapping(value="UploadProductImg", method = RequestMethod.POST)
	public String uploadProduct(@RequestParam("proimg_file") MultipartFile file,
            RedirectAttributes redirectAttributes, HttpSession sessionObj) {
		
		String dir = "//Users//Aman//Documents//workspace//spring-project-1//WebContent//webresources//file_upload//sample//"; 
		System.out.println(dir);
        try{   
        	byte[] bytes = file.getBytes();
            Path path = Paths.get(dir + file.getOriginalFilename());
            Files.write(path, bytes);
	       // ScriptEngine js = new ScriptEngineManager().getEngineByName("JavaScript");
	        redirectAttributes.addFlashAttribute("message", file.getOriginalFilename());
        }catch(Exception e){System.out.println(e);}  

	        
        return "redirect:/uploadProductStatus";
	}
	
	@GetMapping("/uploadProductStatus")
    public String uploadProductStatus() {
        return "uploadProductStatus";
    }
	
	/*
	 * Manage Products
	 * ===============
	 * - Add Order
	 * - View Order
	 * - Edit Order
	 * - Delete Order
	 * CreateNewReview
	 * */
	@RequestMapping(value="CreateNewReview", method = RequestMethod.GET)
    public ResponseEntity<String> createNewProductReview(@RequestParam String data, HttpSession sessionObj) {
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("Status", "Error");
		returnMap.put("Data", "An account with this password does not exist.");
		
		
		Map<String, Object> map = gson.fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
		Account acc = accDao.getAccount((Integer) sessionObj.getAttribute("loginId"));
		System.out.println((String) map.get("date"));
		MDate rDate = new MDate((String) map.get("date"));
		
		ProductReview newReview = new ProductReview();
		newReview.setDate(rDate.getDateObject());
		newReview.setComment((String) map.get("comment"));
		newReview.setAccount(acc);
		newReview.setProduct(productDao.getProduct(Integer.parseInt((String) map.get("productId"))));
		productReviewDao.create(newReview);
	
		String returnJson = gson.toJson(returnMap);
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new ResponseEntity<String>(returnJson, responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="CreateNewProduct", method = RequestMethod.POST)
    public ResponseEntity<String> createNewProduct(@RequestParam String data, HttpSession sessionObj) {
		String returnJson = "{\"Status\":\"Error\", \"Data\":\"An account with this email address already exists.\"}";
		Map<String, Object> map = gson.fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
		Account acc = accDao.getAccount((Integer)sessionObj.getAttribute("loginId"));
		
		Product newProduct = new Product();
		//String[] catId = String.valueOf(map.get("category")).split(".");
		System.out.println((String) map.get("images"));
		newProduct.setCategory(categoryDao.getCategory(Integer.parseInt((String)map.get("category"))));
		newProduct.setImages((String) map.get("images"));
		newProduct.setAccount(acc);
		newProduct.setName((String) map.get("name"));
		newProduct.setAtp((String)map.get("atp"));
		newProduct.setDescription((String)map.get("description"));
		newProduct.setUnitPrice(Double.parseDouble((String)map.get("unitPrice")));
		System.out.println("From New Product : "+newProduct.getAccount().toString());
		productDao.create(newProduct);
		
		Map<String, List> accMap = new HashMap<String, List>();
		accMap.put("Categories", categoryDao.getAllCategorys());
		accMap.put("MyProducts", productDao.getProductsByAccount(acc));
		returnJson = gson.toJson(accMap);
		
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new ResponseEntity<String>(returnJson, responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="UpdateProduct", method = RequestMethod.POST)
    public ResponseEntity<String> updateNewProduct(@RequestParam String data, HttpSession sessionObj) {
		String returnJson = "{\"Status\":\"Error\", \"Data\":\"An account with this email address already exists.\"}";
		Map<String, Object> map = gson.fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
		Account acc = accDao.getAccount((Integer) sessionObj.getAttribute("loginId"));
	
		Product existingProduct = productDao.getProduct(Integer.parseInt((String) map.get("id")));
		
		existingProduct.setCategory(categoryDao.getCategory(Integer.parseInt((String) map.get("category"))));
		existingProduct.setName((String) map.get("name"));
		existingProduct.setAtp((String)map.get("atp"));
		existingProduct.setDescription((String)map.get("description"));
		existingProduct.setUnitPrice(Double.parseDouble((String)map.get("unitPrice")));

		productDao.update(existingProduct);
		
		Map<String, List> accMap = new HashMap<String, List>();
		accMap.put("Categories", categoryDao.getAllCategorys());
		accMap.put("MyProducts", productDao.getProductsByAccount(acc));
		returnJson = gson.toJson(accMap);
		
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new ResponseEntity<String>(returnJson, responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="DeleteProduct", method = RequestMethod.POST)
    public ResponseEntity<String> deleteNewProduct(@RequestParam String data, HttpSession sessionObj) {
		String returnJson = "{\"Status\":\"Error\", \"Data\":\"An account with this email address already exists.\"}";
		Map<String, Object> map = gson.fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
		Account acc = accDao.getAccount((Integer) sessionObj.getAttribute("loginId"));
	
		Product existingProduct = productDao.getProduct(Integer.parseInt((String) map.get("id")));
		productDao.delete(existingProduct);
		
		Map<String, List> accMap = new HashMap<String, List>();
		accMap.put("Categories", categoryDao.getAllCategorys());
		accMap.put("MyProducts", productDao.getProductsByAccount(acc));
		returnJson = gson.toJson(accMap);
		
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new ResponseEntity<String>(returnJson, responseHeaders, HttpStatus.CREATED);
    }
	
	
}
