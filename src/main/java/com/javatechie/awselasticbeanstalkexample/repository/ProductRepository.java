package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.SubCategory;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface ProductRepository extends JpaRepository<Product, Long>{
  List<Product> findByUser(User user);
  
  @Query("SELECT p from product p WHERE p.user = :user AND p.category =:category")
  List<Product> findByUserAndByCategory(User user, Category category);

  List<Product> findAllByOrderByPublicationDateDesc();

  @Query("SELECT p from product p WHERE p.subCategory.category.title  = :title")
  List<Product> findAllByCategory(String title);
  
  @Query("SELECT p from product p WHERE p.subCategory.category.title = :title AND p.subCategory.title =:titleSub")
  List<Product> findAllBySubCategory(String title, String titleSub);
  
  List<Product> findByNameContainingIgnoreCase(String keyword);
  
  List<Product> findByCategory(Category category);

  public List<Product> findBySubCategory(SubCategory subCategory);
}
