package com.javatechie.awselasticbeanstalkexample.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
//	
//	@Autowired
//	private ProductService productService;
//	@Autowired
//    private StorageService storageService;
	
		

	//@RequestMapping("/add")
    //public String add(Model model, Principal principal) {
      //    User user = userService.findByUsername(principal.getName());
        //  model.addAttribute("user", user);
          //UserRole userRole =userRoleService.findByUser(user);
//          Company company = companyService.findByUser(user);
//          model.addAttribute("company",company);
//            List<Day> days = dayService.findAll();
//            model.addAttribute("dayList",days);
//            Category category = categoryService.findByTitle(AppConstants.CATEGORY_SHOP);//Shop
//            List<SubCategory> subCategories = subCategoryService.findByCategory(category);
//            model.addAttribute("subCategoryList",subCategories);
//            model.addAttribute("category",category);
//            
//          if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
//                model.addAttribute("userRole1",AppConstants.ROLE_1);
//                List<TownAvailable> townAvailables = townAvailableService.findByUser(user);
//                if(townAvailables.isEmpty()){
//                    return "redirect:/townAvailable/add";
//                }
//          }
//          
//          return "dashboard/product/shop/add";

   // }
		
	
		
		

}
