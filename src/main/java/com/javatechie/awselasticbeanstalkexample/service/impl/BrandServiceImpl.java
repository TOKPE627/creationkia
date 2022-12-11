package com.javatechie.awselasticbeanstalkexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.awselasticbeanstalkexample.domain.Brand;
import com.javatechie.awselasticbeanstalkexample.repository.BrandRepository;
import com.javatechie.awselasticbeanstalkexample.service.BrandService;


@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;
    
    
    @Override
    public Brand add(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(Brand brand) {
       return brandRepository.saveAndFlush(brand);
    }

    @Override
    public void delete(Long id){
        Brand brand = this.findById(id);
        if(brand !=null) {
            brandRepository.deleteById(id);
        }
    }
    
    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findById(Long id)  {
        return brandRepository.getById(id);

    }

    @Override
    public Brand findByName(String name) {
        return brandRepository.findByName(name);
    }

   

}
