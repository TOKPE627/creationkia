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

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ProductService productService;
    //Front
    
    @RequestMapping(value = "/details/{product_name}", method = RequestMethod.GET)
    public String addProductToCart(@ModelAttribute("product_name") String name,
            Model model,Principal principal) throws IOException{
            model.addAttribute("bProduct",AppConstants.awsBucketProduct);

            Product p=productService.findByName(name);
            model.addAttribute("p","p");
          return "cart";  
    }
}
