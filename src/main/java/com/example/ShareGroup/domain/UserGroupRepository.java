package com.example.ShareGroup.domain;




import org.springframework.data.repository.CrudRepository;

public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {

	UserGroup findByUserGroup(String userGroup);
}