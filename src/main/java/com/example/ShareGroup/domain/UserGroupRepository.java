package com.example.ShareGroup.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {
		
	 List<UserGroup> findByUserGroup(String userGroup);
}