package com.bloggingapplication.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingapplication.entity.Category;
import com.bloggingapplication.exception.ResourceNotFoundException;
import com.bloggingapplication.payloads.CategoryDto;
import com.bloggingapplication.repository.CategoryRepo;
import com.bloggingapplication.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = categoryRepo.findAll();
		List<CategoryDto> receivedCategories = new ArrayList<>();
		for (Category category : categories) {
			CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
			receivedCategories.add(categoryDto);
		}

		return receivedCategories;
	}

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		Category addedCategory = categoryRepo.save(category);
		return modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCatgory(CategoryDto categoryDto, Integer categoryId) throws ResourceNotFoundException {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

		// Updating category with categoryDto
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());

		// Saving into the DB
		categoryRepo.save(category);
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) throws ResourceNotFoundException {
		Category category = null;
		if (categoryId > 0) {
//			category = categoryRepo.findById(categoryId)
//					.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

			category = categoryRepo.getById(categoryId); // Throws 'EntityNotFoundException' Internal Exception
			if (category == null) {
				throw new ResourceNotFoundException("Category", "Id", categoryId);
			}

		}

		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategoryById(Integer categoryId) throws ResourceNotFoundException {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

		categoryRepo.delete(category);

		return;
	}

}
