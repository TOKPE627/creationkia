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

@Table(name="advertise")
@Entity(name="advertise")
public class Advertise  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name="attoly";
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
 
	
	private String image;
	
//	@Column(columnDefinition="text")
//	private String detail;

	

	public Long getId() {
		return id;
	}
	
	

	public Advertise() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Advertise(Long id, User user, String image) {
		super();
		this.id = id;
		this.user = user;
		this.image = image;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
