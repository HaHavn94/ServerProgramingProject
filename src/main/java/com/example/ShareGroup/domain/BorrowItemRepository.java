package com.example.ShareGroup.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface BorrowItemRepository extends CrudRepository<BorrowerItem, Long> {
	List<BorrowerItem> findAllByAppuser(AppUser currentUser );
	
}