package com.project.petstore.controllers;

import java.nio.file.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.petstore.models.Pet;
import com.project.petstore.services.IPetService;

@RestController
@RequestMapping("/pet")
public class PetController {

	@Autowired
	IPetService petService;
	
	
	@PutMapping("/{id}")
	public Pet updateById(@PathVariable Long id, @RequestBody Pet pet) {
		
		return petService.updatePetById(id, pet);
	}
	
	@PostMapping("")
	public Pet createPet(@RequestBody Pet pet,@RequestParam("photoUrls") MultipartFile foto) {

		return petService.createPet(pet, foto);
	}
	
	@GetMapping("findByStatus")
	public List<Pet> findByStatus(String status){
		
		return petService.findByStatus(status);
	}
	
	@GetMapping("findByTags")
	public List<Pet> findByTags(String tags){
		
		//return petService.findByTags(tags);
		return null;
	}
	
	@GetMapping("/{id}")
	public Pet findById(@PathVariable Long id) {
		
		return petService.findById(id);
	}
	
	@PostMapping("/{id}")
	public Pet updatePet(@PathVariable Long id,@RequestBody Pet pet) {
		
		return petService.UpdateById(id, pet);
	}
	
	@DeleteMapping("/{id}")
	public String deletePet(@PathVariable Long id) {
		
		return petService.deletePet(id);
	}
	
	@PostMapping("/{id}/uploadImage")
	public String uploadImagePet (@PathVariable Long id, @PathVariable String photoUrls) {
		
		return petService.uploadImage(id, photoUrls);
	}
	
	
	
	
	
	
	
}
