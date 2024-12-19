package com.bloggingapplication;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.Mapping;

@SpringBootApplication
public class BloggingApplicationSpringBootApplication implements CommandLineRunner {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BloggingApplicationSpringBootApplication.class, args);
		System.out.println("Blogging Application Started..!!!");
	}

	@Bean

	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Encoded Password:-> " + passwordEncoder.encode("onkar"));
	}

}
