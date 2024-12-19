package com.bloggingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapplication.entity.Comments;

public interface CommentsRepo extends JpaRepository<Comments, Integer> {

}
