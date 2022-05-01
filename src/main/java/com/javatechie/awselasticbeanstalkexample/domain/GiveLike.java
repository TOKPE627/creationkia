package com.javatechie.awselasticbeanstalkexample.domain;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Table(name="give_like")
@Entity(name="give_like")
public class GiveLike implements Serializable{
    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = { CascadeType.REMOVE})
	@JoinColumn(name = "company_id")
	private Company company;
	private Integer current;



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Integer getCurrent() {
		return current;
	}
	public void setCurrent(Integer current) {
		this.current = current;
	}
	
	

	
	
}
