package com.example.eCommerceApp.WebApp.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products_images")
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude = "products")
public class ProductsImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publicId;
    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Products products;

    public ProductsImage() {}

    public ProductsImage(Long id, String publicId, String url) {
        this.id = id;
        this.publicId = publicId;
        this.url = url;
    }

    public ProductsImage(String url, Products products) {
        this.url = "/uploads" + url;
        this.publicId = url;
        this.products = products;
    }
}
