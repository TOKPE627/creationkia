package com.javatechie.awselasticbeanstalkexample.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.javatechie.awselasticbeanstalkexample.utility.AppConstants;

@Table(name="booking_company")
@Entity(name = "booking_company")
public class BookingCompany {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String email;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;//Customer
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	private String command;
	private String day;
    private String hour;
	private String status=AppConstants.ORDER_STATUS_0;
	private String  ipaddress;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}

   public String getStatus(){
	   return status;
   }
   public void setStatus(String status){
	   this.status = status;
   }
   public String getIpaddress() {
	return ipaddress;
}

public void setIpaddress(String ipaddress) {
	this.ipaddress = ipaddress;
}

    
}
