package com.example.ShareGroup.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UserGroup {

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usergroup")
	private List<AppUser> users;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long groupId;
	private String userGroup;

	public UserGroup() {
		super();

	}

	public UserGroup(String userGroup) {
		super();
		this.userGroup = userGroup;

	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public List<AppUser> getUsers() {
		return users;
	}

	public void setUsers(List<AppUser> users) {
		this.users = users;
	}

}
