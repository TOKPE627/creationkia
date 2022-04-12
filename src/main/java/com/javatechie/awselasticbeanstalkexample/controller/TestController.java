package com.javatechie.awselasticbeanstalkexample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/testApi")
public class TestController {
    
	@RequestMapping(value = "/successGoogleAuth", method = RequestMethod.GET)
	public String showInfo(@ModelAttribute("email") String email,
        @ModelAttribute("name") String name,
         Model model) {
        System.out.println("Email"+email);
        System.out.println("Name" +name);
        model.addAttribute("email",email);
        model.addAttribute("name",name);
		return "google/success.html";
	}
}
