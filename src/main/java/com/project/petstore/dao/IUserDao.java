package com.project.petstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.petstore.models.User;

@Repository
public interface IUserDao extends JpaRepository<User,Object> {

	User findByUsername(String username);
	
}
