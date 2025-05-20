package com.ecommerce.product.catalog.service.mapper;

import com.ecommerce.product.catalog.models.domain.ProductVO;
import com.ecommerce.product.catalog.models.persistence.ProductPO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("poToVo")
public class ProductVoMapper implements Mapper<ProductPO, ProductVO> {

    @Override
    public ProductVO map(ProductPO productPo) {
        ProductVO product = new ProductVO();
        product.setProductName(productPo.getProductName());
        product.setDescription(productPo.getDescription());
        product.setPrice(productPo.getPrice());
        product.setStockQuantity(productPo.getStockQuantity());
        product.setCategory(productPo.getCategory());
        product.setImageUrl(productPo.getImageUrl());
        product.setStatus(productPo.getStatus());
        product.setCreatedDate(productPo.getCreatedDate());
        product.setUpdatedDate(productPo.getUpdatedDate());
        return product;
    }
}
