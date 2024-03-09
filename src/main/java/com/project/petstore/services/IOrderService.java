package com.project.petstore.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.project.petstore.models.Order;

public interface IOrderService {

	public List<String> statusList();
	public Order createOrder(Order order);
	public ResponseEntity<?> orderById(Long id);
	public ResponseEntity<?> deleteOrder(Long id);
}
