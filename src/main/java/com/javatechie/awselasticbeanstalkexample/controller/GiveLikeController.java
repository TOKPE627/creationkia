package com.javatechie.awselasticbeanstalkexample.controller;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;

import com.javatechie.awselasticbeanstalkexample.domain.AjaxResponseBody;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyType;
import com.javatechie.awselasticbeanstalkexample.domain.GiveLike;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.GiveLikeService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/like")
public class GiveLikeController {
    @Autowired
    private GiveLikeService giveLikeService;

    @Autowired
    private CompanyService companyService;

	@RequestMapping(value="/service", method=RequestMethod.GET)
    public ResponseEntity<?> giveLike(@ModelAttribute("service_id") Long service_id, Model model) throws UnknownHostException {
        Company company = companyService.findById(service_id);

        List<GiveLike> giveLikes = giveLikeService.findAllByCompany(company);
        
        int numberLikes=0;
        System.out.println("Company Id: " +company.getId());
       System.out.println("Number likes: " + giveLikes.size());
        if(!giveLikes.isEmpty()) {
            System.out.println("Company not empty");
            GiveLike checkLikeByIpAdress = giveLikeService.findByCompanyAndIpaddress(company, AppHosts.currentHostIpAddress());
            if(checkLikeByIpAdress == null){
                GiveLike giveLike = new GiveLike();
                giveLike.setCurrent(1);
                giveLike.setCompany(company);
                giveLike.setIpaddress(AppHosts.currentHostIpAddress());
                giveLikeService.save(giveLike);
            }
         
            for(GiveLike giveLikeC: giveLikes){
                numberLikes = numberLikes + giveLikeC.getCurrent();
            }
            System.out.println("Number likes " +numberLikes);
        }
        else{
            System.out.println("Empty company likes");
            GiveLike giveLike = new GiveLike();
            giveLike.setCurrent(1);
            giveLike.setCompany(company);
            giveLike.setIpaddress(AppHosts.currentHostIpAddress());
            giveLikeService.save(giveLike);
        }
        // GiveLike checkLike = giveLikeService.findByCompany(company);
        // if(checkLike !=null){
        //     GiveLike checkLikeByIpAdress = giveLikeService.findByCompanyAndIpaddress(company, AppHosts.currentHostIpAddress());
        //     if(checkLikeByIpAdress == null){
        //         GiveLike giveLike = new GiveLike();
        //         giveLike.setCurrent(1);
        //         giveLike.setCompany(company);
        //         giveLike.setIpaddress(AppHosts.currentHostIpAddress());
        //         giveLikeService.save(giveLike);
        //     }
        // }
      
        // GiveLike likeF =  giveLikeService.findByCompany(company);
        // int numberLikes = likeF.getCurrent(); 
        AjaxResponseBody result = new AjaxResponseBody();
        result.setCompanyId(company.getId());
        result.setNumberLikes(numberLikes);
	    return ResponseEntity.ok(result);
        
    }

    @RequestMapping(value="/service/count", method=RequestMethod.GET)
    public ResponseEntity<?> countServiceLikes(@ModelAttribute("service_id") Long service_id, Model model) throws UnknownHostException {
        Company company = companyService.findById(service_id);       
        List<GiveLike> giveLikes = giveLikeService.findAllByCompany(company);
        int numberLikes=0;
        // GiveLike likeF =  giveLikeService.findByCompany(company);
        // int numberLikes = likeF.getCurrent(); 
        for(GiveLike giveLikeC: giveLikes){
            numberLikes = numberLikes + giveLikeC.getCurrent();
        }
          System.out.println(numberLikes);
        AjaxResponseBody result = new AjaxResponseBody();
        result.setCompanyId(company.getId());
        result.setNumberLikes(numberLikes);
	    return ResponseEntity.ok(result);
    }



    @RequestMapping(value="/store", method=RequestMethod.GET)
    public ResponseEntity<?> giveStoreLike(@ModelAttribute("store_id") Long store_id, Model model) throws UnknownHostException {
        Company company = companyService.findById(store_id);
        List<GiveLike> giveLikes = giveLikeService.findAllByCompany(company);
        int numberLikes=0;
        System.out.println("Company Id: " +company.getId());
       
        if(!giveLikes.isEmpty()) {
            System.out.println("Company not empty");
            GiveLike checkLikeByIpAdress = giveLikeService.findByCompanyAndIpaddress(company, AppHosts.currentHostIpAddress());
            if(checkLikeByIpAdress == null){
                GiveLike giveLike = new GiveLike();
                giveLike.setCurrent(1);
                giveLike.setCompany(company);
                giveLike.setIpaddress(AppHosts.currentHostIpAddress());
                giveLikeService.save(giveLike);
            }
         
            for(GiveLike giveLikeC: giveLikes){
                numberLikes = numberLikes + giveLikeC.getCurrent();
            }
            System.out.println("Number likes " +numberLikes);
        }
        else{
            System.out.println("Empty company likes");
            GiveLike giveLike = new GiveLike();
            giveLike.setCurrent(1);
            giveLike.setCompany(company);
            giveLike.setIpaddress(AppHosts.currentHostIpAddress());
            giveLikeService.save(giveLike);
        }
      
        AjaxResponseBody result = new AjaxResponseBody();
        result.setCompanyId(company.getId());
        result.setNumberLikes(numberLikes);
	    return ResponseEntity.ok(result);
        
    }

    @RequestMapping(value="/store/count", method=RequestMethod.GET)
    public ResponseEntity<?> countStoreLikes(@ModelAttribute("store_id") Long store_id, Model model) throws UnknownHostException {
        Company company = companyService.findById(store_id);       
        List<GiveLike> giveLikes = giveLikeService.findAllByCompany(company);
        int numberLikes=0;
        for(GiveLike giveLikeC: giveLikes){
            numberLikes = numberLikes + giveLikeC.getCurrent();
        }
          System.out.println(numberLikes);
        AjaxResponseBody result = new AjaxResponseBody();
        result.setCompanyId(company.getId());
        result.setNumberLikes(numberLikes);
	    return ResponseEntity.ok(result);
    }
}
