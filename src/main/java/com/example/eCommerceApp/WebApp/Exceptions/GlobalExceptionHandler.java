package com.example.eCommerceApp.WebApp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductsNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleProductNotFound(ProductsNotFoundException exception){

        Map<String,Object> error = new HashMap<>();
        error.put("message", exception.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}
