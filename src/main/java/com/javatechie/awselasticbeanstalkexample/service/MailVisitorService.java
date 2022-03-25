package com.javatechie.awselasticbeanstalkexample.service;

import java.util.List;

import com.javatechie.awselasticbeanstalkexample.domain.MailVisitor;

public interface MailVisitorService {
	MailVisitor findById(Long id);
	MailVisitor update(MailVisitor mailVisitor);
	MailVisitor save(MailVisitor mailVisitor);
	List<MailVisitor> findAll();
	void remove(Long id);
}
