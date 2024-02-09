package com.project.petstore.services;

import java.util.List;

import com.project.petstore.models.Pet;

public interface IPetService {
	
	public Pet updatePetById(Long id,Pet pet);
	public Pet createPet(Pet pet);
	public List<Pet> findByStatus(String status);
	//public List<Pet> findByTags(String tag);
	public Pet findById(Long id);
	public Pet UpdateById(Long id, String name, String status);
	public String deletePet(Long id);
	public String uploadImage(Long id, String[] foto);
	
	
}
