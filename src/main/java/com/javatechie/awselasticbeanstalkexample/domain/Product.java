package com.javatechie.awselasticbeanstalkexample.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="product")
@Entity(name="product")
public class Product  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; //Seller
   //Product Commercial and MultiService
	
	
	
	   @ManyToOne
	    @JoinColumn(name = "category_id")
	    private Category category;
	
    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;
    
    
    @ManyToOne(cascade = { CascadeType.REMOVE})
	@JoinColumn(name = "galery_id")
	private ProductGalery galery;
//	@ManyToOne  
//	@JoinColumn(name = "store_id")
//	private Store store;
	//@ManyToOne --Library
	//@ManyToOne --Shop
	private String name;
	private String downsale; //Downsale:-$25
	private Double upPrice;//Upselling price
	//Description
	private Double price;//Normal price
	private String brand;//Brand
	private Long quantity;	
	@Column(columnDefinition="text")
	private String detail;
	private String publicationDate;
    private String closedDate;
    private double deliveryPrice;
	private int currentBooking=0;
	@OneToMany(mappedBy="product")
	@JsonIgnore()
	private List<MailVisitor> mailVisitorList;
	 
	//Product MultiService
	@Column(columnDefinition="text")
	private String specificity;
	private String deliveryDelay;
		
	public String getDownsale() {
		return downsale;
	}

	public void setDownsale(String downsale) {
		this.downsale = downsale;
	}

	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<MailVisitor> getMailVisitorList() {
		return mailVisitorList;
	}

	public void setMailVisitorList(List<MailVisitor> mailVisitorList) {
		this.mailVisitorList = mailVisitorList;
	}

	public String getSpecificity() {
		return specificity;
	}

	public void setSpecificity(String specificity) {
		this.specificity = specificity;
	}

	public String getDeliveryDelay() {
		return deliveryDelay;
	}

	public void setDeliveryDelay(String deliveryDelay) {
		this.deliveryDelay = deliveryDelay;
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

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

//	public Store getStore() {
//		return store;
//	}
//
//	public void setStore(Store store) {
//		this.store = store;
//	}

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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	

	public int getCurrentBooking() {
		return currentBooking;
	}

	public void setCurrentBooking(int currentBooking) {
		this.currentBooking = currentBooking;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	public String getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}

	public double getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getUpPrice() {
		return upPrice;
	}

	public void setUpPrice(Double upPrice) {
		this.upPrice = upPrice;
	}

	public ProductGalery getGalery() {
		return galery;
	}

	public void setGalery(ProductGalery galery) {
		this.galery = galery;
	}

	
}
