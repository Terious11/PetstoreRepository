package com.project.petstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.petstore.models.Tag;

@Repository
public interface ITagDao extends JpaRepository<Tag, Long>{

}
