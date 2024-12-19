package com.bloggingapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapplication.entity.Category;
import com.bloggingapplication.entity.Post;
import com.bloggingapplication.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	List<Post> findByTitleContaining(String title);

}
