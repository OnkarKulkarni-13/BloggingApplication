package com.bloggingapplication.service;

import java.util.List;

import com.bloggingapplication.exception.InvalidUserIdException;
import com.bloggingapplication.exception.ResourceNotFoundException;
import com.bloggingapplication.exception.UserNameNotFoundException;
import com.bloggingapplication.payloads.UserDto;

public interface UserService {

	/**
	 * @GetMapping
	 * @param NA
	 * @return List<UserDto>
	 */
	List<UserDto> getAllUser();

	/**
	 * @PostMapping
	 * @param user
	 * @return UserDto
	 */
	UserDto createUser(UserDto user);

	/**
	 * @PutMapping
	 * @param user
	 * @return UserDto
	 * @throws ResourceNotFoundException
	 * @throws InvalidUserIdException
	 */
	UserDto updateUser(UserDto user, Integer userId) throws ResourceNotFoundException, InvalidUserIdException;

	/**
	 * @GetMapping -> By Id
	 * @param user
	 * @return UserDto
	 * @throws ResourceNotFoundException
	 */
	UserDto getUserById(Integer userId) throws ResourceNotFoundException;

	/**
	 * @DeleteMapping -> By Id
	 * @param userId
	 * @return void
	 * @throws ResourceNotFoundException
	 */
	void deleteUserById(Integer userId) throws ResourceNotFoundException;

	/**
	 * @GetMapping -> ByName
	 * @param userName
	 * @return UserDto
	 * @throws UserNameNotFoundException
	 * @throws ResourceNotFoundException
	 */
	UserDto getUserByUserName(String userName) throws UserNameNotFoundException;

}
