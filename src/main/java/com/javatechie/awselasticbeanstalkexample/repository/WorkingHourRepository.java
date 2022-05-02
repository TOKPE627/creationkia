package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.domain.WorkingHour;

public interface WorkingHourRepository  extends JpaRepository<WorkingHour, Long>{
	List<WorkingHour> findAll();
	List<WorkingHour> findByUser(User user);
	
	@Query("SELECT w from workinghour w WHERE w.day1  = :day or w.day2  = :day or w.day3  = :day or w.day4  = :day or w.day5  = :day or w.day6  = :day or w.day7  = :day")
	List<WorkingHour> findByDay(String day);
	/*
	 * @Query("SELECT w from workinghour w WHERE (w.user= :user_id and w.day1= :day) or (w.user.id= :user_id and w.day2= :day) or (w.user.id= :user_id and w.day3= :day) or (w.user.id= :user_id and w.day4= :day) or (w.user.id= :user_id and w.day5= :day) or (w.user.id= :user_id and w.day6= :day) or (w.user.id= :user_id and w.day7= :day)"
	 * ) List<WorkingHour> findByUserByDay(Long user_id,String day);
	 */
	
	
	  @Query("SELECT w from workinghour w WHERE (w.user= :user and w.day1= :day) or (w.user= :user and w.day2= :day) or (w.user= :user and w.day3= :day)  or (w.user= :user and w.day4= :day) or (w.user= :user and w.day5= :day) or (w.user= :user and w.day6= :day) or (w.user= :user and w.day7= :day)")
	  List<WorkingHour> findByUserByDay(User user,String day);
}
