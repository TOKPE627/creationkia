package com.javatechie.awselasticbeanstalkexample.controller;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javatechie.awselasticbeanstalkexample.domain.Brand;
import com.javatechie.awselasticbeanstalkexample.domain.Cart;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.ProductGalery;
import com.javatechie.awselasticbeanstalkexample.domain.Style;
import com.javatechie.awselasticbeanstalkexample.domain.Univers;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.service.BrandService;
import com.javatechie.awselasticbeanstalkexample.service.CartService;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.ProductGaleryService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.StyleService;
import com.javatechie.awselasticbeanstalkexample.service.UniversService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private UserService userService;
	
	
	@Autowired
    private CategoryService categoryService;

    @Autowired
    private StyleService styleService;
    
    @Autowired
    private BrandService brandService;
	
    @Autowired
    private ProductService productService;
    
    @Autowired
    private UniversService universService;
    
    @Autowired
    private ProductGaleryService productGaleryService;
    
    @Autowired
    private CartService cartService;
    
	@RequestMapping("/add")
    public String add(Model model, Principal principal) {
          User user = userService.findByUsername(principal.getName());
          model.addAttribute("user", user);
          model.addAttribute("userRole1",AppConstants.ROLE_1);

          List<Category> categories = categoryService.findAll();
          List<Style> styles = styleService.findAll();
          List<Brand> brands = brandService.findAll();
          List<Univers> univers = universService.findAll();
          model.addAttribute("categoryList",categories);
          model.addAttribute("styleList",styles);
          model.addAttribute("brandList",brands);
          model.addAttribute("universList",univers);

          return "dashboard/product/add";
    }
	  @RequestMapping(value = "/add", method = RequestMethod.POST)
	    public String add(@ModelAttribute("product")  Product product,  
	            @RequestParam("category_id") Long category_id,
	            @RequestParam("brand_id") Long brand_id,
	            @RequestParam("style_id") Long style_id,
	            @RequestParam("univers_id") Long univers_id,
	             Model model,Principal principal
	        )throws IOException
	    {

	        User user = userService.findByUsername(principal.getName());
            model.addAttribute("userRole1",AppConstants.ROLE_1);
            model.addAttribute("user",user);

	        Category category=categoryService.findById(category_id);
	        Brand brand = brandService.findById(brand_id);
	        Style style=styleService.findById(style_id);
	        Univers univers =universService.findById(univers_id);
	        product.setCategory(category);
	        product.setBrand(brand);
	        product.setStyle(style);
	        product.setUnivers(univers);
	        Product savedProduct = productService.save(product);
	        model.addAttribute("product",savedProduct);
	        
	        return "dashboard/product/addGalery";
	    }
	
	  @RequestMapping(value = "/all", method = RequestMethod.GET)
      public String all(Model model,Principal principal) throws IOException{
            model.addAttribute("bProduct",AppConstants.awsBucketProduct);
             User user = userService.findByUsername(principal.getName());
              model.addAttribute("user", user);        
             model.addAttribute("userRole1",AppConstants.ROLE_1);
             List<Product> products = productService.findAll(); 
             model.addAttribute("productList", products); 
            return "dashboard/product/all";  
      }
	  @RequestMapping("/productInfo")
      public String storeInfo(@RequestParam("id") Long id,Model model,Principal principal) throws Exception {
          model.addAttribute("bProduct", AppConstants.awsBucketProduct);
          User user = userService.findByUsername(principal.getName());
          model.addAttribute("user", user);        
          model.addAttribute("userRole1",AppConstants.ROLE_1);

          Product product = productService.findById(id);
          model.addAttribute("product", product);     

          
          if(product.getGalery() == null){
              return "dashboard/product/addGalery";
          }
          
          List<Category> categories = categoryService.findAll();
          List<Style> styles = styleService.findAll();
          List<Brand> brands = brandService.findAll();
          List<Univers> univers = universService.findAll();
          model.addAttribute("categoryList",categories);
          model.addAttribute("styleList",styles);
          model.addAttribute("brandList",brands);
          model.addAttribute("universList",univers);
          
          return "dashboard/product/info";
      }

      
      @RequestMapping(value="/update", method=RequestMethod.POST)     
      public String update(
              @ModelAttribute("product") Product product,
              @RequestParam("id") Long id, 
              @RequestParam("galery_id") Long galery_id, 
              @RequestParam("category_id") Long category_id,
              @RequestParam("brand_id") Long brand_id,
              @RequestParam("style_id") Long style_id,
              @RequestParam("univers_id") Long univers_id,
               Model model,Principal principal
          )
      {
          User user = userService.findByUsername(principal.getName());
          model.addAttribute("userRole1",AppConstants.ROLE_1);
          model.addAttribute("user",user);
          
          ProductGalery productGalery=productGaleryService.findById(galery_id);
          Category category=categoryService.findById(category_id);
          Brand brand = brandService.findById(brand_id);
          Style style=styleService.findById(style_id);
          Univers univers =universService.findById(univers_id);
          
          product.setGalery(productGalery);
          product.setCategory(category);
          product.setBrand(brand);            
          product.setStyle(style);
          product.setUnivers(univers);
          productService.update(product);
          
          return "redirect:/product/all";       
       }
      
    //Frontend
      @RequestMapping(value = "/details/{name}", method = RequestMethod.GET)
      public String categoryProducts(@ModelAttribute("name") String name,
              Model model,Principal principal) throws IOException{
            model.addAttribute("bProduct",AppConstants.awsBucketProduct);

            List<Cart> carts=cartService.findByIpaddress(AppHosts.currentHostIpAddress());
            model.addAttribute("cartList",carts);
            
             Product p=productService.findByName(name);
             model.addAttribute("p",p); 
            return "product";  
      }
}
