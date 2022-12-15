package com.javatechie.awselasticbeanstalkexample.controller;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javatechie.awselasticbeanstalkexample.domain.Brand;
import com.javatechie.awselasticbeanstalkexample.domain.Cart;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Style;
import com.javatechie.awselasticbeanstalkexample.domain.Univers;
import com.javatechie.awselasticbeanstalkexample.service.BrandService;
import com.javatechie.awselasticbeanstalkexample.service.CartService;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.CustomerService;
import com.javatechie.awselasticbeanstalkexample.service.OrderCustomerService;
import com.javatechie.awselasticbeanstalkexample.service.StyleService;
import com.javatechie.awselasticbeanstalkexample.service.UniversService;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CartService cartService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private OrderCustomerService orderCustomerService;
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StyleService styleService;
    
    @Autowired
    private BrandService brandService;
    
    @Autowired
    private UniversService universService;
    
    @RequestMapping("/{orderTrackingNumber}")
    public String add(@PathVariable(value="orderTrackingNumber") String orderTrackingNumber,
            Model model)  throws UnknownHostException
      {     
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
        
    
        
        
        return "checkout";       
    }
}
