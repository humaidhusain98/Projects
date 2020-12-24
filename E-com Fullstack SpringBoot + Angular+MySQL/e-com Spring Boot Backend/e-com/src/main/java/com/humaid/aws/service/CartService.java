package com.humaid.aws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humaid.aws.model.CartItem;
import com.humaid.aws.repository.CartRepo;

@Service
public class CartService {
	@Autowired
	private CartRepo cartRepo;
	
	public List<CartItem> getCartItems(){
		List<CartItem> cartlist;
		System.out.println("Getting all CartItems");
		cartlist=cartRepo.findAll();
		return cartlist;
		
	}
	
	public List<CartItem> getCartItemsByUserId(int userId){
		List<CartItem> cartlist;
		System.out.println("Getting all CartItems By user id");
		cartlist=cartRepo.getCartItemsByUserId(userId);
		return cartlist;
		
	}
	
	public List<CartItem> getCartItemsByUserIdAndOrderId(int userId){
		List<CartItem> cartlist;
		System.out.println("Getting all CartItems By user id and Order id");
		cartlist=cartRepo.getCartItemsByUserIdAndOrderid(userId, null);
		return cartlist;
		
	}
	
	public CartItem getCartItemsByProductIdAndUserId(Long pid,int uid){
		CartItem item;
		System.out.println("Getting CartItems By productId and UserId and orderid");
		item=cartRepo.getCartItemsByProductIdAndUserIdAndOrderid(pid,uid,null);
		return item;
		
	}
	
	
	
	public void save(CartItem cartItem) {
		System.out.println("CartItem Saved");
		cartRepo.save(cartItem);
	}
	
	public void edit(CartItem cartItem) {
		System.out.println("CartItem Edited");
		cartRepo.save(cartItem);
	}
	
	public void delete(CartItem cartItem){
		System.out.println("CartItem Deleted");
		cartRepo.delete(cartItem);
	}
	
	public void deleteById(Long id) {
		System.out.println("CartItem Deleted By Id");
		cartRepo.deleteById(id);
	}
	
	
	
	
}
