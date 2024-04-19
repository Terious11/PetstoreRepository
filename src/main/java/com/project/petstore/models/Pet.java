package com.project.petstore.models;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PETS")
public class Pet implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "photo_Urls")
	private String photoUrls;
	@Column(name = "status")
	private String status;
	
	//CREACION DE LLAVE FORANEA MANY TO ONE CON LA TABLA TAG
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_Tags",referencedColumnName ="id")
	private Tag tags;

	//CREACION DE LLAVE FORANE ONE TO ONE CON LA TABLA DE CATEGORIA
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_Category",referencedColumnName ="id")
	private Category category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(String photoUrls) {
		this.photoUrls = photoUrls;
	}

	public Tag getTags() {
		return tags;
	}

	public void setTags(Tag tags) {
		this.tags = tags;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
