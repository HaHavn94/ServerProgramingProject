package com.example.ShareGroup.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Item{
@Id
@GeneratedValue(strategy= GenerationType.AUTO)
private Long id;
private String productName;


@ManyToOne
@JoinColumn(name = "userId")
private AppUser appuser;

public Item() {
	
}


public Item(String productName, AppUser appuser) {
	super();
	this.productName = productName;
	this.appuser=appuser;
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getProductName() {
	return productName;
}


public void setProductName(String productName) {
	this.productName = productName;
}


public AppUser getAppuser() {
	return appuser;
}


public void setAppuser(AppUser appuser) {
	this.appuser = appuser;
}





}