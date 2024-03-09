package com.project.petstore.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.project.petstore.models.User;

public interface IUserService {
	public User createUser(User userCreate);
	public List<User> createWithList(List<User> userAll);
	public ResponseEntity<?> getUser(String username);
	public ResponseEntity<?> updateUser(String username, User userUpdate);
	public ResponseEntity<?> deleteUser(String username);
	public String getLogin(String username, String password);
	public String getLogout();
	
}
