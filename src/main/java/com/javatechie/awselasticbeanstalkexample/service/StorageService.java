package com.javatechie.awselasticbeanstalkexample.service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StorageService {
    
    @Value("${application.bucket.category}")
    private String bucketCategory;
    
//
//    @Value("${application.bucket.product}")
//    private String bucketProduct;    

    @Autowired
    private AmazonS3 s3Client;
    
	private final Logger logger = LoggerFactory.getLogger(StorageService.class);


    public String uploadRestFileInAws(String bucketFolder, Long idGalery, MultipartFile file)  throws IOException{
        File fileObj = convertMultiPartFileToFile(file);
        String fileName =  idGalery + "/" +file.getOriginalFilename();                

        if(bucketFolder.equals(AppConstants.bucket_category)) {
           s3Client.putObject(new PutObjectRequest(bucketCategory, fileName, fileObj));
        }

       
//       if(bucketFolder.equals(AppConstants.bucket_product)) {
//           s3Client.putObject(new PutObjectRequest(bucketProduct, fileName, fileObj));
//       }

     
        fileObj.delete();
        return "File uploaded : " + fileName;
    }
    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("Error converting multipartFile to file" +e);
        }
        return convertedFile;
    }
}
