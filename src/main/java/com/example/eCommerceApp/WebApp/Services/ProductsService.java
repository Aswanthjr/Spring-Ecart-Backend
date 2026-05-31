package com.example.eCommerceApp.WebApp.Services;

import com.example.eCommerceApp.WebApp.DTO.ProductsDTO;
import com.example.eCommerceApp.WebApp.DTO.ProductsImageDTO;
import com.example.eCommerceApp.WebApp.DTO.ProductsReviewDTO;
import com.example.eCommerceApp.WebApp.Entities.Products;
import com.example.eCommerceApp.WebApp.Entities.ProductsImage;
import com.example.eCommerceApp.WebApp.Entities.ProductsReview;
import com.example.eCommerceApp.WebApp.Exceptions.ProductsNotFoundException;
import com.example.eCommerceApp.WebApp.Repositories.ProductRepo;
import com.example.eCommerceApp.WebApp.Repositories.ProductReviewRepo;
import com.example.eCommerceApp.WebApp.Utilities.ProductSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ProductsService {

    private final ProductRepo productRepo;
    private final ProductReviewRepo productReviewRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductsService(ProductRepo productRepo, ProductReviewRepo productReviewRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.productReviewRepo = productReviewRepo;
        this.modelMapper = modelMapper;
    }

    public Map<String,Object> getAllProducts(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Products> productList = productRepo.findAll(pageable);
        Page<ProductsDTO> dtoPage = productList
                .map(products -> modelMapper.map(products, ProductsDTO.class));
        Map<String,Object> response = new HashMap<>();
        response.put("products",dtoPage.getContent());
        response.put("TotalElements", dtoPage.getTotalElements());
        response.put("TotalPages",dtoPage.getTotalPages());
        return response;
    }

    public ProductsDTO convertToDto(Products product) {
        ProductsDTO dto = new ProductsDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setRatings(product.getRatings());
        dto.setCategory(product.getCategory());
        dto.setSeller(product.getSeller());
        dto.setStock(product.getStock());
        dto.setNumOfReviews(product.getNumOfReviews());

        List<ProductsReviewDTO>  reviewDtos = product.getReviews().stream().map(review -> {
            ProductsReviewDTO reviewDto = new ProductsReviewDTO();
            reviewDto.setProductId(review.getId());
            reviewDto.setComment(review.getComment());
            reviewDto.setRating(review.getRating());
            return reviewDto;
        }).toList();

        dto.setReviews(reviewDtos);

        List<ProductsImageDTO>  imageDtos = product.getImages().stream().map(image -> {
            ProductsImageDTO imageDto = new ProductsImageDTO(image.getPublicId());
            return imageDto;
        }).collect(Collectors.toList());

        dto.setImages(imageDtos);
        return dto;
    }

    public ProductsDTO getProductsById(Long id){
        Products products = productRepo.findById(id)
                .orElseThrow(() -> new ProductsNotFoundException("Product Not Found With This Id : " + id));
        return modelMapper.map(products,ProductsDTO.class);
    }

    public List<ProductsDTO> searchProducts(String category, Double minPrice, Double maxPrice, String keyword, Double ratings){
        Specification<Products> spec = Specification.where(ProductSpecification.hasCategory(category))
                .and(ProductSpecification.priceBetween(minPrice,maxPrice))
                .and(ProductSpecification.hasNameOrDescriptionLike(keyword))
                .and(ProductSpecification.ratingGreaterThan(ratings));
        List<Products> products = productRepo.findAll(spec);
        return products.stream()
                .map(products1 -> modelMapper.map(products1,ProductsDTO.class))
                .toList();
    }

    public void addReview(ProductsReviewDTO productsReviewDTO){
        Products products = productRepo.findById(productsReviewDTO.getProductId())
                .orElseThrow(() -> new ProductsNotFoundException("Product Not Found With This Id : " + productsReviewDTO.getProductId()));

        ProductsReview review = new ProductsReview();
        review.setComment(productsReviewDTO.getComment());
        review.setRating(productsReviewDTO.getRating());
        review.setProducts(products);
        productReviewRepo.save(review);
    }


}
