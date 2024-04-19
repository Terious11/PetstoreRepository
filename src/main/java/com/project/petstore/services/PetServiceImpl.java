package com.project.petstore.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.petstore.controllers.HandleExcepcionController;
import com.project.petstore.dao.ICategoryDao;
import com.project.petstore.dao.IPetDao;
import com.project.petstore.dao.ITagDao;
import com.project.petstore.models.Category;
import com.project.petstore.models.Pet;
import com.project.petstore.models.Tag;

@Service
public class PetServiceImpl implements IPetService {

	@Autowired
	IPetDao petDao;

	@Autowired
	ICategoryDao categoryDao;

	@Autowired
	ITagDao tagDao;

	@Autowired
	HandleExcepcionController exception;

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public ResponseEntity<?> updatePetById(Long id, Pet pet, MultipartFile foto) {
		
		try {
			Optional<Pet> idPetExist = petDao.findById(id);
			if (!foto.isEmpty() || !idPetExist.isEmpty()) {

				Category categoryUpdate = new Category();
				Tag tagUpdate = new Tag();
				Pet petUpdate = idPetExist.get();

				// ACTUALIZAR LA TABLA CATEGORIA
				log.info("Actualizacion de tabla categoria");
				categoryUpdate = categoryDao.findById(petUpdate.getCategory().getId()).orElse(null);
				categoryUpdate.setName(pet.getCategory().getName());
				categoryDao.save(categoryUpdate);

				// ACTUALIZAR LA TABLA TAG
				log.info("Actualizacion de tabla Tag");
				tagUpdate = tagDao.findById(petUpdate.getTags().getId()).orElse(null);
				tagUpdate.setName(pet.getTags().getName());
				tagDao.save(tagUpdate);
				log.info("Actualizacion de los demas elemenos de la tabla Pet");
				petUpdate.setCategory(categoryUpdate);
				petUpdate.setTags(tagUpdate);
				petUpdate.setName(pet.getName());
				petUpdate.setStatus(pet.getStatus());

				// ELIMINAR LA FOTO ANTERIOR PARA QUE NO QUEDE EN EL PROYECTO SOLA
				log.info("Eliminar foto antigua del cliente");
				Path rootPath = Paths.get("upload").resolve(petUpdate.getPhotoUrls()).toAbsolutePath();
				File archivo = rootPath.toFile();
				if (archivo.exists())
					archivo.delete();

				// ACTUALIZAR LA FOTO CON EL USUARIO PET
				log.info("Actualizacion de foto nueva del cliente");
				String uniqueFileName = UUID.randomUUID().toString()+ "_"+ foto.getOriginalFilename();
				Path rootPathUpdate = Paths.get("upload").resolve(uniqueFileName);
				Path rootAbsolutPath = rootPathUpdate.toAbsolutePath();
				Files.copy(foto.getInputStream(), rootAbsolutPath);
				petUpdate.setPhotoUrls(uniqueFileName);

				petDao.save(petUpdate);
				
				return ResponseEntity.ok(petUpdate);
			} else {
				
				return (ResponseEntity<?>) ResponseEntity.badRequest();
			}

		} catch (Exception e) {
			
			return exception.errorPet(e);
		}
	}

	@Override
	public ResponseEntity<?> createPet(Pet pet, MultipartFile foto) {
		
		try {
			if (!foto.isEmpty()) {
				String uniqueFileName = UUID.randomUUID().toString()+ "_"+ foto.getOriginalFilename();
				log.info("Creacion de nuevo pet");
				System.out.println("entro : " + foto.getOriginalFilename());
				System.out.println("entro : " + uniqueFileName);
				Path rootPath = Paths.get("upload").resolve(uniqueFileName);
				Path rootAbsolutPath = rootPath.toAbsolutePath();
				try {
					//SUBIDA DEL ARCHIVO AL PROYECTO
					log.info("Subida de foto del Pet");
					Files.copy(foto.getInputStream(), rootAbsolutPath);
					pet.setPhotoUrls(uniqueFileName);
					petDao.save(pet);
					
					return ResponseEntity.ok(pet);	
				} catch (Exception e) {
					
					return exception.errorPet(e);
				}				
			} else {
				
				return (ResponseEntity<?>) ResponseEntity.badRequest();
			}
		} catch (Exception e) {
			
			return exception.errorPet(e);
		}
	}

