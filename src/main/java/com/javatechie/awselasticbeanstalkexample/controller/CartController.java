package com.javatechie.awselasticbeanstalkexample.controller;

import java.net.UnknownHostException;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.Town;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.Role;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.TownService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;
import com.javatechie.awselasticbeanstalkexample.utility.FileUploadUtil;
import com.javatechie.awselasticbeanstalkexample.utility.SecurityUtility;

@Controller
@RequestMapping("/cart")
public class CartController {
   
   
    
    @Autowired
    private BookingService bookingService;
    
   @Autowired
   private ProductService productService;
   
   @Autowired
   private TownService townService;
   
	@RequestMapping("/all")
	public String cart(Model model) throws UnknownHostException {
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
		model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);

	     List<Booking> bookingsAddedToCart = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);
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
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
		model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
	    
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
