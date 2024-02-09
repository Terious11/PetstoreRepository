package com.project.petstore.services;

import java.util.List;

import com.project.petstore.models.User;

public interface IUserService {
	public User createUser(User userCreate);
	public List<User> createWithList(List<User> userAll);
	public User getUser(String username);
	public User updateUser(String username, User userUpdate);
	public String deleteUser(String username);
	public String getLogin(String username, String password);
	public String getLogout();
	
}
