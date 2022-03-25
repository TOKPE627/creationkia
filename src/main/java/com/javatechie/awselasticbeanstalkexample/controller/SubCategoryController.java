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

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.SubCategory;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.SubCategoryService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
@RequestMapping("/subCategory")
public class SubCategoryController {
	@Autowired
	private UserService userService;
	@Autowired 
	private SubCategoryService subCategoryService;
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/add")
    public String add(@RequestParam("idCategory") Long idCategory,Model model, Principal principal) {
		  User user = userService.findByUsername(principal.getName());
		  Category category = categoryService.findById(idCategory);
		  model.addAttribute("user", user.getUsername());
		  model.addAttribute("userRole1",AppConstants.ROLE_1);
		  model.addAttribute("category",category);
		  return "dashboard/admin/subcategory/add";
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("sub_category") SubCategory subCategory,
			@RequestParam("idCategory") Long idCategory,
			 Model model
	)
	{		
		Category category = categoryService.findById(idCategory);
	    subCategory.setCategory(category);
	    //subCategory.setTitle("supermarket");
		subCategoryService.save(subCategory);		
		return "redirect:/subCategory/subCategoryList?idCategory="+category.getId();
	}
	
	  @RequestMapping("/subCategoryList")
	  public String all(@RequestParam("idCategory") Long idCategory,
                  Model model,Principal principal){
			  User user = userService.findByUsername(principal.getName());
				Category category = categoryService.findById(idCategory);

			  model.addAttribute("user", user.getUsername());
			 model.addAttribute("userRole1",AppConstants.ROLE_1);
			 List<SubCategory> subCategories = subCategoryService.findByCategory(category); 
			 model.addAttribute("subCategoryList",subCategories); 
			 return "dashboard/admin/subcategory/all";  
	  }
	  
	  @RequestMapping("/subCategoryInfo")
		public String info(@RequestParam("id") Long id, Model model,Principal principal) {
       	    User user = userService.findByUsername(principal.getName());
	     
			SubCategory subCategory = subCategoryService.findById(id);				
	     
			   model.addAttribute("user", user.getUsername());
			    model.addAttribute("userRole1",AppConstants.ROLE_1);
			    model.addAttribute("subCategory", subCategory);
			return "dashboard/admin/subcategory/info";
		}
	  
		@RequestMapping(value="/remove", method=RequestMethod.POST)
		public String remove(
				@ModelAttribute("id") String id, Model model
				) {
			//SubCategory subCategory = subCategoryService.findById(Long.parseLong(id));
			//Category category = subCategory.getCategory();
			//System.out.println(category.getName());
			subCategoryService.remove(Long.parseLong(id.substring(15)));
			//return "redirect:/subCategory/subCategoryList?idCategory="+category.getId();
			return "redirect:/category/categoryList";
		}
	
}
