package com.javatechie.awselasticbeanstalkexample.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="product_galery")
@Entity(name="product_galery")
public class ProductGalery implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy="galery")
	@JsonIgnore()
	private List<Product> products;
	
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	public ProductGalery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductGalery(Long id, List<Product> products, String image1, String image2, String image3, String image4) {
		super();
		this.id = id;
		this.products = products;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.image4 = image4;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getImage4() {
		return image4;
	}
	public void setImage4(String image4) {
		this.image4 = image4;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
