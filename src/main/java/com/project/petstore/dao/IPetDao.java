package com.project.petstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.petstore.models.Pet;

@Repository
public interface IPetDao extends JpaRepository<Pet, Object>{

	 //available, pending, sold
	List<Pet> findByStatus(String status);
	
	//BUSQUEDA DE LOS STATUS
	@Query(value="SELECT * FROM petstore.pets s WHERE s.STATUS LIKE %?1%",nativeQuery=true)
	List<Pet> findAllByStatus(String status);
	
	//BUSQUEDA DE LAS ETIQUETAS
	@Query(value="SELECT * FROM petstore.pets s",nativeQuery=true)
	List<Pet> findAllByTags();
	
	
}
