package com.javatechie.awselasticbeanstalkexample.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javatechie.awselasticbeanstalkexample.domain.Advertise;
import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.ContactAtooly;
import com.javatechie.awselasticbeanstalkexample.domain.Day;
import com.javatechie.awselasticbeanstalkexample.domain.PartnerAtooly;
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
import com.javatechie.awselasticbeanstalkexample.service.ContactAtoolyService;
import com.javatechie.awselasticbeanstalkexample.service.DayService;
import com.javatechie.awselasticbeanstalkexample.service.PartnerAtoolyService;
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

@Controller
@RequestMapping("/shop")
public class ShopController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BookingService bookingService;

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
    private StorageService storageService;
	
	@Autowired
	private ProductGaleryService productGaleryService;
	
	@Autowired
	private CompanyService companyService;

	@Autowired
	private AdvertiseService advertiseService;

	@Autowired
	private TownAvailableService townAvailableService;
	
	@Autowired
	private ContactAtoolyService contactAtoolyService;
 
	@Autowired
	private PartnerAtoolyService partnerAtoolyService;


	//Dashboard
	
	
	@RequestMapping("/add")
	public String add(Model model, Principal principal) {
		  User user = userService.findByUsername(principal.getName());
          model.addAttribute("user", user);
		  UserRole userRole =userRoleService.findByUser(user);
		  Company company = companyService.findByUser(user);
		  model.addAttribute("company",company);
		    List<Day> days = dayService.findAll();
			model.addAttribute("dayList",days);
			Category category = categoryService.findByTitle(AppConstants.CATEGORY_SHOP);//Shop
		    List<SubCategory> subCategories = subCategoryService.findByCategory(category);
			model.addAttribute("subCategoryList",subCategories);
		    model.addAttribute("category",category);
			
		  if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
				model.addAttribute("userRole1",AppConstants.ROLE_1);
				List<TownAvailable> townAvailables = townAvailableService.findByUser(user);
				if(townAvailables.isEmpty()){
					return "redirect:/townAvailable/add";
				}
	   	  }
		  
		  return "dashboard/product/shop/add";

	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("product")  Product product,	
			@RequestParam("category_id") Long category_id,
			@RequestParam("subCategory_id") Long subCategory_id,
			 Model model,Principal principal
		)throws IOException
	{
		
	    User user = userService.findByUsername(principal.getName());
	    Company company = companyService.findByUser(user);
	    Category category=categoryService.findById(category_id);
	    SubCategory subCategory = subCategoryService.findById(subCategory_id);
	    
        product.setUser(user);
        product.setCategory(category);
        product.setSubCategory(subCategory);
     	product.setPublicationDate(AppDates.currentDateTime());

		Product savedProduct = productService.save(product);
		if(savedProduct !=null) {
			savedProduct.setDownsale(-(100-Math.rint(savedProduct.getPrice()*100/savedProduct.getUpPrice()))+"%");
			productService.update(savedProduct);
		}	
		model.addAttribute("user",user);
	    model.addAttribute("product",product);
	    model.addAttribute("company",company);
		UserRole userRole =userRoleService.findByUser(user);

		  if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
				model.addAttribute("userRole1",AppConstants.ROLE_1);
	   	  }
		  
		return "dashboard/product/shop/addGalery";
	}
	
	  @RequestMapping(value = "/shopList", method = RequestMethod.GET)
	  public String all(Model model,Principal principal) throws IOException{
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
		model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		  
		
		User user = userService.findByUsername(principal.getName());
		    Company company = companyService.findByUser(user);
			model.addAttribute("user", user);
			model.addAttribute("company",company);
			 Category category = categoryService.findById(Long.parseLong("1"));
			 List<Product> products        = productService.findByUserAndByCategory(user, category);
			UserRole userRole =userRoleService.findByUser(user);
			
		 
		
			if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
			   model.addAttribute("userRole1",AppConstants.ROLE_1);
			   if(!products.isEmpty()){
				System.out.println("Products exist");
				model.addAttribute("shopExist", true);
				model.addAttribute("productList",products); 
			   }else{
				   System.out.println("Products Not exist");
				   model.addAttribute("shopExist", false);
			   }
			}
		   return "dashboard/product/shop/all";
	  }
	  
		@RequestMapping("/shopInfo")
		public String storeInfo(@RequestParam("id") Long id,Model model,Principal principal) throws UnknownHostException {
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

			if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
				model.addAttribute("userRole1",AppConstants.ROLE_1);
			    if(product.getGalery() == null){
					return "dashboard/product/shop/addGalery";
				}
			}
		    
			return "dashboard/product/shop/info";
		}
	
		@RequestMapping("/update")
		public String update(@RequestParam("id") Long id, Model model,Principal principal) {

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
		
			
			if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
				model.addAttribute("userRole1",AppConstants.ROLE_1);
		    }
			return "dashboard/product/shop/update";

		}
		
		
		@RequestMapping(value="/update", method=RequestMethod.POST)		
		public String update(
			    @RequestParam("id") Long id, 
			    @RequestParam("galery_id") Long galery_id, 
				@ModelAttribute("product") Product product,		
				@ModelAttribute("category_id") Long category_id,	
				 @RequestParam("subCategory_id") Long subCategory_id,
				 Model model,Principal principal
			)
		{
			User user = userService.findByUsername(principal.getName());
			Category category = categoryService.findById(category_id);
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
			return "redirect:/shop/shopList";		
	     }
		
	
	//Frontend
	@GetMapping("")
	public String index(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

		Advertise advertiseMobile = advertiseService.findByType(AppConstants.mobile);	    

		if(Objects.nonNull(advertiseMobile)) {
			model.addAttribute("advertiseMobileExists",true);
			model.addAttribute("advertise",advertiseMobile);
		}	
			List<Product> shops  = productService.findAllByCategory(AppConstants.CATEGORY_SHOP);

		 List<Product> supermarkets = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_1); 
		 List<Product> healths = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_2); 
		 List<Product> phones = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_3); 
		 List<Product> electronics = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_4); 
		 List<Product> informatics = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_5); 
		 List<Product> homeApplicances = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_6); 
		 List<Product> cloths = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_7); 
		 List<Product> hobbies = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_8); 
		 List<Product> others = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_9); 

		 List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
		  ContactAtooly contactAtooly         = contactAtoolyService.findByName(AppConstants.APP_NAME);
		  List<PartnerAtooly> partnerAtoolies = partnerAtoolyService.findAllPartners();
		  if(Objects.nonNull(contactAtooly)){
			 model.addAttribute("contactExists",true);
			 model.addAttribute("contact",contactAtooly); 
		 }
		 if(!partnerAtoolies.isEmpty()){
			 model.addAttribute("partnerExist",true);
			 model.addAttribute("partnerList",partnerAtoolies);
		 }
		
		if(!shops.isEmpty()) {
			model.addAttribute("shopExist",true);
			model.addAttribute("shopList",shops); 
		}
		
		if(!supermarkets.isEmpty()) {
			model.addAttribute("supermarketExist",true);
			model.addAttribute("supermarketList",supermarkets); 
		}
		if(!healths.isEmpty()) {
			model.addAttribute("healthExist",true);
			model.addAttribute("healthList",healths); 
		}
		 if(!phones.isEmpty()) {
			model.addAttribute("phoneExist",true);
			model.addAttribute("phoneList",phones); 
		}
		 if(!electronics.isEmpty()) {
			model.addAttribute("electronicExist",true);
			model.addAttribute("electronicList",electronics); 
		} 
		if(!informatics.isEmpty()) {
			model.addAttribute("informaticExist",true);
			model.addAttribute("informaticList",informatics); 
		}

		if(!homeApplicances.isEmpty()) {
			model.addAttribute("homeApplianceExist",true);
			model.addAttribute("homeApplianceList",homeApplicances); 
		}
		if(!cloths.isEmpty()) {
			model.addAttribute("clothExist",true);
			model.addAttribute("clothList",cloths); 
		}
	
		if(!hobbies.isEmpty()) {
			model.addAttribute("hobbyExist",true);
			model.addAttribute("hobbyList",hobbies); 
		} 
		if(!others.isEmpty()) {
			model.addAttribute("otherExist",true);
			model.addAttribute("otherList",others); 
		} 
		return "shop";
	}

	@GetMapping("/supermarket")
	public String supermarket(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

		ContactAtooly contactAtooly         = contactAtoolyService.findByName(AppConstants.APP_NAME);
		List<PartnerAtooly> partnerAtoolies = partnerAtoolyService.findAllPartners();
		if(Objects.nonNull(contactAtooly)){
		   model.addAttribute("contactExists",true);
		   model.addAttribute("contact",contactAtooly); 
	   }
	   if(!partnerAtoolies.isEmpty()){
		   model.addAttribute("partnerExist",true);
		   model.addAttribute("partnerList",partnerAtoolies);
	   }
	   Advertise advertiseMobile = advertiseService.findByType(AppConstants.mobile);	    

	   if(Objects.nonNull(advertiseMobile)) {
		   model.addAttribute("advertiseMobileExists",true);
		   model.addAttribute("advertise",advertiseMobile);
	   }		List<Product> shops  = productService.findAllByCategory(AppConstants.CATEGORY_SHOP);

		 List<Product> supermarkets = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_1); 
		
		 List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
		
	
		if(!shops.isEmpty()) {
			model.addAttribute("shopExist",true);
			model.addAttribute("shopList",shops); 
		}
		
		if(!supermarkets.isEmpty()) {
			model.addAttribute("productExist",true);
			model.addAttribute("productList",supermarkets); 
		}
		return "front/shop/supermarket";
	}

	@GetMapping("/healthBeauty")
	public String healthBeauty(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

		ContactAtooly contactAtooly         = contactAtoolyService.findByName(AppConstants.APP_NAME);
		List<PartnerAtooly> partnerAtoolies = partnerAtoolyService.findAllPartners();
		if(Objects.nonNull(contactAtooly)){
		   model.addAttribute("contactExists",true);
		   model.addAttribute("contact",contactAtooly); 
	   }
	   if(!partnerAtoolies.isEmpty()){
		   model.addAttribute("partnerExist",true);
		   model.addAttribute("partnerList",partnerAtoolies);
	   }
	
	   Advertise advertiseMobile = advertiseService.findByType(AppConstants.mobile);	    

	   if(Objects.nonNull(advertiseMobile)) {
		   model.addAttribute("advertiseMobileExists",true);
		   model.addAttribute("advertise",advertiseMobile);
	   }		List<Product> shops  = productService.findAllByCategory(AppConstants.CATEGORY_SHOP);

		 List<Product> healths = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_2); 
	
		 List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
		
		if(!shops.isEmpty()) {
			model.addAttribute("shopExist",true);
			model.addAttribute("shopList",shops); 
		}
		
	
		if(!healths.isEmpty()) {
			model.addAttribute("productExist",true);
			model.addAttribute("productList",healths); 
		}
		
		return "front/shop/healthBeauty";
	}

	@GetMapping("/phone")
	public String phone(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

		ContactAtooly contactAtooly         = contactAtoolyService.findByName(AppConstants.APP_NAME);
		List<PartnerAtooly> partnerAtoolies = partnerAtoolyService.findAllPartners();
		if(Objects.nonNull(contactAtooly)){
		   model.addAttribute("contactExists",true);
		   model.addAttribute("contact",contactAtooly); 
	   }
	   if(!partnerAtoolies.isEmpty()){
		   model.addAttribute("partnerExist",true);
		   model.addAttribute("partnerList",partnerAtoolies);
	   }
	   Advertise advertiseMobile = advertiseService.findByType(AppConstants.mobile);	    

	   if(Objects.nonNull(advertiseMobile)) {
		   model.addAttribute("advertiseMobileExists",true);
		   model.addAttribute("advertise",advertiseMobile);
	   }		List<Product> shops  = productService.findAllByCategory(AppConstants.CATEGORY_SHOP);
         List<Product> phones = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_3); 
		
		 List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
		
	
		if(!shops.isEmpty()) {
			model.addAttribute("shopExist",true);
			model.addAttribute("shopList",shops); 
		}
		
		
		 if(!phones.isEmpty()) {
			model.addAttribute("productExist",true);
			model.addAttribute("productList",phones); 
		}
		
		return "front/shop/phone";
	}

	@GetMapping("/electronic")
	public String electronic(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

		ContactAtooly contactAtooly         = contactAtoolyService.findByName(AppConstants.APP_NAME);
		List<PartnerAtooly> partnerAtoolies = partnerAtoolyService.findAllPartners();
		if(Objects.nonNull(contactAtooly)){
		   model.addAttribute("contactExists",true);
		   model.addAttribute("contact",contactAtooly); 
	   }
	   if(!partnerAtoolies.isEmpty()){
		   model.addAttribute("partnerExist",true);
		   model.addAttribute("partnerList",partnerAtoolies);
	   }
	   Advertise advertiseMobile = advertiseService.findByType(AppConstants.mobile);	    

	   if(Objects.nonNull(advertiseMobile)) {
		   model.addAttribute("advertiseMobileExists",true);
		   model.addAttribute("advertise",advertiseMobile);
	   }
	   
	   List<Product> shops  = productService.findAllByCategory(AppConstants.CATEGORY_SHOP);

		 List<Product> electronics = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_4); 
		
		 List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
		
		
		if(!shops.isEmpty()) {
			model.addAttribute("shopExist",true);
			model.addAttribute("shopList",shops); 
		}
		
	
		 if(!electronics.isEmpty()) {
			model.addAttribute("productExist",true);
			model.addAttribute("productList",electronics); 
		} 
		
		return "front/shop/electronic";
	}

	@GetMapping("/informatic")
	public String informatic(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

		ContactAtooly contactAtooly         = contactAtoolyService.findByName(AppConstants.APP_NAME);
		List<PartnerAtooly> partnerAtoolies = partnerAtoolyService.findAllPartners();
		if(Objects.nonNull(contactAtooly)){
		   model.addAttribute("contactExists",true);
		   model.addAttribute("contact",contactAtooly); 
	   }
	   if(!partnerAtoolies.isEmpty()){
		   model.addAttribute("partnerExist",true);
		   model.addAttribute("partnerList",partnerAtoolies);
	   }
		
	   Advertise advertiseMobile = advertiseService.findByType(AppConstants.mobile);	    

	   if(Objects.nonNull(advertiseMobile)) {
		   model.addAttribute("advertiseMobileExists",true);
		   model.addAttribute("advertise",advertiseMobile);
	   }
	   		List<Product> shops  = productService.findAllByCategory(AppConstants.CATEGORY_SHOP);
         List<Product> informatics = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_5); 
		
		 List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
		
		
		if(!shops.isEmpty()) {
			model.addAttribute("shopExist",true);
			model.addAttribute("shopList",shops); 
		}
		
		
		if(!informatics.isEmpty()) {
			model.addAttribute("productExist",true);
			model.addAttribute("productList",informatics); 
		}

		
		return "front/shop/informatic";
	}

	@GetMapping("/homeAppliance")
	public String homeAppliance(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

		ContactAtooly contactAtooly         = contactAtoolyService.findByName(AppConstants.APP_NAME);
		List<PartnerAtooly> partnerAtoolies = partnerAtoolyService.findAllPartners();
		if(Objects.nonNull(contactAtooly)){
		   model.addAttribute("contactExists",true);
		   model.addAttribute("contact",contactAtooly); 
	   }
	   if(!partnerAtoolies.isEmpty()){
		   model.addAttribute("partnerExist",true);
		   model.addAttribute("partnerList",partnerAtoolies);
	   }
		
	   Advertise advertiseMobile = advertiseService.findByType(AppConstants.mobile);	    

	   if(Objects.nonNull(advertiseMobile)) {
		   model.addAttribute("advertiseMobileExists",true);
		   model.addAttribute("advertise",advertiseMobile);
	   }
	   		List<Product> shops  = productService.findAllByCategory(AppConstants.CATEGORY_SHOP);
        List<Product> homeApplicances = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_6); 
		
		 List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
		
		
		if(!shops.isEmpty()) {
			model.addAttribute("shopExist",true);
			model.addAttribute("shopList",shops); 
		}
		if(!homeApplicances.isEmpty()) {
			model.addAttribute("productExist",true);
			model.addAttribute("productList",homeApplicances); 
		}
		
		return "front/shop/homeAppliance";
	}

	@GetMapping("/cloth")
	public String cloth(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

		ContactAtooly contactAtooly         = contactAtoolyService.findByName(AppConstants.APP_NAME);
		List<PartnerAtooly> partnerAtoolies = partnerAtoolyService.findAllPartners();
		if(Objects.nonNull(contactAtooly)){
		   model.addAttribute("contactExists",true);
		   model.addAttribute("contact",contactAtooly); 
	   }
	   if(!partnerAtoolies.isEmpty()){
		   model.addAttribute("partnerExist",true);
		   model.addAttribute("partnerList",partnerAtoolies);
	   }
	   Advertise advertiseMobile = advertiseService.findByType(AppConstants.mobile);	    

	   if(Objects.nonNull(advertiseMobile)) {
		   model.addAttribute("advertiseMobileExists",true);
		   model.addAttribute("advertise",advertiseMobile);
	   }	
	   	List<Product> shops  = productService.findAllByCategory(AppConstants.CATEGORY_SHOP);

		List<Product> cloths = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_7); 
		
		 List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
		
		
		if(!shops.isEmpty()) {
			model.addAttribute("shopExist",true);
			model.addAttribute("shopList",shops); 
		}
		
		
		if(!cloths.isEmpty()) {
			model.addAttribute("productExist",true);
			model.addAttribute("productList",cloths); 
		}
	
		return "front/shop/cloth";
	}

	@GetMapping("/hobby")
	public String hobby(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

		ContactAtooly contactAtooly         = contactAtoolyService.findByName(AppConstants.APP_NAME);
		List<PartnerAtooly> partnerAtoolies = partnerAtoolyService.findAllPartners();
		if(Objects.nonNull(contactAtooly)){
		   model.addAttribute("contactExists",true);
		   model.addAttribute("contact",contactAtooly); 
	   }
	   if(!partnerAtoolies.isEmpty()){
		   model.addAttribute("partnerExist",true);
		   model.addAttribute("partnerList",partnerAtoolies);
	   }
		
	   Advertise advertiseMobile = advertiseService.findByType(AppConstants.mobile);	    

	   if(Objects.nonNull(advertiseMobile)) {
		   model.addAttribute("advertiseMobileExists",true);
		   model.addAttribute("advertise",advertiseMobile);
	   }	
	   	List<Product> shops  = productService.findAllByCategory(AppConstants.CATEGORY_SHOP);
         List<Product> hobbies = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_8); 

		 List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
		
	
		if(!shops.isEmpty()) {
			model.addAttribute("shopExist",true);
			model.addAttribute("shopList",shops); 
		}
		
		if(!hobbies.isEmpty()) {
			model.addAttribute("productExist",true);
			model.addAttribute("productList",hobbies); 
		} 
		return "front/shop/hobby";
	}

	@GetMapping("/other")
	public String other(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

		
		ContactAtooly contactAtooly         = contactAtoolyService.findByName(AppConstants.APP_NAME);
		List<PartnerAtooly> partnerAtoolies = partnerAtoolyService.findAllPartners();
		if(Objects.nonNull(contactAtooly)){
		   model.addAttribute("contactExists",true);
		   model.addAttribute("contact",contactAtooly); 
	   }
	   if(!partnerAtoolies.isEmpty()){
		   model.addAttribute("partnerExist",true);
		   model.addAttribute("partnerList",partnerAtoolies);
	   }
	   Advertise advertiseMobile = advertiseService.findByType(AppConstants.mobile);	    

	   if(Objects.nonNull(advertiseMobile)) {
		   model.addAttribute("advertiseMobileExists",true);
		   model.addAttribute("advertise",advertiseMobile);
	   }	
	   
	   List<Product> shops  = productService.findAllByCategory(AppConstants.CATEGORY_SHOP);

		 List<Product> others = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SHOP_9); 

		 List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
	
		if(!shops.isEmpty()) {
			model.addAttribute("shopExist",true);
			model.addAttribute("shopList",shops); 
		}
		
		if(!others.isEmpty()) {
			model.addAttribute("productExist",true);
			model.addAttribute("productList",others); 
		} 
		return "front/shop/other";
	}
}
