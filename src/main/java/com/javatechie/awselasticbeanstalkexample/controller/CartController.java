package com.javatechie.awselasticbeanstalkexample.controller;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javatechie.awselasticbeanstalkexample.domain.Brand;
import com.javatechie.awselasticbeanstalkexample.domain.Cart;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.Style;
import com.javatechie.awselasticbeanstalkexample.domain.Univers;
import com.javatechie.awselasticbeanstalkexample.service.BrandService;
import com.javatechie.awselasticbeanstalkexample.service.CartService;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.StyleService;
import com.javatechie.awselasticbeanstalkexample.service.UniversService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CartService cartService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StyleService styleService;
    
    @Autowired
    private BrandService brandService;
    
    @Autowired
    private UniversService universService;
    //Frontend
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String cart(Model model) throws UnknownHostException{
        model.addAttribute("bProduct",AppConstants.awsBucketProduct);
        List<Cart> carts=cartService.findByIpaddress(AppHosts.currentHostIpAddress());
        model.addAttribute("cartList",carts);
        List<Category> categories = categoryService.findAll();
        List<Style> styles = styleService.findAll();
        List<Brand> brands = brandService.findAll();
        List<Univers> univers = universService.findAll();
        model.addAttribute("categoryList",categories);
        model.addAttribute("styleList",styles);
        model.addAttribute("brandList",brands);
        model.addAttribute("universList",univers);
    
         if(!carts.isEmpty()) {
             double total_amount=0;
             for(Cart c: carts) {
                 double total = c.getTotal();
                 total_amount = total + total_amount;
             }
             model.addAttribute("total_amount",total_amount);
         }
        
         return "cart";  
    }
    
    
    @RequestMapping(value = "/add-to-cart/{product_name}", method = RequestMethod.GET)
    public String addProductToCart(@ModelAttribute("product_name") String product_name,
            Model model) throws UnknownHostException{
            model.addAttribute("bProduct",AppConstants.awsBucketProduct);
            
         Product productFind = productService.findByName(product_name);
    
         //Check cart
         Cart checkCart = cartService.findByProductAndIpaddress(productFind, AppHosts.currentHostIpAddress());
         if(checkCart !=null) {
             int newQuantity = checkCart.getQuantity()+1;
             checkCart.setQuantity(newQuantity);
             checkCart.setTotal((productFind.getPrice())*newQuantity);
             checkCart.setProduct(productFind);
             checkCart.setIpaddress(AppHosts.currentHostIpAddress());
             cartService.update(checkCart);        
         }
         else {  
             //Add product to cart
              Cart cart = new Cart();
              cart.setQuantity(1);
              cart.setTotal(productFind.getPrice());
              cart.setProduct(productFind);
              cart.setIpaddress(AppHosts.currentHostIpAddress());
              cartService.save(cart);
         }
         return "redirect:/cart";        
    }
    
   
}
