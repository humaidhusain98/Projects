package com.humaid.aws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humaid.aws.model.Product;
import com.humaid.aws.repository.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAllProducts(){
		System.out.println("Get all Products");
		List<Product> productList;
		productList=productRepo.findAll();
		return productList;
	}
	
	public Product getProduct(Long id) {
		System.out.println("Fetch Product By Id");
		Product obj=productRepo.findById(id).orElse(null);
		return obj;
	}
	
	public void save(Product product) {
		System.out.println("Product Saved");
		productRepo.save(product);
	}
	
	public void edit(Product product) {
		System.out.println("Product Edit");
		productRepo.save(product);
	}
	
	public void delete(Product product) {
		System.out.println("Product Object Deleted");
		productRepo.delete(product);
	}
	
	public void deletebyId(Long id) {
		System.out.println("Product Object Deleted");
		productRepo.deleteById(id);
	}
	
	public List<Product> searchProducts(String keyword){
		return productRepo.findAllByName(keyword);
	}
	 
	
	
	
	
	
	
}
