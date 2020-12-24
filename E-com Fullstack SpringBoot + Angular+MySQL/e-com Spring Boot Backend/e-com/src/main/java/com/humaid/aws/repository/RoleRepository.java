package com.humaid.aws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humaid.aws.model.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {

}
