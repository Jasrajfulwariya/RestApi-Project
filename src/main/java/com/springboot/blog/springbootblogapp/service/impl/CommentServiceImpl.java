package com.springboot.blog.springbootblogapp.service.impl;

import com.springboot.blog.springbootblogapp.entity.Comment;
import com.springboot.blog.springbootblogapp.entity.Post;
import com.springboot.blog.springbootblogapp.exception.BlogApiException;
import com.springboot.blog.springbootblogapp.exception.ResourceNotFoundException;
import com.springboot.blog.springbootblogapp.payload.CommentDto;
import com.springboot.blog.springbootblogapp.repository.CommentRepository;
import com.springboot.blog.springbootblogapp.repository.PostRepository;
import com.springboot.blog.springbootblogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService
{
    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public CommentDto createComment(long postId, CommentDto dto) {

        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId+""));
        Comment comment=convertCommentDtoToComment(dto);
        comment.setPost(post);
        comment=commentRepository.save(comment);
        return convertCommentToCommentDto(comment);
    }

    @Override
    public Set<CommentDto> getAllComment(long postId) {
        Set<Comment> set = commentRepository.findByPostId(postId);
        return set.stream().map((x)->{return convertCommentToCommentDto(x);}).collect(Collectors.toSet());
    }

    @Override
    public CommentDto getCommentById(long postId, long id) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId+""));
        Comment comment=commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("comment","Id",id+""));
        if(!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogApiException("Comment Not Belong To Post",HttpStatus.BAD_REQUEST);
        }
        return convertCommentToCommentDto(comment);
    }

    @Override
    public CommentDto updateCommentById(long postId, long id, CommentDto dto) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId+""));
        Comment comment=commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("comment","Id",id+""));
        if(!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogApiException("Comment Not Belong To Post",HttpStatus.BAD_REQUEST);
        }
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
        return convertCommentToCommentDto(commentRepository.save(comment));
    }

    @Override
    public void deleteCommentById(long postId, long id) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId+""));
        Comment comment=commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("comment","Id",id+""));
        if(!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogApiException("Comment Not Belong To Post",HttpStatus.BAD_REQUEST);
        }
        commentRepository.delete(comment);
    }

    private Comment convertCommentDtoToComment(CommentDto dto)
    {
        Comment comment=new Comment();
        comment.setId(dto.getId());
        comment.setBody(dto.getBody());
        comment.setEmail(dto.getEmail());
        return comment;
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
