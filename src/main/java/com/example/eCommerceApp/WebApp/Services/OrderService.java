package com.example.eCommerceApp.WebApp.Services;

import com.example.eCommerceApp.WebApp.DTO.OrderCreatedDTO;
import com.example.eCommerceApp.WebApp.DTO.OrderItemDTO;
import com.example.eCommerceApp.WebApp.DTO.OrderRequestDTO;
import com.example.eCommerceApp.WebApp.Entities.Order;
import com.example.eCommerceApp.WebApp.Entities.OrderItem;
import com.example.eCommerceApp.WebApp.Entities.Products;
import com.example.eCommerceApp.WebApp.Exceptions.ProductsNotFoundException;
import com.example.eCommerceApp.WebApp.Repositories.OrderRepo;
import com.example.eCommerceApp.WebApp.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {

    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    @Transactional
    public OrderCreatedDTO createOrder(OrderRequestDTO dto) {

        Order order = new Order();
        order.setStatus("PENDING");

        double totalItemsAmount = 0;

        // IMPORTANT: prevent null crash
        if (dto.getOrderItems() == null || dto.getOrderItems().isEmpty()) {
            throw new IllegalArgumentException("Order items cannot be empty");
        }

        for (OrderItemDTO item : dto.getOrderItems()) {

            Products product = productRepo.findById(item.getProductId())
                    .orElseThrow(() ->
                            new ProductsNotFoundException("Product Not Found: " + item.getProductId())
                    );

            OrderItem orderItem = new OrderItem();
            orderItem.setName(product.getName());
            orderItem.setPrice(product.getPrice());
            orderItem.setImage(product.getImages().getFirst().getUrl());
            orderItem.setQuantity(item.getQuantity());

            orderItem.setProducts(product);

            totalItemsAmount += product.getPrice() * item.getQuantity();

            order.getOrderItems().add(orderItem);
        }

        order.setTotalItemsAmount(totalItemsAmount);

        double taxAmount = 10.0;
        order.setTotalAmount(totalItemsAmount + taxAmount);

        String refId = UUID.randomUUID().toString();
        order.setReferenceId(refId);

        Order savedOrder = orderRepo.save(order);
        return new OrderCreatedDTO(savedOrder.getReferenceId());
    }

    public Order getOrder(String referenceId){
        return orderRepo.findByReferenceId(referenceId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + referenceId));
    }
}
