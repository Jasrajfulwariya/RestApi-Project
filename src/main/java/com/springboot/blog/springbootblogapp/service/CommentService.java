package com.springboot.blog.springbootblogapp.service;

import com.springboot.blog.springbootblogapp.payload.CommentDto;

import java.util.List;
import java.util.Set;

public interface CommentService {

    public CommentDto createComment(long postId,CommentDto dto);

    Set<CommentDto> getAllComment(long postId);

    CommentDto getCommentById(long postId, long id);

    CommentDto updateCommentById(long postId, long id,CommentDto dto);

    void deleteCommentById(long postId, long id);
}
