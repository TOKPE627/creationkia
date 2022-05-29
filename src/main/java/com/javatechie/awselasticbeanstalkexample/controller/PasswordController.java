package com.javatechie.awselasticbeanstalkexample.controller;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;
import com.javatechie.awselasticbeanstalkexample.utility.SecurityUtility;

@Controller
@RequestMapping("/password")
public class PasswordController {

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private BookingService bookingService;
	
    @Autowired
    private UserService userService;
	
	@GetMapping("/forgot")
	public String index(Model model) throws UnknownHostException {

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
	  return "front/password/forgot";
	}	
	
    @RequestMapping(value="/checkEmail", method = RequestMethod.POST)
	public String checkEmail(
    	 @ModelAttribute("email")     String userEmail,    
         Model model) throws UnknownHostException{
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
            User user = userService.findByEmail(userEmail);
            if(Objects.nonNull(user)){
                model.addAttribute("user", user);
                return "front/password/createNewPassword";
            }
            else{
                model.addAttribute("userExists", false);
            }
            return "front/password/forgot";
    }

    @RequestMapping(value="/createNewPassword", method = RequestMethod.POST)
	public String createNewPassword(
    	 @ModelAttribute("email")     String userEmail,
         @ModelAttribute("username") String userName,
         @ModelAttribute("password") String newPassword,    
         Model model) throws UnknownHostException{
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
            User user = userService.findByEmail(userEmail);
            if(Objects.nonNull(user)){
                
                String encryptedPassword = SecurityUtility.passwordEncoder().encode(newPassword);
                user.setPassword(encryptedPassword);
                userService.save(user);
              
            }
            else{
                return  "message/technicalIssue";
            }
            
            return "message/successNewPassword";
    }
}
