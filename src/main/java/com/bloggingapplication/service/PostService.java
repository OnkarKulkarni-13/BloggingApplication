package com.bloggingapplication.service;

import java.util.List;

import com.bloggingapplication.exception.InvalidUserIdException;
import com.bloggingapplication.exception.ResourceNotFoundException;
import com.bloggingapplication.exception.TitleSearchNotPresentException;
import com.bloggingapplication.payloads.PostDto;
import com.bloggingapplication.payloads.PostResponse;

public interface PostService {

	/**
	 * 
	 * 
	 * 
	 * @GetMapping
	 * @param NA
	 * @return List<PostDto>
	 */

	List<PostDto> getAllPost();

	/**
	 * @apiNote Pagination
	 * @param pageSize
	 * @param pageNumber
	 * @GetMapping
	 * @param NA
	 * @return List<PostDto>
	 */

	PostResponse getAllPost(Integer pageNumber, Integer pageSize);

	/**
	 * @PostMapping
	 * @param category
	 * @return CategoryDto
	 * @throws ResourceNotFoundException
	 */
	PostDto addPost(PostDto postDto, Integer userId, Integer categoryId) throws ResourceNotFoundException;

	/**
	 * @PostMapping
	 * @param category
	 * @return CategoryDto
	 * @throws ResourceNotFoundException
	 */
	PostDto addPostViaPostman(PostDto postDto);

	/**
	 * @PutMapping
	 * @param category, categoryId
	 * @return UserDto
	 * @throws ResourceNotFoundException
	 * @throws InvalidUserIdException
	 */
	PostDto updatePost(PostDto postDto, Integer postId) throws ResourceNotFoundException;

	/**
	 * @GetMapping -> By Id
	 * @param user
	 * @return UserDto
	 * @throws ResourceNotFoundException
	 */

	PostDto getPostById(Integer postId) throws ResourceNotFoundException;

	/**
	 * @DeleteMapping -> By Id
	 * @param userId
	 * @return void
	 * @throws ResourceNotFoundException
	 */
	void deletePostById(Integer postId) throws ResourceNotFoundException;

	/**
	 * @PutMapping By Postman
	 * @param category, categoryId
	 * @return UserDto
	 * @throws ResourceNotFoundException
	 * @throws InvalidUserIdException
	 */
	PostDto updatePostViaPostman(PostDto postDto, Integer postId) throws ResourceNotFoundException;

	/**
	 * @GetMapping -> By User
	 * @param user
	 * @return UserDto
	 * @throws ResourceNotFoundException
	 */
	List<PostDto> getPostByUser(Integer userId) throws ResourceNotFoundException;

	/**
	 * @GetMapping -> By Category
	 * @param user
	 * @return UserDto
	 * @throws ResourceNotFoundException
	 */
	List<PostDto> getPostByCategory(Integer categoryId) throws ResourceNotFoundException;

	/**
	 * Serch API
	 * 
	 * @param title
	 * @return
	 * @throws TitleSearchNotPresentException
	 */
	List<PostDto> searchByPostTitle(String title) throws TitleSearchNotPresentException;

}
