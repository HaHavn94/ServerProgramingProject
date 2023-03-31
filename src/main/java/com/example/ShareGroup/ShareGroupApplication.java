package com.example.ShareGroup;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import com.example.ShareGroup.domain.AppUser;
import com.example.ShareGroup.domain.AppUserRepository;
import com.example.ShareGroup.domain.Item;
import com.example.ShareGroup.domain.ItemRepository;
import com.example.ShareGroup.domain.UserGroup;
import com.example.ShareGroup.domain.UserGroupRepository;

@SpringBootApplication
public class ShareGroupApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareGroupApplication.class, args);
	}

	@Bean
	public CommandLineRunner haDemo(AppUserRepository urepository, ItemRepository irepo,
			UserGroupRepository groupRepo) {
		return (args) -> {

			UserGroup group1 = new UserGroup("adminGroup");
			UserGroup group2 = new UserGroup("userGroup");
			groupRepo.save(group1);
			groupRepo.save(group2);

			AppUser user1 = new AppUser("user", "$2a$04$JTpZAmTs3DNnUPNrISGnu.VVVeX2roAAA7DBUaNmL3dSc/4y1Cldy", group2);

			AppUser user2 = new AppUser("admin", "$2a$10$p/Bi5xt4flMvFDaQy49UaOAt0GZFydlCWHDwB9U6y8yLS8fVpIgbq",
					group1);
			AppUser user3 = new AppUser("HaUser", "$2a$04$JTpZAmTs3DNnUPNrISGnu.VVVeX2roAAA7DBUaNmL3dSc/4y1Cldy",
					group2);
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);

			Item item1 = new Item("pen", "available", user1, user2);
			Item item2 = new Item("apple", "available", user3, user2);
			Item item3 = new Item("catset", "available", user1, user2);
			Item item4 = new Item("dog", "available", user3, user2);

			irepo.save(item1);
			irepo.save(item2);
			irepo.save(item3);
			irepo.save(item4);

		};
	}
}