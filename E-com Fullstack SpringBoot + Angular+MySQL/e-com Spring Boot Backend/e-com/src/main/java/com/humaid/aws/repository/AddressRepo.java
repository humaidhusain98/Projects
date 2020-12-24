package com.humaid.aws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humaid.aws.model.Address;
@Repository
public interface AddressRepo extends JpaRepository<Address,Long>{
	
	List<Address> getAddressesByUserid(int userid);
}
