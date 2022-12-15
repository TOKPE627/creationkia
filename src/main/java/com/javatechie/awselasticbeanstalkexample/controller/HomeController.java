package com.javatechie.awselasticbeanstalkexample.controller;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javatechie.awselasticbeanstalkexample.domain.Brand;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Style;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.BrandService;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.RoleService;
import com.javatechie.awselasticbeanstalkexample.service.StyleService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	
	@Autowired
	private UserRoleService userRoleService;
	
	
	
	@Autowired 
	RoleService roleService;
	
	
	
	@RequestMapping("/dashboard")	
	public String dashboard(Model model) throws UnknownHostException {
	
	    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*check username*/
	    
		if (userService.findByUsername(userDetails.getUsername())!=null) {
			User currentUser = userService.findByUsername(userDetails.getUsername());
			UserRole userRole =userRoleService.findByUser(currentUser);
			/*
			 * model.addAttribute("user", userDetails.getUsername());
			 */			
			model.addAttribute("user",currentUser);
		    //System.out.println(userRole.getRole().getName());	
			if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
				model.addAttribute("userRole1",AppConstants.ROLE_1);
				return "dashboard/home";
			}
		}
		return "message/badRequest";
	}
	@RequestMapping("/login")
    public String login(Model model){
          return "login";
    }

}