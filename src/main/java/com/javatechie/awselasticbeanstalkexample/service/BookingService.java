package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface BookingService {
	Booking save(Booking booking);
	Booking update(Booking booking);
	List<Booking> findAll();
	Booking findByEmail(String email);

	Booking  findByProductAndIpaddress(Product product, String ipaddress,String status);
	List<Booking> findByUser(User user);
	List<Booking>  findBySeller(User seller, String status);
	List<Booking> findByCustomer(User customer, String status);
	List<Booking> findBySellerAndCustomerAndStatus(User seller,User customer,String status);
	List<Booking> findHistoryBySeller(User seller,String status1, String status2);
	/*
	 * List<Booking> findBySellerAndCustomerEmail(User seller,String
	 * customerEmail,String status);
	 */
	
	List<Booking> findHistoryByCustomer(User customer,String status1, String status2);
	
	List<Booking> findByIpAddressAndStatus(String ipaddress, String status);
	Booking findByIpAddressAndStatusAddedToCart(String ipaddress, Product product,String status);
	void remove(Long id);
	Booking findByIpAddressAndStatusAndQuantityAndTotalPrice(String ipaddress,String status,int quantity,double total_price);
    Booking findById(Long id);
}
