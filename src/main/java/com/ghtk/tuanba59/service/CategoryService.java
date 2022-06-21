package com.ghtk.tuanba59.service;

import com.ghtk.tuanba59.model.entity.CategoryEntity;
import com.ghtk.tuanba59.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Page<CategoryEntity> getAll(int page, int pageSize){
        return categoryRepository.findAll(PageRequest.of(page,pageSize));
    }

    public CategoryEntity add(CategoryEntity product) {
        return categoryRepository.save(product);
    }

    public Iterable<CategoryEntity> addAll(Iterable<CategoryEntity> products) {
        return categoryRepository.saveAll(products);
    }

    public CategoryEntity update(CategoryEntity product, Long id) {
        product.setId(id);
        return categoryRepository.save(product);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
    public Optional<CategoryEntity> getById(Long id){
        return categoryRepository.findById(id);
    }
}
