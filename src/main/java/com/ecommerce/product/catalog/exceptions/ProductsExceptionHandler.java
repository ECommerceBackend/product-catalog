package com.ecommerce.product.catalog.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductsExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsExceptionHandler.class);

    @ExceptionHandler(ProductsNotFoundException.class)
    public ResponseEntity<String> noContent(Exception ex) {
        LOGGER.info(ex.getMessage());
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CreateProductException.class)
    public ResponseEntity<String> internalError(Exception ex) {
        LOGGER.info(ex.getMessage());
        return ResponseEntity.internalServerError().body("Failed to create the product");
    }

}
