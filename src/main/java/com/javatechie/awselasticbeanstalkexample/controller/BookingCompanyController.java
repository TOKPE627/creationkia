package com.javatechie.awselasticbeanstalkexample.controller;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.BookingCompany;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.service.BookingCompanyService;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.RoleService;
import com.javatechie.awselasticbeanstalkexample.service.TownService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
@Controller
@RequestMapping("/bookingCompany")
public class BookingCompanyController {
    @Autowired
    private ProductService productService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private TownService townService;
    
    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private BookingCompanyService bookingCompanyService;
	@Autowired
	private UserRoleService userRoleService;
	
    @Autowired 
	RoleService roleService;

    //Dashboard
	@RequestMapping("/remove")
	public String remove(
			@RequestParam("id") Long id
			) {
		bookingCompanyService.remove(id);
	
	    return "redirect:/dashboard";

	}
    @RequestMapping("/update")
	public String update(
			@RequestParam("id") Long id,Model model
			) {
        model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
        model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
        model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
        model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
        model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
        model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("user",currentUser);

        List<BookingCompany> bookingCompanies = bookingCompanyService.findByUser(currentUser);
        if(!bookingCompanies.isEmpty()){
            model.addAttribute("bookingCompanyListExist", true);
            model.addAttribute("bookingCompanyList", bookingCompanies);
        }else{
            model.addAttribute("bookingCompanyListExist", false);
        }
		BookingCompany bookingCompany = bookingCompanyService.findById(id);
        model.addAttribute("bookingCompany", bookingCompany);
	    return "dashboard/customerCompany/updateBookingCompany";
	}

    @RequestMapping(value="/update", method = RequestMethod.POST)
	public String updateBooking(
				HttpServletRequest request,
				@ModelAttribute("bookingCompany_id") Long bookingCompany_id,
                @ModelAttribute("company_id") Long company_id,
				@ModelAttribute("command") String command,
                @ModelAttribute("day") String day,
                @ModelAttribute("hour") String hour,
				Model model
			) throws Exception{
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.findByUsername(userDetails.getUsername());
       Company company = companyService.findById(company_id);
        BookingCompany bookingCompany=bookingCompanyService.findById(bookingCompany_id);
        if(Objects.nonNull(bookingCompany)){
            bookingCompany.setCompany(company);
            bookingCompany.setUser(currentUser);
            bookingCompany.setCommand(command);
            bookingCompany.setDay(day);
            bookingCompany.setHour(hour);
            bookingCompany.setStatus(AppConstants.ORDER_STATUS_1);
            bookingCompanyService.update(bookingCompany);
        }
	  	return "redirect:/dashboard";
	}

    @RequestMapping("/begun/remove")
	public String removeBegun(
			@RequestParam("id") Long id
			) {
		bookingCompanyService.remove(id);
	
	    return "redirect:/dashboard";

	}
    @RequestMapping("/begun/update")
	public String updateBegun(
			@RequestParam("id") Long id,Model model
			) throws UnknownHostException {
        model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
        model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
        model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
        model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
        model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
        model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("user",currentUser);

        List<BookingCompany> bookingsCompanyBegun = bookingCompanyService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(), AppConstants.ORDER_STATUS_0);
        if(!bookingsCompanyBegun.isEmpty()){
            model.addAttribute("bookingCompanyBegunListExist", true);
            model.addAttribute("bookingCompanyBegunList",bookingsCompanyBegun);
        }else{
            model.addAttribute("bookingCompanyBegunListExist", false);
        }
		BookingCompany bookingCompany = bookingCompanyService.findById(id);
        model.addAttribute("bookingCompany", bookingCompany);
	    return "dashboard/customerCompany/updateBookingCompanyBegun";
	}


    @RequestMapping(value="/begun/update", method = RequestMethod.POST)
	public String updateBookingBegun(
				HttpServletRequest request,
				@ModelAttribute("bookingCompany_id") Long bookingCompany_id,
                @ModelAttribute("company_id") Long company_id,
				@ModelAttribute("command") String command,
                @ModelAttribute("day") String day,
                @ModelAttribute("hour") String hour,
				Model model
			) throws Exception{
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.findByUsername(userDetails.getUsername());
       Company company = companyService.findById(company_id);
        BookingCompany bookingCompany=bookingCompanyService.findById(bookingCompany_id);
        if(Objects.nonNull(bookingCompany)){
            bookingCompany.setCompany(company);
            bookingCompany.setUser(currentUser);
            bookingCompany.setCommand(command);
            bookingCompany.setDay(day);
            bookingCompany.setHour(hour);
            bookingCompany.setStatus(AppConstants.ORDER_STATUS_1);
            bookingCompanyService.update(bookingCompany);
        }
	  	return "redirect:/dashboard";
	}


    @RequestMapping("/customer/historical")
	  public String historical(Model model,Principal principal) throws UnknownHostException {
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);  
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
                     model.addAttribute("bookingAddedToCartExist",false);
                
                    if(!bookingsAddedToCart.isEmpty()) {
                        System.out.println("Added to cart " +bookingsAddedToCart.size());
                        model.addAttribute("bookingAddedToCartExist",true);
                        model.addAttribute("bookingAddedToCartList",bookingsAddedToCart);
                        return "dashboard/customer/addedToCart";
                    }
                    else{
                        List<Booking> bookingsBegun = bookingService.findByCustomer(user,AppConstants.ORDER_STATUS_0);
                        if(!bookingsBegun.isEmpty()) {
                            System.out.println("Bookings begun " +bookingsBegun.size());
                            double total_price_orders=0;
                            for(Booking booking: bookingsBegun) {
                                double total_price = booking.getTotal_price();
                                total_price_orders = total_price + total_price_orders;
                            }
                            model.addAttribute("bookingBegunListExist",true);
                            model.addAttribute("bookingBegunList",bookingsBegun);
                            model.addAttribute("total_price_orders",total_price_orders);	
                            return "dashboard/customer/home";
                        }
                    }
                 
		  }  
          if(userRole.getRole().getName().equals(AppConstants.ROLE_5)) {
            model.addAttribute("userRole5",AppConstants.ROLE_5);
            List<BookingCompany> bookingsCompanyBegun = bookingCompanyService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(), AppConstants.ORDER_STATUS_0);
            if(!bookingsCompanyBegun.isEmpty()){
                System.out.println("Company begun " +bookingsCompanyBegun.size());
                model.addAttribute("bookingCompanyBegunListExist", true);
                model.addAttribute("bookingCompanyBegunList",bookingsCompanyBegun);
                return "dashboard/customerCompany/homeBookingCompanyBegun";
            }
            else{
                List<BookingCompany> bookingCompanies = bookingCompanyService.findByCustomer(user, AppConstants.ORDER_STATUS_1);
                if(!bookingCompanies.isEmpty()){
                    System.out.println("Company pendings " +bookingCompanies.size());
                    model.addAttribute("bookingCompanyListExist", true);
                    model.addAttribute("bookingCompanyList", bookingCompanies);
                    return "dashboard/customerCompany/homeBookingCompany";
                }
            }
            List<BookingCompany> bookingsServed = 
            bookingCompanyService.findByCustomer(user,AppConstants.ORDER_STATUS_2);

            if(!bookingsServed.isEmpty()) {
                model.addAttribute("bookingsServedExist",true);
                model.addAttribute("bookingsServedList",bookingsServed);
            }
          }
        
		return "dashboard/bookingCompany/historicalCustomer";
	  }
}
