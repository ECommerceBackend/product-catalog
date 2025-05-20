package com.ecommerce.product.catalog.exceptions;

public class ProductsNotFoundException extends RuntimeException {

    public ProductsNotFoundException(String message) {
        super(message);
    }
}
