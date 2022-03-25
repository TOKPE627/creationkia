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

import com.javatechie.awselasticbeanstalkexample.domain.Town;
import com.javatechie.awselasticbeanstalkexample.domain.TownAvailable;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.TownAvailableService;
import com.javatechie.awselasticbeanstalkexample.service.TownService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;


@Controller
@RequestMapping("/townAvailable")
public class TownAvailableController {

	@Autowired
	private UserService userService;
    
	@Autowired
    private TownService townService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private TownAvailableService townAvailableService;
	
	@RequestMapping("/add")
    public String add(Model model,Principal principal
    		) {
		List<Town> towns = townService.findAll();
	    User user = userService.findByUsername(principal.getName());
		UserRole userRole =userRoleService.findByUser(user);
	    model.addAttribute("townList",towns);
		model.addAttribute("user",user);
	 	if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
			model.addAttribute("userRole2",AppConstants.ROLE_2);
		}
		return "dashboard/townAvailable/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(	
			@RequestParam("town") String town,
			 Model model,Principal principal
		)
	{	
	    User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		UserRole userRole =userRoleService.findByUser(user);

		TownAvailable townAvailable = new TownAvailable();
		townAvailable.setTown(town);
		townAvailable.setUser(user);
		townAvailableService.save(townAvailable);
		if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
			model.addAttribute("userRole2",AppConstants.ROLE_2);
		}		
		return "redirect:/townAvailable/all";
	}
	
	@RequestMapping(value = "/all")
	public String all(	
			 Model model,Principal principal
		)
	{	
	    User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		UserRole userRole =userRoleService.findByUser(user);
        List<TownAvailable> townAvailables = townAvailableService.findByUser(user);
 		model.addAttribute("townAvailableList",townAvailables);
		if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
			model.addAttribute("userRole2",AppConstants.ROLE_2);
		}		
		return "dashboard/townAvailable/all";
	}
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id
			) {
		townAvailableService.remove(Long.parseLong(id.substring(8)));
		return "redirect:/townAvailable/all";
	}
}