	@Override
	public ResponseEntity<?> findByStatus(String petStatus) {
		try {
			List<Pet> petFound = petDao.findAllByStatus(petStatus);
			if (!petFound.isEmpty()) {

				return ResponseEntity.ok(petFound);
			} else {
			
				return (ResponseEntity<?>) ResponseEntity.badRequest();
			}

		} catch (Exception e) {
		
			return exception.errorPet(e);
		}
	}

	@Override
	public ResponseEntity<?> findByTags() {
		try {
			List<Pet> petFoundTags = petDao.findAllByTags();
			if (!petFoundTags.isEmpty()) {

				return ResponseEntity.ok(petFoundTags);
			} else {
				
				return (ResponseEntity<?>) ResponseEntity.badRequest();
			}
		} catch (Exception e) {
			
			return exception.errorPet(e);
		}

	}

	@Override
	public ResponseEntity<?> findById(Long id) {
		try {
			Optional<Pet> petFind = petDao.findById(id);
			if (!petFind.isEmpty()) {
			
				return ResponseEntity.ok(petFind);
			} else {
				
				return (ResponseEntity<?>) ResponseEntity.badRequest();
			}
		} catch (Exception e) {

			return exception.errorPet(e);
		}

	}

	@Override
	public ResponseEntity<?> updateById(Long id, Pet pet) {
		
		try {
			Optional<Pet> petUpdatebyId = petDao.findById(id);
			if (!petUpdatebyId.isEmpty()) {
				log.info("Actualizacion mediante Id");
				petUpdatebyId.get().setName(pet.getName());
				petUpdatebyId.get().setStatus(pet.getStatus());
				petDao.save(petUpdatebyId.get());

				return ResponseEntity.ok(petUpdatebyId);
			} else {
				
				return (ResponseEntity<?>) ResponseEntity.badRequest();
			}

		} catch (Exception e) {
			
			return exception.errorPet(e);
		}

	}

	@Override
	public ResponseEntity<?> deletePet(Long id) {
		
		try {
			Optional<?> petExist = petDao.findById(id);
			if (!petExist.isEmpty()) {
				log.info("Elminacion del Pet");
				petDao.deleteById(id);
				log.info("Eliminacion de archivo de Pet");
				Path rootPath = Paths.get("upload").resolve(((Pet) petExist.get()).getPhotoUrls()).toAbsolutePath();
				File archivo = rootPath.toFile();
				if (archivo.exists())
					archivo.delete();

				return ResponseEntity.ok("Eliminado con Exito");
			} else {
			
				return (ResponseEntity<?>) ResponseEntity.badRequest();
			}

		} catch (Exception e) {

			return exception.errorPet(e);
		}

	}

	@Override
	public ResponseEntity<?> uploadImage(Long id, MultipartFile foto) {
		try {
			Optional<Pet> idPetExist = petDao.findById(id);
			if (!foto.isEmpty() || !idPetExist.isEmpty()) {
				// ELIMINAR LA FOTO ANTERIOR PARA QUE NO QUEDE EN EL PROYECTO SOLA
				log.info("Eliminar foto antigua del cliente");
				Path rootPath = Paths.get("upload").resolve(idPetExist.get().getPhotoUrls()).toAbsolutePath();
				File archivo = rootPath.toFile();
				if (archivo.exists())
					archivo.delete();
				// ACTUALIZAR LA FOTO CON EL USUARIO PET
				log.info("Actualizacion de foto nueva del cliente");
				String uniqueFileName = UUID.randomUUID().toString()+ "_"+ foto.getOriginalFilename();
				Path rootPathUpdate = Paths.get("upload").resolve(uniqueFileName);
				Path rootAbsolutPath = rootPathUpdate.toAbsolutePath();
				Files.copy(foto.getInputStream(), rootAbsolutPath);
				idPetExist.get().setPhotoUrls(uniqueFileName);
				petDao.save(idPetExist.get());
				
				return ResponseEntity.ok(idPetExist.get());
			} else {
				
				return (ResponseEntity<?>) ResponseEntity.badRequest();
			}
		} catch (Exception e) {
		
			return exception.errorPet(e);
		}
	}

//	public Pet findPetById(Long id) {
//		
//		return petDao.findById(id).orElse(null);
//	}

}
