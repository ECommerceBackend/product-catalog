package com.ecommerce.product.catalog.repository;

import com.ecommerce.product.catalog.models.persistence.ProductPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductPO, Long> {
    ProductPO findByProductName(String name);
}
