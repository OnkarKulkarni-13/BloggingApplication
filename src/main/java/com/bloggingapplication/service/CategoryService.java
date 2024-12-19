package com.bloggingapplication.service;

import java.util.List;

import com.bloggingapplication.exception.InvalidUserIdException;
import com.bloggingapplication.exception.ResourceNotFoundException;
import com.bloggingapplication.payloads.CategoryDto;

public interface CategoryService {
	/**
	 * @GetMapping
	 * @param NA
	 * @return List<CategoryDto>
	 */

	List<CategoryDto> getAllCategories();

	/**
	 * @PostMapping
	 * @param category
	 * @return CategoryDto
	 */
	CategoryDto addCategory(CategoryDto categoryDto);

	/**
	 * @PutMapping
	 * @param category, categoryId
	 * @return UserDto
	 * @throws ResourceNotFoundException
	 * @throws InvalidUserIdException
	 */
	CategoryDto updateCatgory(CategoryDto categoryDto, Integer categoryId) throws ResourceNotFoundException;

	/**
	 * @GetMapping -> By Id
	 * @param user
	 * @return UserDto
	 * @throws ResourceNotFoundException
	 */

	CategoryDto getCategoryById(Integer categoryId) throws ResourceNotFoundException;

	/**
	 * @DeleteMapping -> By Id
	 * @param userId
	 * @return void
	 * @throws ResourceNotFoundException
	 */
	void deleteCategoryById(Integer categoryId) throws ResourceNotFoundException;
}
