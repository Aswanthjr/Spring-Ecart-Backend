package com.example.eCommerceApp.WebApp.DTO;

import com.example.eCommerceApp.WebApp.Entities.Products;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class ProductsImageDTO {

    @NotBlank(message = "url is required")
    private String url;

    public ProductsImageDTO() {
    }

    public ProductsImageDTO(String url) {
        super();
        this.url = url;
    }


}
