package com.javatechie.awselasticbeanstalkexample.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("/agenda")
public class AgendaController {
   

	@Autowired
	private UserRoleService userRoleService;

    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private WorkingHourService workingHourService;
	
	
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
		  UserRole userRole =userRoleService.findByUser(user);
		  model.addAttribute("user",user);
		  Company company=companyService.findByUser(user);
		  model.addAttribute("company",company);
		  
		  if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
			model.addAttribute("userRole3",AppConstants.ROLE_3);
   		  }
		  return "dashboard/agenda/selectDay";
	}
	
	@RequestMapping(value="/selectDay")
	public String selectDay(
			@RequestParam("day") String day,
			Model model, Principal principal)  {
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
		   model.addAttribute("day",day);
		   List<WorkingHour>  workingHours =workingHourService.findByUserByDay(user, day);
		   
		   UserRole userRole =userRoleService.findByUser(user);
		   if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
				model.addAttribute("userRole3",AppConstants.ROLE_3);
		   }
		  
		   if(!workingHours.isEmpty()){
				String message = "Les horaires du jour " + day + " sont déjà configurés"; 
				model.addAttribute("alreadyConfiguredExist",true);  
				model.addAttribute("alreadyConfiguredMessage",message);
				return "dashboard/agenda/selectDay";
			}
		 		
		   return "dashboard/agenda/add";
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(	
			@ModelAttribute("dy") String d,
			@ModelAttribute("openHour") String openHour,
			@ModelAttribute("closeHour") String closeHour,
			 Model model,Principal principal
		)
	{	
		System.out.println("Day: " +d);
	    User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		UserRole userRole =userRoleService.findByUser(user);

		WorkingHour workingHour = new  WorkingHour();
		workingHour.setUser(user);
		workingHour.setOpenHour(openHour);
		workingHour.setCloseHour(closeHour);
        if(d.equals(AppConstants.DAY_1)){

			workingHour.setDay1(d); 
			workingHourService.save(workingHour);
		} 
        if(d.equals(AppConstants.DAY_2)) {
			workingHour.setDay2(d);
			workingHourService.save(workingHour);
		} 
        if(d.equals(AppConstants.DAY_3)){
			workingHour.setDay3(d);
			workingHourService.save(workingHour);
		} 
        if(d.equals(AppConstants.DAY_4)){
			workingHour.setDay4(d);
		    workingHourService.save(workingHour);
		} 
        if(d.equals(AppConstants.DAY_5)){
			workingHour.setDay5(d);
			workingHourService.save(workingHour);
		} 
        if(d.equals(AppConstants.DAY_6)){
			workingHour.setDay6(d);
			workingHourService.save(workingHour);
		}
        if(d.equals(AppConstants.DAY_7)){ 
			workingHour.setDay7(d);
			workingHourService.save(workingHour);
		}

		if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
			model.addAttribute("userRole3",AppConstants.ROLE_3);
		}		
		return "redirect:/agenda/all";
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
		model.addAttribute("user",user);
		Company company=companyService.findByUser(user);
		model.addAttribute("company",company);
		UserRole userRole =userRoleService.findByUser(user);
        List<WorkingHour> wDay1 = workingHourService.findByDay(AppConstants.DAY_1);
 		model.addAttribute("day1List",wDay1);
 	    List<WorkingHour> wDay2 = workingHourService.findByDay(AppConstants.DAY_2);
 		model.addAttribute("day2List",wDay2);
 	    List<WorkingHour> wDay3 = workingHourService.findByDay(AppConstants.DAY_3);
 	 	model.addAttribute("day3List",wDay3);
 	    List<WorkingHour> wDay4 = workingHourService.findByDay(AppConstants.DAY_4);
 		model.addAttribute("day4List",wDay4);
 		List<WorkingHour> wDay5 = workingHourService.findByDay(AppConstants.DAY_5);
 	 	model.addAttribute("day5List",wDay5);
 	    List<WorkingHour> wDay6 = workingHourService.findByDay(AppConstants.DAY_6);
 	 	model.addAttribute("day6List",wDay6);
 	   List<WorkingHour> wDay7 = workingHourService.findByDay(AppConstants.DAY_7);
		model.addAttribute("day7List",wDay7);
		if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
			model.addAttribute("userRole3",AppConstants.ROLE_3);
		}		
		return "dashboard/agenda/all";
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
		   model.addAttribute("user",user);
		   Company company=companyService.findByUser(user);
		   model.addAttribute("company",company);
		   model.addAttribute("workingHour",workingHour);
		   UserRole userRole =userRoleService.findByUser(user);
		   if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
				model.addAttribute("userRole3",AppConstants.ROLE_3);
		   }		
		   return "dashboard/agenda/update";
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
		
		if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
			model.addAttribute("userRole3",AppConstants.ROLE_3);
		}		
		return "redirect:/agenda/all";
	}
	   
	
}