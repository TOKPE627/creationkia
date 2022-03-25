package com.javatechie.awselasticbeanstalkexample.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Table(name="company")
@Entity(name="company")
public class Company implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "sub_category_id")
	private SubCategory subCategory;

	private String name;
	private String town;
	private String district;
	private String address;
	@Column(columnDefinition="text")
	private String description;	
	
	//Contacts
	private String phone;
	private String email;
	private String whatsapp;
	private String facebook;
	private String instagram;
	private String twitter;
	private String linkedin;
	
	
    @Enumerated(EnumType.STRING)
    private CompanyType companyType;
    //	private String publicationDate;
    
    @ManyToOne(cascade = { CascadeType.REMOVE})
  	@JoinColumn(name = "galery_id")
  	private CompanyGalery galery;
    
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Company(Long id, User user, String name, String town, String district, String address, String description,
		 String phone, String email, String whatsapp, String facebook, String instagram,
			String twitter, String linkedin) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.town = town;
		this.district = district;
		this.address = address;
		this.description = description;
		this.phone = phone;
		this.email = email;
		this.whatsapp = whatsapp;
		this.facebook = facebook;
		this.instagram = instagram;
		this.twitter = twitter;
		this.linkedin = linkedin;
	}
	


	public CompanyGalery getGalery() {
		return galery;
	}

	public void setGalery(CompanyGalery galery) {
		this.galery = galery;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}
	

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
