package com.springboot.blog.springbootblogapp.controller;

import com.springboot.blog.springbootblogapp.payload.PostDto;
import com.springboot.blog.springbootblogapp.service.PostService;
import com.springboot.blog.springbootblogapp.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private static final Logger logger= LoggerFactory.getLogger(PostController.class);
    @Autowired
    @Qualifier("postServiceImpl")
    private PostService postService;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        logger.info("creating post {}",postDto);
        return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<PostDto> getAllPost(@RequestParam(value=Constants.PAGE_NO,defaultValue = Constants.PAGE_NO_DEF,required = false)int pageNo,
                                    @RequestParam(value=Constants.PAGE_SIZE,defaultValue = Constants.PAGE_SIZE_DEF,required = false) int pageSize,
                                    @RequestParam(value=Constants.SORT_BY,defaultValue = Constants.SORT_BY_DEF,required = false) String sortBy,
                                    @RequestParam(value=Constants.SORT_DIR,defaultValue =Constants.SORT_DIR_DEF,required = false)String sortDir) {
        logger.info("getting post for pageNo {} ",pageNo);
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }
    @RequestMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id) {
        logger.info("getting post for postID {} ",id);
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(name = "id") long id) {
        logger.info("updating post for postID {}",id);
        return new ResponseEntity<>(postService.updatePost(postDto,id),HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePost(@PathVariable(name="id") long id) {
        logger.info("deleting post for postID {}",id);
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Entity Deleted Successfully",HttpStatus.OK);
    }
}
