package com.project.petstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
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
	public ResponseEntity<?> updateById(@PathVariable Long id,@RequestPart("pet") Pet pet,@RequestParam("file") MultipartFile file) {

		return petService.updatePetById(id, pet, file);
	}

	@PostMapping("")
	public ResponseEntity<?> createPet(@RequestPart("pet") Pet pet,@RequestParam("file") MultipartFile file) {

		return petService.createPet(pet, file);
	}

	@GetMapping("/findByStatus/{petStatus}")
	public ResponseEntity<?> findByStatus(@PathVariable String petStatus) {

		return petService.findByStatus(petStatus);
	}

	@GetMapping("/findByTags")
	public ResponseEntity<?> findByTags() {

		return petService.findByTags();
		}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {

		return petService.findById(id);
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> updatePet(@PathVariable Long id, @RequestBody Pet pet) {

		return petService.updateById(id, pet);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePet(@PathVariable Long id) {

		return petService.deletePet(id);
	}

	@PostMapping("/{id}/uploadImage")
	public ResponseEntity<?> uploadImagePet(@PathVariable Long id,@RequestParam("file") MultipartFile file) {

		return petService.uploadImage(id, file);
	}

}
