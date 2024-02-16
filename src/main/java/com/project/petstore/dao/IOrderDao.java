package com.project.petstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.petstore.models.Order;

@Repository
public interface IOrderDao extends JpaRepository<Order, Object>{

	Order findByStatus(String status);
}
