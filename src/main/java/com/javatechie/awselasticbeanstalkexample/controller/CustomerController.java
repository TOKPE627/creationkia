package com.javatechie.awselasticbeanstalkexample.controller;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Objects;

import com.javatechie.awselasticbeanstalkexample.domain.AjaxResponseBody;
import com.javatechie.awselasticbeanstalkexample.domain.Cart;
import com.javatechie.awselasticbeanstalkexample.domain.Customer;
import com.javatechie.awselasticbeanstalkexample.domain.OrderCustomer;
import com.javatechie.awselasticbeanstalkexample.service.CartService;
import com.javatechie.awselasticbeanstalkexample.service.CustomerService;
import com.javatechie.awselasticbeanstalkexample.service.OrderCustomerService;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;

@Controller
@RequestMapping("/customer")
public class CustomerController {
     
    @Autowired
    private CartService cartService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private OrderCustomerService orderCustomerService;
    
    //Front
    @RequestMapping("/add")
    public String add(Model model)  throws UnknownHostException
      {           
        List<Cart> carts=cartService.findByIpaddress(AppHosts.currentHostIpAddress());
        model.addAttribute("cartList",carts);
        return "customer";       
    }
    
   @PostMapping("/add")
   public String addCustomer(@ModelAttribute("customer")  Customer customer,
                             Model model
           )  throws UnknownHostException
     {           
       List<Cart> carts=cartService.findByIpaddress(AppHosts.currentHostIpAddress());
       model.addAttribute("cartList",carts);
       customerService.add(customer);
       return "phoneNumber";       
   }
   
   @GetMapping("/checkPhoneNumber/{orderTrackingNumber}/{phoneNumber}")
   public ResponseEntity<?> checkPhoneNumber(@PathVariable(value="orderTrackingNumber") String orderTrackingNumber,
                                   @PathVariable(value="phoneNumber") String phoneNumber) throws UnknownHostException {
      OrderCustomer orderCustomer = orderCustomerService.findByOrderTrackingNumber(orderTrackingNumber);
      AjaxResponseBody result = new AjaxResponseBody();
      if(Objects.nonNull(orderCustomer)) {
          Customer customer = customerService.findById(orderCustomer.getCustomer().getId());
          customer.setPhoneNumber(phoneNumber);
          customerService.update(customer);
      }
      result.setOrderCustomer(orderCustomer);
      return ResponseEntity.ok(result);
   }
   
 
}
