package com.javatechie.awselasticbeanstalkexample.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javatechie.awselasticbeanstalkexample.domain.AjaxResponseBody;
import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.BookingCompany;
import com.javatechie.awselasticbeanstalkexample.domain.Catalog;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyGalery;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyType;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.Speciality;
import com.javatechie.awselasticbeanstalkexample.domain.SubCategory;
import com.javatechie.awselasticbeanstalkexample.domain.Town;
import com.javatechie.awselasticbeanstalkexample.domain.TownAvailable;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.WorkingHour;
import com.javatechie.awselasticbeanstalkexample.domain.security.Role;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.BookingCompanyService;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CatalogService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyGaleryService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.SpecialityService;
import com.javatechie.awselasticbeanstalkexample.service.StorageService;
import com.javatechie.awselasticbeanstalkexample.service.SubCategoryService;
import com.javatechie.awselasticbeanstalkexample.service.TownAvailableService;
import com.javatechie.awselasticbeanstalkexample.service.TownService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.service.WorkingHourService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;
import com.javatechie.awselasticbeanstalkexample.utility.FileUploadUtil;
import com.javatechie.awselasticbeanstalkexample.utility.SecurityUtility;

@Controller
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private UserService userService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private CatalogService catalogService;
    
	@Autowired
    private BookingCompanyService bookingCompanyService;

	@Autowired
