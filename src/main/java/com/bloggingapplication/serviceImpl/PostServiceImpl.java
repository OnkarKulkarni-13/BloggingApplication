package com.bloggingapplication.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bloggingapplication.entity.Category;
import com.bloggingapplication.entity.Post;
import com.bloggingapplication.entity.User;
import com.bloggingapplication.exception.ResourceNotFoundException;
import com.bloggingapplication.exception.TitleSearchNotPresentException;
import com.bloggingapplication.payloads.PostDto;
import com.bloggingapplication.payloads.PostResponse;
import com.bloggingapplication.repository.CategoryRepo;
import com.bloggingapplication.repository.PostRepo;
import com.bloggingapplication.repository.UserRepo;
import com.bloggingapplication.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepo postRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	ModelMapper modelMapper;

	// Get All Without Pagination
	@Override
	public List<PostDto> getAllPost() {

		List<Post> posts = postRepo.findAll();
		
		List<PostDto> postDto = new ArrayList<>();
		for (Post post : posts) {
			postDto.add(modelMapper.map(post, PostDto.class));

		}
		return postDto;
	}

	// Pagination Method
	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Page<Post> pagePost = this.postRepo.findAll(pageable);

		List<Post> posts = pagePost.getContent();

		// List<Post> posts = postRepo.findAll();
		List<PostDto> postDto = new ArrayList<>();
		for (Post post : posts) {
			postDto.add(modelMapper.map(post, PostDto.class));

		}

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostDto addPost(PostDto postDto, Integer userId, Integer categoryId) throws ResourceNotFoundException {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		Post post = modelMapper.map(postDto, Post.class);
		post.setUser(user);
		post.setCategory(category);
		post.setImageName("Default.png");
		post.setAddedDate(new Date());

		Post createdPost = postRepo.save(post);

		return modelMapper.map(createdPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) throws ResourceNotFoundException {

		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		

		return postDto;
	}

	@Override
	public PostDto getPostById(Integer postId) throws ResourceNotFoundException {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		PostDto getPostDto = modelMapper.map(post, PostDto.class);
		return getPostDto;
	}

	@Override
	public void deletePostById(Integer postId) throws ResourceNotFoundException {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		postRepo.delete(post);

	}

	@Override
	public PostDto addPostViaPostman(PostDto postDto) {
		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("Default.png");
		post.setAddedDate(new Date());

		// Adding User TO DB
		User fetchUser = modelMapper.map(postDto.getUser(), User.class);
		userRepo.save(fetchUser);

		// Associate the User with the post
		post.setUser(fetchUser);

		// Adding Category To DB
		Category fetchCategory = modelMapper.map(postDto.getCategory(), Category.class);
		categoryRepo.save(fetchCategory);

		// Associate the category with the post
		post.setCategory(fetchCategory);

		// Adding Post To DB
		postRepo.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePostViaPostman(PostDto postDto, Integer postId) throws ResourceNotFoundException {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

		User fetchUser = modelMapper.map(postDto.getUser(), User.class);
		post.setUser(fetchUser);

		Category fetchCategory = modelMapper.map(postDto.getCategory(), Category.class);
		post.setCategory(fetchCategory);

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setAddedDate(postDto.getAddedDate());
		post.setImageName(postDto.getImageName());

		// Adding User TO DB
		userRepo.save(fetchUser);

		// Adding Category To DB
		categoryRepo.save(fetchCategory);

		// Adding Post To DB
		Post updatedPost = postRepo.save(post);
		return modelMapper.map(updatedPost, PostDto.class);

	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) throws ResourceNotFoundException {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) throws ResourceNotFoundException {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;

	}

	@Override
	public List<PostDto> searchByPostTitle(String title) throws TitleSearchNotPresentException {
		List<Post> posts = postRepo.findByTitleContaining(title);
		if (posts.isEmpty()) {
			throw new TitleSearchNotPresentException("Title", "Titlename", title);
		}
		List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

}
