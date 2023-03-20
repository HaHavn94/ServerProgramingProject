package com.example.ShareGroup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.ShareGroup.domain.AppUser;
import com.example.ShareGroup.domain.AppUserRepository;
import com.example.ShareGroup.domain.Item;
import com.example.ShareGroup.domain.ItemRepository;

@SpringBootApplication
public class ShareGroupApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ShareGroupApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner haDemo(AppUserRepository urepository, ItemRepository irepo) {
		return (args) -> {
			AppUser user1 = new AppUser("user", "$2a$10$.mq6qHzVV6F.br8tq.AD7OMDj0MLTwwWoFyjDHaBUmy90k1fz18dS","ha.vu@", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$p/Bi5xt4flMvFDaQy49UaOAt0GZFydlCWHDwB9U6y8yLS8fVpIgbq","admin@gmail", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);	
			
			Item item1 = new Item("pen", user1);
			Item item2 = new Item("macBook", user2);
			irepo.save(item1);
			irepo.save(item2);
			
			
			
		};
	}
}