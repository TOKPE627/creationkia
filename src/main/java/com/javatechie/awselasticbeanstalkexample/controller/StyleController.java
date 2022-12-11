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

import com.javatechie.awselasticbeanstalkexample.domain.Style;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.service.RoleService;
import com.javatechie.awselasticbeanstalkexample.service.StyleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
@RequestMapping("/style")
public class StyleController {
    @Autowired
    private UserService userService;
    
    
    
    @Autowired
    private StyleService styleService;
    
    @Autowired 
    RoleService roleService;
        
    @RequestMapping("/add")
    public String add(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
       
        model.addAttribute("user", user);
       
        model.addAttribute("userRole1",AppConstants.ROLE_1);
     
        return "dashboard/style/add";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("style")  Style style,  
             Model model,Principal principal
        )throws IOException
    {
        
        User user = userService.findByUsername(principal.getName());
       
        styleService.add(style);

        model.addAttribute("user", user);        
        model.addAttribute("userRole1",AppConstants.ROLE_1);
        return "redirect:/style/all";     
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all(Model model,Principal principal) throws IOException{

           User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);        
           model.addAttribute("userRole1",AppConstants.ROLE_1);
           List<Style> l = styleService.findAll(); 
           model.addAttribute("styleList", l); 
          return "dashboard/style/all";  
    }
}
