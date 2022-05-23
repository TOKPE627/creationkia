package com.javatechie.awselasticbeanstalkexample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.IOException;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.ContactAtooly;
import com.javatechie.awselasticbeanstalkexample.domain.Day;
import com.javatechie.awselasticbeanstalkexample.domain.PartnerAtooly;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.ProductGalery;
import com.javatechie.awselasticbeanstalkexample.domain.Speciality;
import com.javatechie.awselasticbeanstalkexample.domain.SubCategory;
import com.javatechie.awselasticbeanstalkexample.domain.Town;
import com.javatechie.awselasticbeanstalkexample.domain.TownAvailable;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.ContactAtoolyService;
import com.javatechie.awselasticbeanstalkexample.service.DayService;
import com.javatechie.awselasticbeanstalkexample.service.PartnerAtoolyService;
import com.javatechie.awselasticbeanstalkexample.service.ProductGaleryService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.SpecialityService;
import com.javatechie.awselasticbeanstalkexample.service.StorageService;
import com.javatechie.awselasticbeanstalkexample.service.SubCategoryService;
import com.javatechie.awselasticbeanstalkexample.service.TownAvailableService;
import com.javatechie.awselasticbeanstalkexample.service.TownService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppDates;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;
import com.javatechie.awselasticbeanstalkexample.utility.FileUploadUtil;


@Controller
@RequestMapping("/partner")
public class PartnerAtoolyController {
    @Autowired
	private CompanyService companyService;
	@Autowired
	private SpecialityService specialityService;
	
	@Autowired
	private UserService userService;

	@Autowired
    PartnerAtoolyService partnerAtoolyService;
	
	@RequestMapping("/add")
    public String add(Model model, Principal principal) {
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
		model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
		model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);    
		     
		
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		Company company = companyService.findByUser(user);
		model.addAttribute("company",company);
		
		model.addAttribute("userRole1",AppConstants.ROLE_1);
        
		return "dashboard/partnerAtooly/add";
	}
	
	@RequestMapping("/partnerList")
    public String partnerList(Model model, Principal principal) {
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
		model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
		model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);    
        model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);    

		
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		Company company = companyService.findByUser(user);
		model.addAttribute("company",company);
		
		model.addAttribute("userRole1",AppConstants.ROLE_1);
        
        List<PartnerAtooly> partnerAtoolies = partnerAtoolyService.findAllPartners();
        if(!partnerAtoolies.isEmpty()){
          model.addAttribute("partnersExist", true);
		  model.addAttribute("partnerList", partnerAtoolies);
		}else{
            model.addAttribute("partnersExist", false);
        }
        return "dashboard/partnerAtooly/all";
	}
	
    @RequestMapping(value="/remove")
	public String removeByOwner(
			@RequestParam("id") Long id)  {	   
		    partnerAtoolyService.remove(id);
        
		  return "redirect:/partner/partnerList";
	}
}
