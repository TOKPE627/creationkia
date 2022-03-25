package com.javatechie.awselasticbeanstalkexample.controller;

import java.net.UnknownHostException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.Delivery;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.WorkingHour;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.DeliveryService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.service.WorkingHourService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;

@Controller
@RequestMapping("/order")
public class OrderController {

	  
	  @Autowired
	  private UserService userService;
	  
	  @Autowired
	  private UserRoleService userRoleService;
	  
	  @Autowired
	  private BookingService bookingService;
	  
	  @Autowired
	  private DeliveryService deliveryService;
	  
	  @Autowired
	  private ProductService productService;

	  @Autowired
	  private CompanyService companyService;
	  
	  @Autowired
	  private WorkingHourService workingHourService;
	  
	  @RequestMapping("/all")
	  public String all(Model model,Principal principal) {
		  model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
		    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
			model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		    model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
	       User user =  userService.findByUsername(principal.getName());
			  model.addAttribute("user",user);
		   UserRole userRole =userRoleService.findByUser(user);

			 
			/*
			 * List<Product> products = productService.findByUser(user);
			 */		      
	         //todo
	          List<Booking> pendingBookings = bookingService.findBySeller(user,AppConstants.ORDER_STATUS_1);
		      model.addAttribute("pendingBookingList",pendingBookings);

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
		  return "dashboard/booking/all";
	  }
	  @RequestMapping("/historical")
	  public String historical(Model model,Principal principal) throws UnknownHostException {
		    model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
		    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
			model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		    model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);    
		    
		    
		    User user =  userService.findByUsername(principal.getName());
			  
		    model.addAttribute("user",user);
				  UserRole userRole =userRoleService.findByUser(user);
		       if(userRole.getRole().getName().equals(AppConstants.ROLE_4)) {
				 model.addAttribute("userRole4",AppConstants.ROLE_4);
				 List<Booking> bookingsAddedToCart = 
				 bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_ADDED_TO_CART);

			     if(!bookingsAddedToCart.isEmpty()) {
			       model.addAttribute("bookingAddedToCartExist",true);
			       model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
				 }
		    	 List<Booking> ordersHistorical = bookingService.findHistoryByCustomer(user,AppConstants.ORDER_STATUS_1,AppConstants.ORDER_STATUS_2);//user=customer
		    	 model.addAttribute("bookingList",ordersHistorical);
				  return "dashboard/customer/historical";
		      }
		       List<Booking> bookingsHistorical = bookingService.findHistoryBySeller(user, AppConstants.ORDER_STATUS_1, AppConstants.ORDER_STATUS_2);//user=seller
			   model.addAttribute("bookingList",bookingsHistorical);
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
		  return "dashboard/booking/historical";
	  }
	  
	  @RequestMapping("/pendingByCustomer")
	  public String pendingByCustomer(
			  @RequestParam("customer_id") Long customer_id,
			  Model model,Principal principal) {
      		  model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
		      model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
			  model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
		      model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		    
		    User user =  userService.findByUsername(principal.getName());
			  model.addAttribute("user",user);
		   UserRole userRole =userRoleService.findByUser(user);

		   User customer = userService.findById(customer_id);
		   //Delivery delivery = deliveryService.findByCustomerAndPaymentStatus(user,customer,"not_payed");
		   //model.addAttribute("delivery",delivery);
		   model.addAttribute("customer",customer);
		    List<Booking> pendingBookings = bookingService.findBySellerAndCustomerAndStatus(user, customer,AppConstants.ORDER_STATUS_1);
		   
         	model.addAttribute("pendingBookingList",pendingBookings); 

		   double total_price_orders=0;
	    	for(Booking booking: pendingBookings) {
	    		double total_price = booking.getTotal_price();
		    	total_price_orders = total_price + total_price_orders;
	    	}
  			model.addAttribute("total_price_orders",total_price_orders);
            //todo
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
		  return "dashboard/booking/bycustomertodeliver";
	  }
	  
	  @RequestMapping("/validate")
	  public String validate(Model model,Principal principal) {
	       User customer =  userService.findByUsername(principal.getName());
			  model.addAttribute("user",customer);
		   UserRole userRole =userRoleService.findByUser(customer);
		   if(userRole.getRole().getName().equals(AppConstants.ROLE_4)) {
				  model.addAttribute("userRole4",AppConstants.ROLE_4);
				  
				  List<Booking> pendingBookings = bookingService.findByCustomer(customer,AppConstants.ORDER_STATUS_0);
				  for(Booking booking: pendingBookings) {
			    	  booking.setStatus(AppConstants.ORDER_STATUS_1);
			    	  bookingService.update(booking);
			      }
				  //Delivery delivery = new Delivery();
				  //delivery.setCustomer(customer);
//				  List<Booking> bookings = bookingService.findByCustomer(customer,AppConstants.ORDER_STATUS_1);
//				  List<Booking> pendingBySellerBookings =new ArrayList<Booking>();
//				  User seller = null;
//			      for(Booking booking: bookings) {
//			          seller = booking.getProduct().getUser();
//			   	      pendingBySellerBookings = bookingService.findBySellerAndCustomerAndStatus(seller, customer,AppConstants.ORDER_STATUS_1);
//			          double total_price_orders=0;	
//				      for(Booking bookingBySeller: pendingBySellerBookings) {
//				     	double total_price = bookingBySeller.getTotal_price();
//					   	total_price_orders = total_price + total_price_orders;
//				      }
//				      delivery.setSeller(seller);
//				      
//					  delivery.setFinal_price(total_price_orders);
//					  deliveryService.save(delivery);
//			      
//			      }
			 
				  model.addAttribute("pendingBookingList",pendingBookings);
				  
			  }	 
			return "redirect:/order/historical";

	  }
}
