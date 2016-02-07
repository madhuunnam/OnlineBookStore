package com.madhu.project;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrder {
	private int orderQuantity;
	private int orderPrice;
	private int orderStatusCode;
	private int orderId;
	private int cardNo;
	private int userId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	private String address;
	private String city;
	private String state;
	public List<OrderContains> getOrderContainsList() {
		return orderContainsList;
	}
	public void setOrderContainsList(List<OrderContains> orderContainsList) {
		this.orderContainsList = orderContainsList;
	}
	private List<OrderContains> orderContainsList = new ArrayList<OrderContains>();
	
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public int getOrderStatusCode() {
		return orderStatusCode;
	}
	public void setOrderStatusCode(int orderStatusCode) {
		this.orderStatusCode = orderStatusCode;
	}
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


}
