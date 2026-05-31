package com.example.eCommerceApp.WebApp.Exceptions;


public class ProductsNotFoundException extends RuntimeException{

    public ProductsNotFoundException(String message){
        super(message);
    }
}
