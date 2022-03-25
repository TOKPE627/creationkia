package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>{
	List<SubCategory> findAll();
	
	@Query("SELECT s from sub_category s WHERE s.category  = :category")
	List<SubCategory> findByCategory(Category category);	
	
	@Query("SELECT s from sub_category s WHERE s.category.title  = :title")
    List<SubCategory> findAllByCategoryTitle(String title);
	

}
