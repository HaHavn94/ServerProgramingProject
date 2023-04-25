package com.example.ShareGroup.domain;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface AppUserRepository  extends CrudRepository<AppUser, Long> {

	AppUser findByUsername(String username);

	AppUser getByUsername(String username);
	
	

}