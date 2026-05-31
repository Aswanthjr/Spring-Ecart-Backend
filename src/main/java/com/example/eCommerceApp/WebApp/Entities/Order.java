package com.example.eCommerceApp.WebApp.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")   // ← This also uses "order_id"
    private List<OrderItem> orderItems = new ArrayList<>();

    private Double totalItemsAmount;
    private Double taxAmount;
    private Double totalAmount;
    private String status;
    private String referenceId;
}
