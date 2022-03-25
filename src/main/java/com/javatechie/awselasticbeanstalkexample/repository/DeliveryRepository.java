package com.javatechie.awselasticbeanstalkexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javatechie.awselasticbeanstalkexample.domain.Delivery;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>{
	  //Delivery findByCustomer(User user);
	@Query("SELECT d from delivery d WHERE  d.customer= :customer AND d.seller= :seller  AND d.payment_status = :payment_status")
	 Delivery findByCustomerAndPaymentStatus(User customer, User seller, String payment_status);

}
