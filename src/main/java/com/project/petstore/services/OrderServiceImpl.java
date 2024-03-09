package com.project.petstore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.petstore.controllers.HandleExcepcionController;
import com.project.petstore.dao.IOrderDao;
import com.project.petstore.models.Order;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderDao orderDao;

	@Autowired
	HandleExcepcionController exception;

	@Override
	public List<String> statusList() {
		List<String> lista = new ArrayList<String>();
		
		lista.add("placed: "+orderDao.findByStatusPlacedCount());
		lista.add("approved: "+orderDao.findByStatusApprovedCount());
		lista.add("delivered: "+orderDao.findByStatusDeliveredCount());
		
		return lista;
	}

	@Override
	public Order createOrder(Order order) {

		return orderDao.save(order);
	}

	@Override
	public ResponseEntity<?> orderById(Long id) {
		try {
			Optional<Order> order = orderDao.findById(id);
			if (!order.isEmpty()) {
				return ResponseEntity.ok(order);
			} else {
				return (ResponseEntity<?>) ResponseEntity.badRequest();
			}
		} catch (Exception e) {
			return exception.errorInternId500(e);
		}
	}

	@Override
	public ResponseEntity<?> deleteOrder(Long id) {
		try {
			if (!orderDao.findById(id).isEmpty()) {
				orderDao.deleteById(id);
				return ResponseEntity.ok("Delete");
			} else {

				return (ResponseEntity<?>) ResponseEntity.badRequest();
			}

		} catch (Exception e) {

			return exception.errorInternId500(e);
		}

	}

}
