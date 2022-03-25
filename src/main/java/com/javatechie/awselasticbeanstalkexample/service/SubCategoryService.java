package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.SubCategory;

public interface SubCategoryService {
	SubCategory findById(Long id);
	SubCategory update(SubCategory subCategory);
	SubCategory save(SubCategory ssubCategory);
	List<SubCategory> findAll();
	List<SubCategory> findByCategory(Category category);
	List<SubCategory> findAllByCategoryTitle(String title);
	void remove(Long id);
	
}
