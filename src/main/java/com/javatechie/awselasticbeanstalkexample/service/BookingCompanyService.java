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

}
