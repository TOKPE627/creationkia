package com.javatechie.awselasticbeanstalkexample.controller;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired 
    private UserService userService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ProductService productService;
    
    @RequestMapping("/add")
    public String add(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
       
        model.addAttribute("user", user);
       
        model.addAttribute("userRole1",AppConstants.ROLE_1);
     
        return "dashboard/category/add";
    }
    
      @RequestMapping(value = "/all", method = RequestMethod.GET)
      public String all(Model model,Principal principal) throws IOException{
            model.addAttribute("awsBucketCategory",AppConstants.awsBucketCategory);

             User user = userService.findByUsername(principal.getName());
              model.addAttribute("user", user);        
             model.addAttribute("userRole1",AppConstants.ROLE_1);
             List<Category> cat = categoryService.findAll(); 
             model.addAttribute("categoryList", cat); 
            return "dashboard/category/all";  
      }
      
      //Frontend
      @RequestMapping(value = "/products/{name}", method = RequestMethod.GET)
      public String categoryProducts(@ModelAttribute("name") String name,
              Model model,Principal principal) throws IOException{
              model.addAttribute("bProduct",AppConstants.awsBucketProduct);

             Category c=categoryService.findByName(name);
             List<Product> products=productService.findByCategory(c);
             model.addAttribute("productList", products); 
            return "categoryProducts";  
      }
       

}
