package com.example.ShareGroup.domain;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class BorrowerItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)		
	private AppUser appuser;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Item item;
	
	

	public BorrowerItem() {
		super();
	}

	public BorrowerItem(AppUser currentUser, Item item) {
		super();
		this.appuser = currentUser;
		this.item = item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AppUser getAppuser() {
		return appuser;
	}

	public void setAppuser(AppUser appuser) {
		this.appuser = appuser;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	

}
