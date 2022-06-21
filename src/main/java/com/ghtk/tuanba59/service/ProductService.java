package com.ghtk.tuanba59.service;

import com.ghtk.tuanba59.model.entity.ProductEntity;
import com.ghtk.tuanba59.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Page<ProductEntity> getAll(int page, int pageSize) {
        return productRepository.findAll(PageRequest.of(page, pageSize));
    }

    public ProductEntity add(ProductEntity product) {
        return productRepository.save(product);
    }

    public Iterable<ProductEntity> addAll(Iterable<ProductEntity> products) {
        return productRepository.saveAll(products);
    }

    public ProductEntity update(ProductEntity product, Long id) {
        product.setId(id);
        return productRepository.save(product);
    }

    public Optional<ProductEntity> delete(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        productRepository.deleteById(id);
        return product;
    }

    public Optional<ProductEntity> getById(Long id) {
        return productRepository.findById(id);
    }

    public Page<ProductEntity> findByNameContainingAndPriceGreaterThanOrderByPriceDesc(Pageable pageable, String name,int price){
        return productRepository.findByNameContainingAndPriceGreaterThanOrderByPriceDesc(pageable,name,price);
    }
}
