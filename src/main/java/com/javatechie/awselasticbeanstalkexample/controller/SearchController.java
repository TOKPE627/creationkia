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
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.ContactAtooly;
import com.javatechie.awselasticbeanstalkexample.domain.PartnerAtooly;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.SubCategory;
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.ContactAtoolyService;
import com.javatechie.awselasticbeanstalkexample.service.PartnerAtoolyService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private ProductService productService;

    @Autowired
    private AdvertiseService advertiseService;

    @Autowired
	private ContactAtoolyService contactAtoolyService;

	@Autowired
	private PartnerAtoolyService partnerAtoolyService;
	
    

    @Autowired
    private CompanyService companyService;

	@RequestMapping(value = "/productOrServiceOrStore")
	public ResponseEntity<?> showInfo(@ModelAttribute("keyword") String keyword) {
        AjaxResponseBody resultProducts = new AjaxResponseBody();
        AjaxResponseBody resultCompanies = new AjaxResponseBody();
		List<Product> products = productService.findByNameLike(keyword);
		List<Company> companies = companyService.findByNameLike(keyword);
        
        if (products.isEmpty()) {//shop-groupSale
            resultProducts.setMsg("no_result_products");
        } 
        if(companies.isEmpty()){//service-store
            resultCompanies.setMsg("no_result_companies");
        }
        if(!products.isEmpty()){
            resultProducts.setMsg("success_products");
            resultProducts.setResult(products);	
            resultProducts.setAwsBucketProduct(AppConstants.awsBucketProduct);
            resultProducts.setAwsBucketGroupSale(AppConstants.awsBucketGroupSale);
            resultProducts.setAwsBucketShop(AppConstants.awsBucketShop);
            System.out.println(resultProducts);
            return ResponseEntity.ok(resultProducts);
        }
        if(!companies.isEmpty()){
            resultCompanies.setMsg("success_companies");
            resultCompanies.setCompanies(companies);	
            resultCompanies.setAwsBucketCompany(AppConstants.awsBucketCompany);
            System.out.println(resultCompanies);
        } 
	    return ResponseEntity.ok(resultCompanies);
	}

    @RequestMapping(value="/findProduct")
    public ResponseEntity<?> findProudct(@ModelAttribute("product_id") String product_id){
        Product result=new Product();
        Product productFind = productService.findById(Long.parseLong(product_id));
        result.setName(productFind.getName());
        result.setId(productFind.getId());
		return ResponseEntity.ok(result);
    }

    @RequestMapping(value="/findCompany")
    public ResponseEntity<?> findCompany(@ModelAttribute("company_id") String company_id){
        Company result=new Company();
        Company companyFind = companyService.findById(Long.parseLong(company_id));
        result.setName(companyFind.getName());
        result.setId(companyFind.getId());
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

        ContactAtooly contactAtooly         = contactAtoolyService.findByName(AppConstants.APP_NAME);
		 List<PartnerAtooly> partnerAtoolies = partnerAtoolyService.findAllPartners();
		 if(Objects.nonNull(contactAtooly)){
			model.addAttribute("contactExists",true);
		    model.addAttribute("contact",contactAtooly); 
		}
		if(!partnerAtoolies.isEmpty()){
			model.addAttribute("partnerExist",true);
			model.addAttribute("partnerList",partnerAtoolies);
		}
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
