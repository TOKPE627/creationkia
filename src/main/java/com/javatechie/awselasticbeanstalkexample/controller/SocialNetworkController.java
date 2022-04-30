package com.javatechie.awselasticbeanstalkexample.controller;

import java.net.UnknownHostException;
import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/socialNetwork")
public class SocialNetworkController {
    
   
    
    @Autowired
    private BookingService bookingService;

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/company/{id}")
    public String share(@ModelAttribute("id") Long id, Model model) throws UnknownHostException {
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
        List<Booking> bookingsAddedToCart = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
		if(!bookingsAddedToCart.isEmpty()) {
			model.addAttribute("bookingAddedToCartExist",true);
			model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
	   }
	   Company company = companyService.findById(id);
       String urlShare = AppConstants.url+"company/info/"+company.getId();
       model.addAttribute("urlShare",urlShare);
       return "share";
    }

}
