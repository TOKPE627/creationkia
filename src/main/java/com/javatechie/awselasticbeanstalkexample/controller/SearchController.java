package com.javatechie.awselasticbeanstalkexample.controller;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javatechie.awselasticbeanstalkexample.domain.Advertise;
import com.javatechie.awselasticbeanstalkexample.domain.AjaxResponseBody;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.SubCategory;
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private ProductService productService;

    @Autowired
    private AdvertiseService advertiseService;

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

    @RequestMapping(value="/findProduct")
    public ResponseEntity<?> findProudct(@ModelAttribute("product_id") String product_id){
        Product result=new Product();
        Product productFind = productService.findById(Long.parseLong(product_id));
        result.setName(productFind.getName());
        result.setId(productFind.getId());
		return ResponseEntity.ok(result);
    }
    @RequestMapping(value="/resultsProduct/{id}/{name}")
    public String resultsProudct(
                                 @PathVariable("id") Long id,
                                 @PathVariable("name") String productName,
                                 Model model
    ){
        model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
        model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

       
        Advertise advertiseMobile = advertiseService.findByType(AppConstants.mobile);	    

		if(Objects.nonNull(advertiseMobile)) {
			model.addAttribute("advertiseMobileExists",true);
			model.addAttribute("advertise",advertiseMobile);
		}       

        Product product = productService.findById(id);
        Category category = product.getCategory();
        model.addAttribute("product",product);
        model.addAttribute("category",category);
        if(product.getSubCategory() !=null){
            SubCategory subCategory = product.getSubCategory();
            List<Product> products = productService.findBySubCategory(subCategory);
            System.out.println("SUB CATEGORy:" + subCategory.getName());
            if(!products.isEmpty()) {
                model.addAttribute("productExist",true);
                model.addAttribute("productList",products); 
            }
            if(product.getCategory().getTitle().equals("shop")){
               
                return "search/resultsProductShop";
           }
       }
      
        if(product.getSubCategory() ==null){
            List<Product> products = productService.findByCategory(category);
            System.out.println("Category: " +category.getName());
            if(!products.isEmpty()) {
                model.addAttribute("productExist",true);
                model.addAttribute("productList",products); 
            }
            if(product.getCategory().getTitle().equals("groupSale")){
                return "search/resultsProductGroupSale";
            }
        }
      
	    return "search/resultsProductStore";
    }
}
