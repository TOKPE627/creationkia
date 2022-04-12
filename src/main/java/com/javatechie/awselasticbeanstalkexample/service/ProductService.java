package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.SubCategory;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface ProductService {
	Product findById(Long id);
    Product update(Product product);
    Product save(Product product);
	void remove(Long id);
	List<Product> findAll();
    List<Product> findByUser(User user);
    List<Product> findByUserAndByCategory(User user, Category category);

    List<Product> findAllByOrderByPublicationDateDesc();
    
    List<Product> findAllByCategory(String title);
    List<Product> findByCategory(Category category);
    List<Product> findAllBySubCategory(String title, String titleSub);
    List<Product> findBySubCategory(SubCategory subCategory);
    List<Product> findByNameLike(String keyword);

   }
