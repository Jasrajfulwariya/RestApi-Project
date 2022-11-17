package com.springboot.blog.springbootblogapp.service.impl;

import com.springboot.blog.springbootblogapp.entity.Comment;
import com.springboot.blog.springbootblogapp.entity.Post;
import com.springboot.blog.springbootblogapp.exception.ResourceNotFoundException;
import com.springboot.blog.springbootblogapp.payload.CommentDto;
import com.springboot.blog.springbootblogapp.payload.PostDto;
import com.springboot.blog.springbootblogapp.repository.PostRepository;
import com.springboot.blog.springbootblogapp.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private static final Logger logger= LoggerFactory.getLogger(PostServiceImpl.class);
    @Autowired
    private PostRepository postRepository;


    @Override
    public PostDto createPost(PostDto postDto) {
        logger.info("creating post {} in DAO ",postDto);
        Post post=convertPostDtoToPost(postDto);
        Post newPost=postRepository.save(post);
        return convertPostToPostDto(newPost);
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {
        logger.info("getting all post for pageNo {} from DAO",pageNo);
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
        logger.info("getting post for postID {} from DAO",id);
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",String.valueOf(id)));
        return convertPostToPostDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        logger.info("update post for postID {} from DAO",id);
        Post post =postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","ID",id+""));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return convertPostToPostDto(postRepository.save(post));
    }

    @Override
    public void deletePostById(long id) {
        logger.info("delete post for postID {} form DAO",id);
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
        postDto.setComments(post.getComments().stream().map((x)->convertCommentToCommentDto(x)).collect(Collectors.toSet()));
        return postDto;
    }
    private CommentDto convertCommentToCommentDto(Comment dto)
    {
        CommentDto comment=new CommentDto();
        comment.setBody(dto.getBody());
        comment.setEmail(dto.getEmail());
        comment.setId(dto.getId());
        return comment;
    }
}
