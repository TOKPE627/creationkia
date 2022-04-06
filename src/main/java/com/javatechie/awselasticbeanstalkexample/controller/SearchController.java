package com.javatechie.awselasticbeanstalkexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.javatechie.awselasticbeanstalkexample.domain.AjaxResponseBody;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private ProductService productService;
	

	@RequestMapping(value = "/products")
	public ResponseEntity<?> showInfo(@ModelAttribute("keyword") String keyword) {
        AjaxResponseBody result = new AjaxResponseBody();
		List<Product> products = productService.findByNameLike(keyword);
		
        if (products.isEmpty()) {
            result.setMsg("no_result");
        } else {
            result.setMsg("success");
        }
        result.setResult(products);	
        result.setAwsBucketProduct(AppConstants.awsBucketProduct);
        result.setAwsBucketGroupSale(AppConstants.awsBucketGroupSale);
        result.setAwsBucketShop(AppConstants.awsBucketShop);
        System.out.println(result);
	    return ResponseEntity.ok(result);
	}

    // @RequestMapping(value="/findProduct")
    // public ResponseEntity<?> findProudct(@ModelAttribute("product_id") String product_id){
    //     AjaxResponseBody result = new AjaxResponseBody();
    //     Product product = productService.findById(Long.parseLong(product_id));
	// 	result.setBrand(image);
	// 	    return ResponseEntity.ok(result);
    // }
}
