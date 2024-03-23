package com.project.petstore.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.petstore.models.Order;

@Repository
public interface IOrderDao extends JpaRepository<Order, Long> {

	
	
	@Query(value="SELECT COUNT(*) FROM petstore.orders p WHERE p.STATUS='placed'",nativeQuery=true)
	Long findByStatusPlacedCount();
	
	@Query(value="SELECT COUNT(*) FROM petstore.orders p WHERE p.STATUS='approved'",nativeQuery=true)
	Long findByStatusApprovedCount();
	
	@Query(value="SELECT COUNT(*) FROM petstore.orders p WHERE p.STATUS='delivered'",nativeQuery=true)
	Long findByStatusDeliveredCount();
	
	Order findByStatus(String status);

	

}
