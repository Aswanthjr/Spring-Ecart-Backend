package com.example.eCommerceApp.WebApp.Controllers;

import com.example.eCommerceApp.WebApp.DTO.ProductsReviewDTO;
import com.example.eCommerceApp.WebApp.Services.ProductsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products/reviews")
public class ProductReviewController {

    private final ProductsService productsService;

    @Autowired
    public ProductReviewController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody @Valid ProductsReviewDTO productsReviewDTO){
        productsService.addReview(productsReviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Review Added");
    }
}
