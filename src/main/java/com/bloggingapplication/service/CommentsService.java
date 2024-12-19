package com.bloggingapplication.service;

import com.bloggingapplication.exception.ResourceNotFoundException;
import com.bloggingapplication.payloads.CommentsDto;

public interface CommentsService {

	public CommentsDto createComments(CommentsDto commentDto, Integer postId) throws ResourceNotFoundException;

	public void deleteComments(Integer commentId) throws ResourceNotFoundException;

}
