package com.humaid.aws.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long productId;
	
	private int userId;
	
	private int price;
	
	private Long orderid=null;
	
	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	private String productName;
	
	public CartItem() {
		super();
	}

	public CartItem(Long productId, int userId, int qty) {
		super();
		this.productId = productId;
		this.userId = userId;
		this.qty = qty;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	private int qty;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Long getId() {
		return id;
	}

	public CartItem(Long productId, int userId, int qty, int price, String productName,Long orderid) {
		super();
		this.productId = productId;
		this.userId = userId;
		this.qty = qty;
		this.price = price;
		this.productName = productName;
		this.orderid=orderid;
	}
	
	
}
