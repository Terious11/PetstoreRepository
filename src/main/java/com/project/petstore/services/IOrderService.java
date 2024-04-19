package com.project.petstore.services;

import org.springframework.http.ResponseEntity;

import com.project.petstore.models.Order;

public interface IOrderService {

	public ResponseEntity<?> statusList();
	
	public ResponseEntity<?> createOrder(Order order);
	
	public ResponseEntity<?> orderById(Long id);
	
	public ResponseEntity<?> deleteOrder(Long id);
}
