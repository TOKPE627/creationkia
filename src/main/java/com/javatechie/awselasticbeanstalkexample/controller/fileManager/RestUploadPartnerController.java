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
import com.javatechie.awselasticbeanstalkexample.domain.PartnerAtooly;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;
import com.javatechie.awselasticbeanstalkexample.service.PartnerAtoolyService;
import com.javatechie.awselasticbeanstalkexample.service.StorageService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@RestController
@RequestMapping("/uploadPartner")
public class RestUploadPartnerController {
	@Autowired
	private UserService userService;
	
	
	
	@Autowired
    private StorageService storageService;
	
	@Autowired
	private PartnerAtoolyService partnerAtoolyService;
    
	private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);
	
    @PostMapping("/single")
    public ResponseEntity<?> uploadFile(        
        @RequestParam("namePartner") String namePartner,
        @RequestParam("files") MultipartFile[] uploadfiles,Principal principal) {
		  User user = userService.findByUsername(principal.getName());

        logger.debug("Single file upload!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        
        if (uploadedFileName.isEmpty()) {
        	return new ResponseEntity("Veuillez sélectionner une photo!!", HttpStatus.OK);
        }

        try {
            
      		for(MultipartFile extraMultipart : uploadfiles) {
      			String imageName = StringUtils.cleanPath(extraMultipart.getOriginalFilename());
                 PartnerAtooly partnerAtooly = new PartnerAtooly();
                 partnerAtooly.setName(namePartner);
                 partnerAtooly.setBrand(imageName);
                 partnerAtoolyService.save(partnerAtooly);  
                 saveUploadedFiles(Arrays.asList(uploadfiles),partnerAtooly.getId());
      		   }
        
                 

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Photo téléversée avec succès. - "
                + uploadedFileName, HttpStatus.OK);
    }
    private void saveUploadedFiles(List<MultipartFile> files,Long id) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }
            
            storageService.uploadRestFileInAws(AppConstants.bucket_partner, id,file);
        }

    }
    
    
//     @PostMapping("/update")
//     public ResponseEntity<?> updateFile(
//             @RequestParam("idPartner") String idPartner,
//             @RequestParam("namePartner") String namePartner,
//             @RequestParam("files") MultipartFile[] uploadfiles,
//             Principal principal) {
// 		  User user = userService.findByUsername(principal.getName());

//         logger.debug("Single file upload!");

//         String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
//                 .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        
//         if (uploadedFileName.isEmpty()) {
//         	return new ResponseEntity("Veuillez sélectionner un fichier!!", HttpStatus.OK);
//         }

//         try {
//       		for(MultipartFile extraMultipart : uploadfiles) {
//       			String imageName = StringUtils.cleanPath(extraMultipart.getOriginalFilename());
//       			PartnerAtooly partnerAtooly = partnerAtoolyService.findById(Long.parseLong(idPartner));
//                 partnerAtooly.setName(namePartner);
//                 partnerAtooly.setBrand(imageName);
//                 partnerAtoolyService.update(partnerAtooly);
//                 saveUploadedFiles(Arrays.asList(uploadfiles), partnerAtooly.getId());
//       		   }
//         } catch (IOException e) {
//             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//         }

//         return new ResponseEntity("Fichier modifié avec succès - " +
//                 uploadedFileName, new HttpHeaders(), HttpStatus.OK);
//     }
    
 }
