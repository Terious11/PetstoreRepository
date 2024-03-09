package com.project.petstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.petstore.models.User;
import com.project.petstore.services.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService userService;

	@PostMapping("")
	public User createUser(@RequestBody User userCreate) {

		return userService.createUser(userCreate);
	}
	
	@PostMapping("/createWithList")
	public List<User> createWithList(@RequestBody List<User> userList) {

		return userService.createWithList(userList);
	}

	@GetMapping("/{username}")
	public ResponseEntity<?> getUsername(@PathVariable("username") String username) {

		return userService.getUser(username);
	}

	@PutMapping("/{username}")
	public ResponseEntity<?> updateUser(@PathVariable("username") String username, @RequestBody User userUpdate) {

		return userService.updateUser(username, userUpdate);
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {

		return userService.deleteUser(username);
	}

	@GetMapping("/login/{username},{password}")
	public String loginUser(@PathVariable String username, @PathVariable String password) {

		return userService.getLogin(username, password);
	}

	

}
