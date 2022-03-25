package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.MailVisitor;
import com.javatechie.awselasticbeanstalkexample.repository.MailVisitorRepository;
import com.javatechie.awselasticbeanstalkexample.service.MailVisitorService;

@Service
public class MailVisitorServiceImpl implements MailVisitorService {

	@Autowired
	private MailVisitorRepository mailVisitorRepository;
	@Override
	public MailVisitor findById(Long id) {
		return mailVisitorRepository.getById(id);
	}

	@Override
	public MailVisitor update(MailVisitor mailVisitor) {
		return mailVisitorRepository.saveAndFlush(mailVisitor);
	}

	@Override
	public MailVisitor save(MailVisitor mailVisitor) {
		return mailVisitorRepository.save(mailVisitor);
	}

	@Override
	public List<MailVisitor> findAll() {
		return mailVisitorRepository.findAll();
	}


	@Override
	public void remove(Long id) {
		mailVisitorRepository.deleteById(id);
	}

	
}
