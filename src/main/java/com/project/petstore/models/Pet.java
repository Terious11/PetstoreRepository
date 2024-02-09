package com.project.petstore.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PETS")
public class Pet {

	@Id
	private Long id;
	private String name;
	private String[] photoUrls;

//	
//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "tag_id")
//	private List<Tag> tags;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	private Category category;


	
	@Column(name="status")
	private String status;

	public Pet() {
		//this.tags = new ArrayList<Tag>();
	}

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

	

	public String[] getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(String[] photoUrls) {
		this.photoUrls = photoUrls;
	}
	/*
	 * public List<Tag> getTags() { return tags; }
	 * 
	 * public void setTags(List<Tag> tags) { this.tags = tags; }
	 */

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

}
