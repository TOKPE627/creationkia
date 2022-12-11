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
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.service.BrandService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private UserService userService;
    
    
    
    @Autowired
    private BrandService brandService;
    
        
    @RequestMapping("/add")
    public String add(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
       
        model.addAttribute("user", user);
       
        model.addAttribute("userRole1",AppConstants.ROLE_1);
     
        return "dashboard/brand/add";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("brand")  Brand brand,  
             Model model,Principal principal
        )throws IOException
    {
        
        User user = userService.findByUsername(principal.getName());
       
        brandService.add(brand);

        model.addAttribute("user", user);        
        model.addAttribute("userRole1",AppConstants.ROLE_1);
        return "redirect:/brand/all";     
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all(Model model,Principal principal) throws IOException{

           User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);        
           model.addAttribute("userRole1",AppConstants.ROLE_1);
           List<Brand> l = brandService.findAll(); 
           model.addAttribute("brandList", l); 
          return "dashboard/brand/all";  
    }
}
