package com.example.eCommerceApp.WebApp.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products_reviews")
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude = "products")
public class ProductsReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

    public ProductsReview() {}

    public ProductsReview(Long id, Integer rating, String comment) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
    }
}
