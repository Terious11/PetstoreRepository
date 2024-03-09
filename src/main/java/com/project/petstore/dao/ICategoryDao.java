package com.project.petstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.petstore.models.Category;

@Repository
public interface ICategoryDao extends JpaRepository<Category, Long> {

	Category findByName(String name);
}
