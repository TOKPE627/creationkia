package com.javatechie.awselasticbeanstalkexample.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.WorkingHour;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.service.WorkingHourService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
@RequestMapping("/workingHour")
public class WorkingHourController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
		
	@Autowired
	private WorkingHourService workingHourService;

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping("/add")
    public String add(Model model, Principal principal) {
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
		model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
		model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
	   
		  User user = userService.findByUsername(principal.getName());
          model.addAttribute("user", user);
		  Company company=companyService.findByUser(user);
		  model.addAttribute("company",company);
		  UserRole userRole =userRoleService.findByUser(user);
		  
		 List<WorkingHour> workingHours = workingHourService.findByUser(user);
		 if(!workingHours.isEmpty()){
			 System.out.println("Size W: " + workingHours.size());
			 return "redirect:/workingHour/all";
		 }
		  if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
			model.addAttribute("userRole2",AppConstants.ROLE_2);
   		  }
		  return "dashboard/workingHour/add";
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(	
			@RequestParam("openHour") String openHour,
			@RequestParam("closeHour") String closeHour,
			 Model model,Principal principal
		)
	{	
	    User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		UserRole userRole =userRoleService.findByUser(user);

		WorkingHour workingHour = new  WorkingHour();
		workingHour.setUser(user);
		workingHour.setOpenHour(openHour);
		workingHour.setCloseHour(closeHour);
		workingHour.setStatus("open");
		workingHourService.save(workingHour);
		
		if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
			model.addAttribute("userRole2",AppConstants.ROLE_2);
		}		
		return "redirect:/workingHour/all";
	}
	
	@RequestMapping(value = "/all")
	public String all(	
			 Model model,Principal principal
		)
	{	
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
		model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
		model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
	   
	    User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		UserRole userRole =userRoleService.findByUser(user);
		Company company=companyService.findByUser(user);
		model.addAttribute("company",company);
        List<WorkingHour> workingHours = workingHourService.findByUser(user);
 		model.addAttribute("workingHourList",workingHours);
		if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
			model.addAttribute("userRole2",AppConstants.ROLE_2);
		}		
		return "dashboard/workingHour/all";
	}
	
	@RequestMapping(value="/byOwner")
	public String byOwner(
			@RequestParam("id") Long id,
			Model model, Principal principal)  {
				model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
				model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
				model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
				model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
				model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
				model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
			   
		   WorkingHour workingHour = workingHourService.findById(id);
		   User user = userService.findByUsername(principal.getName());
		   model.addAttribute("user", user);
		   Company company=companyService.findByUser(user);
		   model.addAttribute("company",company);
		   model.addAttribute("workingHour",workingHour);
		   UserRole userRole =userRoleService.findByUser(user);
		   if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
				model.addAttribute("userRole2",AppConstants.ROLE_2);
		   }		
		   return "dashboard/workingHour/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(	
			@RequestParam("id") Long id,
			@RequestParam("status") String status,
			@RequestParam("openHour") String openHour,
			@RequestParam("closeHour") String closeHour,
			 Model model,Principal principal
		)
	{	
	    User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		UserRole userRole =userRoleService.findByUser(user);

		WorkingHour workingHour = workingHourService.findById(id);
		workingHour.setOpenHour(openHour);
		workingHour.setCloseHour(closeHour);
		workingHour.setStatus(status);
		workingHourService.update(workingHour);
		
		if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
			model.addAttribute("userRole2",AppConstants.ROLE_2);
		}		
		return "redirect:/workingHour/all";
	}
	
	
}
