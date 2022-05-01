package com.javatechie.awselasticbeanstalkexample.controller;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;

import com.javatechie.awselasticbeanstalkexample.domain.AjaxResponseBody;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.Like;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.LikeService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @Autowired
    private CompanyService companyService;

	@RequestMapping(value="/service", method=RequestMethod.GET)
    public ResponseEntity<?> like(@ModelAttribute("service_id") Long service_id, Model model) throws UnknownHostException {
        Company company = companyService.findById(service_id);
        System.out.println("Company"  +company.getId()+ " "+company.getName());
        Like checkLike = likeService.findByCompany(company);
        if(checkLike !=null){
            checkLike.setNumber(checkLike.getNumber()+1);
            likeService.update(checkLike);
        }
        else{
            Like like = new Like();
            like.setNumber(1);
            likeService.save(like);
        }
        Like likeF = likeService.findByCompany(company);
        int numberLikes = likeF.getNumber(); 
        AjaxResponseBody result = new AjaxResponseBody();
        result.setCompanyId(company.getId());
        result.setNumberLikes(numberLikes);
	    return ResponseEntity.ok(result);
    }
}
