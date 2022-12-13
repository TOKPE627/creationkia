package com.javatechie.awselasticbeanstalkexample.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Table(name="product")
@Entity(name="product")
public class Product  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	  @Id
	     @GeneratedValue(strategy = GenerationType.IDENTITY)
	     private Long id;
	     @Column(columnDefinition="text")
	     private String name;
	     private Double price;
	     private Double oldPrice;
	     private int availableQuantity;  
	     @JsonBackReference(value = "category")
	     @ManyToOne
	     private Category category;

	     private String c1;
	     private String c2;
	     private String c3;
	     private String c4;
	     private String c5;
	    
	     private String d1; 
	     private String d2; 
	     private String d3;
	     private String d4;
	     private String d5; 
	   
	     
	     @ManyToOne(cascade = { CascadeType.REMOVE})
	     @JoinColumn(name = "galery_id")
	     private ProductGalery galery;
	     
	     @OneToOne
	     private Brand brand;
	     
	     @OneToOne
	     private Style style ;
	     
	     @OneToOne
	     private Univers univers;
	     
	     private String gender;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(Double oldPrice) {
            this.oldPrice = oldPrice;
        }

        public int getAvailableQuantity() {
            return availableQuantity;
        }

        public void setAvailableQuantity(int availableQuantity) {
            this.availableQuantity = availableQuantity;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public String getC1() {
            return c1;
        }

        public void setC1(String c1) {
            this.c1 = c1;
        }

        public String getC2() {
            return c2;
        }

        public void setC2(String c2) {
            this.c2 = c2;
        }

        public String getC3() {
            return c3;
        }

        public void setC3(String c3) {
            this.c3 = c3;
        }

        public String getC4() {
            return c4;
        }

        public void setC4(String c4) {
            this.c4 = c4;
        }

        public String getC5() {
            return c5;
        }

        public void setC5(String c5) {
            this.c5 = c5;
        }

        public String getD1() {
            return d1;
        }

        public void setD1(String d1) {
            this.d1 = d1;
        }

        public String getD2() {
            return d2;
        }

        public void setD2(String d2) {
            this.d2 = d2;
        }

        public String getD3() {
            return d3;
        }

        public void setD3(String d3) {
            this.d3 = d3;
        }

        public String getD4() {
            return d4;
        }

        public void setD4(String d4) {
            this.d4 = d4;
        }

        public String getD5() {
            return d5;
        }

        public void setD5(String d5) {
            this.d5 = d5;
        }

        public ProductGalery getGalery() {
            return galery;
        }

        public void setGalery(ProductGalery galery) {
            this.galery = galery;
        }

        public Brand getBrand() {
            return brand;
        }

        public void setBrand(Brand brand) {
            this.brand = brand;
        }

        public Style getStyle() {
            return style;
        }

        public void setStyle(Style style) {
            this.style = style;
        }

        public Univers getUnivers() {
            return univers;
        }

        public void setUnivers(Univers univers) {
            this.univers = univers;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public static long getSerialversionuid() {
            return serialVersionUID;
        }

        
	
}
