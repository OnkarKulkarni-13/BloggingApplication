package com.bloggingapplication.entity;

import java.util.ArrayList;
import java.util.List;
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;

	@NotNull(message = "CategoryTitle should not ne null")
	@NotBlank(message = "CategoryTitle should not be blank")
	private String categoryTitle;

	@NotNull(message = "CategoryDescription should not ne null")
	@NotBlank(message = "CategoryDescription should not be blank")
	private String categoryDescription;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<Post>();

}
