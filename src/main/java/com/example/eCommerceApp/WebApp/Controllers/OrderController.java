package com.example.eCommerceApp.WebApp.Controllers;

import com.example.eCommerceApp.WebApp.DTO.OrderCreatedDTO;
import com.example.eCommerceApp.WebApp.DTO.OrderRequestDTO;
import com.example.eCommerceApp.WebApp.Entities.Order;
import com.example.eCommerceApp.WebApp.Services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO){
        System.out.println(orderRequestDTO);
        System.out.println(orderRequestDTO.getOrderItems());
        OrderCreatedDTO orderCreatedDTO = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok().body(orderCreatedDTO);
    }

    @GetMapping("/{referenceId}")
    public ResponseEntity<?> getOrder(@PathVariable String referenceId){
        Order order = orderService.getOrder(referenceId);
        return ResponseEntity.ok().body(order);
    }
}
