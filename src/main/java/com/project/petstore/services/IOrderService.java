package com.project.petstore.services;

import java.util.List;

import com.project.petstore.models.Order;

public interface IOrderService {

	public List<String> statusList();
	public Order createOrder(Order order);
	public Order orderById(Long id);
	public String deleteOrder(Long id);
	//prueba regreso de status
	public List<Order> listaOrder();
}
