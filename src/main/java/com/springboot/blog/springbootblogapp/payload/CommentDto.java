package com.springboot.blog.springbootblogapp.payload;

import com.springboot.blog.springbootblogapp.entity.Post;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CommentDto {
    private Long id;
    @NotEmpty
    @Size(min = 10,message = "Body At least 10 char")
    private String body;
    @NotEmpty(message = "email should not empty or null")
    @Email
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
