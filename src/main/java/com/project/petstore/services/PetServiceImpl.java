package com.project.petstore.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	@Override
	public Pet updatePetById(Long id, Pet pet) {
		try {
			Pet petUpdate = new Pet();
			petUpdate = findPetById(id);
			Category categoryUpdate = new Category();
			Tag tagUpdate = new Tag();

			categoryUpdate = categoryDao.findById(petUpdate.getCategory().getId()).orElse(null);
			categoryUpdate.setName(pet.getCategory().getName());
			categoryDao.save(categoryUpdate);

			tagUpdate = tagDao.findById(petUpdate.getTags().getId()).orElse(null);
			tagUpdate.setName(pet.getTags().getName());
			tagDao.save(tagUpdate);

			petUpdate.setCategory(categoryUpdate);
			petUpdate.setTags(tagUpdate);
			petUpdate.setName(pet.getName());
			petUpdate.setStatus(pet.getStatus());
			petUpdate.setPhotoUrls(pet.getPhotoUrls());
			petDao.save(petUpdate);
			return petDao.save(petUpdate);
		} catch (Exception e) {
			System.out.print("EXCEPCION----------" + e);// TODO: handle exception
			return null;
		}
	}

	@Override
	public Pet createPet(Pet pet, MultipartFile foto) {

		
		if(!foto.isEmpty()) {
			Path dirResourses=Paths.get("src//main//resources//static/upload");
			String rootPath = dirResourses.toFile().getAbsolutePath();
			try {
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath+"//"+foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				System.out.println("FOTO SUBIDA");
				pet.setPhotoUrls(foto.getOriginalFilename());
			}catch (Exception e) {
				System.out.println(e);
				
			}
		}
		return petDao.save(pet);
	}

	@Override
	public List<Pet> findByStatus(String status) {

		return petDao.findByStatus(status);
	}

	@Override
	public Pet findById(Long id) {
		return petDao.findById(id).orElse(null);
	}

	@Override
	public Pet UpdateById(Long id, Pet pet) {

		Pet petUpdate = findPetById(id);
		petUpdate.setName(pet.getName());
		petUpdate.setStatus(pet.getStatus());

		return petDao.save(petUpdate);
	}

	@Override
	public String deletePet(Long id) {
		try {
			Pet petExist = new Pet();
			petExist = findPetById(id);
			if (petExist != null) {
				petDao.deleteById(id);
				return "OK";
			}

			return null;
		} catch (Exception e) {
			
			return null;
		}

	}

	@Override
	public String uploadImage(Long id, String foto) {
		Pet pet = findPetById(id);
		pet.setPhotoUrls(foto);
		petDao.save(pet);
		return "Actualizada";
	}

	public Pet findPetById(Long id) {
		return petDao.findById(id).orElse(null);
	}
}
