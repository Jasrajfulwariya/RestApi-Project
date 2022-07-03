package com.springboot.blog.springbootblogapp.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException
{
    private HttpStatus status;
    private String message;
    public BlogApiException(String message,HttpStatus status)
    {
        super(message);
        this.message=message;
        this.status=status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
