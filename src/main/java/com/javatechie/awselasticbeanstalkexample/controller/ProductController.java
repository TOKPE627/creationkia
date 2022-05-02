package com.javatechie.awselasticbeanstalkexample.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.Day;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.ProductGalery;
import com.javatechie.awselasticbeanstalkexample.domain.SubCategory;
import com.javatechie.awselasticbeanstalkexample.domain.TownAvailable;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.DayService;
import com.javatechie.awselasticbeanstalkexample.service.ProductGaleryService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.StorageService;
import com.javatechie.awselasticbeanstalkexample.service.SubCategoryService;
import com.javatechie.awselasticbeanstalkexample.service.TownAvailableService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppDates;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;
import com.javatechie.awselasticbeanstalkexample.utility.FileUploadUtil;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private DayService dayService;
	
	@Autowired
	private ProductService productService;
	@Autowired
    private StorageService storageService;
	
	@Autowired
	private ProductGaleryService productGaleryService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private BookingService bookingService;

	@Autowired
	private TownAvailableService townAvailableService;
	
	@RequestMapping("/add")
    public String add(Model model, Principal principal) {
		  User user = userService.findByUsername(principal.getName());
          model.addAttribute("user", user);
		  UserRole userRole =userRoleService.findByUser(user);
		  Company company = companyService.findByUser(user);
		  model.addAttribute("company",company);
		    List<Day> days = dayService.findAll();
			model.addAttribute("dayList",days);

	
		  if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
			model.addAttribute("userRole2",AppConstants.ROLE_2);
			List<TownAvailable> townAvailables = townAvailableService.findByUser(user);
			if(townAvailables.isEmpty()){
				return "redirect:/townAvailable/add";
			}
			else{
				Category category = categoryService.findByTitle(AppConstants.CATEGORY_STORE);//Store
				//List<SubCategory> subCategories = subCategoryService.findByCategory(category);
				//model.addAttribute("subCategoryList",subCategories);
				model.addAttribute("category",category);
				return "dashboard/product/addStore";
			}
   		  }
		  if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
				Category category = categoryService.findByTitle(AppConstants.CATEGORY_SERVICE);//Shop	    
			    List<SubCategory> subCategories = subCategoryService.findByCategory(category);
				model.addAttribute("subCategoryList",subCategories);
			    model.addAttribute("category",category);
			  model.addAttribute("userRole3",AppConstants.ROLE_3);
		  }

		  return "dashboard/product/addService";

	}
	

	@RequestMapping(value = "/addStore", method = RequestMethod.POST)
	public String addStore(@ModelAttribute("product")  Product product,	
			@RequestParam("category_id") Long category_id,
			 Model model,Principal principal
		)throws IOException
	{
		
	    User user = userService.findByUsername(principal.getName());
	    Company company = companyService.findByUser(user);
	    Category category = categoryService.findById(category_id);
        product.setUser(user);
        product.setCategory(category);
     	product.setPublicationDate(AppDates.currentDateTime());

		Product savedProduct = productService.save(product);
		if(savedProduct !=null) {
			savedProduct.setDownsale(Math.rint(-savedProduct.getPrice()*100/savedProduct.getUpPrice())+"%");
			productService.update(savedProduct);
		}	
		model.addAttribute("user",user);
	    model.addAttribute("product",product);
	    model.addAttribute("company",company);
		UserRole userRole =userRoleService.findByUser(user);

		if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
			model.addAttribute("userRole2",AppConstants.ROLE_2);
		}
		
		return "dashboard/product/addGalery";
	}
	
	
	@RequestMapping(value = "/addService", method = RequestMethod.POST)
	public String add(@ModelAttribute("product")  Product product,	
			@RequestParam("category_id") Long category_id,
			@RequestParam("subCategory_id") Long subCategory_id,
			 Model model,Principal principal
		)throws IOException
	{
		
	    User user = userService.findByUsername(principal.getName());
	    Company company = companyService.findByUser(user);
	    Category category = categoryService.findById(category_id);
	    SubCategory subCategory = subCategoryService.findById(subCategory_id);
        product.setUser(user);
        product.setCategory(category);
        product.setSubCategory(subCategory);
     	product.setPublicationDate(AppDates.currentDateTime());

		Product savedProduct = productService.save(product);
		if(savedProduct !=null) {
			savedProduct.setDownsale(Math.rint(-savedProduct.getPrice()*100/savedProduct.getUpPrice())+"%");
			productService.update(savedProduct);
		}	
		model.addAttribute("user",user);
	    model.addAttribute("product",product);
	    model.addAttribute("company",company);
		UserRole userRole =userRoleService.findByUser(user);

		if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
			model.addAttribute("userRole3",AppConstants.ROLE_3);
		}
		return "dashboard/product/addGalery";
	}
	

	
	
	  @RequestMapping(value = "/productList", method = RequestMethod.GET)
	  public String all(Model model,Principal principal) throws IOException{
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
 
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
		    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
			model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		    model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);    
		     
		  User user = userService.findByUsername(principal.getName());
		    Company company = companyService.findByUser(user);
			model.addAttribute("user", user);
			model.addAttribute("company",company);
			List<Product> products = productService.findByUser(user); 
			model.addAttribute("productList",products); 
			UserRole userRole =userRoleService.findByUser(user);
			
		
			if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
			   model.addAttribute("userRole2",AppConstants.ROLE_2);
			}
		 	
			if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
			   model.addAttribute("userRole3",AppConstants.ROLE_3);
			}
			return "dashboard/product/all";  
	  }
	  
		@RequestMapping("/productInfo")
		public String storeInfo(@RequestParam("id") Long id,Model model,Principal principal) throws UnknownHostException {
			model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
  
			model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
			    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
				model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
			    model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);    
			    
		    
			User user = userService.findByUsername(principal.getName());
			Company company = companyService.findByUser(user);
			Product product = productService.findById(id);
			UserRole userRole =userRoleService.findByUser(user);
			model.addAttribute("user", user);
			model.addAttribute("company",company);
			model.addAttribute("product", product);		
		 	
		 	if(userRole.getRole().getName().equals(AppConstants.ROLE_4)) {
		 		model.addAttribute("userRole4",AppConstants.ROLE_4);
		 	    List<Booking> bookingsAddedToCart = 
			    		bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);

			    if(!bookingsAddedToCart.isEmpty()) {
			    	 model.addAttribute("bookingAddedToCartExist",true);
			    	 model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
				}
			    return "dashboard/customer/productInfo";
		    	 
		 	}
			if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
				model.addAttribute("userRole2",AppConstants.ROLE_2);
			}
		 	if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
				model.addAttribute("userRole3",AppConstants.ROLE_3);
			}	
			if(product.getGalery() == null){
				return "dashboard/product/addGalery";
			}
		 	return "dashboard/product/info";
		}

		
		@RequestMapping("/update")
		public String update(@RequestParam("id") Long id, Model model,Principal principal) {
			model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);

			   model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
			    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
				model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
			    model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);    
			    
  
    		User user = userService.findByUsername(principal.getName());
		    Product product = productService.findById(id);
		    model.addAttribute("user", user);
		    model.addAttribute("product", product);
		    UserRole userRole =userRoleService.findByUser(user);
		     Company company=companyService.findByUser(user);
		     model.addAttribute("company",company);
		    List<Day> days = dayService.findAll();
			model.addAttribute("dayList",days);
		
		
		    if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
				model.addAttribute("userRole2",AppConstants.ROLE_2);
			    return "dashboard/product/updateStore";
		    }
		    if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
			  model.addAttribute("userRole3",AppConstants.ROLE_3);
		    }
		    return "dashboard/product/updateService";
		}
		
		@RequestMapping(value="/updateStore", method=RequestMethod.POST)		
		public String updateStore(
			    @RequestParam("id") Long id, 
			    @RequestParam("galery_id") Long galery_id, 
				@ModelAttribute("product") Product product,			
				 @RequestParam("category_id") Long category_id,
				 Model model,Principal principal
			)
		{
			User user = userService.findByUsername(principal.getName());
		    Category category = categoryService.findById(category_id);
	        ProductGalery productGalery=productGaleryService.findById(galery_id);
		    product.setUser(user);
	        product.setCategory(category);	        
	     	product.setPublicationDate(AppDates.currentDateTime());
	     	product.setGalery(productGalery);
	     	
			Product updatedProduct = productService.update(product);

			if(updatedProduct !=null) {
				updatedProduct.setDownsale(Math.rint(-updatedProduct.getPrice()*100/updatedProduct.getUpPrice())+"%");
				productService.update(updatedProduct);
			}	
			return "redirect:/product/productList";		
	     }
		

		@RequestMapping(value="/updateService", method=RequestMethod.POST)		
		public String updateService(
			    @RequestParam("id") Long id, 
			    @RequestParam("galery_id") Long galery_id, 
				@ModelAttribute("product") Product product,		
				@RequestParam("category_id") Long category_id,
				@RequestParam("subCategory_id") Long subCategory_id,
				 Model model,Principal principal
			)
		{
			User user = userService.findByUsername(principal.getName());
			Category category=categoryService.findById(id);
		    SubCategory subCategory = subCategoryService.findById(subCategory_id);
	        ProductGalery productGalery=productGaleryService.findById(galery_id);
		    product.setUser(user);
		    product.setCategory(category);
	        product.setSubCategory(subCategory);	        
	     	product.setPublicationDate(AppDates.currentDateTime());
	     	product.setGalery(productGalery);
	     	
			Product updatedProduct = productService.update(product);

			if(updatedProduct !=null) {
				updatedProduct.setDownsale(Math.rint(-updatedProduct.getPrice()*100/updatedProduct.getUpPrice())+"%");
				productService.update(updatedProduct);
			}	
			return "redirect:/product/productList";		
	     }
		
		

		@RequestMapping(value="/remove", method=RequestMethod.POST)
		public String remove(
				@ModelAttribute("id") String id
				) {
			productService.remove(Long.parseLong(id.substring(11)));
			return "redirect:/product/productList";
		}

		@RequestMapping(value="/changeImage", method=RequestMethod.GET , produces = "application/json;charset=UTF-8")
		public ResponseEntity<?> changeImage(
			@ModelAttribute("image") String image) {
			Product result = new Product();
			result.setBrand(image);
		    return ResponseEntity.ok(result);
		}
		

		
		
	
		
		

}
