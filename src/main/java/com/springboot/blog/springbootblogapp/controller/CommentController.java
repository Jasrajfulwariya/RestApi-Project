package com.springboot.blog.springbootblogapp.controller;

import com.springboot.blog.springbootblogapp.payload.CommentDto;
import com.springboot.blog.springbootblogapp.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/posts")
public class CommentController
{
    private static final Logger logger= LoggerFactory.getLogger(CommentController.class);
    @Autowired
    CommentService service;

    @RequestMapping(value = "/{postId}/comments",method = RequestMethod.POST)
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto dto, @PathVariable(name = "postId") long postId ) {
        logger.info("creating comment for postID {} ",postId);
        return new ResponseEntity<>(service.createComment(postId,dto),HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{postId}/comments",method = RequestMethod.GET)
    public Set<CommentDto> getAllComment(@PathVariable(name="postId")long postId) {
        logger.info("getting all comment for postID {} ",postId);
        return service.getAllComment(postId);
    }
    @RequestMapping(value = "/{postId}/comments/{id}",method = RequestMethod.GET)
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name="postId")long postId,@PathVariable(name="id")long id) {
        logger.info("getting comment for postID {} and commentID {}",postId,id);
        return new ResponseEntity<>(service.getCommentById(postId,id),HttpStatus.OK);
    }
    @RequestMapping(value = "/{postId}/comments/{id}",method = RequestMethod.PUT)
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable(name="postId")long postId,@PathVariable(name="id")long id,@Valid@RequestBody CommentDto comment) {
        logger.info("updating comment for postID {} and commentID {}",postId,id);
        return new ResponseEntity<>(service.updateCommentById(postId,id,comment),HttpStatus.OK);
    }
    @RequestMapping(value = "/{postId}/comments/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCommentById(@PathVariable(name="postId")long postId,@PathVariable(name="id")long id) {
        logger.info("deleting comment for postID {} and commentID {}",postId,id);
        service.deleteCommentById(postId,id);
        return new ResponseEntity<>("Comment Delete Successfully",HttpStatus.OK);
    }
}
