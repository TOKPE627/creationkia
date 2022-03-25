package com.javatechie.awselasticbeanstalkexample.controller.fileManager;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;


@Controller
public class FileUploadController {
//
//	@Autowired
//	DocumentService documentService;
//	
//	@Autowired
//    private AmazonS3 amazonS3;
//	
//	@Value("${aws.s3.bucket}")
//	private String s3bucket;	
	
//	@GetMapping("/showFile")
//	public String showFile() {
//		return "showFile";
//	}
	
	@GetMapping("/showRestFile")
	public String showRestFile() {
		return "showRestFile";
	}
//    @PostMapping("/uploadFileToS3")
//    public String uploadFile(@RequestParam(value = "file") MultipartFile file,ModelMap map) {
//    	 String message = "";
// 	    String docId=null;
// 	    if(file.getSize()>0) {
// 	    	 try {
// 	 	    	   docId= documentService.uploadFileToS3Bucket(file);
// 	 	   	message = "Uploaded the file successfully: " + file.getOriginalFilename();
// 	 	    } catch (Exception e) {
// 	 	    	e.printStackTrace();
// 	 	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
// 	 	   }	
// 	    }
// 	    
// 	   if(null!=docId && !docId.isEmpty()) {
// 		  String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
// 					.path("/viewFile/")
// 					.path(docId)
// 					.toUriString();  
// 		    map.put("downloadurl", fileDownloadUri);
//
// 	  }
//	    map.put("message", message);
//
//		return "message/success";
//    	
//    	
//    }
//    
//    @GetMapping("/viewFile/{docId}")
//	public void viewFile(@PathVariable String docId,HttpServletResponse response) throws IOException {
//		
//	  	DocumentDetails documentDetails = documentService.getDocumentById(docId);
//	  	
//	  	String folderPath = documentDetails.getPath();
//	  	String fileName = documentDetails.getDocument_name();
//	  	
//  		response.setHeader("Content-Disposition", "inline;filename=\"" + fileName + "\"");
//	 
//  		S3Object s3object = amazonS3.getObject(s3bucket, folderPath);
//  		S3ObjectInputStream inputStream = s3object.getObjectContent();
//		IOUtils.copy(inputStream, response.getOutputStream());
//
//  		
//	  	    
//	  	
//	}
}
