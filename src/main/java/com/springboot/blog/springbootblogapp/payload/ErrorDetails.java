package com.springboot.blog.springbootblogapp.payload;

import java.util.Date;

public class ErrorDetails
{
    private Date time;
    private String message;
    private String details;

    public ErrorDetails(Date time, String message, String details) {
        this.time = time;
        this.message = message;
        this.details = details;
    }

    public Date getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
