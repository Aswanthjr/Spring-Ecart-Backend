package com.example.eCommerceApp.WebApp.DTO;

import com.example.eCommerceApp.WebApp.Entities.OrderItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDTO {

    @NotEmpty(message = "Order must contain at least one item")
    private List<@Valid OrderItemDTO> orderItems;
}
