package com.javatechie.awselasticbeanstalkexample.controller;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private UserService userService;
	 
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/add")
    public String add(Model model, Principal principal) {
		  User user = userService.findByUsername(principal.getName());
		  model.addAttribute("user", user.getUsername());
		  model.addAttribute("userRole1",AppConstants.ROLE_1);
			return "dashboard/admin/category/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("category") Category category,
			 Model model,
		     Principal principal
		) throws IOException
	{		
		User user = userService.findByUsername(principal.getName());
	    category.setUser(user);
		categoryService.save(category);		
		return "redirect:/category/categoryList";
	}
	
	  @RequestMapping(value = "/categoryList", method = RequestMethod.GET)
	  public String all(Model model,Principal principal) throws IOException{
			  User user = userService.findByUsername(principal.getName());
			  model.addAttribute("user", user.getUsername());
			 model.addAttribute("userRole1",AppConstants.ROLE_1);
			 List<Category> categories = categoryService.findByUser(user); 
			 model.addAttribute("categoryList",categories); 
			 return "dashboard/admin/category/all";  
	  }
		
	  
	  @RequestMapping("/categoryInfo")
		public String info(@RequestParam("id") Long id, Model model,Principal principal) {
       	    User user = userService.findByUsername(principal.getName());
	     
			Category category = categoryService.findById(id);				
	     
			   model.addAttribute("user", user.getUsername());
			    model.addAttribute("userRole1",AppConstants.ROLE_1);
			    model.addAttribute("category", category);
			return "dashboard/admin/category/info";
		}
		
	  @RequestMapping("/update")
		public String update(@RequestParam("id") Long id, Model model,Principal principal) {
       	    User user = userService.findByUsername(principal.getName());
	     
			Category category = categoryService.findById(id);				
	     
			   model.addAttribute("user", user.getUsername());
			    model.addAttribute("userRole2",AppConstants.ROLE_1);
			    model.addAttribute("category", category);
			return "dashboard/admin/category/update";
		}
		@RequestMapping(value="/update", method=RequestMethod.POST)		
		public String update(@ModelAttribute("category") Category category,
				 Model model,
		         Principal principal			) 
		{
			
			User user = userService.findByUsername(principal.getName());
			category.setUser(user);
			categoryService.update(category);
			
			return "redirect:/category/categoryInfo?id="+category.getId();		
	     }
		@RequestMapping(value="/remove", method=RequestMethod.POST)
		public String remove(
				@ModelAttribute("id") String id, Model model
				) {
			categoryService.remove(Long.parseLong(id.substring(12)));
			return "redirect:/category/categoryList";
		}
	
  }
