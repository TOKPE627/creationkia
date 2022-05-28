package com.javatechie.awselasticbeanstalkexample.controller;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;

import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.ContactAtooly;
import com.javatechie.awselasticbeanstalkexample.domain.PartnerAtooly;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.ContactAtoolyService;
import com.javatechie.awselasticbeanstalkexample.service.PartnerAtoolyService;
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

    @Autowired
	private ContactAtoolyService contactAtoolyService;

	@Autowired
	private PartnerAtoolyService partnerAtoolyService;
    @RequestMapping("/company/{id}")
    public String share(@ModelAttribute("id") Long id, Model model) throws UnknownHostException {
        model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

		List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	    model.addAttribute("bookingBegunList",bookingsBegun);
	    List<Booking> bookingsAddedToCart = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
		if(!bookingsAddedToCart.isEmpty()) {
			model.addAttribute("bookingAddedToCartExist",true);
			model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
	   }
       ContactAtooly contactAtooly         = contactAtoolyService.findByName(AppConstants.APP_NAME);
       List<PartnerAtooly> partnerAtoolies = partnerAtoolyService.findAllPartners();
       if(Objects.nonNull(contactAtooly)){
          model.addAttribute("contactExists",true);
          model.addAttribute("contact",contactAtooly); 
      }
      if(!partnerAtoolies.isEmpty()){
          model.addAttribute("partnerExist",true);
          model.addAttribute("partnerList",partnerAtoolies);
      }
	   Company company = companyService.findById(id);
       String urlShare = AppConstants.url+"company/info/"+company.getId();
       model.addAttribute("urlShare",urlShare);
       return "share";
    }

}
