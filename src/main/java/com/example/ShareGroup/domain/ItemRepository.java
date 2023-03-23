package com.example.ShareGroup.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface ItemRepository extends CrudRepository <Item, Long>{
	 List<Item> findByProductName(String productName);
	 List<Item> findByStatus(String status);


}
