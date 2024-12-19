package com.bloggingapplication.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingapplication.exception.ResourceNotFoundException;
import com.bloggingapplication.payloads.ApiResponse;
import com.bloggingapplication.payloads.CommentsDto;
import com.bloggingapplication.service.CommentsService;
import javax.validation.Valid;
//import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CommentsController {

	@Autowired
	private CommentsService commentService;

	@PostMapping("/register/post/{postId}/comments")
	public ResponseEntity<CommentsDto> createComments(@Valid @RequestBody CommentsDto commentsDto,
			@PathVariable("postId") Integer postId) throws ResourceNotFoundException {
		CommentsDto createdComments = commentService.createComments(commentsDto, postId);

		return new ResponseEntity<CommentsDto>(createdComments, HttpStatus.CREATED);

	}

	@DeleteMapping("/comments/{commentsId}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("commentsId") Integer commentsId)
			throws ResourceNotFoundException {
		commentService.deleteComments(commentsId);

		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Comment with I'd" + commentsId + " Deleted ", new Date(), true), HttpStatus.OK);
	}

}
