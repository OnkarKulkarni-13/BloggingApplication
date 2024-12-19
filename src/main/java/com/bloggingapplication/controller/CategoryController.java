package com.bloggingapplication.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingapplication.exception.ResourceNotFoundException;
import com.bloggingapplication.payloads.ApiResponse;
import com.bloggingapplication.payloads.CategoryDto;
import com.bloggingapplication.service.CategoryService;
import javax.validation.Valid;
//import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/category")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		List<CategoryDto> categoryDto = categoryService.getAllCategories();
		return new ResponseEntity<List<CategoryDto>>(categoryDto, HttpStatus.OK);
	}

	@PostMapping("/register/category")
	public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) {

		CategoryDto categoryCreated = categoryService.addCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryCreated, HttpStatus.CREATED);

	}

	@PutMapping("/category/{categoryId}")
	public ResponseEntity<CategoryDto> updateUser(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable("categoryId") Integer categoryId) throws ResourceNotFoundException {

		CategoryDto categoryUpdated = categoryService.updateCatgory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(categoryUpdated, HttpStatus.CREATED);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<CategoryDto> getUserById(@PathVariable("categoryId") Integer categoryId)
			throws ResourceNotFoundException {
		CategoryDto category = categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
	}

	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("categoryId") Integer categoryId)
			throws ResourceNotFoundException {
		categoryService.deleteCategoryById(categoryId);

		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Category with I'd" + categoryId + " Deleted ", new Date(), true), HttpStatus.OK);
	}

}
