package com.javatechie.awselasticbeanstalkexample.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.javatechie.awselasticbeanstalkexample.domain.security.Authority;
import com.javatechie.awselasticbeanstalkexample.domain.security.UserRole;
import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="users")
@Entity(name="users")
public class User implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable = false, updatable = false)
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
    private String photo;
	
    //Address
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
	private String town;
	
	private String district;
	
	@Column(columnDefinition="text")
	private String address;

	//Contact
	private String phone;
	@Column(name="email", nullable = false, updatable = false)
	private String email;
	private String whatsapp;
	private String facebook;
	private String instagram;
	private String twitter;
		
	private boolean enabled=true;
	
	
	
	
	/*
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "user") private
	 * List<UserPayment> userPaymentList;
	 */
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();
	
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	@JsonIgnore()
	private Company company;
	

	
	@OneToMany(mappedBy="user")
	@JsonIgnore()
	private List<Catalog> catalogList;
	
	
	@OneToMany(mappedBy="sender")
	@JsonIgnore()
	private List<MailVisitor> senderMessages;

	@OneToMany(mappedBy="receiver")
	@JsonIgnore()
	private List<MailVisitor> receiverMessages;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore()
	private List<Speciality> specialities;
	
	public List<Speciality> getSpecialities() {
		return specialities;
	}
	public void setSpecialities(List<Speciality> specialities) {
		this.specialities = specialities;
	}
	public List<MailVisitor> getSenderMessages() {
		return senderMessages;
	}
	public void setSenderMessages(List<MailVisitor> senderMessages) {
		this.senderMessages = senderMessages;
	}
	public List<MailVisitor> getReceiverMessages() {
		return receiverMessages;
	}
	public void setReceiverMessages(List<MailVisitor> receiverMessages) {
		this.receiverMessages = receiverMessages;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	public List<Catalog> getCatalogList() {
		return catalogList;
	}
	public void setCatalogList(List<Catalog> catalogList) {
		this.catalogList = catalogList;
	}


	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorites = new HashSet<>();
		userRoles.forEach(ur -> authorites.add(new Authority(ur.getRole().getName())));
		
		return authorites;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}

	
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	@Transient
	public String getImagePath() {
		if(id==null || photo == null) return null;
		return AppConstants.userPath + id + "/" + photo;
	}
	
}
