package com.javatechie.awselasticbeanstalkexample.controller;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javatechie.awselasticbeanstalkexample.domain.Advertise;
import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.CompanyType;
import com.javatechie.awselasticbeanstalkexample.domain.ContactAtooly;
import com.javatechie.awselasticbeanstalkexample.domain.PartnerAtooly;
import com.javatechie.awselasticbeanstalkexample.domain.SubCategory;
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;
import com.javatechie.awselasticbeanstalkexample.service.CompanyService;
import com.javatechie.awselasticbeanstalkexample.service.ContactAtoolyService;
import com.javatechie.awselasticbeanstalkexample.service.PartnerAtoolyService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.SubCategoryService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.javatechie.awselasticbeanstalkexample.utility.AppHosts;

@Controller
@RequestMapping("/service")
public class ServiceCategoryController {
	
	@Autowired
	private ProductService productService;

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private AdvertiseService advertiseService;
	
	@Autowired
    private CompanyService companyService;

	@Autowired
	private SubCategoryService subCategoryService;

	@Autowired
	private ContactAtoolyService contactAtoolyService;
 
	@Autowired
	private PartnerAtoolyService partnerAtoolyService;
	
	@GetMapping("")
	public String index(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

		
		Advertise advertise = advertiseService.findByName(AppConstants.APP_NAME);
		List<Company> services          = companyService.findAllByType(CompanyType.SERVICE);
        
		 SubCategory subCategorySewing = subCategoryService.findById(Long.parseLong("9"));
		 SubCategory subCategoryAirdressing = subCategoryService.findById(Long.parseLong("10"));
		 SubCategory subCategoryCarpentry = subCategoryService.findById(Long.parseLong("11"));
		 SubCategory subCategoryGrossery = subCategoryService.findById(Long.parseLong("12"));
		 SubCategory subCategoryMeca = subCategoryService.findById(Long.parseLong("13"));
		 SubCategory subCategoryRepair = subCategoryService.findById(Long.parseLong("14"));
		 SubCategory subCategoryOther = subCategoryService.findById(Long.parseLong("16"));


		 List<Company> sewings = companyService.findByCompanyTypeBySubCategory(CompanyType.SERVICE, subCategorySewing);
		 List<Company> hairdressings = companyService.findByCompanyTypeBySubCategory(CompanyType.SERVICE, subCategoryAirdressing);
		 List<Company> carpentries = companyService.findByCompanyTypeBySubCategory(CompanyType.SERVICE, subCategoryCarpentry);
		 List<Company> grosseries = companyService.findByCompanyTypeBySubCategory(CompanyType.SERVICE, subCategoryGrossery);
		 List<Company> mecas = companyService.findByCompanyTypeBySubCategory(CompanyType.SERVICE, subCategoryMeca);
		 List<Company> repairs = companyService.findByCompanyTypeBySubCategory(CompanyType.SERVICE, subCategoryRepair);
		 List<Company> others = companyService.findByCompanyTypeBySubCategory(CompanyType.SERVICE, subCategoryOther);
		 List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
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
		  
		  if(Objects.nonNull(advertise)) {
			model.addAttribute("advertiseExists",true);
			model.addAttribute("advertise",advertise);
		}
		if(!services.isEmpty()) {
			model.addAttribute("serviceExist",true);
			model.addAttribute("serviceList",services); 
		}
		if(!sewings.isEmpty()) {
			model.addAttribute("sewingExist",true);
			model.addAttribute("sewingList",sewings); 
		}
		if(!hairdressings.isEmpty()) {
			model.addAttribute("hairdressingExist",true);
			model.addAttribute("hairdressingList",hairdressings); 
		}
		if(!carpentries.isEmpty()) {
			model.addAttribute("carpentryExist",true);
			model.addAttribute("carpentryList",carpentries); 
		}
		  
		if(!grosseries.isEmpty()) {
			model.addAttribute("grosseryExist",true);
			model.addAttribute("grosseryList",grosseries); 
		}
		
		if(!mecas.isEmpty()) {
			model.addAttribute("mecaExist",true);
			model.addAttribute("mecaList",mecas); 
		}
		if(!repairs.isEmpty()) {
			model.addAttribute("repairExist",true);
			model.addAttribute("repairList",repairs); 
		}
		if(!others.isEmpty()) {
			model.addAttribute("otherExist",true);
			model.addAttribute("otherList",others); 
		}
		return "service";
	}
	
