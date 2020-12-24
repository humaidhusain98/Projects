package com.humaid.aws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humaid.aws.model.OrderItem;
@Repository
public interface OrderRepo extends JpaRepository<OrderItem,Long> {
	List<OrderItem> getOrdersByUserid(int userId);
}
