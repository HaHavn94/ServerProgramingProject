package com.example.ShareGroup.domain;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class AppUser {
		
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "appuser")
	private List <Item> items;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String username;

    private String passwordHash;

    private String role;
   
    private String email;
    

    
    public AppUser() {
    }

	public AppUser(String username, String passwordHash, String email, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}




	
}
