package com.humaid.aws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.humaid.aws.model.Product;
import com.humaid.aws.service.CustomUserDetails;
import com.humaid.aws.service.ProductService;

@RestController
public class ProdController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<List<Product>> getAllproducts(){
		List<Product> prodlist;
		prodlist=productService.getAllProducts();
		return ResponseEntity.ok().body(prodlist);
		
	}
	
	@GetMapping("/search/{keyword}")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<List<Product>> searchProduct(@PathVariable String keyword){
		return ResponseEntity.ok().body(productService.searchProducts(keyword));
	}
	
	@GetMapping("/isauth")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<Boolean> isauthenticated(){
		try{CustomUserDetails user= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return ResponseEntity.ok().body(true);	
			}
		catch (Exception e) {
			return ResponseEntity.ok().body(false);	
		}
		
	}
	
	@GetMapping("/getUser")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<String> getUsername(){
		CustomUserDetails user= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok().body(user.getUser().getUsername());
		
	} 
	

	
	
}
