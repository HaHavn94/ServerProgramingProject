package com.example.ShareGroup;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ShareGroup.domain.AppUser;
import com.example.ShareGroup.domain.AppUserRepository;
import com.example.ShareGroup.domain.UserGroup;
import com.example.ShareGroup.domain.UserGroupRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ShareGroupRepositoryTest {
	
	@Autowired
    private AppUserRepository repository;
	@Autowired
    private UserGroupRepository groupRepo;
	

    @Test
    public void findByUsernameReturnUserObj() {
        AppUser users = repository.findByUsername("user");
        assertThat(users).isNotNull();
        assertThat(users.getUsername()).isEqualTo("user");
    }
   
    @Test
    public void createNewUser() {
    	UserGroup getUserGroup1 = groupRepo.findById((long) 2).get();
    	AppUser user = new AppUser("Lasse","jhfhdshjfdshjfd",getUserGroup1 );
    	repository.save(user);
    	assertThat(user.getUserId()).isNotNull();
    }   
    
    public void checkRole() {
    	AppUser user = repository.findByUsername("admin");
    	assertThat(user.getUsergroup().getUserGroup()).isEqualTo("ADMIN");
    }
    @Test
    public void deleteUser() {
		Long userId= repository.findByUsername("user").getUserId();
		repository.delete(repository.findById(userId).get());
		assertThat(repository.findByUsername("user")).isNull();
     }
   
  
}