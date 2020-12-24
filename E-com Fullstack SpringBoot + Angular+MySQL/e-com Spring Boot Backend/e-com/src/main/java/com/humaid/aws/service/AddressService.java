package com.humaid.aws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humaid.aws.model.Address;
import com.humaid.aws.repository.AddressRepo;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepo addressRepo;
	
	public void save(Address address) {
		addressRepo.save(address);
	}
	
	public void edit(Address address) {
		addressRepo.save(address);	
	}
	
	public void delete(Address address) {
		addressRepo.delete(address);
	}
	
	public List<Address> getAddressByUserId(int userid) {
		List<Address> objList;
		objList=addressRepo.getAddressesByUserid(userid);
		return objList; 
		
	}
	
	public List<Address> getAllAddress() {
		List<Address> objList;
		objList=addressRepo.findAll();
		return objList; 
		
	}
	
	public Address getAddress(Long id) {
		Address objAddress=addressRepo.findById(id).orElse(null);
		return objAddress;
		
	}
	
	
	
	
}
