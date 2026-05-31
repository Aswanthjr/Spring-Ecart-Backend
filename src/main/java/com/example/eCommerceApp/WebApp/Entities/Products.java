package com.example.eCommerceApp.WebApp.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude = {"images", "reviews"})
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private String description;
    private String category;
    private Double ratings = 0.0;
    private String seller;
    private Integer stock = 0;
    private Integer numOfReviews = 0;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductsImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductsReview> reviews = new ArrayList<>();

    public Products(Long id, String name, Double price, String description, String category,
                    Double ratings, String seller, Integer stock, Integer numOfReviews, List<String> imageUrls) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.ratings = ratings;
        this.seller = seller;
        this.stock = stock;
        this.numOfReviews = numOfReviews;
        this.images = imageUrls.stream()
                .map(url -> new ProductsImage(url, this))
                .toList();
    }

    public Products() {}
}
