package com.javatechie.awselasticbeanstalkexample.controller;

import java.net.UnknownHostException;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.Town;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.Role;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.TownService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;
import com.javatechie.awselasticbeanstalkexample.utility.FileUploadUtil;
import com.javatechie.awselasticbeanstalkexample.utility.SecurityUtility;

@Controller
@RequestMapping("/booking")
public class BookingController {
   
   
    @Autowired
    private ProductService productService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private TownService townService;
    
    @Autowired
    private CompanyService companyService;
    
    //Dashboard
    
	@RequestMapping("/remove")
	public String remove(
			@RequestParam("id") Long id
			) {
		bookingService.remove(id);
	
	    return "redirect:/dashboard";

	}
    
	@RequestMapping("/continue")
	public String continueBooking(
			@RequestParam("id") Long id,Principal principal,Model model
			) throws UnknownHostException {

		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);

	    List<Booking> bookingsAddedToCart = 
	    		bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);

	    if(!bookingsAddedToCart.isEmpty()) {
	    	 model.addAttribute("bookingAddedToCartExist",true);
	    	 model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
		}
	    
		User user = userService.findByUsername(principal.getName());
		Company company=companyService.findByUser(user);
		Booking booking =bookingService.findById(id);
		model.addAttribute("user",user);
		model.addAttribute("company",company);
		model.addAttribute("booking",booking);
		
		return "dashboard/customer/continueBooking";
	}
    
	
	@RequestMapping(value="/finalize", method = RequestMethod.POST)
	public String continueInitiatedBooking(
				HttpServletRequest request,
		        @ModelAttribute("id") Long id,
				@ModelAttribute("product_id")Long product_id,
				@ModelAttribute("quantity")  int quantity,
				Model model,Principal principal
			) throws Exception{

		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);

	    List<Booking> bookingsAddedToCart = 
	    		bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);

	    if(!bookingsAddedToCart.isEmpty()) {
	    	 model.addAttribute("bookingAddedToCartExist",true);
	    	 model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
		}
	    
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user",user);
	    Product product = productService.findById(product_id);
        Booking booking=bookingService.findById(id);
        model.addAttribute("booking",booking);
	    if(product !=null) {
	      if(quantity > product.getQuantity()) {
	    	model.addAttribute("quantityGreather", true);
			return "dashboard/customer/continueBooking";	
	       }
        }		
		
	    if(Objects.nonNull(booking)) {
	       //Update Booking
	    	booking.setUser(user);
	    	booking.setEmail(user.getEmail());
	    	booking.setQuantity(quantity);
	    	booking.setStatus(AppConstants.ORDER_STATUS_0);
	    	booking.setTotal_price(quantity*product.getPrice()+product.getDeliveryPrice());
            
		  	Booking bookingUpdated = bookingService.update(booking);
		  	if(bookingUpdated !=null) {
		  	  product.setQuantity(product.getQuantity() - quantity);
		  	  productService.update(product);
	    	}
		 }
	  	  return "redirect:/dashboard";
	}
	
	
	
	
	
	//Front
	@SuppressWarnings("null")
	@RequestMapping("/add/{product_id}")
    public String add(Model model,
    		@PathVariable Long product_id
    		) throws UnknownHostException {
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketShop",    AppConstants.awsBucketShop);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    
	    List<Booking> bookingsAddedToCart = 
	    		bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);

	    if(!bookingsAddedToCart.isEmpty()) {
	    	 model.addAttribute("bookingAddedToCartExist",true);
	    	 model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
		}
	    
	    
	    Product product = productService.findById(product_id);

		Booking bookingCheck = bookingService.findByProductAndIpaddress(product,AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
	  	if(bookingCheck == null) {
	  		Booking booking =new Booking();
	  		booking.setProduct(product);
			booking.setStatus(AppConstants.ORDER_STATUS_ADDED_TO_CART);
	        booking.setIpaddress(AppHosts.currentHostIpAddress());
		  	bookingService.save(booking);//Add to cart
	  	}
	
		model.addAttribute("product", product);
        Category category= product.getCategory();
        model.addAttribute("category",category);
	    List<Town> towns= townService.findAll();
		model.addAttribute("townList",towns); 
		  return "booking/add";
	}
	
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String createBooking(
				HttpServletRequest request,
				@ModelAttribute("product_id")Long product_id,
				@ModelAttribute("quantity")  int quantity,
				@ModelAttribute("role")      String role,
				@ModelAttribute("username")  String username,
				@ModelAttribute("email")     String userEmail,
				@ModelAttribute("lastName")  String lastName,
				@ModelAttribute("firstName") String firstName,
				@ModelAttribute("phone")     String phone,
				@ModelAttribute("town")      String town,
				@ModelAttribute("district")  String district,
				@ModelAttribute("address")   String address,
				@ModelAttribute("password")  String password,
				Model model
			) throws Exception{
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketShop",    AppConstants.awsBucketShop);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    
		model.addAttribute("email", userEmail);
		model.addAttribute("username", username);
		model.addAttribute("role",role);
        		
	
		
	    Product product = productService.findById(product_id);

		model.addAttribute("product", product);
        Category category= product.getCategory();
        model.addAttribute("category",category);
	    List<Town> towns= townService.findAll();
		model.addAttribute("townList",towns); 
		if (userService.findByUsername(username) != null) {
			model.addAttribute("usernameExists", true);
			return "booking/add";
		}
		
		if (bookingService.findByEmail(userEmail) != null) {
			model.addAttribute("emailExists", true);
			return "booking/add";
		}
		if(product !=null) {
        	if(quantity > product.getQuantity()) {
        		model.addAttribute("quantityGreather", true);
    			return "booking/add";	
        	}
        }		
		
		User user = new User();
		user.setUsername(username);
		user.setEmail(userEmail);
		user.setLastName(lastName);
		user.setFirstName(firstName);
		user.setPhone(phone);
		user.setTown(town);
		user.setDistrict(district);
		user.setAddress(address);
			
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
	
		Role roleR = new Role();
        roleR.setName(role);//By Default
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, roleR));
		User savedUser = userService.createUser(user, userRoles);
		
	    if(Objects.nonNull(savedUser)) {
	       //Update Booking
	    	Booking booking =bookingService.findByIpAddressAndStatusAddedToCart(AppHosts.currentHostIpAddress(), product, AppConstants.ORDER_STATUS_ADDED_TO_CART);
		  	booking.setUser(savedUser);
		  	booking.setEmail(userEmail);
		  	booking.setQuantity(quantity);
		  	booking.setTotal_price(quantity*product.getPrice()+product.getDeliveryPrice());
		  	booking.setStatus(AppConstants.ORDER_STATUS_0);

		  	Booking bookingUpdated = bookingService.update(booking);
		  	if(bookingUpdated !=null) {
		  	  product.setQuantity(product.getQuantity() - quantity);
		  	  productService.update(product);
		  	}
		}
		return "message/registrationBookingSuccess";
	}
	
	@RequestMapping(value="/product", method=RequestMethod.GET)
	public ResponseEntity<?> bookingNow(Model model,
	   @ModelAttribute("product_id") Long id) throws UnknownHostException {
	   List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	   model.addAttribute("bookingBegunList",bookingsBegun);
	   Product result = new Product();
	   //result.setName(AppConstants.url);
	   Product product = productService.findById(id);
	   result.setId(product.getId());
	   return ResponseEntity.ok(result);
	}
	


}
