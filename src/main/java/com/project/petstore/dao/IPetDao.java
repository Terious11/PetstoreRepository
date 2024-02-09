package com.project.petstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.petstore.models.Pet;

@Repository
public interface IPetDao extends JpaRepository<Pet, Object>{

	List<Pet> findByStatus(String status);
	//List<Pet> findByTags(String tags);

}