private SpecialityService specialityService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private TownService townService;
	
	@Autowired
	private WorkingHourService workingHourService;
	
	@Autowired
	private TownAvailableService townAvailableService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private CompanyGaleryService companyGaleryService;
	
	
	
	
	
	@RequestMapping("/companyInfo")
	public String info(Model model, Principal principal) throws UnknownHostException {
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);        		
		User user = userService.findByUsername(principal.getName());
		Company company = companyService.findByUser(user);
		
		model.addAttribute("company",company);
		
		UserRole userRole =userRoleService.findByUser(user);
		model.addAttribute("user",user);
		if(userRole.getRole().getName().equals(AppConstants.ROLE_2) || userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
			if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
				model.addAttribute("userRole2",AppConstants.ROLE_2);
			}
			if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
				model.addAttribute("userRole3",AppConstants.ROLE_3);
			}
			return "dashboard/company/info";			
		}else {
			return "message/badRequest";			
		}
	}

	@RequestMapping("/add")
	public String ad(Model model, Principal principal) throws UnknownHostException {
		User user = userService.findByUsername(principal.getName());
		Company company = companyService.findByUser(user);
		model.addAttribute("user", user);
		model.addAttribute("company", company);
		UserRole userRole = userRoleService.findByUser(user);
        List<Town> towns = townService.findAll();
        model.addAttribute("townList", towns);
		if (userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
			model.addAttribute("userRole2", AppConstants.ROLE_2);	
			return "dashboard/company/add/store";
		}
		if (userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
			model.addAttribute("userRole3", AppConstants.ROLE_3);
		    List<SubCategory> subCategories = subCategoryService.findAllByCategoryTitle(AppConstants.CATEGORY_SERVICE);
		    model.addAttribute("subCategoryList",subCategories);	
			return "dashboard/company/add/service";
		}
		return "message/technicalIssue";

	}

	@RequestMapping(value = "/add/service", method = RequestMethod.POST)
	public String addServicePost(@ModelAttribute("company") Company company, Principal principal,
			Model model
			)
			 {
		User user = userService.findByUsername(principal.getName());
		company.setUser(user);
		model.addAttribute("user", user);
		model.addAttribute("userRole3", AppConstants.ROLE_3);	
		model.addAttribute("company", company);

		Company savedCompany = companyService.save(company);
		if(savedCompany !=null) {
				  savedCompany.setCompanyType(CompanyType.SERVICE);
			  
			  companyService.update(savedCompany);
			
		}
		return "dashboard/company/add/addGalery";

	}
	
	@RequestMapping(value = "/add/store", method = RequestMethod.POST)
	public String addStorePost(@ModelAttribute("company") Company company,  
			Principal principal,Model model)
			 {
		
		User user = userService.findByUsername(principal.getName());
		company.setUser(user);
		model.addAttribute("user", user);
		model.addAttribute("userRole2", AppConstants.ROLE_2);	
		model.addAttribute("company", company);

		Company savedCompany = companyService.save(company);
		if(savedCompany !=null) {
				  savedCompany.setCompanyType(CompanyType.STORE);
			  
			  companyService.update(savedCompany);
			  
		}
		return "dashboard/company/add/addGalery";
	}


	
	//Update on Dashboard Parameters
	@RequestMapping("/update")
	public String update(Model model) {
		model.addAttribute("awsBucketCompany",AppConstants.awsBucketCompany);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		Company company = companyService.findByUser(user);
		List<Town> towns=townService.findAll();
		model.addAttribute("townList",towns);
		model.addAttribute("user", user);
		model.addAttribute("company", company);
		UserRole userRole =userRoleService.findByUser(user);
	    if(company.getGalery() ==null) {
		  return "dashboard/company/add/addGalery";
	    }
		if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
			model.addAttribute("userRole2",AppConstants.ROLE_2);
		}
		if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
			model.addAttribute("userRole3",AppConstants.ROLE_3);
		}
		return "dashboard/company/update";
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateInfos(@ModelAttribute("company") Company company,
		    @RequestParam("galery_id") Long galery_id,
			Principal principal) {
		User user = userService.findByUsername(principal.getName());
		CompanyGalery companyGalery=companyGaleryService.findById(galery_id);
		if(user.getCompany().getCompanyType().equals(CompanyType.STORE)){
			company.setCompanyType(CompanyType.STORE);			
		}
		if(user.getCompany().getCompanyType().equals(CompanyType.SERVICE)){
			company.setCompanyType(CompanyType.SERVICE);			
		}
		
		company.setUser(user);
		company.setGalery(companyGalery);
        companyService.update(company);         
	    return "redirect:/company/companyInfo?id=" + company.getId();	
	}
	
	
	
	
	//Front

	@RequestMapping(value = "/companyImage", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> showInfo(@ModelAttribute("image") String image,Model model) {
		Company result = new Company();
		System.out.println("Image:" +image);
		String s1 = AppConstants.awsBucketCompany;
        int l1 = s1.length();
       // System.out.println("Length of l1:"+l1);
		String s2 = image.substring(l1);
	    //System.out.println("S2: " +s2);
		int i1 = s2.indexOf("/");
		//System.out.println("Index of /: " +i1);
		String s3 = s2.substring(0, i1);
		//System.out.println("S3: "+s3);
        CompanyGalery companyGalery=companyGaleryService.findById(Long.parseLong(s3));
		Company company = companyService.findByGalery(companyGalery);
		result.setName(AppConstants.url);
		result.setId(company.getId());
		return ResponseEntity.ok(result);
	}


	@RequestMapping(value = "/companyImageAttoly", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> showInfoAttoly(@ModelAttribute("image") String image) {
		Company result = new Company();
		result.setName(AppConstants.url);
		return ResponseEntity.ok(result);
	}

	
	
	@RequestMapping("/info/{id}")
	public String companyInfo(@ModelAttribute("id") Long id, Model model) throws UnknownHostException {
		model.addAttribute("awsBucketCompany",AppConstants.awsBucketCompany);
		model.addAttribute("awsBucketProduct",AppConstants.awsBucketProduct);
		model.addAttribute("awsBucketCatalog",AppConstants.awsBucketCatalog);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);

		List<Booking> bookingsAddedToCart = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
		if(!bookingsAddedToCart.isEmpty()) {
			model.addAttribute("bookingAddedToCartExist",true);
			model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
	   }
		Company company = companyService.findById(id);
		User user = company.getUser();
		model.addAttribute("company", company);
	
        if(company.getCompanyType().name().equals("SERVICE")) {
        	List<Catalog> catalogs = catalogService.findByUser(user);
			List<Speciality> specialities = specialityService.findByUser(user);
    		List<Speciality> specialityTop4List = specialityService.findTop4ByUser(user.getId());
    		model.addAttribute("catalogList",catalogs);
			model.addAttribute("specialityList",specialities);
    		model.addAttribute("specialityTop4List", specialityTop4List);
		  return "front/company/service";	
		}
        if(company.getCompanyType().name().equals("STORE")) {
        	List<WorkingHour> workingHours = workingHourService.findByUser(user);
			List<TownAvailable> townAvailables= townAvailableService.findByUser(user);
        	List<Product> products = productService.findByUser(user);
        	if(!products.isEmpty()) {
			  model.addAttribute("storeProductsExist",true);
        	  model.addAttribute("productList",products);         	
        	}
			 model.addAttribute("townAvailableList", townAvailables);
			model.addAttribute("workingHourList", workingHours);
        }
		    return "front/company/store";        	
	}
	
   	@RequestMapping(value = "/agenda")
   	public String allInfos(
   			@ModelAttribute("company_id") Long id,
   			Model model) throws UnknownHostException
   	{	
		model.addAttribute("awsBucketCatalog",AppConstants.awsBucketCatalog);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		List<Booking> bookingsAddedToCart = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
		if(!bookingsAddedToCart.isEmpty()) {
			model.addAttribute("bookingAddedToCartExist",true);
			model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
	   }
        Company company = companyService.findById(id);
        model.addAttribute("company",company);
   		User user = company.getUser();
   		List<Speciality> specialities = specialityService.findByUser(user);
    		List<Speciality> specialityTop4List = specialityService.findTop4ByUser(user.getId());
			model.addAttribute("specialityList",specialities);
    		model.addAttribute("specialityTop4List", specialityTop4List);
   	   List<WorkingHour> wDay1 = workingHourService.findByUserByDay(user,AppConstants.DAY_1);
   	 		model.addAttribute("day1List",wDay1);
   	 	    List<WorkingHour> wDay2 = workingHourService.findByUserByDay(user,AppConstants.DAY_2);
   	 		model.addAttribute("day2List",wDay2);
   	 	    List<WorkingHour> wDay3 = workingHourService.findByUserByDay(user,AppConstants.DAY_3);
   	 	 	model.addAttribute("day3List",wDay3);
   	 	    List<WorkingHour> wDay4 = workingHourService.findByUserByDay(user,AppConstants.DAY_4);
   	 		model.addAttribute("day4List",wDay4);
   	 		List<WorkingHour> wDay5 = workingHourService.findByUserByDay(user,AppConstants.DAY_5);
   	 	 	model.addAttribute("day5List",wDay5);
   	 	    List<WorkingHour> wDay6 = workingHourService.findByUserByDay(user,AppConstants.DAY_6);
   	 	 	model.addAttribute("day6List",wDay6);
   	 	   List<WorkingHour> wDay7 = workingHourService.findByUserByDay(user,AppConstants.DAY_7);
   			model.addAttribute("day7List",wDay7);
   		return "front/company/agenda";
   	}
   	
   	@RequestMapping(value="/booking/add")
   	public String booking(@ModelAttribute("company_id") Long id, Model model) throws UnknownHostException {

		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		List<Booking> bookingsAddedToCart = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
		if(!bookingsAddedToCart.isEmpty()) {
			model.addAttribute("bookingAddedToCartExist",true);
			model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
	   }
   	 Company company = companyService.findById(id);
     model.addAttribute("company",company);

	 List<Speciality> specialities = specialityService.findByUser(company.getUser());
			model.addAttribute("specialityList",specialities);

			List<Speciality> specialityTop4List = specialityService.findTop4ByUser(company.getUser().getId());
			model.addAttribute("specialityTop4List",specialityTop4List);
     List<Town> towns=townService.findAll();
     model.addAttribute("townList",towns);
	 //create BookingCompany
	 BookingCompany bookingCompany = new BookingCompany();
	 bookingCompany.setCompany(company);
	 bookingCompany.setIpaddress(AppHosts.currentHostIpAddress());
	 bookingCompanyService.save(bookingCompany);
	return "front/company/bookingAdd";
   	}
   	
/* todo */   	
   	@RequestMapping(value="/booking/add", method = RequestMethod.POST)
	public String createBookingService(
				HttpServletRequest request,
				@ModelAttribute("company_id") Long company_id,
				@ModelAttribute("role")      String role,
				@ModelAttribute("lastName")  String lastName,
				@ModelAttribute("firstName") String firstName,
				@ModelAttribute("town")      String town,
				@ModelAttribute("district")  String district,
				@ModelAttribute("address")   String address,
				@ModelAttribute("phone")     String phone,
				@ModelAttribute("email")     String userEmail,
				@ModelAttribute("command")   String command,
				@ModelAttribute("day")       String day,
				@ModelAttribute("hour")       String hour,
				@ModelAttribute("username")  String username,
				@ModelAttribute("password")  String password,
				Model model
			) throws Exception{
		List<Booking> bookingsAddedToCart = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
		if(!bookingsAddedToCart.isEmpty()) {
			model.addAttribute("bookingAddedToCartExist",true);
			model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
		}
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
	
		model.addAttribute("email", userEmail);
		model.addAttribute("username", username);
		model.addAttribute("role",role);
		if (userService.findByUsername(username) != null) {
			model.addAttribute("usernameExists", true);
			return "front/company/bookingAdd";
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
		if(savedUser !=null){
			Company company = companyService.findById(company_id);
			BookingCompany checkBookingCompany = bookingCompanyService.findByCompanyByIpaddressAndByStatus(company, AppHosts.currentHostIpAddress(), AppConstants.ORDER_STATUS_0);
			if(Objects.nonNull(checkBookingCompany)){
				checkBookingCompany.setUser(user);
				checkBookingCompany.setCompany(company);
				checkBookingCompany.setEmail(userEmail);
				checkBookingCompany.setCommand(command);
				checkBookingCompany.setDay(day);
				checkBookingCompany.setHour(hour);
				checkBookingCompany.setStatus(AppConstants.ORDER_STATUS_1);
				bookingCompanyService.update(checkBookingCompany);
			}
			else{
				BookingCompany bookingCompany = new BookingCompany();
				bookingCompany.setUser(user);
				bookingCompany.setCompany(company);
				bookingCompany.setEmail(userEmail);
				bookingCompany.setCommand(command);
				bookingCompany.setDay(day);
				bookingCompany.setHour(hour);
				bookingCompany.setStatus(AppConstants.ORDER_STATUS_1);
				bookingCompanyService.save(bookingCompany);
			}
		
			return "message/registrationBookingSuccess";
		}
	   
		return "message/technicalIssue";
	}
	@RequestMapping(value="/services", method=RequestMethod.GET)
	public ResponseEntity<?> getServices(Model model
	    ) throws UnknownHostException {
		List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	    model.addAttribute("bookingBegunList",bookingsBegun);
		AjaxResponseBody result = new AjaxResponseBody();
		List<Company> services          = companyService.findAllByType(CompanyType.SERVICE);
		result.setCompanies(services);
	    return ResponseEntity.ok(result);
	}
	@RequestMapping(value="/service/specialities", method=RequestMethod.GET)
	public ResponseEntity<?> getSpecialities(Model model,
	   @ModelAttribute("service_id") Long service_id) throws UnknownHostException {
		List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	    model.addAttribute("bookingBegunList",bookingsBegun);
		AjaxResponseBody result = new AjaxResponseBody();

		Company company = companyService.findById(service_id);
		User user = company.getUser();
		List<Speciality> specialities = specialityService.findTop4ByUser(user.getId());
		result.setSpecialities(specialities);	
	    return ResponseEntity.ok(result);
	}
	@RequestMapping("/service/allSpecialities/{id}")
	public String serviceSpecialities(@ModelAttribute("id") Long id, Model model) throws UnknownHostException {
		model.addAttribute("awsBucketCompany",AppConstants.awsBucketCompany);
		model.addAttribute("awsBucketProduct",AppConstants.awsBucketProduct);
		model.addAttribute("awsBucketCatalog",AppConstants.awsBucketCatalog);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);

		List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	       model.addAttribute("bookingBegunList",bookingsBegun);
		Company company = companyService.findById(id);
		User user = company.getUser();
		model.addAttribute("company", company);
		List<Speciality> specialities = specialityService.findByUser(user);
		model.addAttribute("specialityList",specialities);
		return "front/company/serviceSpecialities";	
   	}
}
