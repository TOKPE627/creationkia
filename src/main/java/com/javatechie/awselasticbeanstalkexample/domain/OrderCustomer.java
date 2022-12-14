package com.javatechie.awselasticbeanstalkexample.domain;

import org.hibernate.annotations.CreationTimestamp;

import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderTrackingNumber;
    private int totalQuantity;
    private double totalAmount;
    private String status= AppConstants.ORDER_STATUS_0;

   
    private String dateCreated;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "orderCustomer")
    private List<OrderItemCustomer> orderItems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItemCustomer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemCustomer> orderItems) {
        this.orderItems = orderItems;
    }
   
   


  

}
