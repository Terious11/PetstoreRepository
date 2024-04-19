package com.project.petstore.controllers;

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
	public ResponseEntity<?> statusString(){
		
		return orderService.statusList();
	}
	
	@PostMapping("/order")
	public ResponseEntity<?> createOrder(@RequestBody  Order orderNew) {
		
		return orderService.createOrder(orderNew);
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long idFindOrder) {
		
		return orderService.orderById(idFindOrder);
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable("id") Long idDeleteOrder) {
		
		return orderService.deleteOrder(idDeleteOrder);
	}
}
