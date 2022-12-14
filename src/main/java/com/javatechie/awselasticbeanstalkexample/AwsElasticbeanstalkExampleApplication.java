package com.javatechie.awselasticbeanstalkexample;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javatechie.awselasticbeanstalkexample.domain.Brand;
import com.javatechie.awselasticbeanstalkexample.domain.Cart;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Style;
import com.javatechie.awselasticbeanstalkexample.domain.Univers;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.Role;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.BrandService;
import com.javatechie.awselasticbeanstalkexample.service.CartService;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.StyleService;
import com.javatechie.awselasticbeanstalkexample.service.UniversService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;
import com.javatechie.awselasticbeanstalkexample.utility.SecurityUtility;

@SpringBootApplication
@Controller
public class AwsElasticbeanstalkExampleApplication implements CommandLineRunner{


	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StyleService styleService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private UniversService universService;
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/")
	public String welcome(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("bCategory",AppConstants.awsBucketCategory);
		List<Cart> carts = cartService.findByIpaddress(AppHosts.currentHostIpAddress());
        System.out.println("Cart size:" +carts.size());
		model.addAttribute("cartList",carts);
		
	    List<Category> categories = categoryService.findAll();
	    List<Style> styles = styleService.findAll();
	    List<Brand> brands = brandService.findAll();
	    List<Univers> univers = universService.findAll();
	    
        model.addAttribute("categoryList",categories);
        model.addAttribute("styleList",styles);
        model.addAttribute("brandList",brands);
        model.addAttribute("universList",univers);

		return "index";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AwsElasticbeanstalkExampleApplication.class, args);
	
	}
	   @Override
		public void run(String... args) throws Exception {
		
			  User user = new User(); 
			  user.setId(Long.parseLong("1"));
			   user.setEmail("lamaisondecreationkia@yahoo.com"); 
			   user.setUsername("samba");
			   user.setLastName("Samb");
			   user.setFirstName("samba");
			   BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
			   //user.setPassword(passwordEncoder.encode("Samba12345@")); 
	           user.setPassword(passwordEncoder.encode("passer")); 
			   Role roleR = new Role();
			   roleR.setRoleId(Long.parseLong("1")); 
			   roleR.setName(AppConstants.ROLE_1);//By Default
			   Set<UserRole> userRoles = new HashSet<>(); userRoles.add(new
			   UserRole(user, roleR)); 
			   userService.createUser(user, userRoles);
		}
}
