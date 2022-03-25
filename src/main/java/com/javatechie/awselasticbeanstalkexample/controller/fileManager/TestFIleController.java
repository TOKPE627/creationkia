package com.javatechie.awselasticbeanstalkexample.controller.fileManager;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.javatechie.awselasticbeanstalkexample.service.StorageService;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;


@Controller
@RequestMapping("/testFile")
public class TestFIleController {
	@Autowired
    private AmazonS3 amazonS3;
	
	@Value("${application.bucket.name}")
    private String bucketName;
	
//	    @Value("${cloud.aws.credentials.access-key}")
//	    private String accessKey;

//	    @Value("${cloud.aws.credentials.secret-key}")
//	    private String secretKey;
//	    @Value("${cloud.aws.region.static}")
//	    private String region;
	
    @Autowired
    private StorageService service;

	@GetMapping("/showFile")
	public String showFile() {
		return "showFile";
	}
	
//	@GetMapping("/display/{filename}")
//	public String displayFile(Model model,HttpServletResponse response,
//			@PathVariable String fileName) throws IOException{
//	 	//String imagePath = service.display(filename);
//		//model.addAttribute("imagePath",imagePath);
//		//String folderPath = documentDetails.getPath();
//	  	//String fileName = documentDetails.getDocument_name();
//	  	String folderPath = "https://attolystorage.s3.us-east-2.amazonaws.com/1641297952199_library.png";
//		
//  		response.setHeader("Content-Disposition", "inline;filename=\"" + fileName + "\"");
//	 
//  		S3Object s3object = amazonS3.getObject(bucketName, folderPath);
//  		S3ObjectInputStream inputStream = s3object.getObjectContent();
//		IOUtils.copy(inputStream, response.getOutputStream());
//		model.addAttribute("folderPath",folderPath);
//		return "display";
//	}
	
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam(value = "file") MultipartFile file,
    		ModelMap modelMap
    		) {
        String message="";
        if(file.getSize()>0) {
	    	 try {
	    	        service.uploadFile(file);
	    		 	message = "Uploaded the file successfully: " + file.getOriginalFilename();
	    		 	System.out.println(message);
	    		
	    	 } catch (Exception e) {
	 	    	e.printStackTrace();
	 	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	 	      System.out.println(message);
	 	   }	
	    }
     	String imgSrc = service.display(file.getOriginalFilename());
        modelMap.put("imgSrc", imgSrc);
        modelMap.put("message", message);
	   // return "message/success";      
	    return "showImage";      
    }
    
//    @PostMapping(value = "/uploadFileTest")
//    public ModelAndView uploads3(@RequestParam("photo") MultipartFile image, @RequestParam(name = "desc") String desc) {
//        ModelAndView returnPage = new ModelAndView();
//        System.out.println("description      " + desc);
//        System.out.println(image.getOriginalFilename());
//    
//        BasicAWSCredentials cred = new BasicAWSCredentials(accessKey,secretKey);
//        // AmazonS3Client client=AmazonS3ClientBuilder.standard().withCredentials(new
//        // AWSCredentialsProvider(cred)).with
//        AmazonS3 client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred))
//                .withRegion(region).build();
//        try {
//            PutObjectRequest put = new PutObjectRequest(bucketName, image.getOriginalFilename(),
//                    image.getInputStream(), new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);
//            client.putObject(put);
//
//            String imgSrc = "http://" + bucketName + ".s3.amazonaws.com/" + image.getOriginalFilename();
//
//            returnPage.setViewName("showImage");
//            returnPage.addObject("name", desc);
//            returnPage.addObject("imgSrc", imgSrc);
//
//            //Save this in the DB. 
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            returnPage.setViewName("error");
//        }
//        return returnPage;
//
//    }

    

}
