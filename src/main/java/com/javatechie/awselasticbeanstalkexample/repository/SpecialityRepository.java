package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javatechie.awselasticbeanstalkexample.domain.Speciality;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface SpecialityRepository extends JpaRepository<Speciality, Long>{
	
	List<Speciality> findAll();
	
	List<Speciality>  findByUser(User user);

	@Query(nativeQuery = true, value="SELECT * from speciality s WHERE s.user_id  = :userId ORDER BY s.id ASC limit 4")
    List<Speciality> findTop4ByUser(@Param("userId") Long userId);
}
