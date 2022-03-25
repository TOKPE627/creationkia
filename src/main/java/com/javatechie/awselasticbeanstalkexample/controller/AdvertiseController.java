package com.javatechie.awselasticbeanstalkexample.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javatechie.awselasticbeanstalkexample.domain.Advertise;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CatalogService;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyGaleryService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.ProductGaleryService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.SpecialityService;
import com.javatechie.awselasticbeanstalkexample.service.SubCategoryService;
import com.javatechie.awselasticbeanstalkexample.service.TownAvailableService;
import com.javatechie.awselasticbeanstalkexample.service.TownService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.service.WorkingHourService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
@Controller
@RequestMapping("/advertise")
public class AdvertiseController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private AdvertiseService advertiseService;
	
	
	@RequestMapping("/add")
	public String index(Model model,Principal principal){
		User user = userService.findByUsername(principal.getName());
		UserRole userRole =userRoleService.findByUser(user);
		if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
			model.addAttribute("user",user);
			model.addAttribute("userRole1",AppConstants.ROLE_1);
		}
		return "dashboard/advertise/add";
	}
	@RequestMapping("/update")
	public String info(Model model,Principal principal){
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user",user);
		UserRole userRole =userRoleService.findByUser(user);
		model.addAttribute("userRole1",AppConstants.ROLE_1);

		if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
			Advertise advertise =advertiseService.findByUser(user);
			if(advertise !=null) {
				model.addAttribute("advertiseExists",true);
				model.addAttribute("advertise",advertise);
				model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
			}else {
				model.addAttribute("advertiseExists",false);
			}
	        	
		}
		return "dashboard/advertise/update";
	}
}
