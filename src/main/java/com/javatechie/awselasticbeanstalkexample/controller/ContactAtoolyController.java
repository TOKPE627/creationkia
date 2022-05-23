package com.javatechie.awselasticbeanstalkexample.controller;

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
import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.ContactAtooly;
import com.javatechie.awselasticbeanstalkexample.domain.Speciality;
import com.javatechie.awselasticbeanstalkexample.domain.Town;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.ContactAtoolyService;
import com.javatechie.awselasticbeanstalkexample.service.SpecialityService;
import com.javatechie.awselasticbeanstalkexample.service.TownService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;



@Controller
@RequestMapping("/contact")
public class ContactAtoolyController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private SpecialityService specialityService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private ContactAtoolyService contactAtoolyService;

	@Autowired
	private TownService townService;
	
	@RequestMapping("/add")
    public String add(Model model, Principal principal) {
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
		model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
		model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);    
		     
		
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		Company company = companyService.findByUser(user);
		model.addAttribute("company",company);
		
		model.addAttribute("userRole1",AppConstants.ROLE_1);
		List<Town> towns = townService.findAll();
		model.addAttribute("townList", towns);

		ContactAtooly contactAtooly = contactAtoolyService.findByName(AppConstants.APP_NAME);
		if(Objects.nonNull(contactAtooly)){
		  model.addAttribute("contact", contactAtooly);
		  return "dashboard/contactAtooly/update";
		}
     
		return "dashboard/contactAtooly/add";

	}


	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(
		@RequestParam("country") String country,
		@RequestParam("town") String town,	
		@RequestParam("address") String address,
		@RequestParam("phone") String phone,
		@RequestParam("email") String email,
		@RequestParam("facebook") String facebook,
		@RequestParam("whatsapp") String whatsapp,
		@RequestParam("rule") String rule
       ) {
	
		ContactAtooly contactAtooly = new ContactAtooly();
		contactAtooly.setCountry(country);
		contactAtooly.setTown(town);
		contactAtooly.setAddress(address);
		contactAtooly.setPhone(phone);
		contactAtooly.setEmail(email);
		contactAtooly.setFacebook(facebook);
		contactAtooly.setWhatsapp(whatsapp);
		contactAtooly.setRule(rule);

		contactAtoolyService.save(contactAtooly);
		
		return "redirect:/contact/add";		

	}



	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
		@RequestParam("country") String country,
		@RequestParam("town") String town,	
		@RequestParam("address") String address,
		@RequestParam("phone") String phone,
		@RequestParam("email") String email,
		@RequestParam("facebook") String facebook,
		@RequestParam("whatsapp") String whatsapp,
		@RequestParam("rule") String rule
       ) {
	
		ContactAtooly contactAtooly = contactAtoolyService.findByName(AppConstants.APP_NAME);
		contactAtooly.setCountry(country);
		contactAtooly.setTown(town);
		contactAtooly.setAddress(address);
		contactAtooly.setPhone(phone);
		contactAtooly.setEmail(email);
		contactAtooly.setFacebook(facebook);
		contactAtooly.setWhatsapp(whatsapp);
		contactAtooly.setRule(rule);

		contactAtoolyService.update(contactAtooly);
		
		return "redirect:/contact/add";		

	}
	//Front
	
	@GetMapping("")
	public String index(Model model) throws UnknownHostException {
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);

	    List<Booking> bookingsAddedToCart = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
		if(!bookingsAddedToCart.isEmpty()) {
	    	 model.addAttribute("bookingAddedToCartExist",true);
	    	 model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
		}
   
	  return "contact";
	}	
	

	@RequestMapping("/all")
	public String companyContact(@ModelAttribute("company_id") Long id, Model model) throws UnknownHostException {
		
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
		model.addAttribute("company", company);
		List<Speciality> specialities = specialityService.findByUser(company.getUser());
		model.addAttribute("specialityList",specialities);

		List<Speciality> specialityTop4List = specialityService.findTop4ByUser(company.getUser().getId());
		model.addAttribute("specialityTop4List",specialityTop4List);
		return "front/company/contact";
	}
}