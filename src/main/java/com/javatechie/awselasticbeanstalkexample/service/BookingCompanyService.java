package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.BookingCompany;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.User;

public interface BookingCompanyService {
    BookingCompany save(BookingCompany bookingCompany);
    BookingCompany update(BookingCompany bookingCompany);
    void remove(Long id);
    List<BookingCompany> findAll();
	List<BookingCompany> findByUser(User user);  
	BookingCompany findByEmail(String email);
    BookingCompany findById(Long id);
	List<BookingCompany> findByIpAddressAndStatus(String ipaddress, String status);
	BookingCompany  findByCompanyByIpaddressAndByStatus(Company company, String ipaddress,String status);

	List<BookingCompany>  findBySeller(User seller, String status);
	List<BookingCompany> findByCustomer(User customer, String status);
    List<BookingCompany> findBySellerAndCustomerAndStatus(User seller,User customer,String status);
	List<BookingCompany> findHistoryBySeller(User seller,String status);
	List<BookingCompany> findHistoryByCustomer(User customer,String status);
}
