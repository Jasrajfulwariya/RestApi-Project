package com.springboot.blog.springbootblogapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @RequestMapping("/healthCheck")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("Running...", HttpStatus.OK);
    }
}
