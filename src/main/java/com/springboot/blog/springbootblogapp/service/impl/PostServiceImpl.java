package com.springboot.blog.springbootblogapp.service.impl;

import com.springboot.blog.springbootblogapp.entity.Post;
import com.springboot.blog.springbootblogapp.exception.ResourceNotFoundException;
import com.springboot.blog.springbootblogapp.payload.PostDto;
import com.springboot.blog.springbootblogapp.repository.PostRepository;
import com.springboot.blog.springbootblogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;


    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=convertPostDtoToPost(postDto);
        Post newPost=postRepository.save(post);
        return convertPostToPostDto(newPost);
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        //  not support asc and desc
        // Pageable pageable=PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Pageable pageable=PageRequest.of(pageNo,pageSize, sort);
        Page<Post> page=postRepository.findAll(pageable);
        List<Post> posts=page.getContent();
        List<PostDto> postDtos=new ArrayList<>();
        posts.forEach((post)->{postDtos.add(convertPostToPostDto(post));});
        return postDtos;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",String.valueOf(id)));
        return convertPostToPostDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post =postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","ID",id+""));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return convertPostToPostDto(postRepository.save(post));
    }

    @Override
    public void deletePostById(long id) {
        Post post =postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","ID",id+""));
        postRepository.delete(post);
    }

    private Post convertPostDtoToPost(PostDto postDto)
    {
        Post post=new Post();
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());
        return post;
    }
    private PostDto convertPostToPostDto(Post post)
    {
        PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        postDto.setTitle(post.getTitle());
        return postDto;
    }
}