	@GetMapping("/update")
	public String update(Model model) {
	  return "dashboard/service/update";
	}

	@GetMapping("/sewing")
	public String sewing(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

		Advertise advertise = advertiseService.findByName(AppConstants.APP_NAME);
		List<Company> services          = companyService.findAllByType(CompanyType.SERVICE);
		SubCategory subCategorySewing = subCategoryService.findById(Long.parseLong("9"));
		

		List<Company> sewings = companyService.findByCompanyTypeBySubCategory(CompanyType.SERVICE, subCategorySewing);
			List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
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
		  
		  if(Objects.nonNull(advertise)) {
			model.addAttribute("advertiseExists",true);
			model.addAttribute("advertise",advertise);
		}
		if(!services.isEmpty()) {
			model.addAttribute("serviceExist",true);
			model.addAttribute("serviceList",services); 
		}
		if(!sewings.isEmpty()) {
			model.addAttribute("companyExist",true);
			model.addAttribute("companyList",sewings); 
		}
		
		return "front/service/sewing";
	}

	@GetMapping("/hairdressing")
	public String hairdressing(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

		Advertise advertise = advertiseService.findByName(AppConstants.APP_NAME);
		List<Company> services          = companyService.findAllByType(CompanyType.SERVICE);

		SubCategory subCategoryAirdressing = subCategoryService.findById(Long.parseLong("10"));

		List<Company> hairdressings = companyService.findByCompanyTypeBySubCategory(CompanyType.SERVICE, subCategoryAirdressing);

		List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
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
		  
		  if(Objects.nonNull(advertise)) {
			model.addAttribute("advertiseExists",true);
			model.addAttribute("advertise",advertise);
		}
		if(!services.isEmpty()) {
			model.addAttribute("serviceExist",true);
			model.addAttribute("serviceList",services); 
		}

		if(!hairdressings.isEmpty()) {
			model.addAttribute("companyExist",true);
			model.addAttribute("companyList",hairdressings); 
		}
		
		return "front/service/hairdressing";
	}	


	@GetMapping("/carpentry")
	public String carpentry(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

		
		Advertise advertise = advertiseService.findByName(AppConstants.APP_NAME);
		//  List<Product> services = productService.findAllByCategory(AppConstants.CATEGORY_SERVICE);
		List<Company> services          = companyService.findAllByType(CompanyType.SERVICE);
	SubCategory subCategoryCarpentry = subCategoryService.findById(Long.parseLong("11"));
			List<Company> carpentries = companyService.findByCompanyTypeBySubCategory(CompanyType.SERVICE, subCategoryCarpentry);
	
		List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	model.addAttribute("bookingBegunList",bookingsBegun);
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
		  
		  if(Objects.nonNull(advertise)) {
			model.addAttribute("advertiseExists",true);
			model.addAttribute("advertise",advertise);
		}
		if(!services.isEmpty()) {
			model.addAttribute("serviceExist",true);
			model.addAttribute("serviceList",services); 
		}
		
		if(!carpentries.isEmpty()) {
			model.addAttribute("companyExist",true);
			model.addAttribute("companyList",carpentries); 
		}
		return "front/service/carpentry";
	}

