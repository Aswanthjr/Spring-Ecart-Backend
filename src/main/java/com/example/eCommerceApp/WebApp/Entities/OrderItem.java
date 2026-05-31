package com.example.eCommerceApp.WebApp.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer quantity;

    @Lob
    private String image;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Products products;

    public OrderItem() {}

    public OrderItem(String name, Integer quantity, String image, Double price, Products products) {
        this.name = name;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
        this.products = products;
    }
}
