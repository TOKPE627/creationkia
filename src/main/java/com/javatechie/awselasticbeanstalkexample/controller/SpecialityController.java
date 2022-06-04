package com.javatechie.awselasticbeanstalkexample.controller;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.Speciality;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.WorkingHour;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.SpecialityService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.service.WorkingHourService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
@RequestMapping("/speciality")
public class SpecialityController {
    @Autowired 
    private UserService userService;
	
    @Autowired
    private UserRoleService userRoleService;
    
	@Autowired
	private SpecialityService specialityService;

	@Autowired
	private WorkingHourService workingHourService;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping("/specialityInfo")
	public String specialityInfo(@RequestParam("id") Long id,Model model,Principal principal) {
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
		model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
		model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);  
		
		User user = userService.findByUsername(principal.getName());
		Speciality speciality = specialityService.findById(id);
		model.addAttribute("user", user);
		Company company=companyService.findByUser(user);
		model.addAttribute("company",company);
		UserRole userRole =userRoleService.findByUser(user);
		model.addAttribute("speciality",speciality);
	 	if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
			model.addAttribute("userRole2",AppConstants.ROLE_2);
		}
	 	if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
			model.addAttribute("userRole3",AppConstants.ROLE_3);
		}		
		return "dashboard/speciality/info";
	}
	
	@RequestMapping("/add")
	public String add(Model model, Principal principal) {
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
		model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
		model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);  

		User user = userService.findByUsername(principal.getName());
		List<Speciality> specialities = specialityService.findByUser(user);
		model.addAttribute("user", user);
		Company company=companyService.findByUser(user);
		model.addAttribute("company",company);
		model.addAttribute("specialityList", specialities);
		UserRole userRole = userRoleService.findByUser(user);
		
		List<WorkingHour> workingHours = workingHourService.findByUser(user);
		if(workingHours.isEmpty()){
			return "redirect:/agenda/add";
		}
		// if (userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
		// 	model.addAttribute("userRole2", AppConstants.ROLE_2);
		// }
		if (userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
			model.addAttribute("userRole3", AppConstants.ROLE_3);
		}
		return "dashboard/speciality/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addSpecialityPost(@ModelAttribute("speciality") Speciality speciality,
		 Principal principal)
			throws IOException {
		User user = userService.findByUsername(principal.getName());
		System.out.println("-----User------");
		speciality.setUser(user);
	    specialityService.save(speciality);
		return "redirect:/speciality/specialityList";
	}
	
	  @RequestMapping(value = "/specialityList", method = RequestMethod.GET)
	  public String all(Model model,Principal principal) throws IOException{
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
		model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
		model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);  

		     User user = userService.findByUsername(principal.getName());
			 model.addAttribute("user", user);
			 Company company=companyService.findByUser(user);
			 model.addAttribute("company",company);
			 List<Speciality> specialities = specialityService.findByUser(user); 
			 model.addAttribute("specialityList", specialities); 
			 UserRole userRole =userRoleService.findByUser(user);
		 	if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
				model.addAttribute("userRole2",AppConstants.ROLE_2);
			}
		 	if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
				model.addAttribute("userRole3",AppConstants.ROLE_3);
			}
			return "dashboard/speciality/all";  
	  }
	  
	  
		@RequestMapping("/updateSpeciality")
		public String updateSpeciality(@RequestParam("id") Long id, Model model) {
			model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		    model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
		    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
			model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		    model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);  

			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Speciality speciality = specialityService.findById(id);
			User user = userService.findByUsername(userDetails.getUsername());
			model.addAttribute("user", user);
			Company company=companyService.findByUser(user);
			model.addAttribute("company",company);
			model.addAttribute("speciality", speciality);
			 UserRole userRole =userRoleService.findByUser(user);
		 	if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
				model.addAttribute("userRole2",AppConstants.ROLE_2);
			}
		 	if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
				model.addAttribute("userRole3",AppConstants.ROLE_3);
			}
			return "dashboard/speciality/updateInfo";
		}

		@RequestMapping(value = "/updateSpeciality", method = RequestMethod.POST)
		public String updateSpeciality(@ModelAttribute("speciality") Speciality speciality, 
				Principal principal
				) throws IOException {
			User user = userService.findByUsername(principal.getName());
			speciality.setUser(user);
			specialityService.update(speciality);
			return "redirect:/speciality/specialityInfo?id=" + speciality.getId();
		}
		
		@RequestMapping(value="/remove", method=RequestMethod.POST)
		public String remove(
				@ModelAttribute("id") String id
				) {
			specialityService.remove(Long.parseLong(id.substring(14)));
			return "redirect:/speciality/specialityList";
		}
		
		
		//Front
		@RequestMapping("/info")
		public String Info(@RequestParam("id") Long id,Model model) {
			model.addAttribute("url",AppConstants.url);
			model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
			model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
			model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
			model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
			model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
			model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
			model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);
		   Speciality speciality = specialityService.findById(id);
		   Company company = companyService.findByUser(speciality.getUser());
		   model.addAttribute("speciality",speciality);
		   model.addAttribute("company",company);
		   return "speciality";
		}
    
}