package com.project.petstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.petstore.dao.IPetDao;
import com.project.petstore.models.Pet;



@Service
public class PetServiceImpl implements IPetService{

	@Autowired
	IPetDao petDao;
	
	@Override
	public Pet updatePetById(Long id, Pet pet) {
		// TODO Auto-generated method stub
		Pet petUpdate = findPetById(id);
		petUpdate.setName(petUpdate.getName());
		petUpdate.setStatus(pet.getStatus());
		//petUpdate.setCategory(pet.getCategory());
		//petUpdate.setTags(pet.getTags());
		//falta actualizar la foto 
		return petDao.save(petUpdate);
	}

	@Override
	public Pet createPet(Pet pet) {
		
		return petDao.save(pet);
	}

	@Override
	public List<Pet> findByStatus(String status) {
				
		return petDao.findByStatus(status);
	}

	/*
	 * @Override public List<Pet> findByTags(String tag) { List<Pet> pet = new
	 * ArrayList<Pet>();;
	 * 
	 * for(int i =0; i<tag.length(); i++){ pet.add( (Pet) petDao.findByTags(tag)); }
	 * 
	 * return pet; }
	 */
	@Override
	public Pet findById(Long id) {
		
		return findPetById(id);
	}

	@Override
	public Pet UpdateById(Long id, String name, String status) {

		Pet pet = findPetById(id);
		pet.setName(name);
		pet.setStatus(status);
	
		return petDao.save(pet);
	}

	@Override
	public String deletePet(Long id) {
		petDao.deleteById(id);
		return "OK";
	}

	@Override
	public String uploadImage(Long id, String[] foto) {
		Pet pet = findPetById(id);
		pet.setPhotoUrls(foto);
		petDao.save(pet);
		return "Actualizada";
	}

	public Pet findPetById(Long id) {
		return petDao.findById(id).orElse(null);
	}
}
