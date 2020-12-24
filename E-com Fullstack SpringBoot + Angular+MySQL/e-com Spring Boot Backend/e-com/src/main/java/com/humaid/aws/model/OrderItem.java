package com.humaid.aws.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long addressid;
	
	private int userid;
	
	private int amt;
	
	private paymentMode mode;
	
	private Status status;
	
	
	
	

	public paymentMode getMode() {
		return mode;
	}

	public void setMode(paymentMode mode) {
		this.mode = mode;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getAddressid() {
		return addressid;
	}

	public void setAddressid(Long addressid) {
		this.addressid = addressid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getAmt() {
		return amt;
	}

	public void setAmt(int amt) {
		this.amt = amt;
	}




	public OrderItem(Long addressid, int userid, int amt) {
		super();
		this.addressid = addressid;
		this.userid = userid;
		this.amt = amt;
	}

	public Long getId() {
		return id;
	}

	public OrderItem() {
		super();
	}
	
	
	
	
	
}
