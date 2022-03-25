package com.javatechie.awselasticbeanstalkexample.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="delivery")
@Entity(name = "delivery")
public class Delivery implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "seller_id")
	private User seller;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User customer;
	
	private double delivery_price;
	
	private double final_price;
	
	private String payment_status="not_payed";
	
	public Delivery() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getSeller() {
		return seller;
	}
	public void setSeller(User seller) {
		this.seller = seller;
	}
	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}
	public double getDelivery_price() {
		return delivery_price;
	}
	public void setDelivery_price(double delivery_price) {
		this.delivery_price = delivery_price;
	}
	public double getFinal_price() {
		return final_price;
	}
	public void setFinal_price(double final_price) {
		this.final_price = final_price;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	
	
	
	
	
	
	
	
}
	