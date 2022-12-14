package com.javatechie.awselasticbeanstalkexample.domain;

import java.util.List;

public class AjaxResponseBody {

	String msg;
	List<Product> result;
	String awsBucketProduct;
	String awsBucketBrand;
	String awsBucketStyle;
	String awsBucketUnivers;
	String awsBucketCategory;
	
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

    public String getAwsBucketBrand() {
        return awsBucketBrand;
    }

    public void setAwsBucketBrand(String awsBucketBrand) {
        this.awsBucketBrand = awsBucketBrand;
    }

    public String getAwsBucketStyle() {
        return awsBucketStyle;
    }

    public void setAwsBucketStyle(String awsBucketStyle) {
        this.awsBucketStyle = awsBucketStyle;
    }

    public String getAwsBucketUnivers() {
        return awsBucketUnivers;
    }

    public void setAwsBucketUnivers(String awsBucketUnivers) {
        this.awsBucketUnivers = awsBucketUnivers;
    }

    public String getAwsBucketCategory() {
        return awsBucketCategory;
    }

    public void setAwsBucketCategory(String awsBucketCategory) {
        this.awsBucketCategory = awsBucketCategory;
    }



	

}
