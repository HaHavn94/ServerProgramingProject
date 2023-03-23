package com.example.ShareGroup.domain;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class AppUser {
		
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "appuser")
	private List <Item> ownItems;

		
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
	
	@Column(name = "username", nullable = false, unique = true)
    private String username;

    private String passwordHash;
   
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "userGroup")
    private UserGroup usergroup;
    
    
    public AppUser() {
    }
    public AppUser(String username) {
    	this.username = username;
    }

	public AppUser(String username, String passwordHash, String email,  UserGroup usergroup) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;		
		this.usergroup = usergroup;
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



	public UserGroup getUsergroup() {
		return usergroup;
	}

	public void setUsergroup(UserGroup usergroup) {
		this.usergroup = usergroup;
	}

	public List<Item> getOwnItems() {
		return ownItems;
	}

	public void setOwnItems(List<Item> ownItems) {
		this.ownItems = ownItems;
	}










	
}
