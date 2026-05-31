package com.example.eCommerceApp.WebApp.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreatedDTO {
    private String referenceId;

    public OrderCreatedDTO(String refId) {
        this.referenceId = refId;
    }
}
