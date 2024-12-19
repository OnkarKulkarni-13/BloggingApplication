package com.bloggingapplication.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bloggingapplication.entity.User;
import com.bloggingapplication.exception.InvalidUserIdException;
import com.bloggingapplication.exception.ResourceNotFoundException;
import com.bloggingapplication.exception.UserNameNotFoundException;
import com.bloggingapplication.payloads.UserDto;
import com.bloggingapplication.repository.UserRepo;
import com.bloggingapplication.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * @GetMapping
	 */
	@Override
	public List<UserDto> getAllUser() {

		List<UserDto> userDto = new ArrayList<>();
		List<User> users = userRepo.findAll();
		for (User user : users) {
			userDto.add(this.userToUserDto(user));

		}
		return userDto;
	}

	/**
	 * @PostMapping
	 */
	@Override
	public UserDto createUser(UserDto userDto) {

		/**
		 * @param 1.Converting UserDTO => Actual User
		 * @param 2.           Saving user into the database
		 * @param 3.Converting User => UserDTO
		 */
		User user = userDtoTOUser(userDto);
		// Encoding Password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepo.save(user);
		return userToUserDto(savedUser);
	}

	/**
	 * @throws ResourceNotFoundException
	 * @throws InvalidUserIdException
	 * @PutMapping
	 */
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId)
			throws ResourceNotFoundException, InvalidUserIdException {

		if (userId > 0) {

			User user = userRepo.getById(userId);
			if (user == null) {
				throw new ResourceNotFoundException("User", "Id", userId);
			} else {
				user.setName(userDto.getName());
				user.setEmail(userDto.getEmail());
				user.setPassword(userDto.getPassword());
				user.setAbout(userDto.getAbout());

				userRepo.save(user);

			}

		} else {
			throw new InvalidUserIdException("Invalid", "User", userId);
		}
		userDto.setId(userId);
		return userDto;
	}

	/**
	 * @throws ResourceNotFoundException
	 * @GetMapping => By I'd
	 */
	@Override
	public UserDto getUserById(Integer userId) throws ResourceNotFoundException {

		User user;
		try {
			user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			throw new ResourceNotFoundException("User", "Id", userId);
		}

		return this.userToUserDto(user);
	}

	/**
	 * @throws ResourceNotFoundException
	 * @DeleteMapping
	 */
	@Override
	public void deleteUserById(Integer userId) throws ResourceNotFoundException {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		userRepo.delete(user);

	}

	/**
	 * @Converting UserDTO => Actual User
	 * @param userDto
	 * @return User
	 */
	public User userDtoTOUser(UserDto userDto) {

		User user = modelMapper.map(userDto, User.class);

		/**
		 * User user = new User(); user.setId(userDto.getId());
		 * user.setName(userDto.getName()); user.setEmail(userDto.getEmail());
		 * user.setPassword(userDto.getPassword()); user.setAbout(userDto.getAbout());
		 */
		return user;

	}

	/**
	 * @Converting userToUserDto
	 * @param user
	 * @return
	 */
	public UserDto userToUserDto(User user) {

		UserDto userDto = modelMapper.map(user, UserDto.class);

		/**
		 * UserDto userDto = new UserDto(); userDto.setId(user.getId());
		 * userDto.setName(user.getName()); userDto.setEmail(user.getEmail());
		 * userDto.setPassword(user.getPassword()); userDto.setAbout(user.getAbout());
		 */
		return userDto;

	}

	@Override
	public UserDto getUserByUserName(String userName) throws UserNameNotFoundException {
		User user = userRepo.getByName(userName);
		if (user == null) {
			throw new UserNameNotFoundException("User", "Name", userName);
		}

		return this.userToUserDto(user);
	}

}
