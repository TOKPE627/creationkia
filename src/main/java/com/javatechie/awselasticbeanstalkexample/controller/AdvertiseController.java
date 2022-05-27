package com.javatechie.awselasticbeanstalkexample.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javatechie.awselasticbeanstalkexample.domain.Advertise;
import com.javatechie.awselasticbeanstalkexample.domain.AjaxResponseBody;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
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
	public String indexPc(Model model,Principal principal){
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);

		User user = userService.findByUsername(principal.getName());
		UserRole userRole =userRoleService.findByUser(user);
		if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
			model.addAttribute("user",user);
			model.addAttribute("userRole1",AppConstants.ROLE_1);
		}
		return "dashboard/advertise/add";
	}
	@RequestMapping("/update")
	public String infoPc(Model model,Principal principal){
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user",user);
		UserRole userRole =userRoleService.findByUser(user);
		model.addAttribute("userRole1",AppConstants.ROLE_1);

		if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
			Advertise advertise =advertiseService.findByType(AppConstants.pc);
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

	@RequestMapping("/tablet/add")
	public String indexTablet(Model model,Principal principal){
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);

		User user = userService.findByUsername(principal.getName());
		UserRole userRole =userRoleService.findByUser(user);
		if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
			model.addAttribute("user",user);
			model.addAttribute("userRole1",AppConstants.ROLE_1);
		}
		return "dashboard/advertise/tablet/add";
	}

	@RequestMapping("/tablet/update")
	public String infoTablet(Model model,Principal principal){
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user",user);
		UserRole userRole =userRoleService.findByUser(user);
		model.addAttribute("userRole1",AppConstants.ROLE_1);

		if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
			Advertise advertise =advertiseService.findByType(AppConstants.tablet);
			if(advertise !=null) {
				model.addAttribute("advertiseExists",true);
				model.addAttribute("advertise",advertise);
				model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
			}else {
				model.addAttribute("advertiseExists",false);
			}
	        	
		}
		return "dashboard/advertise/tablet/update";
	}

	@RequestMapping("/mobile/add")
	public String indexMobile(Model model,Principal principal){
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);

		User user = userService.findByUsername(principal.getName());
		UserRole userRole =userRoleService.findByUser(user);
		if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
			model.addAttribute("user",user);
			model.addAttribute("userRole1",AppConstants.ROLE_1);
		}
		return "dashboard/advertise/mobile/add";
	}

	@RequestMapping("/mobile/update")
	public String infoMobile(Model model,Principal principal){
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user",user);
		UserRole userRole =userRoleService.findByUser(user);
		model.addAttribute("userRole1",AppConstants.ROLE_1);

		if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
			Advertise advertise =advertiseService.findByType(AppConstants.mobile);
			if(advertise !=null) {
				model.addAttribute("advertiseExists",true);
				model.addAttribute("advertise",advertise);
				model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
			}else {
				model.addAttribute("advertiseExists",false);
			}
	        	
		}
		return "dashboard/advertise/mobile/update";
	}

	//Front
	@RequestMapping(value="/rest/tablet", method=RequestMethod.GET , produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> advertiseTablet(){
		Advertise result = advertiseService.findByType(AppConstants.tablet);
		String image =AppConstants.awsBucketAdvertise + result.getId()+"/tablet/"+result.getImage();
		result.setImage(image);
		return ResponseEntity.ok(result);
	}

	@RequestMapping(value="/rest/pc", method=RequestMethod.GET , produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> advertisePc(){
		Advertise result = advertiseService.findByType(AppConstants.pc);
		String image =AppConstants.awsBucketAdvertise + result.getId()+"/pc/"+result.getImage();
		result.setImage(image);
		return ResponseEntity.ok(result);
	}
	
	

}
