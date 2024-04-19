package com.project.petstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.petstore.controllers.HandleExcepcionController;
import com.project.petstore.dao.IUserDao;
import com.project.petstore.models.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao userDao;

	@Autowired
	HandleExcepcionController responses;

	@Override
	public ResponseEntity<?> createUser(User userCreate) {
		try {
			
			return ResponseEntity.ok(userDao.save(userCreate));
		}catch (Exception e) {
			
			return responses.errorInternUsername500(e);
		}
	}

	@Override
	public ResponseEntity<?> createWithList(List<User> userAll) {
		try {
			
			return ResponseEntity.ok(userDao.saveAll(userAll));
		}catch (Exception e) {
			
			return responses.errorInternUsername500(e);
		}
	}

	@Override
	public ResponseEntity<?> getUser(String username) {

		try {
			User userExist = userDao.findByUsername(username);

			if (userExist.getId() != null) {
				return ResponseEntity.ok(userExist);
			}
			return (ResponseEntity<?>) ResponseEntity.notFound();

		} catch (Exception e) {
			return responses.errorInternUsername500(e);
		}

	}

	@Override
	public ResponseEntity<?> updateUser(String username, User userUpdate) {

		try {
			User userOld = userDao.findByUsername(username);

			if (userOld.getId() != null) {
				userOld.setFirstName(userUpdate.getFirstName());
				userOld.setLastName(userUpdate.getLastName());
				userOld.setEmail(userUpdate.getEmail());
				userOld.setPhone(userUpdate.getPhone());
				userOld.setUserStatus(userUpdate.getUserStatus());
				userOld.setPassword(userUpdate.getPassword());
				userOld.setUsername(userUpdate.getUsername());
				userDao.save(userOld);

				return ResponseEntity.ok(userOld);
			}
			return (ResponseEntity<?>) ResponseEntity.notFound();

		} catch (Exception e) {
			return responses.errorInternUsername500(e);
		}
	}

	@Override
	public ResponseEntity<?> deleteUser(String username) {
	
		try {
			User userDelete = userDao.findByUsername(username);

			if (userDelete.getId() != null) {
				userDao.delete(userDelete);
				return ResponseEntity.ok("delete");
			}
			return (ResponseEntity<?>) ResponseEntity.notFound();

		} catch (Exception e) {
			return responses.errorInternUsername500(e);
		}
	}

	@Override
	public String getLogin(String username, String password) {
		// TODO Auto-generated method stub
		
		return "Login";
	}

	@Override
	public String getLogout() {
		// TODO Auto-generated method stub
		return "successful operation";
	}

}
