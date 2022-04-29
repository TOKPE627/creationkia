package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.BookingCompany;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingCompanyRepository extends JpaRepository<BookingCompany,Long> {
    List<BookingCompany> findAll();
	List<BookingCompany> findByUser(User user);  
	BookingCompany findByEmail(String email);
		

	@Query("SELECT b from booking_company b WHERE  b.company= :company AND b.ipaddress = :ipaddress AND b.status= :status")
	BookingCompany  findByCompanyByIpaddressAndByStatus(Company company, String ipaddress,String status);
	
	@Query("SELECT b from booking_company b WHERE b.ipaddress = :ipaddress AND b.status= :status")
	List<BookingCompany> findByIpAddressAndStatus(String ipaddress, String status);
}
