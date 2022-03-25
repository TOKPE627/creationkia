package com.javatechie.awselasticbeanstalkexample.controller;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyType;
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
	private CategoryService categoryService;
	
	
	@GetMapping("")
	public String index(Model model) throws UnknownHostException {
	  List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	  model.addAttribute("bookingBegunList",bookingsBegun);
//		  Category category    = categoryService.findById(Long.parseLong("3"));
	  List<Company> stores = companyService.findAllByType(CompanyType.STORE);
	  model.addAttribute("storeList", stores); 	 
	  return "store";
	}	
	
	@GetMapping("/update")
	public String update(Model model) {
	  return "dashboard/store/update";
	}	
}
