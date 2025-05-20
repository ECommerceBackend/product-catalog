package com.ecommerce.product.catalog.service.mapper;

import com.ecommerce.product.catalog.models.domain.ProductVO;
import com.ecommerce.product.catalog.models.persistence.ProductPO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("voToPo")
public class ProductPoMapper implements Mapper<ProductVO, ProductPO> {
    @Override
    public ProductPO map(ProductVO productVo) {
        ProductPO productPo = new ProductPO();
        productPo.setProductName(productVo.getProductName());
        productPo.setDescription(productVo.getDescription());
        productPo.setPrice(productVo.getPrice());
        productPo.setStockQuantity(productVo.getStockQuantity());
        productPo.setCategory(productVo.getCategory());
        productPo.setImageUrl(productVo.getImageUrl());
        productPo.setStatus(productVo.getStatus());
        return productPo;
    }
}
