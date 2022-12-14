package com.javatechie.awselasticbeanstalkexample.domain;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer { 
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    
     private String lastname;
     private String firstname;
     private String email;
    
     private String shippingAddressCountry;
     private String shippingAddressState;
     private String shippingAddressCity;
     
     @Column(columnDefinition="text")
     private String shippingAddressStreet;
     
     @Column(columnDefinition="text")
     private String shippingAddressZipCode;
     
     private String billingAddressCountry;
     private String billingAddressState;
     private String billingAddressCity;
     
     @Column(columnDefinition="text")
     private String billingAddressStreet;
     
     @Column(columnDefinition="text")
     private String billingAddressZipCode;
     
     @Column(columnDefinition="text")
     private String phoneNumber;
     
     @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
     private List<OrderCustomer> orders = new ArrayList<>();
     
    public Customer() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public Customer(String lastname, String firstname, String email, String shippingAddressCountry,
            String shippingAddressState, String shippingAddressCity, String shippingAddressStreet,
            String shippingAddressZipCode, String billingAddressCountry, String billingAddressState,
            String billingAddressCity, String billingAddressStreet, String billingAddressZipCode) {
        super();
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.shippingAddressCountry = shippingAddressCountry;
        this.shippingAddressState = shippingAddressState;
        this.shippingAddressCity = shippingAddressCity;
        this.shippingAddressStreet = shippingAddressStreet;
        this.shippingAddressZipCode = shippingAddressZipCode;
        this.billingAddressCountry = billingAddressCountry;
        this.billingAddressState = billingAddressState;
        this.billingAddressCity = billingAddressCity;
        this.billingAddressStreet = billingAddressStreet;
        this.billingAddressZipCode = billingAddressZipCode;
    }
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getShippingAddressCountry() {
        return shippingAddressCountry;
    }
    public void setShippingAddressCountry(String shippingAddressCountry) {
        this.shippingAddressCountry = shippingAddressCountry;
    }
    public String getShippingAddressState() {
        return shippingAddressState;
    }
    public void setShippingAddressState(String shippingAddressState) {
        this.shippingAddressState = shippingAddressState;
    }
    public String getShippingAddressCity() {
        return shippingAddressCity;
    }
    public void setShippingAddressCity(String shippingAddressCity) {
        this.shippingAddressCity = shippingAddressCity;
    }
    public String getShippingAddressStreet() {
        return shippingAddressStreet;
    }
    public void setShippingAddressStreet(String shippingAddressStreet) {
        this.shippingAddressStreet = shippingAddressStreet;
    }
    public String getShippingAddressZipCode() {
        return shippingAddressZipCode;
    }
    public void setShippingAddressZipCode(String shippingAddressZipCode) {
        this.shippingAddressZipCode = shippingAddressZipCode;
    }
    public String getBillingAddressCountry() {
        return billingAddressCountry;
    }
    public void setBillingAddressCountry(String billingAddressCountry) {
        this.billingAddressCountry = billingAddressCountry;
    }
    public String getBillingAddressState() {
        return billingAddressState;
    }
    public void setBillingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
    }
    public String getBillingAddressCity() {
        return billingAddressCity;
    }
    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }
    public String getBillingAddressStreet() {
        return billingAddressStreet;
    }
    public void setBillingAddressStreet(String billingAddressStreet) {
        this.billingAddressStreet = billingAddressStreet;
    }
    public String getBillingAddressZipCode() {
        return billingAddressZipCode;
    }
    public void setBillingAddressZipCode(String billingAddressZipCode) {
        this.billingAddressZipCode = billingAddressZipCode;
    }
  
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void add(OrderCustomer order){
        if(order != null){
            if(orders == null){
                orders = new ArrayList<>();
            }
            orders.add(order);
            order.setCustomer(this);
        }
    }
 
 
}
