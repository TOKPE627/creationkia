package com.javatechie.awselasticbeanstalkexample.controller;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javatechie.awselasticbeanstalkexample.domain.Cart;
import com.javatechie.awselasticbeanstalkexample.service.CartService;
import com.javatechie.awselasticbeanstalkexample.service.CustomerService;
import com.javatechie.awselasticbeanstalkexample.service.OrderCustomerService;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CartService cartService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private OrderCustomerService orderCustomerService;
    
    @RequestMapping("/{orderTrackingNumber}")
    public String add(@PathVariable(value="orderTrackingNumber") String orderTrackingNumber,
            Model model)  throws UnknownHostException
      {           
        List<Cart> carts=cartService.findByIpaddress(AppHosts.currentHostIpAddress());
        model.addAttribute("cartList",carts);
        
        
        return "checkout";       
    }
}
