package com.ecommerce.product.catalog.controller;

import com.ecommerce.product.catalog.exceptions.CreateProductException;
import com.ecommerce.product.catalog.exceptions.ProductsNotFoundException;
import com.ecommerce.product.catalog.models.domain.ProductVO;
import com.ecommerce.product.catalog.service.ProductCatalogService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductCatalogController {

    private final ProductCatalogService productService;

    @Autowired
    public ProductCatalogController(ProductCatalogService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductVO>> getAllProducts(@RequestParam(defaultValue = "0") Integer offset,
                                                          @RequestParam(defaultValue = "20") Integer size,
                                                          @RequestParam(required = false)
                                                          @Pattern(regexp = "price|name",
                                                                  message = "sort available only on price or name")
                                                          String sort) {
        List<ProductVO> fetchedProducts = productService.getAllProducts(offset, size, sort);
        if (fetchedProducts.isEmpty()) {
            throw new ProductsNotFoundException("Empty products list");
        }
        return ResponseEntity.ok(fetchedProducts);
    }

    @GetMapping("/{productName}")
    public ResponseEntity<ProductVO> fetchProduct(@PathVariable String productName) {
        Optional<ProductVO> productVo = productService.getProduct(productName);
        if (productVo.isEmpty()) {
            throw new ProductsNotFoundException(String.format("Product %s Could not be found", productName));
        }
        return ResponseEntity.ok(productVo.get());
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@Valid @RequestBody ProductVO productVo) {
        try {
            productService.addProduct(productVo);
        } catch (Exception ex) {
            throw new CreateProductException(
                    String.format("Error while adding the product. Exception = %s", ex.getMessage()));
        }
        return ResponseEntity.ok("Successfully added the product");
    }
}
