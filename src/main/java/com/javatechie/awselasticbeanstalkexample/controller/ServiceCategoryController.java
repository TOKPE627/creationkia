package com.javatechie.awselasticbeanstalkexample.controller;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javatechie.awselasticbeanstalkexample.domain.Advertise;
import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyType;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.Speciality;
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
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
	
	@Autowired
	private AdvertiseService advertiseService;
	
	@Autowired
    private CompanyService companyService;

	@GetMapping("")
	public String index(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		Advertise advertise = advertiseService.findByName(AppConstants.APP_NAME);
		//  List<Product> services = productService.findAllByCategory(AppConstants.CATEGORY_SERVICE);
		List<Company> services          = companyService.findAllByType(CompanyType.SERVICE);

		 List<Product> sewings = productService.findAllBySubCategory(AppConstants.CATEGORY_SERVICE, AppConstants.SUB_SERVICE_1); 
		 List<Product> hairdressings = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SERVICE_2); 
		 List<Product> carpentries = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SERVICE_3); 
		 List<Product> grosseries = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SERVICE_4); 
		 List<Product> mecas = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SERVICE_5); 
		 List<Product> repairs = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SERVICE_6); 
		 List<Product> others = productService.findAllBySubCategory(AppConstants.CATEGORY_SHOP, AppConstants.SUB_SERVICE_7); 
		 List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
		
		  
		  if(Objects.nonNull(advertise)) {
			model.addAttribute("advertiseExists",true);
			model.addAttribute("advertise",advertise);
		}
		if(!services.isEmpty()) {
			model.addAttribute("serviceExist",true);
			model.addAttribute("serviceList",services); 
		}
		if(!sewings.isEmpty()) {
			model.addAttribute("sewingExist",true);
			model.addAttribute("sewingList",sewings); 
		}
		if(!hairdressings.isEmpty()) {
			model.addAttribute("hairdressingExist",true);
			model.addAttribute("hairdressingList",hairdressings); 
		}
		if(!carpentries.isEmpty()) {
			model.addAttribute("carpentryExist",true);
			model.addAttribute("carpentryList",carpentries); 
		}
		  
		if(!grosseries.isEmpty()) {
			model.addAttribute("grosseryExist",true);
			model.addAttribute("grosseryList",grosseries); 
		}
		
		if(!mecas.isEmpty()) {
			model.addAttribute("mecaExist",true);
			model.addAttribute("mecaList",mecas); 
		}
		if(!repairs.isEmpty()) {
			model.addAttribute("repairExist",true);
			model.addAttribute("repairList",repairs); 
		}
		if(!others.isEmpty()) {
			model.addAttribute("otherExist",true);
			model.addAttribute("otherList",others); 
		}
		return "service";
	}
	
	@GetMapping("/update")
	public String update(Model model) {
	  return "dashboard/service/update";
	}

	//
    	
}
