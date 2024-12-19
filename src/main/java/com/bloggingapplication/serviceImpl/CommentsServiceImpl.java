package com.bloggingapplication.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingapplication.entity.Comments;
import com.bloggingapplication.entity.Post;
import com.bloggingapplication.exception.ResourceNotFoundException;
import com.bloggingapplication.payloads.CommentsDto;
import com.bloggingapplication.repository.CommentsRepo;
import com.bloggingapplication.repository.PostRepo;
import com.bloggingapplication.service.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	PostRepo postRepo;

	@Autowired
	CommentsRepo commentsRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public CommentsDto createComments(CommentsDto commentDto, Integer postId) throws ResourceNotFoundException {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		Comments comment = modelMapper.map(commentDto, Comments.class);
		comment.setPost(post);
		// comment.setContent(commentDto.getContent());
		// comment.setCommentId(commentDto.getCommentId());
		Comments savedComment = this.commentsRepo.save(comment);

		return modelMapper.map(savedComment, CommentsDto.class);
	}

	@Override
	public void deleteComments(Integer commentId) throws ResourceNotFoundException {
		Comments comment = commentsRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
		commentsRepo.delete(comment);

		return;

	}

}
