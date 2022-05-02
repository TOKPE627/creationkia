package com.javatechie.awselasticbeanstalkexample.controller;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javatechie.awselasticbeanstalkexample.domain.Advertise;
import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.BookingCompany;
import com.javatechie.awselasticbeanstalkexample.domain.Catalog;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyType;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.Speciality;
import com.javatechie.awselasticbeanstalkexample.domain.Town;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.WorkingHour;
import com.javatechie.awselasticbeanstalkexample.domain.security.PasswordResetToken;
import com.javatechie.awselasticbeanstalkexample.domain.security.Role;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;
import com.javatechie.awselasticbeanstalkexample.service.BookingCompanyService;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CatalogService;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.RoleService;
import com.javatechie.awselasticbeanstalkexample.service.SpecialityService;
import com.javatechie.awselasticbeanstalkexample.service.StorageService;
import com.javatechie.awselasticbeanstalkexample.service.TownService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.service.WorkingHourService;
import com.javatechie.awselasticbeanstalkexample.service.impl.UserSecurityService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;
import com.javatechie.awselasticbeanstalkexample.utility.FileUploadUtil;
import com.javatechie.awselasticbeanstalkexample.utility.MailConstructor;
import com.javatechie.awselasticbeanstalkexample.utility.SecurityUtility;

@Controller
public class HomeController {
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
    private StorageService service;
	
	@Autowired
	private AdvertiseService advertiseService;

	@Autowired
	private BookingCompanyService bookingCompanyService;

	@RequestMapping("/login-admin")
	public String loginAdmin() {
		return "loginAdmin";
	}
	@RequestMapping("/loginType")
	public String loginType(Model model) throws UnknownHostException {
		List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	       model.addAttribute("bookingBegunList",bookingsBegun);
		return "loginType";
	}
	
	@RequestMapping("/chooseTypeAccount")
	public ResponseEntity<?> chooseTypeAccount(@ModelAttribute("type") String type){
		Company result = new Company();
		result.setDescription(type);
		result.setName(AppConstants.url);
		return ResponseEntity.ok(result);
	}
	
	
	@RequestMapping("/login")
	public String login(
			@ModelAttribute("type") String type,
			Model model) throws UnknownHostException{
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);

