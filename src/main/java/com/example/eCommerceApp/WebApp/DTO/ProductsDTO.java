package com.example.eCommerceApp.WebApp.DTO;

import com.example.eCommerceApp.WebApp.Entities.ProductsImage;
import com.example.eCommerceApp.WebApp.Entities.ProductsReview;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class ProductsDTO {

        private Long id;

        @Column(nullable = false)
        @NotBlank(message = "Name Field is Required")
        private String name;

        @Column(nullable = false)
        @NotNull(message = "Price Field is Required")
        @PositiveOrZero(message = "Value must be Zero or Greater than Zero")
        private Double price;

        @NotBlank(message = "Description Field is Required")
        private String description;

        @NotBlank(message = "Category Field is Required")
        private String category;

        private Double ratings = 0.0;

        @Column(nullable = false)
        @NotBlank(message = "Seller Field is Required")
        private String seller;

        @NotNull(message = "stock Field is Required")
        private Integer stock = 0;

        private Integer numOfReviews = 0;

        private List<ProductsImageDTO> images;

        private List<ProductsReviewDTO> reviews;

        public ProductsDTO(Long id,String name, Double price, String description, String category, Double ratings, String seller, Integer stock, Integer numOfReviews) {
                this.id = id;
                this.name = name;
                this.price = price;
                this.description = description;
                this.category = category;
                this.ratings = ratings;
                this.seller = seller;
                this.stock = stock;
                this.numOfReviews = numOfReviews;
        }


        public ProductsDTO() {
        }
}
