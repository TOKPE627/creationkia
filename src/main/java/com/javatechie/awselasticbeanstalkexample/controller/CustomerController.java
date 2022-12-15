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
import com.javatechie.awselasticbeanstalkexample.domain.Brand;
import com.javatechie.awselasticbeanstalkexample.domain.Cart;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Customer;
import com.javatechie.awselasticbeanstalkexample.domain.OrderCustomer;
import com.javatechie.awselasticbeanstalkexample.domain.OrderItemCustomer;
import com.javatechie.awselasticbeanstalkexample.domain.Style;
import com.javatechie.awselasticbeanstalkexample.domain.Univers;
import com.javatechie.awselasticbeanstalkexample.service.BrandService;
import com.javatechie.awselasticbeanstalkexample.service.CartService;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.CustomerService;
import com.javatechie.awselasticbeanstalkexample.service.OrderCustomerService;
import com.javatechie.awselasticbeanstalkexample.service.OrderItemCustomerService;
import com.javatechie.awselasticbeanstalkexample.service.StyleService;
import com.javatechie.awselasticbeanstalkexample.service.UniversService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppDates;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;
import com.javatechie.awselasticbeanstalkexample.utility.SecurityUtility;

@Controller
@RequestMapping("/customer")
public class CustomerController {
     
    @Autowired
    private CartService cartService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private OrderCustomerService orderCustomerService;
    
    @Autowired
    private OrderItemCustomerService orderItemCustomerService;
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StyleService styleService;
    
    @Autowired
    private BrandService brandService;
    
    @Autowired
    private UniversService universService;
    
    //Front
    @RequestMapping("")
    public String add(Model model)  throws UnknownHostException
      {           
        List<Category> categories = categoryService.findAll();
        List<Style> styles = styleService.findAll();
        List<Brand> brands = brandService.findAll();
        List<Univers> univers = universService.findAll();
        model.addAttribute("categoryList",categories);
        model.addAttribute("styleList",styles);
        model.addAttribute("brandList",brands);
        model.addAttribute("universList",univers);
        
        List<Cart> carts=cartService.findByIpaddress(AppHosts.currentHostIpAddress());
        model.addAttribute("cartList",carts);
        return "customer";       
    }
    
   @PostMapping("/add")
   public String addCustomer(@ModelAttribute("customer")  Customer customer,
                             Model model
           )  throws UnknownHostException
     {           
       List<Category> categories = categoryService.findAll();
       List<Style> styles = styleService.findAll();
       List<Brand> brands = brandService.findAll();
       List<Univers> univers = universService.findAll();
       model.addAttribute("categoryList",categories);
       model.addAttribute("styleList",styles);
       model.addAttribute("brandList",brands);
       model.addAttribute("universList",univers);
       
       List<Cart> carts=cartService.findByIpaddress(AppHosts.currentHostIpAddress());
       double total_amount=0;
       for(Cart c: carts) {
           double total = c.getTotal();
           total_amount = total + total_amount;
       }
       //Add customer
       Customer customerSaved = customerService.add(customer);

       if(Objects.nonNull(customerSaved)) {
           System.out.println("Customer registered");

           //Add OrderCustomer
           OrderCustomer orderCustomer=new OrderCustomer();
           
           orderCustomer.setOrderTrackingNumber(SecurityUtility.generateOrderTrackingNumber());
           orderCustomer.setTotalQuantity(carts.size());
           orderCustomer.setTotalAmount(total_amount);
           orderCustomer.setDateCreated(AppDates.currentDateTime());
           orderCustomer.setCustomer(customerSaved);
           OrderCustomer orderCustomerSaved = orderCustomerService.add(orderCustomer);
           
           if(Objects.nonNull(orderCustomerSaved)) {
               System.out.println("Order Customer registered");
               model.addAttribute("orderCustomer",orderCustomerSaved);

               OrderItemCustomer orderItemCustomer, orderItemCustomerSaved = null;
               for(Cart c2: carts) {
                   //Add OrderItemCustomer
                   orderItemCustomer=new OrderItemCustomer();
                   orderItemCustomer.setProduct(c2.getProduct());
                   orderItemCustomer.setQuantity(c2.getQuantity());
                   orderItemCustomer.setTotal(c2.getTotal());
                   orderItemCustomer.setOrderCustomer(orderCustomerSaved); 
                   orderItemCustomerSaved = orderItemCustomerService.add(orderItemCustomer);
               }
               if(Objects.nonNull(orderItemCustomerSaved)) {
                   System.out.println("Order Item Customer registered");

                   //Delete carts
                   for(Cart c3:carts) {
                       cartService.delete(c3.getId());
                   }
                   System.out.println("ALL CARTS OF CUSTOMER DELETED");
               }
           }
       }
       List<Cart> cartsUpdated=cartService.findByIpaddress(AppHosts.currentHostIpAddress());
       model.addAttribute("cartList",cartsUpdated);
       return "phoneNumber";       
   }
   
   @GetMapping("/checkPhoneNumber/{orderTrackingNumber}/{phoneNumber}")
   public ResponseEntity<?> checkPhoneNumber(@PathVariable(value="orderTrackingNumber") String orderTrackingNumber,
                                   @PathVariable(value="phoneNumber") String phoneNumber) throws UnknownHostException {
      OrderCustomer orderCustomer = orderCustomerService.findByOrderTrackingNumber(orderTrackingNumber);
      System.out.println("Order Customer Phone Number: " + orderTrackingNumber);
      AjaxResponseBody result = new AjaxResponseBody();
      if(Objects.nonNull(orderCustomer)) {
          Customer customer = customerService.findById(orderCustomer.getCustomer().getId());
          customer.setPhoneNumber(phoneNumber);
          customerService.update(customer);
      }
      result.setMsg(orderTrackingNumber);
      return ResponseEntity.ok(result);
   }
   
 
}
