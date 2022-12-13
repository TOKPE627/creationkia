package com.javatechie.awselasticbeanstalkexample.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
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
	
	@Column(columnDefinition="text")
	private String image1;
	
	@Column(columnDefinition="text")
	private String image2;
	
	@Column(columnDefinition="text")
	private String image3;
	
	@Column(columnDefinition="text")
	private String image4;
	
	@Column(columnDefinition="text")
	private String image5;
	
	@Column(columnDefinition="text")
    private String image6;
    
    @Column(columnDefinition="text")
    private String image7;
	public ProductGalery() {
		super();
		// TODO Auto-generated constructor stub
	}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
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
    public String getImage5() {
        return image5;
    }
    public void setImage5(String image5) {
        this.image5 = image5;
    }
    public String getImage6() {
        return image6;
    }
    public void setImage6(String image6) {
        this.image6 = image6;
    }
    public String getImage7() {
        return image7;
    }
    public void setImage7(String image7) {
        this.image7 = image7;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

	
	
	
}
