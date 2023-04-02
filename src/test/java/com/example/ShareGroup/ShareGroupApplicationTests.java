package com.example.ShareGroup;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ShareGroup.web.AppUserController;
import com.example.ShareGroup.web.ShareGroupController;
import com.example.ShareGroup.web.UserDetailServiceImpl;


@SpringBootTest
class ShareGroupApplicationTests {

	
	@Autowired
	private AppUserController userController; 
	
	@Autowired
	private ShareGroupController groupController; 
	
	@Autowired
	private UserDetailServiceImpl detail; 
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(groupController).isNotNull();
	}
	@Test
	public void contextLoadsUser() throws Exception{
		assertThat(userController).isNotNull();
	}

	@Test
	public void contextLoadsDetail() throws Exception{
		assertThat(detail).isNotNull();
	}

}
