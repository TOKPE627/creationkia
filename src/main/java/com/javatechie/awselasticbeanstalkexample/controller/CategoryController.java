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

import com.javatechie.awselasticbeanstalkexample.domain.Brand;
import com.javatechie.awselasticbeanstalkexample.domain.Cart;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.Style;
import com.javatechie.awselasticbeanstalkexample.domain.Univers;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.service.BrandService;
import com.javatechie.awselasticbeanstalkexample.service.CartService;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.StyleService;
import com.javatechie.awselasticbeanstalkexample.service.UniversService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired 
    private UserService userService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CartService cartService;
    

    @Autowired
    private StyleService styleService;
    
    @Autowired
    private BrandService brandService;
    
    @Autowired
    private UniversService universService;
    
    
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
              List<Cart> carts=cartService.findByIpaddress(AppHosts.currentHostIpAddress());
              model.addAttribute("cartList",carts);
              
              List<Category> categories = categoryService.findAll();
              List<Style> styles = styleService.findAll();
              List<Brand> brands = brandService.findAll();
              List<Univers> univers = universService.findAll();
              model.addAttribute("categoryList",categories);
              model.addAttribute("styleList",styles);
              model.addAttribute("brandList",brands);
              model.addAttribute("universList",univers);
             
             Category c=categoryService.findByName(name);
             List<Product> products=productService.findByCategory(c);
             model.addAttribute("productList", products); 
            return "categoryProducts";  
      }
       

}
