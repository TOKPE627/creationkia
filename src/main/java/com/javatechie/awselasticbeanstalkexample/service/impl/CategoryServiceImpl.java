package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.repository.CategoryRepository;
import com.javatechie.awselasticbeanstalkexample.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public Category findById(Long id) {
		return categoryRepository.getById(id);
	}

	@Override
	public Category update(Category category) {
		return categoryRepository.saveAndFlush(category);
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Category> findByUser(User user) {
		return categoryRepository.findByUser(user);
	}

	@Override
	public void remove(Long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public Category findByTitle(String title) {
		return categoryRepository.findByTitle(title);
	}

	@Override
	public Category findById(int id) {
		return categoryRepository.findById(id);
	}

	@Override
	public List<Category> findAllBytTitle1Title2(String title1, String title2) {
		return categoryRepository.findAllBytTitle1Title2(title1, title2);
	}


}
