package com.javatechie.awselasticbeanstalkexample.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="company_galery")
@Entity(name="company_galery")
public class CompanyGalery implements Serializable {


		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
				
		private String companyImage;
		
	    private String geolocateImage1;
	    private String geolocateImage2;
	    
	    @OneToMany(mappedBy="galery")
		@JsonIgnore()
		private List<Company> companies;
	    
		public CompanyGalery() {
			super();
			// TODO Auto-generated constructor stub
		}
		public CompanyGalery(Long id, String companyImage, String geolocateImage1,
				String geolocateImage2) {
			super();
			this.id = id;
			this.companyImage = companyImage;
			this.geolocateImage1 = geolocateImage1;
			this.geolocateImage2 = geolocateImage2;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
	
		public String getCompanyImage() {
			return companyImage;
		}
		public void setCompanyImage(String companyImage) {
			this.companyImage = companyImage;
		}
		public String getGeolocateImage1() {
			return geolocateImage1;
		}
		public void setGeolocateImage1(String geolocateImage1) {
			this.geolocateImage1 = geolocateImage1;
		}
		public String getGeolocateImage2() {
			return geolocateImage2;
		}
		public void setGeolocateImage2(String geolocateImage2) {
			this.geolocateImage2 = geolocateImage2;
		}
	    
	    
	    
	    
}
