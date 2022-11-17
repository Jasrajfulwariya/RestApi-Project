package com.springboot.blog.springbootblogapp;

import com.springboot.blog.springbootblogapp.entity.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBlogAppApplication {
	private static final Logger logger= LoggerFactory.getLogger(SpringBootBlogAppApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBlogAppApplication.class, args);
		logger.info("Application Started http://localhost:8080/healthCheck");
	}

}
