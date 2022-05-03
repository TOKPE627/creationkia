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
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
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
	
	
	@GetMapping("")
	public String index(Model model) throws UnknownHostException {

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
	  }     List<Company> stores            = companyService.findAllByType(CompanyType.STORE);

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
