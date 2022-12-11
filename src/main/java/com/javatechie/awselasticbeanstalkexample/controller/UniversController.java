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
import com.javatechie.awselasticbeanstalkexample.domain.Univers;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.service.RoleService;
import com.javatechie.awselasticbeanstalkexample.service.UniversService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
@RequestMapping("/univers")
public class UniversController {
    @Autowired
    private UserService userService;
    
    
    @Autowired
    private UniversService universService;
    
    @Autowired 
    RoleService roleService;
        
    @RequestMapping("/add")
    public String add(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
       
        model.addAttribute("user", user);
       
        model.addAttribute("userRole1",AppConstants.ROLE_1);
     
        return "dashboard/univers/add";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("univers")  Univers univers,  
             Model model,Principal principal
        )throws IOException
    {
        
        User user = userService.findByUsername(principal.getName());
       
        universService.add(univers);

        model.addAttribute("user", user);        
        model.addAttribute("userRole1",AppConstants.ROLE_1);
        return "redirect:/univers/all";     
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all(Model model,Principal principal) throws IOException{

           User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);        
           model.addAttribute("userRole1",AppConstants.ROLE_1);
           List<Univers> l = universService.findAll(); 
           model.addAttribute("universList", l); 
          return "dashboard/univers/all";  
    }
}
