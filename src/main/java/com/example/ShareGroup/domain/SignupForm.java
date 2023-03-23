package com.example.ShareGroup.domain;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.constraints.*;

public class SignupForm {

	
	UserGroup defaultGroup= new UserGroup();
	
	


	
    @NotEmpty
    @Size(min=5, max=30)
    private String username = "";

    @NotEmpty
    @Size(min=7, max=30)
    private String password = "";

    @NotEmpty
    @Size(min=7, max=30)
    private String passwordCheck = "";
    
    @NotEmpty
    @Size(min=7, max=30)
    private String email = "";
    
    


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


    
    
}
