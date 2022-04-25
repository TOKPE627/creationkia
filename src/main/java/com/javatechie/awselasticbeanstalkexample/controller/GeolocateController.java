package com.javatechie.awselasticbeanstalkexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.UnknownHostException;
import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.SpecialityService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;
import com.javatechie.awselasticbeanstalkexample.domain.Speciality;

@Controller
@RequestMapping("/geolocate")
public class GeolocateController {
	@Autowired
	private CompanyService companyService;

	@Autowired
	private SpecialityService specialityService;
	
	@Autowired
	private BookingService bookingService;
	//Front
	@RequestMapping("/info")
	public String companyContact(@ModelAttribute("company_id") Long id, Model model) throws UnknownHostException {
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		List<Booking> bookingsAddedToCart = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
		if(!bookingsAddedToCart.isEmpty()) {
			model.addAttribute("bookingAddedToCartExist",true);
			model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
	   }
		Company company = companyService.findById(id);
		model.addAttribute("company", company);
		List<Speciality> specialities = specialityService.findByUser(company.getUser());
			model.addAttribute("specialityList",specialities);

			List<Speciality> specialityTop4List = specialityService.findTop4ByUser(company.getUser().getId());
			model.addAttribute("specialityTop4List",specialityTop4List);
		return "front/company/geolocate";
	}
}
