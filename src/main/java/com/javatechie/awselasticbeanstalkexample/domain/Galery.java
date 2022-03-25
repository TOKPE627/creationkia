package com.javatechie.awselasticbeanstalkexample.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity
public class Galery {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
	private String extraImage1;
	
	private String extraImage2;

	private String extraImage3;
		
	private String extraImage4;

	public Galery() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Galery(Long id, Location location) {
		super();
		this.id = id;
		this.location = location;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getExtraImage1() {
		return extraImage1;
	}
	public void setExtraImage1(String extraImage1) {
		this.extraImage1 = extraImage1;
	}
	public String getExtraImage2() {
		return extraImage2;
	}
	public void setExtraImage2(String extraImage2) {
		this.extraImage2 = extraImage2;
	}
	public String getExtraImage3() {
		return extraImage3;
	}
	public void setExtraImage3(String extraImage3) {
		this.extraImage3 = extraImage3;
	}
	
	
	public String getExtraImage4() {
		return extraImage4;
	}
	public void setExtraImage4(String extraImage4) {
		this.extraImage4 = extraImage4;
	}


	@Transient
	public String getExtraImagePath1() {
		String galeryImagePath = "/src/main/resources/static/image/galery/";
		if(id==null || extraImage1 == null) return null;
		return galeryImagePath + id + "/" + extraImage1;
	}
	
	@Transient
	public String getExtraImagePath2() {
		String galeryImagePath = "/src/main/resources/static/image/galery/";
		if(id==null || extraImage2 == null) return null;
		return galeryImagePath + id + "/" + extraImage2;
	}
	
	@Transient
	public String getExtraImagePath3() {
		String galeryImagePath = "/src/main/resources/static/image/galery/";
		if(id==null || extraImage3 == null) return null;
		return galeryImagePath + id + "/" + extraImage3;
	}
	@Transient
	public String getExtraImagePath4() {
		String galeryImagePath = "/src/main/resources/static/image/galery/";
		if(id==null || extraImage4 == null) return null;
		return galeryImagePath + id + "/" + extraImage4;
	}
}
