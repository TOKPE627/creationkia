package com.javatechie.awselasticbeanstalkexample.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javatechie.awselasticbeanstalkexample.domain.Advertise;
import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyType;
import com.javatechie.awselasticbeanstalkexample.domain.Day;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.ProductGalery;
import com.javatechie.awselasticbeanstalkexample.domain.SubCategory;
import com.javatechie.awselasticbeanstalkexample.domain.TownAvailable;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.DayService;
import com.javatechie.awselasticbeanstalkexample.service.ProductGaleryService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.TownAvailableService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppDates;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;

@Controller
@RequestMapping("/groupSale")
public class GroupSaleController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductGaleryService productGaleryService;
	

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private DayService dayService;

	@Autowired
	private TownAvailableService townAvailableService;
	
	@Autowired
	private AdvertiseService advertiseService;
	@RequestMapping("/add")
    public String add(Model model, Principal principal) {

	    model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		  User user = userService.findByUsername(principal.getName());
          model.addAttribute("user", user);
		  UserRole userRole =userRoleService.findByUser(user);
		  Company company = companyService.findByUser(user);
		  model.addAttribute("company",company);
		    List<Day> days = dayService.findAll();
			model.addAttribute("dayList",days);

		  if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
			    Category category = categoryService.findByTitle(AppConstants.CATEGORY_GROUPSALE);
				model.addAttribute("userRole1",AppConstants.ROLE_1);
				model.addAttribute("category", category);
				List<TownAvailable> townAvailables = townAvailableService.findByUser(user);
				if(townAvailables.isEmpty()){
					return "redirect:/townAvailable/add";
				}
	   	  }
		  
		  return "dashboard/product/groupSale/add";


	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addGroupSale(@ModelAttribute("product")  Product product,	
			@RequestParam("category_id") Long category_id,
			 Model model,Principal principal
		)
	{
		
	    User user = userService.findByUsername(principal.getName());
	    model.addAttribute("user",user);
        product.setUser(user);
        Category category =categoryService.findById(category_id);
        product.setCategory(category);
     	product.setPublicationDate(AppDates.currentDateTime());     		
		Product savedProduct = productService.save(product);
        model.addAttribute("product",product);
		UserRole userRole =userRoleService.findByUser(user);
    	if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
			   model.addAttribute("userRole1",AppConstants.ROLE_1);
			}
		 	
		if(savedProduct !=null) {
		    model.addAttribute("product",product);
			return "dashboard/product/groupSale/addGalery";
		}else {
			return "message/badRequest";
		}
	}
	@RequestMapping("/update")
	public String update(@RequestParam("id") Long id, Model model,Principal principal) {

	    model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);

		User user = userService.findByUsername(principal.getName());
	    Product product = productService.findById(id);
	    model.addAttribute("user", user);
	    model.addAttribute("product", product);
	    UserRole userRole =userRoleService.findByUser(user);
	     Company company=companyService.findByUser(user);
	     model.addAttribute("company",company);
	    List<Day> days = dayService.findAll();
		model.addAttribute("dayList",days);
	
		
		if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
			model.addAttribute("userRole1",AppConstants.ROLE_1);
	    }
	   
		return "dashboard/product/groupSale/update";
	}
	
	
	
	@RequestMapping(value="/update", method=RequestMethod.POST)		
	public String update(
		    @RequestParam("galery_id") Long galery_id, 
			@ModelAttribute("product") Product product,			
			 Model model,Principal principal
		)
	{
		User user = userService.findByUsername(principal.getName());
        ProductGalery galery = productGaleryService.findById(galery_id);
		product.setGalery(galery);
        product.setUser(user);
     	product.setPublicationDate(AppDates.currentDateTime());
		productService.update(product);
		return "redirect:/groupSale/groupSaleList";		
		
     }
	
	  @RequestMapping(value = "/groupSaleList", method = RequestMethod.GET)
	  public String all(Model model,Principal principal) throws IOException{
		    User user = userService.findByUsername(principal.getName());
		    Company company = companyService.findByUser(user);
			model.addAttribute("user", user);
			model.addAttribute("company",company);
			 Category category = categoryService.findById(Long.parseLong("3"));
			 List<Product> products        = productService.findByUserAndByCategory(user, category);
			
			model.addAttribute("productList",products); 
			UserRole userRole =userRoleService.findByUser(user);

		    model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
		    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
			
	        
			if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
			   model.addAttribute("userRole1",AppConstants.ROLE_1);
			}
		 	
		    return "dashboard/product/groupSale/all";
	  }
	  
	  @RequestMapping("/groupSaleInfo")
		public String storeInfo(@RequestParam("id") Long id,Model model,Principal principal) throws UnknownHostException {

		    model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
		    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		    
			User user = userService.findByUsername(principal.getName());
			Company company = companyService.findByUser(user);
			Product product = productService.findById(id);
			UserRole userRole =userRoleService.findByUser(user);
			model.addAttribute("user", user);
			model.addAttribute("company",company);
			model.addAttribute("product", product);		

			if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
				model.addAttribute("userRole1",AppConstants.ROLE_1);
			}
		
			return "dashboard/product/groupSale/info";
		}

 
	  
	  //Frontend
		
		@RequestMapping("")
		public String index(Model model) throws UnknownHostException{
			model.addAttribute("url",AppConstants.url);
			model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
			model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
			model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
			model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
			model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
			model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		 
			List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	     model.addAttribute("bookingBegunList",bookingsBegun);
		
			  Advertise advertise = advertiseService.findByName(AppConstants.APP_NAME);	    
	
			  if(Objects.nonNull(advertise)) {
				  model.addAttribute("advertiseExists",true);
				  model.addAttribute("advertise",advertise);
			  }
			  Category category = categoryService.findById(Long.parseLong("3"));
			 List<Product> groupSales        = productService.findByCategory(category); 
			 
			 if(!groupSales.isEmpty()) {
				model.addAttribute("groupSaleExist",true);
				model.addAttribute("groupSaleList",groupSales);			 
			}
			return "groupSale";
		}

}
