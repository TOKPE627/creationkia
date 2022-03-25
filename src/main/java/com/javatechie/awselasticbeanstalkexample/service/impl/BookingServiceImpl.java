package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Booking;
import com.javatechie.awselasticbeanstalkexample.domain.Product;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.repository.BookingRepository;
import com.javatechie.awselasticbeanstalkexample.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;
	@Override
	public Booking save(Booking booking) {
		return bookingRepository.save(booking);
	}

	@Override
	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}

	@Override
	public List<Booking> findByUser(User user) {
		return bookingRepository.findByUser(user);
	}

	@Override
	public Booking findByProductAndIpaddress(Product product, String ipaddress,String status) {
		return bookingRepository.findByProductAndIpaddress(product, ipaddress,status);
	}
	@Override
	public Booking update(Booking booking) {
		return bookingRepository.saveAndFlush(booking);
	}

	@Override
	public List<Booking> findHistoryBySeller(User seller, String status1, String status2) {
		return bookingRepository.findHistoryBySeller(seller, status1, status2);
	}

	@Override
	public List<Booking> findHistoryByCustomer(User customer, String status1, String status2) {
		return bookingRepository.findHistoryByCustomer(customer, status1, status2);
	}

	@Override
	public List<Booking> findBySeller(User seller, String status) {
		return bookingRepository.findBySeller(seller, status);
	}

	@Override
	public List<Booking> findByCustomer(User customer, String status) {
		return bookingRepository.findByCustomer(customer, status);
	}



	@Override
	public List<Booking> findByIpAddressAndStatus(String ipaddress, String status) {
		return bookingRepository.findByIpAddressAndStatus(ipaddress, status);
	}

	@Override
	public Booking findByIpAddressAndStatusAddedToCart(String ipaddress, Product product, String status) {
		return bookingRepository.findByIpAddressAndStatusAddedToCart(ipaddress, product, status);
	}

	@Override
	public void remove(Long id) {
		bookingRepository.deleteById(id);
	}

	@Override
	public Booking findByIpAddressAndStatusAndQuantityAndTotalPrice(String ipaddress, String status, int quantity,
			double total_price) {
		return bookingRepository.findByIpAddressAndStatusAndQuantityAndTotalPrice(ipaddress, status, quantity, total_price);
	}

	@Override
	public Booking findById(Long id) {
	   return bookingRepository.getById(id);
	}

	@Override
	public List<Booking> findBySellerAndCustomerAndStatus(User seller, User customer, String status) {
		return bookingRepository.findBySellerAndCustomerAndStatus(seller, customer, status);
	}

	@Override
	public Booking findByEmail(String email) {
		return bookingRepository.findByEmail(email);
	}



	





	




}
