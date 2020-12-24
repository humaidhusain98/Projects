package com.humaid.aws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humaid.aws.model.CartItem;
@Repository
public interface CartRepo extends JpaRepository<CartItem,Long> {

	List<CartItem> getCartItemsByUserId(int userId);
	List <CartItem> getCartItemsByUserIdAndOrderid(int userId,Long orderid);
	//CartItem getCartItemsByProductId(Long productId);
	CartItem getCartItemsByProductIdAndUserId(Long productId,int userId);
	CartItem getCartItemsByProductIdAndUserIdAndOrderid(Long productId,int userId,Long orderid);
}
