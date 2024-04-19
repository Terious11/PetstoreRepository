package com.project.petstore.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.project.petstore.models.Pet;

public interface IPetService {
	
	public ResponseEntity<?> updatePetById(Long id,Pet pet, MultipartFile photoUrls);
	
	public ResponseEntity<?> createPet(Pet pet, MultipartFile photoUrls);
	
	public ResponseEntity<?> findByStatus(String petStatus);
	
	public ResponseEntity<?> findByTags();
	
	public ResponseEntity<?> findById(Long id);
	
	public ResponseEntity<?> updateById(Long id, Pet pet);
	
	public ResponseEntity<?> deletePet(Long id);
	
	public ResponseEntity<?> uploadImage(Long id, MultipartFile photoUrls);
	
	
}
