package com.humaid.aws.controller;



import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humaid.aws.model.Address;
import com.humaid.aws.model.CartItem;
import com.humaid.aws.model.OrderItem;
import com.humaid.aws.model.Product;
import com.humaid.aws.model.Role;
import com.humaid.aws.model.User;
import com.humaid.aws.service.AddressService;
import com.humaid.aws.service.CartService;
import com.humaid.aws.service.CustomUserDetails;
import com.humaid.aws.service.OrderService;
import com.humaid.aws.service.ProductService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private com.humaid.aws.repository.UserRepository UserRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private CartService cartService;
	
	@Autowired 
	private AddressService addressService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired 
	private ProductService productService;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/cart")
	public ResponseEntity<List<CartItem>> getAllCart() {
		return ResponseEntity.ok().body(cartService.getCartItems());
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/order")
	public ResponseEntity<List<OrderItem>> getAllOrders(){
		return ResponseEntity.ok().body(orderService.getAllOrders());
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/address")
	public ResponseEntity<List<Address>> getAllAddress(){
		return ResponseEntity.ok().body(addressService.getAllAddress());
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/add")
	@CrossOrigin(origins="http://localhost:4200")
	public String addUserByAdmin(@RequestBody User user) {
		
		CustomUserDetails admin= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user.setRoles(admin.getUser().getRoles());
		String pwd=user.getPassword();
		String encrypPwd=passwordEncoder.encode(pwd);
		user.setPassword(encrypPwd);
		UserRepository.save(user);
		return "user added succesfully..";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok().body(UserRepository.findAll());
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/product/add")
	public void addProduct(@RequestBody Product product) {
		productService.save(product);
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/product/remove/{id}")
	public void removeProduct(@PathVariable Long id) {
		productService.deletebyId(id);
	}
	
	
	
	

}
