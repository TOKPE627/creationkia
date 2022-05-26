package com.javatechie.awselasticbeanstalkexample.domain;

import java.util.List;

public class AjaxResponseBody {

	String msg;
	List<Product> result;
	String awsBucketProduct;
	String awsBucketGroupSale;
	String awsBucketShop;
	List<Company> companies;
	List<Speciality> specialities;
	Long companyId;
	int numberLikes;
    CharSequence contactrule;
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Product> getResult() {
		return result;
	}

	public void setResult(List<Product> result) {
		this.result = result;
	}

	public String getAwsBucketProduct() {
		return awsBucketProduct;
	}

	public void setAwsBucketProduct(String awsBucketProduct) {
		this.awsBucketProduct = awsBucketProduct;
	}

	public String getAwsBucketGroupSale() {
		return awsBucketGroupSale;
	}

	public void setAwsBucketGroupSale(String awsBucketGroupSale) {
		this.awsBucketGroupSale = awsBucketGroupSale;
	}

	public String getAwsBucketShop() {
		return awsBucketShop;
	}

	public void setAwsBucketShop(String awsBucketShop) {
		this.awsBucketShop = awsBucketShop;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public List<Speciality> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(List<Speciality> specialities) {
		this.specialities = specialities;
	}

	

	public int getNumberLikes() {
		return numberLikes;
	}

	public void setNumberLikes(int numberLikes) {
		this.numberLikes = numberLikes;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public CharSequence getContactrule() {
		return contactrule;
	}

	public void setContactrule(CharSequence contactrule) {
		this.contactrule = contactrule;
	}



}
