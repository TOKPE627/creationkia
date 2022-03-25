package com.javatechie.awselasticbeanstalkexample.controller.fileManager;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.Principal;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang.CharSet;
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

import com.amazonaws.util.Base64;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.service.AdvertiseService;
import com.javatechie.awselasticbeanstalkexample.service.CatalogService;
import com.javatechie.awselasticbeanstalkexample.service.StorageService;
import com.javatechie.awselasticbeanstalkexample.service.UserRoleService;
import com.javatechie.awselasticbeanstalkexample.service.UserService;

@RestController
@RequestMapping("/cropImage")
public class RestCropImageController {

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
    
	 @PostMapping("/upload")
	    public ResponseEntity<?> uploadFile(
	            @RequestParam("image") String image, Principal principal) {
		  User user = userService.findByUsername(principal.getName());
		  ///data:image/png;base64,iVBORwo.png
          System.out.println(image);
		  String[] image_array1= image.split(";");
		  for(String a:image_array1) {
			  System.out.println(a); //base64,iVBORwo.png
		  }
		  String[] image_array2 = image_array1[1].split(",");
		  System.out.println(image_array2);//iVBORwo.png
		   byte[] data = Base64.decode(image_array2[1]);
	    
		  String imageName= System.currentTimeMillis()+".png";
		  
		  //Files.write(imageName, data,  StandardOpenOption.CREATE);
		  	 // return new ResponseEntity("Recadrage effecté avec succès.", HttpStatus.OK);
	       return new ResponseEntity(imageName,HttpStatus.OK);
		 // return new ResponseEntity("<img src="`+imageName+`" class="img-thumbnail"/>");
	 }
	 
	 public static void writeToFile(String text, String targetFilePath) throws IOException
	 {
	     Path targetPath = Paths.get(targetFilePath);
	     byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
	     Files.write(targetPath, bytes, StandardOpenOption.CREATE);
	 }

}
