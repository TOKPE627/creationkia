package com.javatechie.awselasticbeanstalkexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
@RequestMapping("/geolocate")
public class GeolocateController {
	@Autowired
	private CompanyService companyService;
	//Front
	@RequestMapping("/info")
	public String companyContact(@ModelAttribute("company_id") Long id, Model model) {
		model.addAttribute("awsBucketCompany",AppConstants.awsBucketCompany);
		Company company = companyService.findById(id);
		model.addAttribute("company", company);
		return "front/company/geolocate";
	}
}
