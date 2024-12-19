package com.bloggingapplication.entity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;

	private String content;

	@ManyToOne
	@JoinColumn(name = "postId")
	private Post post;

}
