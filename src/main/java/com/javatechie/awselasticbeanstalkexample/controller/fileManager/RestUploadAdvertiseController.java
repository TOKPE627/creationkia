package com.javatechie.awselasticbeanstalkexample.controller.fileManager;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
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
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;
import com.javatechie.awselasticbeanstalkexample.service.StorageService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@RestController
@RequestMapping("/uploadAdvertise")
public class RestUploadAdvertiseController {
	@Autowired
	private UserService userService;
	
	
	
	@Autowired
    private StorageService storageService;
	
	@Autowired
	private AdvertiseService advertiseService;
    
	private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);
	
    @PostMapping("/single")
    public ResponseEntity<?> uploadFile(
            @RequestParam("files") MultipartFile[] uploadfiles,Principal principal) {
		  User user = userService.findByUsername(principal.getName());

        logger.debug("Single file upload!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        
        if (uploadedFileName.isEmpty()) {
        	return new ResponseEntity("Veuillez sélectionner un fichier!!", HttpStatus.OK);
        }

        try {
            int count=0;
      		for(MultipartFile extraMultipart : uploadfiles) {
      			String extraImageName = StringUtils.cleanPath(extraMultipart.getOriginalFilename());
      			if(count == 0) {
      				if(extraMultipart.getSize()>0) {
      					if(advertiseService.findByUser(user) !=null) {
      			        	return new ResponseEntity("Affiche déjà enregistrée!!", HttpStatus.OK);
      					}
      					else {
      					    Advertise advertise=new Advertise();
          		            advertise.setUser(user);
          		            Advertise advertiseSaved=advertiseService.save(advertise);  
          		            //TODO
          				   //storageService.deleteFileInAws(AppConstants.bucket_groupsale,galery.getId(),galery.getId()+'/'+galery.getImage1());
          		            if(advertiseSaved!=null) {
          		            	advertiseSaved.setImage(extraImageName);
          		              advertiseService.update(advertiseSaved);
          		              saveUploadedFiles(Arrays.asList(uploadfiles),advertise.getId());
                             }
      					}
      				
      				 }  
      			 }
      		   }
        
                 

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Fichier téléversé avec succès. - "
                + uploadedFileName, HttpStatus.OK);
    }
    private void saveUploadedFiles(List<MultipartFile> files,Long id) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }
            
            storageService.uploadRestFileInAws(AppConstants.bucket_advertise, id,file);
        }

    }
    
    
    @PostMapping("/update")
    public ResponseEntity<?> updateFile(
            @RequestParam("extraField") String extraField,
            @RequestParam("files") MultipartFile[] uploadfiles,
            Principal principal) {
		  User user = userService.findByUsername(principal.getName());

        logger.debug("Single file upload!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        
        if (uploadedFileName.isEmpty()) {
        	return new ResponseEntity("Veuillez sélectionner un fichier!!", HttpStatus.OK);
        }

        try {
           
            
            int count=0;
      		for(MultipartFile extraMultipart : uploadfiles) {
      			String extraImageName = StringUtils.cleanPath(extraMultipart.getOriginalFilename());
      			if(count == 0) {
      				if(extraMultipart.getSize()>0) { 
      		            Advertise advertise = advertiseService.findById(Long.parseLong(extraField));
      		            advertise.setUser(user);
      		            Advertise advertiseUpdated=advertiseService.update(advertise);
                		//TODO:delete the file on AWS Bucket Advertise
      				   //storageService.deleteFileInAws(AppConstants.bucket_groupsale,galery.getId(),galery.getId()+'/'+galery.getImage1());
      		            if(advertiseUpdated!=null) {
      		              advertise.setImage(extraImageName);
      		              advertiseService.update(advertiseUpdated);
      		              saveUploadedFiles(Arrays.asList(uploadfiles),advertise.getId());
                         }
      				 }  
      			 }
      		   }

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Fichier modifié avec succès - " +
                uploadedFileName, new HttpHeaders(), HttpStatus.OK);
    }
    
}
