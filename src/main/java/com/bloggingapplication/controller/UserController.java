package com.bloggingapplication.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

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

import com.bloggingapplication.exception.InvalidUserIdException;
import com.bloggingapplication.exception.ResourceNotFoundException;
import com.bloggingapplication.exception.UserNameNotFoundException;
import com.bloggingapplication.payloads.ApiResponse;
import com.bloggingapplication.payloads.UserDto;
import com.bloggingapplication.service.UserService;

//import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUser() {
		List<UserDto> receivedUsers = userService.getAllUser();
		return new ResponseEntity<>(receivedUsers, HttpStatus.OK);

	}

	@PostMapping("/register/users")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUser = userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer userId) throws ResourceNotFoundException, InvalidUserIdException {
		UserDto createdUser = userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);

	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId)
			throws ResourceNotFoundException {
		UserDto receivedUserById = userService.getUserById(userId);
		return new ResponseEntity<UserDto>(receivedUserById, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("userId") Integer userId)
			throws ResourceNotFoundException {
		userService.deleteUserById(userId);
		// return new ResponseEntity(Map.of("message", "User deleted sucessfully"),
		// HttpStatus.OK);

		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", new Date(), true),
				HttpStatus.OK);
	}

	@GetMapping("/users/userName/{userName}")
	public ResponseEntity<UserDto> getUserByUserName(@PathVariable("userName") String userName)
			throws UserNameNotFoundException {
		UserDto userByName = userService.getUserByUserName(userName);
		return new ResponseEntity<UserDto>(userByName, HttpStatus.OK);
	}

}
