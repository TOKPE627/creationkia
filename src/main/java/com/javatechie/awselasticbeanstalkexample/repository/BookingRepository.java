package com.javatechie.awselasticbeanstalkexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	List<Booking> findAll();
	List<Booking> findByUser(User user);
    
	Booking findByEmail(String email);
  

	@Query("SELECT b from booking b WHERE  b.product= :product AND b.ipaddress = :ipaddress AND b.status= :status")
	Booking  findByProductAndIpaddress(Product product, String ipaddress,String status);
	
	@Query("SELECT b from booking b WHERE  b.product.user= :seller AND b.status = :status")
	List<Booking>  findBySeller(User seller, String status);
	
	@Query("SELECT b from booking b WHERE b.user = :customer AND b.status = :status")
	List<Booking> findByCustomer(User customer, String status);

	
	@Query("SELECT b from booking b WHERE b.product.user= :seller AND b.user = :customer AND b.status = :status")
	List<Booking> findBySellerAndCustomerAndStatus(User seller,User customer,String status);
	
	
	@Query("SELECT b from booking b WHERE b.product.user = :seller AND (b.status = :status1 or b.status= :status2)")
	List<Booking> findHistoryBySeller(User seller,String status1, String status2);
	
	
	@Query("SELECT b from booking b WHERE b.user = :customer AND (b.status = :status1 or b.status= :status2)")
	List<Booking> findHistoryByCustomer(User customer,String status1, String status2);
	
	@Query("SELECT b from booking b WHERE b.ipaddress = :ipaddress AND b.status= :status")
	List<Booking> findByIpAddressAndStatus(String ipaddress, String status);
	

	@Query("SELECT b from booking b WHERE b.ipaddress = :ipaddress AND b.product= :product  AND b.status= :status")
	Booking findByIpAddressAndStatusAddedToCart(String ipaddress, Product product,String status);

	@Query("SELECT b from booking b WHERE b.ipaddress = :ipaddress AND b.status= :status AND b.quantity= :quantity AND b.total_price=:total_price")
	Booking findByIpAddressAndStatusAndQuantityAndTotalPrice(String ipaddress,String status,int quantity,double total_price);
	
}
