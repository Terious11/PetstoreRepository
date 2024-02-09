package com.project.petstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.petstore.dao.IUserDao;
import com.project.petstore.models.User;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	IUserDao userDao;
	
	@Override
	public User createUser(User userCreate) {
		// TODO Auto-generated method stub
		return userDao.save(userCreate);
	}
	
	@Override
	public List<User> createWithList(List<User> userAll) {
		
		
		return userDao.saveAll(userAll);
	}
	
	@Override
	public User getUser(String username) {
		
		return userDao.findByUsername(username);
	}

	@Override
	public User updateUser(String username, User userUpdate) {
		User user = userDao.findByUsername(username);
		user.setFirstName(userUpdate.getFirstName());
		user.setLastName(userUpdate.getLastName());
		user.setEmail(userUpdate.getEmail());
		user.setPhone(userUpdate.getPhone());
		user.setUserStatus(userUpdate.getUserStatus());
		user.setPassword(userUpdate.getPassword());
		user.setUsername(userUpdate.getUsername());
		userDao.save(user);
		
		return user;
	}

	@Override
	public String deleteUser(String username) {
		User userDelete = userDao.findByUsername(username);
		userDao.delete(userDelete);
		
		return "DONE";
	}

	@Override
	public String getLogin(String username, String password) {
		// TODO Auto-generated method stub
		User userGetLogin = userDao.findByUsername(username);
		
		if(!userGetLogin.getUsername().isEmpty() || !userGetLogin.getPassword().isEmpty()) {
			
			return "Login";
		}
		
		return "Error";
	}

	@Override
	public String getLogout() {
		// TODO Auto-generated method stub
		return "successful operation";
	}

	
	
	
}
