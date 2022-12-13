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

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;
import com.javatechie.awselasticbeanstalkexample.service.StorageService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@RestController
@RequestMapping("/uploadCategory")
public class RestUploadCategoryController {
	@Autowired
	private UserService userService;
	
    
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
    private StorageService storageService;
	
    
	private final Logger logger = LoggerFactory.getLogger(RestUploadCategoryController.class);
	
    @PostMapping("/single")
    public ResponseEntity<?> uploadFile(
    		@RequestParam("name") String name,
            @RequestParam("files") MultipartFile[] uploadfiles,Principal principal) {
		  User user = userService.findByUsername(principal.getName());

        logger.debug("Single file upload!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        
        if (uploadedFileName.isEmpty()) {
        	return new ResponseEntity("Veuillez sélectionner un fichier!!", HttpStatus.OK);
        }

        try {
        	Category c= new Category();
            c.setName(name);
         
            Category cSaved = categoryService.save(c);
            if(Objects.nonNull(cSaved)) {
              int count=0;
       		  for(MultipartFile extraMultipart : uploadfiles) {
       		      String extraImageName = StringUtils.cleanPath(extraMultipart.getOriginalFilename());
	       		  if(count == 0) {
	       			if(extraMultipart.getSize()>0) { 
	   		           cSaved.setImage(extraImageName);
	   	               categoryService.update(cSaved);
	   	               saveUploadedFiles(Arrays.asList(uploadfiles),cSaved.getId());
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
            
            storageService.uploadRestFileInAws(AppConstants.bucket_category, id,file);
        }

    }
    
    
//    @PostMapping("/update")
//    public ResponseEntity<?> uploadFile(
//    		@RequestParam("id") String id,
//    		@RequestParam("name") String name,
//    		@RequestParam("price") Double price,
//    		@RequestParam("detail") String detail,
//            @RequestParam("files") MultipartFile[] uploadfiles,Principal principal) {
//		  User user = userService.findByUsername(principal.getName());
//
//        logger.debug("Single file updating!");
//
//        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
//                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));
//
//        try {
//        	Catalog catalog= catalogService.findById(Long.parseLong(id));
//        	if(Objects.nonNull(catalog)) {
//         		catalog.setUser(user);
//                catalog.setName(name);
//                catalog.setPrice(price);
//                catalog.setDetail(detail);
//        		Catalog catalogUpdated = catalogService.update(catalog);
//
//                int count=0;
//       		    for(MultipartFile extraMultipart : uploadfiles) {
//       		      String extraImageName = StringUtils.cleanPath(extraMultipart.getOriginalFilename());
//           		  if(count == 0) {
//           			if(extraMultipart.getSize()>0) { 
//           				catalogUpdated.setImage(extraImageName);
//           				catalogService.update(catalogUpdated);
//       	               saveUploadedFiles(Arrays.asList(uploadfiles),catalog.getId());
//             		 }  
//           		  }
//       		   }
//        	}
//      
//       
//        } catch (IOException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity("Modification effectué avec succès. - "
//                + uploadedFileName, HttpStatus.OK);
//    }
   
}
