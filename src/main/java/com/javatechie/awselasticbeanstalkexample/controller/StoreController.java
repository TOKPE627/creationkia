package com.javatechie.awselasticbeanstalkexample.controller;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javatechie.awselasticbeanstalkexample.domain.Advertise;
import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyType;
import com.javatechie.awselasticbeanstalkexample.domain.ContactAtooly;
import com.javatechie.awselasticbeanstalkexample.domain.PartnerAtooly;
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.ContactAtoolyService;
import com.javatechie.awselasticbeanstalkexample.service.PartnerAtoolyService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;

@Controller
@RequestMapping("/store")
public class StoreController {

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private AdvertiseService advertiseService;
	@Autowired
	private ContactAtoolyService contactAtoolyService;
 
	@Autowired
	private PartnerAtoolyService partnerAtoolyService;
	
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

		List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	  model.addAttribute("bookingBegunList",bookingsBegun);
	  Advertise advertiseMobile = advertiseService.findByType(AppConstants.mobile);	    

	  if(Objects.nonNull(advertiseMobile)) {
		  model.addAttribute("advertiseMobileExists",true);
		  model.addAttribute("advertise",advertiseMobile);
	  } 
	 
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
		  
	 
	    List<Company> stores            = companyService.findAllByType(CompanyType.STORE);

		if(!stores.isEmpty()) {
			model.addAttribute("storeExist",true);
			model.addAttribute("storeList", stores);
		}
	
	  return "store";
	}	
	
	@GetMapping("/update")
	public String update(Model model) {
		
	  return "dashboard/store/update";
	}	
}
