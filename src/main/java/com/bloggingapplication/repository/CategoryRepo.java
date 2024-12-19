package com.bloggingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapplication.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
