package com.project.petstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.petstore.models.Order;
import com.project.petstore.services.IOrderService;

@RestController
@RequestMapping("/store")
public class OrderController {

	@Autowired
	IOrderService orderService;
	
	@GetMapping("/inventory")
	public List<String> statusString(){
		
		return orderService.statusList();
	}
	
	@PostMapping("/order")
	public Order createOrder(@RequestBody  Order orderNew) {
		
		return orderService.createOrder(orderNew);
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		
		return orderService.orderById(id);
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
		
		return orderService.deleteOrder(id);
	}
}
