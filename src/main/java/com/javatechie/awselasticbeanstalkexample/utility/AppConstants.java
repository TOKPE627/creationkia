package com.javatechie.awselasticbeanstalkexample.utility;

import org.springframework.beans.factory.annotation.Value;

public class AppConstants {
	
   public static String APP_NAME="attoly";
   public static final String ROLE_1 = "ROLE_ADMIN";//Admin
   public static final String ROLE_2 = "ROLE_STOREKEEPER";
   public static final String ROLE_3 = "ROLE_SERVICE";
   public static final String ROLE_4 = "ROLE_CUSTOMER";
   
   //
   public static String companyImage= "companyimage";
   public static String geolocate1= "geolocate1";
   public static String geolocate2= "geolocate2";
   public static String img1="image1";
   public static String img2="image2";
   public static String img3="image3";
   public static String img4="image4";
   
   //AWS Directories Paths prefixes
   
   public static final String bucket_user      =  "user";
   public static final String bucket_company   =  "company";
   public static final String bucket_product   =  "product";
   public static final String bucket_shop      =  "shop";
   public static final String bucket_groupsale =  "groupsale";
   public static final String bucket_advertise =  "advertise";
   public static final String bucket_catalog   =  "catalog";

   //localhostUrl
   //public static String url="http://localhost:8080/";
   //awsUrl
   
   public static String url="http://attoly-env.eba-9tajrzrg.us-east-1.elasticbeanstalk.com/";
   
//   @Value("${application.bucket.user}")
//   public static String awsBucketUser;
   public static String awsBucketIcon="https://attolyicon.s3.us-east-1.amazonaws.com/";
    public static String awsBucketUser="https://attolyuser.s3.us-east-1.amazonaws.com/";
    public static String awsBucketCompany="https://attolycompany.s3.us-east-1.amazonaws.com/";
    public static String awsBucketProduct="https://attolyproduct.s3.us-east-1.amazonaws.com/";
    public static String awsBucketShop ="https://attolyshop.s3.us-east-1.amazonaws.com/";
    public static String awsBucketGroupSale="https://attolygroupsale.s3.us-east-1.amazonaws.com/";
    public static String awsBucketAdvertise="https://attolyad.s3.us-east-1.amazonaws.com/";
    public static String awsBucketCatalog ="https://attolycatalog.s3.us-east-1.amazonaws.com/";

//   @Value("${application.bucket.product}")
//   public static String awsBucketProduct;

   
   //Week DAYS
   public static final String DAY_1 = "Lundi";
   public static final String DAY_2 = "Mardi";
   public static final String DAY_3 = "Mercredi";
   public static final String DAY_4 = "Jeudi";
   public static final String DAY_5 = "Vendredi";
   public static final String DAY_6 = "Samedi";
   public static final String DAY_7 = "Dimanche";

   
   //ORDER_STATUS
   public static final String ORDER_STATUS_ADDED_TO_CART = "ADDED_TO_CART";
   public static final String ORDER_STATUS_0 = "BEGIN";
   public static final String ORDER_STATUS_1 = "PENDING";
   public static final String ORDER_STATUS_2 = "DELIVERED";
   public static final String ORDER_STATUS_3 = "FAILED";
	   
	
   //CATEGORY
   public static final String CATEGORY_GROUPSALE = "groupSale";
   public static final String CATEGORY_SHOP    = "shop";
   public static final String CATEGORY_SERVICE = "service";
   public static final String CATEGORY_STORE   = "store";

   //SUB_CATEGORIES OF SHOP
   public static final String SUB_SHOP_1    = "supermarket";
   public static final String SUB_SHOP_2    = "healthBeauty";
   public static final String SUB_SHOP_3    = "phone";
   public static final String SUB_SHOP_4    = "electronics";
   public static final String SUB_SHOP_5    = "informatics";
   public static final String SUB_SHOP_6    = "homeAppliances";
   public static final String SUB_SHOP_7    = "cloths";
   public static final String SUB_SHOP_8    = "hobbies";
   public static final String SUB_SHOP_9    = "other";

   //SUB_CATEGORIES OF SERVICE
   public static final String SUB_SERVICE_1    = "sewing";
   public static final String SUB_SERVICE_2    = "hairdressing";
   public static final String SUB_SERVICE_3    = "carpentry";
   public static final String SUB_SERVICE_4    = "groceries";
   public static final String SUB_SERVICE_5    = "mechanical";
   public static final String SUB_SERVICE_6    = "repair";
   public static final String SUB_SERVICE_7   = "other";

   //SUB_CATEGORIES OF LIBRARY
   public static final String SUB_LIBRARY_1    = "comic";
   public static final String SUB_LIBRARY_2    = "science";
   public static final String SUB_LIBRARY_3    = "technology";
   public static final String SUB_LIBRARY_4    = "math";
   
   //SUB-CATEGORY OF STORE
   public static final String SUB_STORE_1    = "agro";
   public static final String SUB_STORE_2    = "breeding";


   //public static String imagePath="https://attolystorage.s3.us-east-2.amazonaws.com/1641297952199_library.png";
   //public static String pathImg="/src/main/resources/static";
   //public static String pathImg ="/home/tokpe/development/jee/attol/src/main/resources/static";
   
   //AWS

   //Path
   public static final String userPath      =  "/user/";
   public static final String adPath        =  "/ad/";
   public static final String companyPath   =  "/company/";
   public static final String storePath     =  "/store/";
   public static final String productPath   =  "/product/";
   public static final String catalogPath   =  "/catalog/";

  //public static  final String FILE_UPLOAD_USER_PATH="src/main/resources/static/image/user/";
   public static  final String FILE_UPLOAD_USER_PATH=      "/user/";
   public static  final String FILE_UPLOAD_AD_PATH=        "/ad/";
  public static  final String FILE_UPLOAD_COMPANY_PATH=    "/company/";
  public static  final String FILE_UPLOAD_STORE_PATH=      "/store/";
  public static  final String FILE_UPLOAD_PRODUCT_PATH=    "/product/";
  public static  final String FILE_UPLOAD_CATALOG_PATH=    "/catalog/";
  public static  final String FILE_UPLOAD_ADVERTISE_PATH=  "/advertise/";

  //File too large
  public static final String error_max_size="Fichiers téléversés trop lourds.La  taille totale des fichiers ne doit pas dépasser 1024 Mégabites";
  
}
