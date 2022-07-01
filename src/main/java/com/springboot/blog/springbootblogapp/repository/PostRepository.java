package com.springboot.blog.springbootblogapp.repository;

import com.springboot.blog.springbootblogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {


}
