package com.humaid.aws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.humaid.aws.model.Address;
import com.humaid.aws.service.AddressService;
import com.humaid.aws.service.CustomUserDetails;

@RestController
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/user/address")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<List<Address>> getUserAddress(){
		CustomUserDetails user= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Address> addrlist=addressService.getAddressByUserId(user.getUser().getUser_id());
		return ResponseEntity.ok().body(addrlist);
		
	}
	
	@GetMapping("/user/address/template")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<Address> Template() {
		return ResponseEntity.ok().body(new Address());
	}
	
	
	@PostMapping("/user/address")
	@CrossOrigin(origins="http://localhost:4200")
	public void saveAddress(@RequestBody Address address) {
		CustomUserDetails user= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		address.setUserid(user.getUser().getUser_id());
		addressService.save(address);
		
	}
	
	

}
