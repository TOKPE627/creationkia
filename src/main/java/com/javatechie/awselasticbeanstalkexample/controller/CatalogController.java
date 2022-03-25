package com.javatechie.awselasticbeanstalkexample.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javatechie.awselasticbeanstalkexample.domain.Catalog;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.CatalogService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.FileUploadUtil;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    @Autowired 
    private UserService userService;
	
    @Autowired
    private UserRoleService userRoleService;
    
	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping("/catalogInfo")
	public String catalogInfo(@RequestParam("id") Long id,Model model,Principal principal) {
		model.addAttribute("awsBucketCatalog",AppConstants.awsBucketCatalog);

		User user = userService.findByUsername(principal.getName());
		Catalog catalog = catalogService.findById(id);
		model.addAttribute("user", user);
		UserRole userRole =userRoleService.findByUser(user);

		Company company=companyService.findByUser(user);
		model.addAttribute("company",company);
		
		model.addAttribute("catalog",catalog);
	 	if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
			model.addAttribute("userRole2",AppConstants.ROLE_2);
		}
	 	if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
			model.addAttribute("userRole3",AppConstants.ROLE_3);
		}		
		return "dashboard/catalog/info";
	}
	
	@RequestMapping("/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		List<Catalog> catalogs = catalogService.findByUser(user);
		model.addAttribute("user", user);
		Company company=companyService.findByUser(user);
		model.addAttribute("company",company);
		model.addAttribute("catalogList", catalogs);
		UserRole userRole = userRoleService.findByUser(user);

		if (userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
			model.addAttribute("userRole2", AppConstants.ROLE_2);
		}
		if (userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
			model.addAttribute("userRole3", AppConstants.ROLE_3);
		}
		return "dashboard/catalog/add";
	}
	
	  @RequestMapping(value = "/catalogList", method = RequestMethod.GET)
	  public String all(Model model,Principal principal) throws IOException{
			model.addAttribute("awsBucketCatalog",AppConstants.awsBucketCatalog);

		  User user = userService.findByUsername(principal.getName());
			  model.addAttribute("user", user);
			  Company company=companyService.findByUser(user);
			  model.addAttribute("company",company);
			 List<Catalog> catalogs = catalogService.findByUser(user); 
			 model.addAttribute("catalogList", catalogs); 
			 UserRole userRole =userRoleService.findByUser(user);
		 	if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
				model.addAttribute("userRole2",AppConstants.ROLE_2);
			}
		 	if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
				model.addAttribute("userRole3",AppConstants.ROLE_3);
			}
			return "dashboard/catalog/all";  
	  }
	  
	  
		@RequestMapping("/updateCatalog")
		public String updateCatalog(@RequestParam("id") Long id, Model model) {
			model.addAttribute("awsBucketCatalog",AppConstants.awsBucketCatalog);
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Catalog catalog = catalogService.findById(id);
			User user = userService.findByUsername(userDetails.getUsername());
			model.addAttribute("user", user);
			Company company=companyService.findByUser(user);
			 model.addAttribute("company",company);
			model.addAttribute("catalog", catalog);
			 UserRole userRole =userRoleService.findByUser(user);
			if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
				model.addAttribute("userRole2",AppConstants.ROLE_2);
			}
		 	if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
				model.addAttribute("userRole3",AppConstants.ROLE_3);
			}
			return "dashboard/catalog/update";
		}

	
		@RequestMapping(value="/remove", method=RequestMethod.POST)
		public String remove(
				@ModelAttribute("id") String id
				) {
			catalogService.remove(Long.parseLong(id.substring(11)));
			return "redirect:/catalog/catalogList";
		}
		
		//Front
		
		@RequestMapping("/info")
		public String info(@RequestParam("id") Long id,Model model) {
			model.addAttribute("awsBucketCompany",AppConstants.awsBucketCompany);
			model.addAttribute("awsBucketCatalog",AppConstants.awsBucketCatalog);
			Catalog catalog = catalogService.findById(id);
			Company company = companyService.findByUser(catalog.getUser());
			model.addAttribute("catalog",catalog);	
			model.addAttribute("company",company);
			return "front/catalog/info";
		}
		
		@RequestMapping("/all")
		public String all(@RequestParam("company_id") Long id, Model model) {
			model.addAttribute("awsBucketCompany",AppConstants.awsBucketCompany);
			model.addAttribute("awsBucketCatalog",AppConstants.awsBucketCatalog);
            Company company = companyService.findById(id);
			List<Catalog> catalogs = catalogService.findByUser(company.getUser());
			model.addAttribute("company", company);
			model.addAttribute("catalogList", catalogs);
			if(!catalogs.isEmpty()) {
				model.addAttribute("catalogListExit",true);
			}
			return "front/catalog/all";
		}

}
