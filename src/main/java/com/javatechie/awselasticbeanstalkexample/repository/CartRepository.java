package com.javatechie.awselasticbeanstalkexample.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javatechie.awselasticbeanstalkexample.domain.Cart;
import com.javatechie.awselasticbeanstalkexample.domain.Product;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findById(Long id);
      
    Cart  findByProductAndIpaddress(Product product, String ipaddress);
    
    List<Cart> findByIpaddress(String ipaddress);
}
