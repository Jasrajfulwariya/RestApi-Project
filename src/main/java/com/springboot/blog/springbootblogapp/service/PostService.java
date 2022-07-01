package com.springboot.blog.springbootblogapp.service;

import com.springboot.blog.springbootblogapp.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(long id);
}
