package com.humaid.aws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.humaid.aws.model.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
	
	@Query("SELECT p FROM Product p where p.name LIKE %?1%")
	public List<Product> findAllByName(String name);
}
