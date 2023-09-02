package com.miniproject.springboot.designpattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
    public static <T> ResponseEntity<T> build(T body,HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }

    private ResponseEntityBuilder() {
        // This private constructor prevents the class from being instantiated
        throw new AssertionError("Utility class. Instantiation not allowed.");
    }
}
