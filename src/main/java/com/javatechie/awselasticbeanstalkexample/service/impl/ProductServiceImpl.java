package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.repository.ProductRepository;
import com.javatechie.awselasticbeanstalkexample.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	@Override
	public Product findById(Long id) {
		return productRepository.getById(id);
	}

	@Override
	public Product update(Product product) {
		return productRepository.saveAndFlush(product);
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> findByUser(User user) {
		return productRepository.findByUser(user);
	}


	@Override
	public List<Product> findAllByOrderByPublicationDateDesc() {
		return productRepository.findAllByOrderByPublicationDateDesc();
	}

	@Override
	public void remove(Long id) {
		productRepository.deleteById(id);
	}
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> findAllByCategory(String title) {
		return productRepository.findAllByCategory(title);
	}

	@Override
	public List<Product> findAllBySubCategory(String title, String titleSub) {
		return productRepository.findAllBySubCategory(title, titleSub);
	}

	@Override
	public List<Product> findByNameLike(String keyword) {
		return productRepository.findByNameContainingIgnoreCase(keyword);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

	@Override
	public List<Product> findByUserAndByCategory(User user, Category category) {
		return productRepository.findByUserAndByCategory(user, category);
	}




}
