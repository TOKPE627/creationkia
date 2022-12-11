package com.javatechie.awselasticbeanstalkexample.domain;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
	 
 private String name; 
 	 
 @Column(columnDefinition="text")
 private String image;
 
// @JsonProperty(access = Access.WRITE_ONLY)
 @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
 private List<Product> products;
 
 
 public Category() {
  super();
 }
 
 public Category(Long id, String name, String image, List<Product> products) {
    super();
    this.id = id;
    this.name = name;
    this.image = image;
    this.products = products;
}



public String getImage() {
    return image;
}

public void setImage(String image) {
    this.image = image;
}

public void setId(Long id) {
    this.id = id;
}

public Long  getId() {
   return id;
 }

 public void setId(long id) {
   this.id = id;
 }

 public String getName() {
   return name;
 }

 public void setName(String name) {
   this.name = name;
 }



 public List<Product> getProducts() {
   return products;
 }

 public void setProducts(List<Product> products) {
   this.products = products;
 }
	 
 public void addProductToCategory(Product product) {
  if (getProducts()==null) {
    this.products = new ArrayList<>();
  }
    getProducts().add(product);
    product.setCategory(this);
 }	 
}

