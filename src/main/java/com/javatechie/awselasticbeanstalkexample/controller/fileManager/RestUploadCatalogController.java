package com.javatechie.awselasticbeanstalkexample.controller.fileManager;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javatechie.awselasticbeanstalkexample.domain.Advertise;
import com.javatechie.awselasticbeanstalkexample.domain.Catalog;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;
import com.javatechie.awselasticbeanstalkexample.service.CatalogService;
import com.javatechie.awselasticbeanstalkexample.service.StorageService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@RestController
@RequestMapping("/uploadCatalog")
public class RestUploadCatalogController {
	@Autowired
	private UserService userService;
	
    @Autowired
    private UserRoleService userRoleService;
    
	@Autowired
	private CatalogService catalogService;
	
	@Autowired
    private StorageService storageService;
	
	@Autowired
	private AdvertiseService advertiseService;
    
	private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);
	
    @PostMapping("/single")
    public ResponseEntity<?> uploadFile(
    		@RequestParam("name") String name,
    		@RequestParam("price") Double price,
    		@RequestParam("detail") String detail,
            @RequestParam("files") MultipartFile[] uploadfiles,Principal principal) {
		  User user = userService.findByUsername(principal.getName());

        logger.debug("Single file upload!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        
        if (uploadedFileName.isEmpty()) {
        	return new ResponseEntity("Veuillez sélectionner un fichier!!", HttpStatus.OK);
        }

        try {
        	Catalog catalog= new Catalog();
       		catalog.setUser(user);
            catalog.setName(name);
            catalog.setPrice(price);
            catalog.setDetail(detail);
            Catalog catalogSaved = catalogService.save(catalog);
            if(Objects.nonNull(catalogSaved)) {
              int count=0;
       		  for(MultipartFile extraMultipart : uploadfiles) {
       		      String extraImageName = StringUtils.cleanPath(extraMultipart.getOriginalFilename());
	       		  if(count == 0) {
	       			if(extraMultipart.getSize()>0) { 
	   		           catalogSaved.setImage(extraImageName);
	   	               catalogService.update(catalogSaved);
	   	               saveUploadedFiles(Arrays.asList(uploadfiles),catalog.getId());
	         		 }  
	       		  }
       		   }
              	
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Ajout effecté avec succès. - "
                + uploadedFileName, HttpStatus.OK);
    }
    private void saveUploadedFiles(List<MultipartFile> files,Long id) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }
            
            storageService.uploadRestFileInAws(AppConstants.bucket_catalog, id,file);
        }

    }
    
    
    @PostMapping("/update")
    public ResponseEntity<?> uploadFile(
    		@RequestParam("id") String id,
    		@RequestParam("name") String name,
    		@RequestParam("price") Double price,
    		@RequestParam("detail") String detail,
            @RequestParam("files") MultipartFile[] uploadfiles,Principal principal) {
		  User user = userService.findByUsername(principal.getName());

        logger.debug("Single file updating!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        try {
        	Catalog catalog= catalogService.findById(Long.parseLong(id));
        	if(Objects.nonNull(catalog)) {
         		catalog.setUser(user);
                catalog.setName(name);
                catalog.setPrice(price);
                catalog.setDetail(detail);
        	}
      
            int count=0;
   		    for(MultipartFile extraMultipart : uploadfiles) {
   		      String extraImageName = StringUtils.cleanPath(extraMultipart.getOriginalFilename());
       		  if(count == 0) {
       			if(extraMultipart.getSize()>0) { 
   		           catalog.setImage(extraImageName);
   	               saveUploadedFiles(Arrays.asList(uploadfiles),catalog.getId());
         		 }  
       		  }
   		   }
   		   catalogService.update(catalog);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Modification effectué avec succès. - "
                + uploadedFileName, HttpStatus.OK);
    }
   
}
