package com.javatechie.awselasticbeanstalkexample.controller.fileManager;


import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.ProductGalery;
import com.javatechie.awselasticbeanstalkexample.service.ProductGaleryService;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;
import com.javatechie.awselasticbeanstalkexample.service.StorageService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@RestController
@RequestMapping("/uploadProduct")
public class RestUploadProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductGaleryService productGaleryService;
	
	
	@Autowired
    private StorageService storageService;
    
	private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);

	@PostMapping("/multi")
    public ResponseEntity<?> uploadFileMulti(
            @RequestParam("extraField") String extraField,
            @RequestParam("files") MultipartFile[] uploadfiles,Principal principal) {

        logger.debug("Multiple file upload!");

        // Get file name
        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        
        if (uploadedFileName.isEmpty()) {
            return new ResponseEntity("Veuillez sélectionner un fichier!", HttpStatus.OK);
        }
        

        try {
        	int count=0;
            Product product= productService.findById(Long.parseLong(extraField));
            
            ProductGalery galery = new ProductGalery();
	 	    ProductGalery galerySaved = productGaleryService.save(galery);
	 	    if(galerySaved !=null) {
	 	     	for(MultipartFile extraMultipart : uploadfiles) {
    		    	String extraImageName = StringUtils.cleanPath(extraMultipart.getOriginalFilename());
 
	    			if(count == 0) galerySaved.setImage1(extraImageName);
	    		    if(count == 1) galerySaved.setImage2(extraImageName);
	    		    if(count == 2) galerySaved.setImage3(extraImageName);
	    		    if(count == 3) galerySaved.setImage4(extraImageName);
    		        count++;
    		    } 	
		 	    productGaleryService.update(galerySaved);
	 	    	product.setGalery(galerySaved);
	 	    	productService.update(product);
	            saveUploadedFiles(Arrays.asList(uploadfiles),galery.getId());
	 	    }
    	    	
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Fichiers téléversés avec succès. - "
                + uploadedFileName, HttpStatus.OK);

    }

    //save file
    private void saveUploadedFiles(List<MultipartFile> files,Long idGalery) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }
            
            storageService.uploadRestFileInAws(AppConstants.bucket_product, idGalery,file);
        }

    }
    
    
    
    
    @PostMapping("/update")
    public ResponseEntity<?> updateFileMulti(
            @RequestParam("extraField") String extraField,
            @RequestParam("files") MultipartFile[] uploadfiles,Principal principal) {

        logger.debug("Multiple file update!");

        // Get file name
        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        
        if (uploadedFileName.isEmpty()) {
            return new ResponseEntity("Veuillez sélectionner un fichier!", HttpStatus.OK);
        }
        

        try {
        	int count=0;
            ProductGalery galery= productGaleryService.findById(Long.parseLong(extraField));
                
    		for(MultipartFile extraMultipart : uploadfiles) {
    			String extraImageName = StringUtils.cleanPath(extraMultipart.getOriginalFilename());
    			if(count == 0) {
    				if(extraMultipart.getSize()>0) { 
    				   galery.setImage1(extraImageName);
    				   //TODO
    				   //storageService.deleteFileInAws(AppConstants.bucket_groupsale,galery.getId(),galery.getId()+'/'+galery.getImage1());
    			     }  
    			 }
    			if(count == 1) {
    				if(extraMultipart.getSize()>0) { 
    				galery.setImage2(extraImageName);
        				//storageService.deleteFileInAws(AppConstants.bucket_groupsale,galery.getId(),galery.getId()+'/'+galery.getImage2());    					
    				}
    			}
    			if(count == 2) {
    				if(extraMultipart.getSize()>0) { 
    				galery.setImage3(extraImageName);
        				//storageService.deleteFileInAws(AppConstants.bucket_groupsale,galery.getId(),galery.getId()+'/'+galery.getImage3());
    				}
    			}
    			if(count == 3) {
    			  if(extraMultipart.getSize()>0) { 
    			    galery.setImage4(extraImageName);
      			    //storageService.deleteFileInAws(AppConstants.bucket_groupsale,galery.getId(),galery.getId()+'/'+galery.getImage4());
    			  }
    			}
    		    count++;
    		} 	    	
	 	    ProductGalery galeryUpdated = productGaleryService.update(galery);
	 	    if(galeryUpdated !=null) {
              saveUploadedFiles(Arrays.asList(uploadfiles),galery.getId());
             }

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Fichiers modifiélés avec succès. - "
                + uploadedFileName, HttpStatus.OK);

    }
}

