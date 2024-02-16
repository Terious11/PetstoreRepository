package com.project.petstore.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.petstore.dao.IOrderDao;
import com.project.petstore.models.Order;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderDao orderDao;

	@Override
	public List<String> statusList() {
		List<String> lista = new ArrayList<String>();
		Order orderPlaced = new Order();
		/*
		 * Order orderApproved= new Order(); Order orderDeliveried=new Order();
		 */
		orderPlaced = orderDao.findByStatus("placed");
		
		
		
		/*
		 * lista.add("" + orderPlaced.getStatus().length()); orderApproved =
		 * orderDao.findByStatus("approved"); lista.add("" +
		 * orderApproved.getStatus().length()); orderDeliveried =
		 * orderDao.findByStatus("deliveried"); lista.add("" +
		 * orderDeliveried.getStatus().length());
		 */
		return lista;
	}
	
	@Override
	public List<Order> listaOrder() {
		//List<Order> lista = new ArrayList<Order>() ;
		String placed = "placed";
		List<Long> cantidad = new ArrayList<Long>();
		
		
		
		
		List<Order> findByStatus = (List<Order>) orderDao.findByStatus("placed");
		return findByStatus;
		
	}

	@Override
	public Order createOrder(Order order) {

		return orderDao.save(order);
	}

	@Override
	public Order orderById(Long id) {

		return orderDao.findById(id).orElse(null);
	}

	@Override
	public String deleteOrder(Long id) {
		try {
			if(orderDao.findById(id).isEmpty()) {
			
				return "Id Empty";
			}
			else 
			{
				orderDao.deleteById(id);
				
				return "delete";
			}
				
			
		}catch (Exception e) {
				
			return e.getMessage();
		}
		
	}



}