	     List<Booking> bookingsAddedToCart = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
			if(!bookingsAddedToCart.isEmpty()) {
		    	 model.addAttribute("bookingAddedToCartExist",true);
		    	 model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
			}
	       model.addAttribute("type",type);
		  return "login";
	}
	
	/*
	 * @RequestMapping("/login") public String login(Model model) {
	 * model.addAttribute("classActiveLogin", true); return "myAccount"; }
	 */
	
	@RequestMapping("/createAccount")
	public String createAccount(Model model) throws UnknownHostException{
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);

	     List<Booking> bookingsAddedToCart = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
			if(!bookingsAddedToCart.isEmpty()) {
		    	 model.addAttribute("bookingAddedToCartExist",true);
		    	 model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
			}
		List<Town> towns = townService.findAll();
		model.addAttribute("townList",towns);
		return "createAccount";
	}
	
	@RequestMapping(value="/createAccount", method = RequestMethod.POST)
	public String createAccountPost(
			HttpServletRequest request,
			@ModelAttribute("role")      String role,
			@ModelAttribute("username")  String username,
			@ModelAttribute("email")     String userEmail,
			@ModelAttribute("lastName")  String lastName,
			@ModelAttribute("firstName") String firstName,
			@ModelAttribute("phone")     String phone,
			@ModelAttribute("whatsapp")  String whatsapp,
			@ModelAttribute("facebook")  String facebook,
			@ModelAttribute("instagram") String instagram,
			@ModelAttribute("twitter")   String twitter,
			@ModelAttribute("town")      String town,
			@ModelAttribute("district")  String district,
			@ModelAttribute("address")   String address,
			@ModelAttribute("password")  String password,
		     @RequestParam("extraImageFile")  MultipartFile[] extraMultipartFiles,

			Model model
			) throws Exception{

		model.addAttribute("email", userEmail);
		model.addAttribute("username", username);
		model.addAttribute("role",role);
	    List<Booking> bookingsAddedToCart = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
		if(!bookingsAddedToCart.isEmpty()) {
	    	 model.addAttribute("bookingAddedToCartExist",true);
	    	 model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
		}
		List<Town> towns = townService.findAll();
		model.addAttribute("townList",towns);
		
		if (userService.findByUsername(username) != null) {
			model.addAttribute("usernameExists", true);
			return "createAccount";
		}
		
		if (userService.findByEmail(userEmail) != null) {
			model.addAttribute("emailExists", true);
			return "createAccount";			
		}
		
		User user = new User();
		user.setUsername(username);
		user.setEmail(userEmail);
		user.setLastName(lastName);
		user.setFirstName(firstName);
		user.setPhone(phone);
		user.setWhatsapp(whatsapp);
		user.setFacebook(facebook);
		user.setInstagram(instagram);
		user.setTwitter(twitter);
		user.setTown(town);
		user.setDistrict(district);
		user.setAddress(address);
			
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
	
		Role roleR = new Role();
        roleR.setName(role);//By Default
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, roleR));
		
		int count=0;
		for(MultipartFile extraMultipart : extraMultipartFiles) {
			String extraImageName = StringUtils.cleanPath(extraMultipart.getOriginalFilename());
		    if(count == 0) user.setPhoto(extraImageName);
		    count++;
		}
		User savedUser = userService.createUser(user, userRoles);

	   // String uploadDir=AppConstants.FILE_UPLOAD_USER_PATH+savedUser.getId();
		for(MultipartFile extraMultipart : extraMultipartFiles) {
			//String fileName = "user/"+savedUser.getId()+"/"+
			//StringUtils.cleanPath(extraMultipart.getOriginalFilename());
			//FileUploadUtil.saveFile(uploadDir, extraMultipart, fileName);
			
			service.uploadFileInAWSDirectory(extraMultipart,savedUser.getId(),AppConstants.bucket_user);
		}
		return "message/registrationSuccess";
	}
	
	
	@RequestMapping("/myAccount")
	public String myAccount(Model model) {
		model.addAttribute("classActiveLogin", true);
		return "myAccount";
	}
	
	@RequestMapping(value="/newUser", method = RequestMethod.POST)
	public String newUserPost(
			HttpServletRequest request,
			@ModelAttribute("email") String userEmail,
			@ModelAttribute("username") String username,
			@ModelAttribute("role") String role,
			Model model
			) throws Exception{
		model.addAttribute("classActiveNewAccount", true);
		model.addAttribute("email", userEmail);
		model.addAttribute("username", username);
		model.addAttribute("role",role);
		
		if (userService.findByUsername(username) != null) {
			model.addAttribute("usernameExists", true);
			
			return "myAccount";
		}
		
		if (userService.findByEmail(userEmail) != null) {
			model.addAttribute("emailExists", true);
			
			return "myAccount";
		}
		
		User user = new User();
		user.setUsername(username);
		user.setEmail(userEmail);
		
		String password = SecurityUtility.randomPassword();
		
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		Role roleR = new Role();
        roleR.setName(role);//By Default
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, roleR));
		userService.createUser(user, userRoles);
		
		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(user, token);
		
		String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		
		SimpleMailMessage email = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);
		
		mailSender.send(email);

		model.addAttribute("emailSent", "true");
		//model.addAttribute("orderList", user.getOrderList());
		
		return "myAccount";
	}
	
	@RequestMapping("/newUser")
	public String newUser(Locale locale, @RequestParam("token") String token, Model model) {
		PasswordResetToken passToken = userService.getPasswordResetToken(token);

		if (passToken == null) {
			String message = "Invalide Token.";
			model.addAttribute("message", message);
			return "redirect:/badRequest";
		}

		User user = passToken.getUser();
		String username = user.getUsername();

		UserDetails userDetails = userSecurityService.loadUserByUsername(username);

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		model.addAttribute("user", user);

		model.addAttribute("classActiveEdit", true);
		
		return "myProfile";
	}
	
	@RequestMapping(value="/updateUserInfo", method=RequestMethod.POST)
	public String updateUserInfo(
			//@ModelAttribute("role") String roleTaken,
			@ModelAttribute("user") User user,
			@ModelAttribute("newPassword") String newPassword,
		     @RequestParam("extraImageFile")  MultipartFile[] extraMultipartFiles,
			Model model
			) throws Exception {
		User currentUser = userService.findById(user.getId());
		//Role		
		//Role role;
		UserRole userRole =userRoleService.findByUser(currentUser);
//		Long roleId = userRole.getRole().getRoleId();
//		role = roleService.findOne(roleId);	    
		
		if(currentUser == null) {
			throw new Exception ("Utilisateur introuvable");
		}
		
		
		int count=0;
		for(MultipartFile extraMultipart : extraMultipartFiles) {
			String extraImageName = StringUtils.cleanPath(extraMultipart.getOriginalFilename());
		    if(count == 0) currentUser.setPhoto(extraImageName);
		    count++;
		}
		currentUser.setLastName(user.getLastName());
		currentUser.setFirstName(user.getFirstName());
		currentUser.setTown(user.getTown());
		currentUser.setAddress(user.getAddress());
		currentUser.setWhatsapp(user.getWhatsapp());
		currentUser.setInstagram(user.getInstagram());
		currentUser.setTwitter(user.getTwitter());
		currentUser.setFacebook(user.getFacebook());
		currentUser.setPhone(user.getPhone());
		User saveUser  = userService.save(currentUser);
	    String uploadDir=AppConstants.FILE_UPLOAD_USER_PATH+saveUser.getId();
		for(MultipartFile extraMultipart : extraMultipartFiles) {
			String fileName = StringUtils.cleanPath(extraMultipart.getOriginalFilename());
			FileUploadUtil.saveFile(uploadDir, extraMultipart, fileName);
		}
		
//        if(roleTaken.equals(AppConstants.ROLE_2)) {
//    	 System.out.println("Role updated: "+ roleTaken);
//         role.setName(AppConstants.ROLE_2);
//         roleService.update(role);
//        }
//    
//	    if(roleTaken.equals(AppConstants.ROLE_3)) {
//	    	System.out.println("Role updated: "+ roleTaken);
//	        role.setName(AppConstants.ROLE_3);
//	        roleService.update(role);
//	     }
		
		/*Member: check email already exists*/ 		/*check username already exists*/
		if ((userService.findByEmail(user.getEmail())!=null) && (userService.findByUsername(user.getUsername())!=null)) {
			if(userService.findByEmail(user.getEmail()).getId() != currentUser.getId() && (userRole.getRole().getName()).equals(AppConstants.ROLE_1)) {
				model.addAttribute("emailExists", true);
				model.addAttribute("usernameExists", true);
				return "dashboard/member/home";
			}
		}
		
		/*Storekeeper: check email already exists*/ 		/*check username already exists*/
		if ((userService.findByEmail(user.getEmail())!=null) && (userService.findByUsername(user.getUsername())!=null)) {
			if(userService.findByEmail(user.getEmail()).getId() != currentUser.getId() && (userRole.getRole().getName()).equals(AppConstants.ROLE_2)) {
				model.addAttribute("emailExists", true);
				model.addAttribute("usernameExists", true);
				return "dashboard/storekeeper/home";
			}
		}
		/*Service: check email already exists*/ 		/*check username already exists*/
		if ((userService.findByEmail(user.getEmail())!=null) && (userService.findByUsername(user.getUsername())!=null)) {
			if(userService.findByEmail(user.getEmail()).getId() != currentUser.getId() && (userRole.getRole().getName()).equals(AppConstants.ROLE_3)) {
				model.addAttribute("emailExists", true);
				model.addAttribute("usernameExists", true);
				return "dashboard/multiservice/home";
			}
		}
		/*Customer: check email already exists*/ 		/*check username already exists*/
		if ((userService.findByEmail(user.getEmail())!=null) && (userService.findByUsername(user.getUsername())!=null)) {
			if(userService.findByEmail(user.getEmail()).getId() != currentUser.getId() && (userRole.getRole().getName()).equals(AppConstants.ROLE_4)) {
				model.addAttribute("emailExists", true);
				model.addAttribute("usernameExists", true);
				return "dashboard/customer/home";
			}
		}
		
		/*check username already exists*/
//		if (userService.findByUsername(user.getUsername())!=null) {
//			if(userService.findByUsername(user.getUsername()).getId() != currentUser.getId()) {
//				model.addAttribute("usernameExists", true);
//				return "dashboard/home";
//			}
//		}
		
//		update password
		if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")){
			BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
			String dbPassword = currentUser.getPassword();
			if(passwordEncoder.matches(user.getPassword(), dbPassword)){
				currentUser.setPassword(passwordEncoder.encode(newPassword));
			} else {
				model.addAttribute("incorrectPassword", true);
				return "myProfile";
			}
		}
		
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setPhone(user.getPhone());
		currentUser.setUsername(user.getUsername());
		currentUser.setEmail(user.getEmail());
		
		userService.save(currentUser);
		
		model.addAttribute("updateSuccess", true);
		model.addAttribute("user", currentUser);
		model.addAttribute("classActiveEdit", true);
		
		model.addAttribute("listOfShippingAddresses", true);
		model.addAttribute("listOfCreditCards", true);
		
		UserDetails userDetails = userSecurityService.loadUserByUsername(currentUser.getUsername());

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		//model.addAttribute("orderList", user.getOrderList());
		
		return "myProfile";
	}
	
	

	
	@RequestMapping("/forgetPassword")
	public String forgetPassword(
			HttpServletRequest request,
			@ModelAttribute("email") String email,
			Model model
			) {

		model.addAttribute("classActiveForgetPassword", true);
		
		User user = userService.findByEmail(email);
		
		if (user == null) {
			model.addAttribute("emailNotExist", true);
			return "myAccount";
		}
		
		String password = SecurityUtility.randomPassword();
		
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		
		userService.save(user);
		
		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(user, token);
		
		String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		
		SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);
		
		mailSender.send(newEmail);
		
		model.addAttribute("forgetPasswordEmailSent", "true");
		
		
		return "myAccount";
	}
	
	@RequestMapping("/dashboard")	
	public String dashboard(Model model) throws UnknownHostException {
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
	
	    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*check username*/
		
		if (userService.findByUsername(userDetails.getUsername())!=null) {
			User currentUser = userService.findByUsername(userDetails.getUsername());
			UserRole userRole =userRoleService.findByUser(currentUser);
			/*
			 * model.addAttribute("user", userDetails.getUsername());
			 */			
			model.addAttribute("user",currentUser);
		    //System.out.println(userRole.getRole().getName());	
			if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
				model.addAttribute("userRole1",AppConstants.ROLE_1);
				return "dashboard/home";
			}
			
		   //Check if company registered
			if(userRole.getRole().getName().equals(AppConstants.ROLE_2) || (userRole.getRole().getName().equals(AppConstants.ROLE_3))) {
			   Company company = companyService.findByUser(currentUser);
			   List<Catalog> catalogs = catalogService.findByUser(currentUser);
			   List<Speciality> specialities = specialityService.findByUser(currentUser);
               model.addAttribute("company",company);
               model.addAttribute("catalogList",catalogs);
               model.addAttribute("specialityList",specialities);
               
          	   if(company == null) {
          			if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
          				model.addAttribute("userRole2",AppConstants.ROLE_2);
          			 }
          			if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
          				model.addAttribute("userRole3",AppConstants.ROLE_3);
          			 }
    		         return "redirect:/company/add";          			
      		   }else {
      		      if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
     				   model.addAttribute("userRole2",AppConstants.ROLE_2);
      		    	  if(company.getGalery() ==null) {
      		    		return "dashboard/company/add/addGalery";
      		    	  }
      				  List<WorkingHour> workingHours = workingHourService.findByUser(currentUser);
      		    	  model.addAttribute("workingHourList",workingHours); 
    				  return "dashboard/home";  
      			}
      			if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
      				model.addAttribute("userRole3",AppConstants.ROLE_3);
      			    if(company.getGalery() ==null) {
    		    		return "dashboard/company/add/addGalery";
    		        }
    			    return "dashboard/home";
      			}  
      		   }
			}
			
			if(userRole.getRole().getName().equals(AppConstants.ROLE_4)) {
				model.addAttribute("userRole4",AppConstants.ROLE_4);

	
			    List<Booking> bookingsAddedToCart = 
			    		bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);

				model.addAttribute("bookingAddedToCartExist",false);

			    if(!bookingsAddedToCart.isEmpty()) {
			       System.out.println("Added to cart " +bookingsAddedToCart.size());
					model.addAttribute("bookingAddedToCartExist",true);
			    	 model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
					 return "dashboard/customer/addedToCart";
				}
				else{
					List<Booking> bookingsBegun = bookingService.findByCustomer(currentUser,AppConstants.ORDER_STATUS_0);
					if(!bookingsBegun.isEmpty()) {
						System.out.println("Bookings begun " +bookingsBegun.size());
						double total_price_orders=0;
						for(Booking booking: bookingsBegun) {
							double total_price = booking.getTotal_price();
							total_price_orders = total_price + total_price_orders;
						}
						model.addAttribute("bookingBegunListExist",true);
						model.addAttribute("bookingBegunList",bookingsBegun);
						model.addAttribute("total_price_orders",total_price_orders);	
					}else{
						List<BookingCompany> bookingsCompanyBegun = bookingCompanyService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(), AppConstants.ORDER_STATUS_0);
						if(!bookingsCompanyBegun.isEmpty()){
							System.out.println("Company begun " +bookingsCompanyBegun.size());
							model.addAttribute("bookingCompanyBegunListExist", true);
							model.addAttribute("bookingCompanyBegunList",bookingsCompanyBegun);
							return "dashboard/customer/homeBookingCompanyBegun";
						}
						else{
							List<BookingCompany> bookingCompanies = bookingCompanyService.findByCustomer(currentUser, AppConstants.ORDER_STATUS_1);
							if(!bookingCompanies.isEmpty()){
								System.out.println("Company pendings " +bookingCompanies.size());
								model.addAttribute("bookingCompanyListExist", true);
								model.addAttribute("bookingCompanyList", bookingCompanies);
								return "dashboard/customer/homeBookingCompany";
							}
						}
						
					}
				}
		   }
	   }
	     return "dashboard/customer/home";
	  }
	}

