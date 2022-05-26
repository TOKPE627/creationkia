package com.javatechie.awselasticbeanstalkexample.controller;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.ContactAtooly;
import com.javatechie.awselasticbeanstalkexample.domain.PartnerAtooly;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.Town;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.ContactAtoolyService;
import com.javatechie.awselasticbeanstalkexample.service.PartnerAtoolyService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.TownService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;

@Controller
@RequestMapping("/cart")
public class CartController {
   
   
    
    @Autowired
    private BookingService bookingService;
    
   @Autowired
   private ProductService productService;
   
   @Autowired
   private TownService townService;

   @Autowired
   private ContactAtoolyService contactAtoolyService;

   @Autowired
   private PartnerAtoolyService partnerAtoolyService;
   
	@RequestMapping("/all")
	public String cart(Model model) throws UnknownHostException {
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
		model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
        model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

	     List<Booking> bookingsAddedToCart = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
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
	
		 if(!bookingsAddedToCart.isEmpty()) {
	    	 model.addAttribute("bookingAddedToCartExist",true);
	    	 model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
	 		return "front/cart/all";

		}
		return "redirect:/";
	}
	
	@RequestMapping("/productInfo")
	public String productInfo(
			@RequestParam("id") Long id,Model model
	 ) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
        model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);
	 
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
   
	    List<Booking> bookingsAddedToCart = 
	    		bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
	    List<Town> towns = townService.findAll();

	    if(!bookingsAddedToCart.isEmpty()) {
	    	 model.addAttribute("bookingAddedToCartExist",true);
	    	 model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
		}		
		Product product=productService.findById(id);
		model.addAttribute("product", product);
		model.addAttribute("townList", towns);

		return "booking/add";
	}
	
	@RequestMapping("/remove")
	public String remove(
			 @RequestParam("id") Long id
			) {
		bookingService.remove(id);
		return "redirect:/cart/all";
	}

}
