package com.project.petstore.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.petstore.models.Pet;

public interface IPetService {
	
	public Pet updatePetById(Long id,Pet pet);
	public Pet createPet(Pet pet, MultipartFile foto);
	public List<Pet> findByStatus(String status);
	//public List<Pet> findByTags(String tag);
	public Pet findById(Long id);
	public Pet UpdateById(Long id, Pet pet);
	public String deletePet(Long id);
	public String uploadImage(Long id, String foto);
	
	
}
