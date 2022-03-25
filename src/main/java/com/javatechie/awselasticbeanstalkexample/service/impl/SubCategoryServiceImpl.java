package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.SubCategory;
import com.javatechie.awselasticbeanstalkexample.repository.SubCategoryRepository;
import com.javatechie.awselasticbeanstalkexample.service.SubCategoryService;
@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRepository;
	@Override
	public SubCategory findById(Long id) {
		return subCategoryRepository.getById(id);
	}

	@Override
	public SubCategory update(SubCategory subCategory) {
		return subCategoryRepository.saveAndFlush(subCategory);
	}

	@Override
	public SubCategory save(SubCategory subCategory) {
		return subCategoryRepository.save(subCategory);
	}

	@Override
	public List<SubCategory> findAll() {
		return subCategoryRepository.findAll();
	}

	@Override
	public void remove(Long id) {
		subCategoryRepository.deleteById(id);
	}

	@Override
	public List<SubCategory> findByCategory(Category category) {
		return subCategoryRepository.findByCategory(category);
	}

	@Override
	public List<SubCategory> findAllByCategoryTitle(String title) {
		return subCategoryRepository.findAllByCategoryTitle(title);
	}
	


}
