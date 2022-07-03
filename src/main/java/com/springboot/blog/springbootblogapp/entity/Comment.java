package com.springboot.blog.springbootblogapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="body")
    private String body;
    @Column(name="email")
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id",nullable=false)
    private Post post;

    public Comment(Long id, String body, String email, Post post) {
        this.id = id;
        this.body = body;
        this.email = email;
        this.post = post;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
