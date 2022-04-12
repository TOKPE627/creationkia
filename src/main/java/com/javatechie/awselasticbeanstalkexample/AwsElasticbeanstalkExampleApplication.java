package com.javatechie.awselasticbeanstalkexample;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.mail.event.StoreListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.javatechie.awselasticbeanstalkexample.domain.Advertise;
import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyType;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.Role;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CatalogService;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.RoleService;
import com.javatechie.awselasticbeanstalkexample.service.SpecialityService;
import com.javatechie.awselasticbeanstalkexample.service.TownService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.service.WorkingHourService;
import com.javatechie.awselasticbeanstalkexample.service.impl.UserSecurityService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;
import com.javatechie.awselasticbeanstalkexample.utility.MailConstructor;
import com.javatechie.awselasticbeanstalkexample.utility.SecurityUtility;

@SpringBootApplication
@Controller
public class AwsElasticbeanstalkExampleApplication implements CommandLineRunner{

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private UserService userService;
	
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private ProductService productService;
	
	
	@Autowired 
	RoleService roleService;
		
	@Autowired 
	private CompanyService companyService;
	
	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private SpecialityService specialityService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TownService townService;
		
	@Autowired
	private WorkingHourService workingHourService;
	
	@Autowired
	private BookingService bookingService;

	@Autowired
	private AdvertiseService advertiseService;
	
	// @GetMapping("/")
	// public String indexGoogle(){
	// 	return "google/loginStatus";
	// }
	@GetMapping("/")
	public String welcome(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);

	    Advertise advertise = advertiseService.findByName(AppConstants.APP_NAME);	    
	     List<Booking> bookingsAddedToCart = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
 		if(!bookingsAddedToCart.isEmpty()) {
	    	 model.addAttribute("bookingAddedToCartExist",true);
	    	 model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
		}
 	     
 	     Category category               = categoryService.findById(Long.parseLong("3"));
		 List<Product> groupSales        = productService.findByCategory(category); 
		 List<Product> shops             = productService.findAllByCategory(AppConstants.CATEGORY_SHOP);
		 List<Company> services          = companyService.findAllByType(CompanyType.SERVICE);
		 List<Company> stores            = companyService.findAllByType(CompanyType.STORE);
		
		 if(Objects.nonNull(advertise)) {
    		 model.addAttribute("advertiseExists",true);
    		 model.addAttribute("advertise",advertise);
         }	
		 if(!shops.isEmpty()) {
			 model.addAttribute("shopExist",true);
			 model.addAttribute("shopList",shops); 
		 }
		 
		 if(!services.isEmpty()) {
			 model.addAttribute("serviceExist",true);
			 model.addAttribute("serviceList",services);			 
		 }
		 if(!stores.isEmpty()) {
			 model.addAttribute("storeExist",true);
			 model.addAttribute("storeList", stores);
		 }
    
		 if(!groupSales.isEmpty()) {
			 model.addAttribute("groupSaleExist",true);
			 model.addAttribute("groupSaleList",groupSales);			 
		 }
		return "welcome";
	}
	@GetMapping("/status")
	public String deploy() {
		return "Application deployed to AWS beanstalk...";
	}
	public static void main(String[] args) {
		SpringApplication.run(AwsElasticbeanstalkExampleApplication.class, args);
	}
	   @Override
		public void run(String... args) throws Exception {
			
			//   User user = new User(); 
			//   //user.setId(Long.parseLong("1"));
			//   user.setEmail("kouassielysee@gmail.com"); 
			//   user.setUsername("elysee");
			//   user.setLastName("Kouassi");
			//   user.setFirstName("Elysee");
			//   BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
			//   user.setPassword(passwordEncoder.encode("passer")); 
			//   Role roleR = new Role();
			//   roleR.setRoleId(Long.parseLong("1")); 
			//   roleR.setName(AppConstants.ROLE_1);//By Default
			//   Set<UserRole> userRoles = new HashSet<>(); userRoles.add(new
			//   UserRole(user, roleR)); 
			//   userService.createUser(user, userRoles);
		}
}
