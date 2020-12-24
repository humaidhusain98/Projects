package com.humaid.aws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humaid.aws.model.User;



public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);

}
