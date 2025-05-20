package com.ecommerce.product.catalog.service;

import com.ecommerce.product.catalog.models.domain.ProductVO;
import com.ecommerce.product.catalog.models.persistence.ProductPO;
import com.ecommerce.product.catalog.repository.ProductRepository;
import com.ecommerce.product.catalog.service.mapper.Mapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductCatalogService {

    private final ProductRepository productRepository;
    private final Mapper<ProductPO, ProductVO> poToVoMapper;
    private final Mapper<ProductVO, ProductPO> voToPoMapper;

    @Autowired
    public ProductCatalogService(ProductRepository productRepository,
                                 @Qualifier("poToVo")
                                 Mapper<ProductPO, ProductVO> poToVoMapper,
                                 @Qualifier("voToPo")
                                 Mapper<ProductVO, ProductPO> voToPoMapper) {
        this.productRepository = productRepository;
        this.poToVoMapper = poToVoMapper;
        this.voToPoMapper = voToPoMapper;
    }

    public List<ProductVO> getAllProducts(Integer offset,
                                          Integer size,
                                          String sortProperty) {
        Sort sort =
                StringUtils.hasLength(sortProperty) ?
                        Sort.by(new Sort.Order(Sort.Direction.ASC, sortProperty)) :
                        Sort.unsorted();
        Pageable pageable = PageRequest.of(offset, size, sort);
        Page<ProductPO> products = productRepository.findAll(pageable);
        return products.stream().map(poToVoMapper::map).toList();
    }

    public void addProduct(@Valid ProductVO productVo) {
        ProductPO productPo = voToPoMapper.map(productVo);
        productRepository.save(productPo);
    }

    public Optional<ProductVO> getProduct(String name) {
        ProductPO productPO = productRepository.findByProductName(name);
        if (Objects.isNull(productPO)) {
            return Optional.empty();
        }
        ProductVO productVO = poToVoMapper.map(productPO);
        return Optional.of(productVO);

    }
}
