package com.example.eCommerceApp.WebApp.Controllers;

import com.example.eCommerceApp.WebApp.DTO.ProductsDTO;
import com.example.eCommerceApp.WebApp.Entities.Products;
import com.example.eCommerceApp.WebApp.Services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping(value = "api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductsService productsService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "5") int size){
        Map<String, Object> products = productsService.getAllProducts(page, size);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductsDTO> getProductsById(@PathVariable Long id){
        ProductsDTO products = productsService.getProductsById(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<ProductsDTO>> searchProducts(@RequestParam(required = false) String category,
                                                            @RequestParam(required = false) Double minPrice,
                                                            @RequestParam(required = false) Double maxPrice,
                                                            @RequestParam(required = false) String keyword,
                                                            @RequestParam(required = false) Double ratings){
        List<ProductsDTO> productsDTO = productsService.searchProducts(category, minPrice, maxPrice, keyword, ratings);
        return new ResponseEntity<>(productsDTO,HttpStatus.OK);
    }
}
