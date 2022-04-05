package com.javatechie.awselasticbeanstalkexample.domain;

import java.util.List;

public class AjaxResponseBody {
   
    String msg;
    List<Product> result;
	String awsBucketProduct;
	String awsBucketGroupSale;
	String awsBucketShop;
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

    

}
