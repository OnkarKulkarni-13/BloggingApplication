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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingapplication.exception.ResourceNotFoundException;
import com.bloggingapplication.exception.TitleSearchNotPresentException;
import com.bloggingapplication.payloads.ApiResponse;
import com.bloggingapplication.payloads.PostDto;
import com.bloggingapplication.payloads.PostResponse;
import com.bloggingapplication.service.PostService;
import javax.validation.Valid;
//import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	PostService postService;

	// GetAll Without pagination
	@GetMapping("/post")
	public ResponseEntity<List<PostDto>> getAllPost() {

		List<PostDto> postDto = postService.getAllPost();

		return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.OK);

	}

//Pagination GetAll Controller
	@GetMapping("pagination/post")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize) {

		// List<PostDto> postDto = postService.getAllPost(pageNumber, pageSize);

		PostResponse postResponse = postService.getAllPost(pageNumber, pageSize);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

		// return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.OK);

	}

	@PostMapping("/register/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> addPost(@Valid @RequestBody PostDto postDto, @PathVariable("userId") Integer userId,
			@PathVariable("categoryId") Integer categoryId) throws ResourceNotFoundException {

		PostDto createdPost = postService.addPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);

	}

	@PostMapping("/register/post")
	public ResponseEntity<PostDto> addPostViaPostman(@Valid @RequestBody PostDto postDto) {

		PostDto createdPostViaPostman = postService.addPostViaPostman(postDto);

		return new ResponseEntity<PostDto>(createdPostViaPostman, HttpStatus.CREATED);
	}

	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePostViaPostman(@Valid @RequestBody PostDto postDto,
			@PathVariable Integer postId) throws ResourceNotFoundException {
		PostDto updatedPost = postService.updatePostViaPostman(postDto, postId);

		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.CREATED);
	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId)
			throws ResourceNotFoundException {

		PostDto postDto = postService.getPostById(postId);

		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable("postId") Integer postId)
			throws ResourceNotFoundException {
		postService.deletePostById(postId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully", new Date(), true),
				HttpStatus.OK);

	}

	@GetMapping("/user/{userId}/post")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) throws ResourceNotFoundException {
		List<PostDto> postDtos = postService.getPostByUser(userId);

		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}/post")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
			throws ResourceNotFoundException {
		List<PostDto> postDtos = postService.getPostByCategory(categoryId);

		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}

	@GetMapping("/searchBy/{titleName}/post")
	public ResponseEntity<List<PostDto>> searchByPostTitle(@PathVariable("titleName") String titleName)
			throws TitleSearchNotPresentException {
		List<PostDto> postDtos = postService.searchByPostTitle(titleName);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}

}
