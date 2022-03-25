package com.javatechie.awselasticbeanstalkexample.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.Delivery;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.WorkingHour;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.DeliveryService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.service.WorkingHourService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {

	  
	  @Autowired
	  private UserService userService;
	  
	  @Autowired
	  private UserRoleService userRoleService;
	  
	  @Autowired
	  private BookingService bookingService;
	  
	  @Autowired
	  private DeliveryService deliveryService;

	 @Autowired
	 private CompanyService companyService;
	  
	 @Autowired
	 private WorkingHourService workingHourService;
	
		/*
		 * @RequestMapping(value = "/update", method = RequestMethod.POST) public String
		 * update(
		 * 
		 * @RequestParam("delivery_id") Long delivery_id,
		 * 
		 * @RequestParam("delivery_price") double deliveryPrice, Model model,Principal
		 * principal )throws IOException { Delivery delivery =
		 * deliveryService.findById(delivery_id); User user =
		 * userService.findByUsername(principal.getName());
		 * model.addAttribute("user",user);
		 * model.addAttribute("userRole1",AppConstants.ROLE_1);
		 * 
		 * delivery.setSeller(user); delivery.setDelivery_price(deliveryPrice);
		 * delivery.setFinal_price(delivery.getFinal_price()+delivery.getDelivery_price(
		 * )); deliveryService.update(delivery); return "redirect:/order/all"; }
		 */
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String updateStaus(	
			@RequestParam("customer_id") Long customer_id,
			 Model model,Principal principal
			)throws IOException
	{
	    User user =  userService.findByUsername(principal.getName());
		  UserRole userRole =userRoleService.findByUser(user);
			User customer = userService.findById(customer_id);

	    model.addAttribute("user",user);


	   // User customer = userService.findById(delivery.getCustomer().getId());
		
	    List<Booking> pendingBookings = bookingService.findBySellerAndCustomerAndStatus(user,customer,AppConstants.ORDER_STATUS_1);     
        for(Booking booking: pendingBookings) {
        	booking.setStatus(AppConstants.ORDER_STATUS_2);
    		bookingService.update(booking);
        }
        
        double total_price_orders=0;
    	for(Booking booking: pendingBookings) {
    		double total_price = booking.getTotal_price();
	    	total_price_orders = total_price + total_price_orders;
    	}
		
        
        Delivery delivery = new Delivery();
        delivery.setCustomer(customer);
        delivery.setSeller(user);
        delivery.setFinal_price(total_price_orders);
        delivery.setPayment_status("payed");
        deliveryService.save(delivery);
        
		  if(userRole.getRole().getName().equals(AppConstants.ROLE_1)) {
			  model.addAttribute("userRole1",AppConstants.ROLE_1);
		  }		
		  if(userRole.getRole().getName().equals(AppConstants.ROLE_2) || (userRole.getRole().getName().equals(AppConstants.ROLE_3))) {
			  Company company = companyService.findByUser(user);
              model.addAttribute("company",company);
			  if(userRole.getRole().getName().equals(AppConstants.ROLE_2)) {
				  model.addAttribute("userRole2",AppConstants.ROLE_2);
				   List<WorkingHour> workingHours = workingHourService.findByUser(user);
  		    	   model.addAttribute("workingHourList",workingHours);
			  }
			  
			  if(userRole.getRole().getName().equals(AppConstants.ROLE_3)) {
				  model.addAttribute("userRole3",AppConstants.ROLE_3);
			  } 
			}       
		return "redirect:/order/all";
	}

}
