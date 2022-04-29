package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.BookingCompany;
import com.javatechie.awselasticbeanstalkexample.domain.Company;
import com.javatechie.awselasticbeanstalkexample.domain.User;
import com.javatechie.awselasticbeanstalkexample.repository.BookingCompanyRepository;
import com.javatechie.awselasticbeanstalkexample.service.BookingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BookingCompanyServiceImpl implements BookingCompanyService{

    @Autowired
    private BookingCompanyRepository bookingCompanyRepository;

    @Override
    public BookingCompany save(BookingCompany bookingCompany) {
        return bookingCompanyRepository.save(bookingCompany);
    }

    @Override
    public BookingCompany update(BookingCompany bookingCompany) {
        return bookingCompanyRepository.saveAndFlush(bookingCompany);
    }

    @Override
    public List<BookingCompany> findAll() {
        return bookingCompanyRepository.findAll();
    }

    @Override
    public List<BookingCompany> findByUser(User user) {
        return bookingCompanyRepository.findByUser(user);
    }

    @Override
    public BookingCompany findByEmail(String email) {
        return bookingCompanyRepository.findByEmail(email);
    }

    @Override
    public void remove(Long id) {
        bookingCompanyRepository.deleteById(id);
    }

    @Override
    public BookingCompany findById(Long id) {
        return bookingCompanyRepository.getById(id);
    }

    @Override
    public List<BookingCompany> findByIpAddressAndStatus(String ipaddress, String status) {
        return bookingCompanyRepository.findByIpAddressAndStatus(ipaddress, status);
    }

    @Override
    public BookingCompany findByCompanyByIpaddressAndByStatus(Company company, String ipaddress, String status) {
        return bookingCompanyRepository.findByCompanyByIpaddressAndByStatus(company, ipaddress, status);
    }

  
    
}
