package com.springboot.blog.springbootblogapp.payload;

import com.springboot.blog.springbootblogapp.entity.Post;

public class CommentDto {
    private Long id;
    private String body;
    private String email;
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
    public CommentDto() {
    }
}
