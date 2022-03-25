package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javatechie.awselasticbeanstalkexample.domain.Category;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	List<Category> findAll();
	Category findById(int id);
	List<Category> findByUser(User user);
	@Query("SELECT c from category c WHERE c.title  = :title")
	Category findByTitle(String title);
	
	@Query("SELECT c from category c WHERE c.title = :title1 AND c.title = :title2")
	List<Category> findAllBytTitle1Title2(String title1, String title2);
}
