package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amazonaws.services.customerprofiles.model.Gender;
import com.javatechie.awselasticbeanstalkexample.domain.Brand;
import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.Style;
import com.javatechie.awselasticbeanstalkexample.domain.Univers;

public interface ProductRepository extends JpaRepository<Product, Long>{
    Product findByName(String name);
    List<Product> findByCategory(Category category);
    @Query("SELECT p from product p where p.id != :product_id AND p.category =:category")
    List<Product> findOthersOfCategory(Long product_id, Category category);
        
     List<Product> findByBrand(Brand brand);
     List<Product> findByGender(Gender gender);
     List<Product> findByUnivers(Univers univers);
     List<Product> findByStyle(Style style);
}
