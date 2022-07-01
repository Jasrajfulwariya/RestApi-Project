package com.springboot.blog.springbootblogapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldValue;
    private String fieldName;
    public ResourceNotFoundException(String resourceName,String fieldName,String fieldValue)
    {
        super(resourceName+" not found with "+fieldName+" : "+fieldValue);
        this.resourceName=resourceName;
        this.fieldValue=fieldValue;
        this.fieldName=fieldName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }
}
