package com.humaid.aws.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.humaid.aws.model.Role;
import com.humaid.aws.model.User;
import com.humaid.aws.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	@CrossOrigin(origins="http://localhost:4200")
	public void addUserByAdmin(@RequestBody User user) {
		Set<Role> roles=new HashSet<Role>();
		Role role=new Role();
		role.setRole("USER");
		roles.add(role);
		user.setRoles(roles);
		String pwd=user.getPassword();
		String encrypPwd=passwordEncoder.encode(pwd);
		user.setPassword(encrypPwd);
		userRepository.save(user);
		
	}
	
}
