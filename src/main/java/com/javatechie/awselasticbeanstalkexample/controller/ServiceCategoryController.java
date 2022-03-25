package com.javatechie.awselasticbeanstalkexample.controller;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;

@Controller
@RequestMapping("/service")
public class ServiceCategoryController {
	
	@Autowired
	private ProductService productService;

	@Autowired
	private BookingService bookingService;
	
	
	@GetMapping("")
	public String index(Model model) throws UnknownHostException {
		 List<Product> services = productService.findAllByCategory(AppConstants.CATEGORY_SERVICE);

		 List<Product> s1 = productService.findAllBySubCategory(AppConstants.CATEGORY_SERVICE, AppConstants.SUB_SERVICE_1); 
		 List<Product> s2 = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SERVICE_2); 
		 List<Product> s3 = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SERVICE_3); 
		 List<Product> s4 = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SERVICE_4); 
		 List<Product> s5 = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SERVICE_5); 
		 List<Product> s6 = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SERVICE_6); 
		 List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
		 
		 model.addAttribute("serviceList",services);
		 model.addAttribute("sewingList",s1); 
		 model.addAttribute("hairdressingList",s2); 
		 model.addAttribute("carpentryList",s3); 
		 model.addAttribute("grosseryList",s4); 
		 model.addAttribute("mechanicList",s5); 
		 model.addAttribute("repairList",s6); 
		return "service";
	}
	
	@GetMapping("/update")
	public String update(Model model) {
	  return "dashboard/service/update";
	}
}
