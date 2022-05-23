package com.javatechie.awselasticbeanstalkexample.service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import com.javatechie.awselasticbeanstalkexample.controller.fileManager.RestUploadController;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;
    
    
    @Value("${application.bucket.user}")
    private String bucketUser;
   

    @Value("${application.bucket.company}")
    private String bucketCompany;
    
    @Value("${application.bucket.product}")
    private String bucketProduct;
    
    
    @Value("${application.bucket.shop}")
    private String bucketShop;
    
    @Value("${application.bucket.groupsale}")
    private String bucketGroupSale;
    
    @Value("${application.bucket.advertise}")
    private String bucketAdvertise;
    
    @Value("${application.bucket.catalog}")
    private String bucketCatalog;
    
    @Value("${application.bucket.partner}")
    private String bucketPartner;

    @Autowired
    private AmazonS3 s3Client;
    
	private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);

    public String uploadRestFileInAws(String bucketFolder, Long idGalery, MultipartFile file)  throws IOException{
        File fileObj = convertMultiPartFileToFile(file);
        String fileName =  idGalery + "/" +file.getOriginalFilename();                

        if(bucketFolder.equals(AppConstants.bucket_groupsale)) {
       	   s3Client.putObject(new PutObjectRequest(bucketGroupSale, fileName, fileObj));
        }

        if(bucketFolder.equals(AppConstants.bucket_company)) {
        	   s3Client.putObject(new PutObjectRequest(bucketCompany, fileName, fileObj));
        }
        
       if(bucketFolder.equals(AppConstants.bucket_product)) {
      	   s3Client.putObject(new PutObjectRequest(bucketProduct, fileName, fileObj));
       }

       if(bucketFolder.equals(AppConstants.bucket_shop)) {
    	   s3Client.putObject(new PutObjectRequest(bucketShop, fileName, fileObj));
    }
       if(bucketFolder.equals(AppConstants.bucket_advertise)) {
      	   s3Client.putObject(new PutObjectRequest(bucketAdvertise, fileName, fileObj));
       }
       if(bucketFolder.equals(AppConstants.bucket_catalog)) {
      	   s3Client.putObject(new PutObjectRequest(bucketCatalog, fileName, fileObj));
       }
       if(bucketFolder.equals(AppConstants.bucket_partner)) {
        s3Client.putObject(new PutObjectRequest(bucketPartner, fileName, fileObj));
  }
        fileObj.delete();
        return "File uploaded : " + fileName;
    }
    
    public boolean deleteFileInAws(String bucketFolder, Long idGalery ,String fileName) {
   
        if(bucketFolder.equals(AppConstants.bucket_groupsale)) {
            s3Client.deleteObject(bucketGroupSale, fileName);
            logger.debug("File deleted successfully!");
            System.out.println("File Deleted successfully!");
            return true;
        }
        return false;
    }
    
    
    
    
    public String uploadFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        //String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        //System.out.println(fileName);
        String fileName = file.getOriginalFilename();
        System.out.println("File name " + fileName);
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File uploaded : " + fileName;
    }
    

    
    public int checkFilesInFolderInAws(String typeGalery ,Long id, String directory) {
        ObjectListing objectListing = null;
    	if(directory.equals(AppConstants.bucket_company)) {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                    .withBucketName(bucketCompany)
                    .withPrefix(id+"/"+typeGalery+"/");

            objectListing = s3Client.listObjects(listObjectsRequest);
        }
    	if(directory.equals(AppConstants.bucket_product)) {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                    .withBucketName(bucketProduct)
                    .withPrefix(id+"/"+typeGalery+"/");

            objectListing = s3Client.listObjects(listObjectsRequest);
        }
    	if(directory.equals(AppConstants.bucket_groupsale)) {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                    .withBucketName(bucketGroupSale)
                    .withPrefix(id+"/"+typeGalery+"/");

            objectListing = s3Client.listObjects(listObjectsRequest);
        }
		return objectListing.getObjectSummaries().size();
    }
    public boolean deleteFolderInAws(String bucketFolder, Long idGalery, String fileName) {
        if(bucketFolder.equals(AppConstants.bucket_company)) {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                    .withBucketName(bucketCompany)
                    .withPrefix(idGalery+"/"+ fileName);

            ObjectListing objectListing = s3Client.listObjects(listObjectsRequest);

            while (true) {
                for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                    s3Client.deleteObject(bucketCompany, objectSummary.getKey());
                }
                if (objectListing.isTruncated()) {
                    objectListing = s3Client.listNextBatchOfObjects(objectListing);
                } else {
                    break;
                }
            }
            System.out.println("Folder Deleted successfully!");
            return true;
        }
        if(bucketFolder.equals(AppConstants.bucket_product)) {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                    .withBucketName(bucketCompany)
                    .withPrefix(idGalery+"/"+ fileName +"/");

            ObjectListing objectListing = s3Client.listObjects(listObjectsRequest);

            while (true) {
                for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                    s3Client.deleteObject(bucketProduct, objectSummary.getKey());
                }
                if (objectListing.isTruncated()) {
                    objectListing = s3Client.listNextBatchOfObjects(objectListing);
                } else {
                    break;
                }
            }
            System.out.println("Folder Deleted successfully!");
            return true;
        }
        if(bucketFolder.equals(AppConstants.bucket_groupsale)) {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                    .withBucketName(bucketGroupSale)
                    .withPrefix(idGalery+"/"+ fileName);

            ObjectListing objectListing = s3Client.listObjects(listObjectsRequest);

            while (true) {
                for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                    s3Client.deleteObject(bucketGroupSale, objectSummary.getKey());
                }
                if (objectListing.isTruncated()) {
                    objectListing = s3Client.listNextBatchOfObjects(objectListing);
                } else {
                    break;
                }
            }
            System.out.println("Folder Deleted successfully!");
            return true;
        }
        return false;
    }
    
    
    public String uploadGaleryFileTEst(MultipartFile file,String directory)  throws IOException{
        File fileObj = convertMultiPartFileToFile(file);
        String fileName =  null;        

        if(directory.equals(AppConstants.bucket_company)) {
              fileName =  file.getOriginalFilename();        
        	   s3Client.putObject(new PutObjectRequest(bucketCompany, fileName, fileObj));
        }
  
        fileObj.delete();
        return "File uploaded : " + fileName;
    }
    
    
    public String uploadGaleryFile(MultipartFile file,String typeGalery ,Long id,String directory)  throws IOException{
        File fileObj = convertMultiPartFileToFile(file);
        String fileName =  id + "/" + typeGalery +"/" +file.getOriginalFilename();                

        if(directory.equals(AppConstants.bucket_groupsale)) {
       	   s3Client.putObject(new PutObjectRequest(bucketGroupSale, fileName, fileObj));
        }

        if(directory.equals(AppConstants.bucket_company)) {
        	   s3Client.putObject(new PutObjectRequest(bucketCompany, fileName, fileObj));
        }
        
       if(directory.equals(AppConstants.bucket_product)) {
      	   s3Client.putObject(new PutObjectRequest(bucketProduct, fileName, fileObj));
       }

        fileObj.delete();
        return "File uploaded : " + fileName;
    }
    
    public String uploadFileInAWSDirectory(MultipartFile file,Long id,String directory)  throws IOException{
        File fileObj = convertMultiPartFileToFile(file);
        //String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String   fileName =  id + "/" +file.getOriginalFilename();        
      
        if(directory.equals(AppConstants.bucket_user)) {
            	s3Client.putObject(new PutObjectRequest(bucketUser, fileName, fileObj));	            

        }
   
        fileObj.delete();
        return "File uploaded : " + fileName;
    }
    
    



    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        System.out.println(s3Object);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String display(String fileName) {
    	  S3Object s3Object = s3Client.getObject(bucketName, fileName);
		 	 String imgSrc = "https://" + bucketName + ".s3.us-east-2.amazonaws.com/"+fileName;
           return imgSrc;
     }

    
    
    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("Error converting multipartFile to file" +e);
        	//log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
