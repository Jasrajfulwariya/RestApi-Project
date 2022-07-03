package com.springboot.blog.springbootblogapp.repository;

import com.springboot.blog.springbootblogapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment,Long>
{
    Set<Comment> findByPostId(long postId);
}
