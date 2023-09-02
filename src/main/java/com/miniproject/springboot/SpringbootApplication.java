package com.miniproject.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringbootApplication.class, args);
		byte[] imageBytes = Files.readAllBytes(Paths.get("C:/Users/a880521/Postman/files/download (1).jpg"));
		String base64ImageData = Base64.getEncoder().encodeToString(imageBytes);
		System.out.println(base64ImageData);
	}

}
