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
	     
	     @Column(columnDefinition="text")
	     private String mainImage;
	    
	     @JsonBackReference(value = "category")
	     @ManyToOne
	     private Category category;
	    
	     
	     
	     private String c1;
	     private String c2;
	     private String c3;
	     private String c4;
	     private String c5;
	     private String c6;
	     private String c7;
	     private String c8;
	     private String c9;
	     private String c10;
	    
	     private String d1; 
	     private String d2; 
	     private String d3;
	     private String d4;
	     private String d5; 
	     private String d6; 
	     private String d7; 
	     private String d8; 
	     private String d9; 
	     private String d10; 
	     
	     @ManyToOne(cascade = { CascadeType.REMOVE})
	     @JoinColumn(name = "galery_id")
	     private ProductGalery galery;
	     
	     @OneToOne
	     private Brand brand;
	     
	     @OneToOne
	     private Style style ;
	     
	     @OneToOne
	     private Univers univers;
	     
	     @OneToOne
	     private Gender gender;

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

        public String getMainImage() {
            return mainImage;
        }

        public void setMainImage(String mainImage) {
            this.mainImage = mainImage;
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

        public String getC6() {
            return c6;
        }

        public void setC6(String c6) {
            this.c6 = c6;
        }

        public String getC7() {
            return c7;
        }

        public void setC7(String c7) {
            this.c7 = c7;
        }

        public String getC8() {
            return c8;
        }

        public void setC8(String c8) {
            this.c8 = c8;
        }

        public String getC9() {
            return c9;
        }

        public void setC9(String c9) {
            this.c9 = c9;
        }

        public String getC10() {
            return c10;
        }

        public void setC10(String c10) {
            this.c10 = c10;
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

        public String getD6() {
            return d6;
        }

        public void setD6(String d6) {
            this.d6 = d6;
        }

        public String getD7() {
            return d7;
        }

        public void setD7(String d7) {
            this.d7 = d7;
        }

        public String getD8() {
            return d8;
        }

        public void setD8(String d8) {
            this.d8 = d8;
        }

        public String getD9() {
            return d9;
        }

        public void setD9(String d9) {
            this.d9 = d9;
        }

        public String getD10() {
            return d10;
        }

        public void setD10(String d10) {
            this.d10 = d10;
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

        public Gender getGender() {
            return gender;
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public static long getSerialversionuid() {
            return serialVersionUID;
        }
	
}
