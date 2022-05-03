package com.javatechie.awselasticbeanstalkexample.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Table(name="catalog")
@Entity(name="catalog")
public class Catalog  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
 
	private String name;
	private String image;
	private Double price;//price
	
	@Column(columnDefinition="text")
	private String detail;

	public Catalog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Catalog(Long id, User user, String name, String image, Double price, String detail) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.image = image;
		this.price = price;
		this.detail = detail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	@Transient
	public String getImagePath() {
		if(id==null || image == null) return null;
		return AppConstants.catalogPath + id + "/" + image;
	}
	
	
}