	@GetMapping("/grocery")
	public String grossery(Model model) throws UnknownHostException {
		model.addAttribute("url",AppConstants.url);
		model.addAttribute("awsBucketIcon", AppConstants.awsBucketIcon);
		model.addAttribute("awsBucketCompany", AppConstants.awsBucketCompany);
	    model.addAttribute("awsBucketProduct", AppConstants.awsBucketProduct);
	    model.addAttribute("awsBucketGroupSale", AppConstants.awsBucketGroupSale);
	    model.addAttribute("awsBucketAdvertise",AppConstants.awsBucketAdvertise);
		model.addAttribute("awsBucketShop", AppConstants.awsBucketShop);
		model.addAttribute("awsBucketPartner", AppConstants.awsBucketPartner);

	
		Advertise advertise = advertiseService.findByName(AppConstants.APP_NAME);
		//  List<Product> services = productService.findAllByCategory(AppConstants.CATEGORY_SERVICE);
		List<Company> services          = companyService.findAllByType(CompanyType.SERVICE);
	   SubCategory subCategoryGrossery = subCategoryService.findById(Long.parseLong("12"));
	
       List<Company> grosseries = companyService.findByCompanyTypeBySubCategory(CompanyType.SERVICE, subCategoryGrossery);

		List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	model.addAttribute("bookingBegunList",bookingsBegun);
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
		if(Objects.nonNull(advertise)) {
		  model.addAttribute("advertiseExists",true);
		  model.addAttribute("advertise",advertise);
		}
		if(!services.isEmpty()) {
			model.addAttribute("serviceExist",true);
			model.addAttribute("serviceList",services); 
		}
		  
		if(!grosseries.isEmpty()) {
			model.addAttribute("companyExist",true);
			model.addAttribute("companyList",grosseries); 
		}
		
		return "front/service/grocery";
	}

	@GetMapping("/mechanic")
	public String meca(Model model) throws UnknownHostException {
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
		Advertise advertise = advertiseService.findByName(AppConstants.APP_NAME);
		//  List<Product> services = productService.findAllByCategory(AppConstants.CATEGORY_SERVICE);
		List<Company> services          = companyService.findAllByType(CompanyType.SERVICE);
		SubCategory subCategoryMeca = subCategoryService.findById(Long.parseLong("13"));
        List<Company> mecas = companyService.findByCompanyTypeBySubCategory(CompanyType.SERVICE, subCategoryMeca);
		
		List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
		
		  
		  if(Objects.nonNull(advertise)) {
			model.addAttribute("advertiseExists",true);
			model.addAttribute("advertise",advertise);
		}
		if(!services.isEmpty()) {
			model.addAttribute("serviceExist",true);
			model.addAttribute("serviceList",services); 
		}
	
		if(!mecas.isEmpty()) {
			model.addAttribute("companyExist",true);
			model.addAttribute("companyList",mecas); 
		}
		
		return "front/service/meca";
	}

	@GetMapping("/repair")
	public String repair(Model model) throws UnknownHostException {
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
		Advertise advertise = advertiseService.findByName(AppConstants.APP_NAME);
		List<Company> services          = companyService.findAllByType(CompanyType.SERVICE);
		SubCategory subCategoryRepair = subCategoryService.findById(Long.parseLong("14"));
		List<Company> repairs = companyService.findByCompanyTypeBySubCategory(CompanyType.SERVICE, subCategoryRepair);
	   
		List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
		
		  
		  if(Objects.nonNull(advertise)) {
			model.addAttribute("advertiseExists",true);
			model.addAttribute("advertise",advertise);
		}
		if(!services.isEmpty()) {
			model.addAttribute("serviceExist",true);
			model.addAttribute("serviceList",services); 
		}
		
		if(!repairs.isEmpty()) {
			model.addAttribute("companyExist",true);
			model.addAttribute("companyList",repairs); 
		}
		
		return "front/service/repair";
	}

	@GetMapping("/other")
	public String other(Model model) throws UnknownHostException {
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
		Advertise advertise = advertiseService.findByName(AppConstants.APP_NAME);
		List<Company> services          = companyService.findAllByType(CompanyType.SERVICE);
		SubCategory subCategoryOther = subCategoryService.findById(Long.parseLong("16"));
     	List<Company> others = companyService.findByCompanyTypeBySubCategory(CompanyType.SERVICE, subCategoryOther);
	   

			 List<Booking> bookingsBegun = bookingService.findByIpAddressAndStatus(AppHosts.currentHostIpAddress(),AppConstants.ORDER_STATUS_0);
	 	 model.addAttribute("bookingBegunList",bookingsBegun);
		
		  
		  if(Objects.nonNull(advertise)) {
			model.addAttribute("advertiseExists",true);
			model.addAttribute("advertise",advertise);
		}
		if(!services.isEmpty()) {
			model.addAttribute("serviceExist",true);
			model.addAttribute("serviceList",services); 
		}
		
		if(!others.isEmpty()) {
			model.addAttribute("companyExist",true);
			model.addAttribute("companyList",others); 
		}
		return "front/service/other";
	}
}
