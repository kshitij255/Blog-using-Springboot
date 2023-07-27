package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId, Integer categotyId);
	
	 PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	 PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
	
	 PostDto getPostById(Integer postId);
	 
	 List<PostDto> getPostByCategory(Integer categoryId);
	 
	 List<PostDto> getPostByUser(Integer userId);
	 
	 List<PostDto> searchPosts(String keyword);
	
}
