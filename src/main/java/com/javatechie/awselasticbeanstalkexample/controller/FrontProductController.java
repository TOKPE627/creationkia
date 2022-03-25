package com.javatechie.awselasticbeanstalkexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;

@Controller
@RequestMapping("/products")
public class FrontProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/productInfo")
	public String info(@RequestParam("id") Long id,Model model) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "front/product/info";
	}
	@RequestMapping("/productGalery")
	public String galery(@RequestParam("id") Long id,Model model) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "front/product/galery";
	}
	
	@RequestMapping("/productList")
	public String all(Model model) {
	 	List<Product>  products =  productService.findAllByOrderByPublicationDateDesc();
	    model.addAttribute("productList", products);
	    return "front/product/productList";
	}
	
//	  @RequestMapping("/commercialList") 
//	  public String commercial(Model model)
//	  {
//		  List<Product> products = productService.findCommercialProducts();
//	      model.addAttribute("productList",products); 
//	 	 if (products.isEmpty()) {
//			  model.addAttribute("emptyList", true);
//			  return "front/product/commercialList"; 
//		 }
//	     return "front/product/commercialList"; 
//    }
//	 
//	
//	  @RequestMapping("/multiserviceList") 
//	  public String multiservice(Model model)
//	  {
//		  List<Product> products = productService.findMutliserviceProducts();
//	      model.addAttribute("productList",products); 
//	 	 if (products.isEmpty()) {
//			  model.addAttribute("emptyList", true);
//		      return "front/product/multiserviceList"; 
//		}
//	      return "front/product/multiserviceList"; 
//    }
//	  
	
}
