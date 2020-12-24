package com.humaid.aws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humaid.aws.model.OrderItem;
import com.humaid.aws.repository.OrderRepo;

@Service
public class OrderService {
	@Autowired
	private OrderRepo orderRepo;
	
	public List<OrderItem> getOrderByUserId(int userid){
		List<OrderItem> orderlist;
		orderlist=orderRepo.getOrdersByUserid(userid);
		return orderlist;
		
	}
	
	public List<OrderItem> getAllOrders(){
		List<OrderItem> orderlist;
		orderlist=orderRepo.findAll();
		return orderlist;
	}
	
	public OrderItem getOrderById(Long orderid) {
		OrderItem obj;
		obj=orderRepo.findById(orderid).orElse(null);
		return obj;
	}
	
	public void save(OrderItem order) {
		orderRepo.save(order);
		
	}
	
	public void edit(OrderItem order) {
		orderRepo.save(order);
	}
	
	public void delete(OrderItem order) {
		orderRepo.delete(order);	
	}
	
	
	
	
	
}
