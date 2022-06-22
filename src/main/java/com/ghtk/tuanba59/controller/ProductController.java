package com.ghtk.tuanba59.controller;

import com.ghtk.tuanba59.model.entity.CategoryEntity;
import com.ghtk.tuanba59.model.entity.ProductEntity;
import com.ghtk.tuanba59.repository.ProductRepository;
import com.ghtk.tuanba59.service.CategoryService;
import com.ghtk.tuanba59.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> get(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page_size") int pageSize
    ) {
        return new ResponseEntity<>(productService.getAll(page, pageSize), HttpStatus.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(productService.getById(id),HttpStatus.valueOf(200));
    }

    @PostMapping("")
    public ResponseEntity<?> add(
            @RequestBody ProductEntity product
    ) {
        Optional<CategoryEntity> category = categoryService.getById(product.getCategoryId());
        if(category.isPresent()){
            return new ResponseEntity<>(productService.add(product), HttpStatus.valueOf(201));
        }
        return new ResponseEntity<>("add false", HttpStatus.valueOf(400));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestBody ProductEntity product,
            @PathVariable Long id
    ) {
        Optional<CategoryEntity> category = categoryService.getById(product.getCategoryId());
        if(category.isPresent()) {
            return new ResponseEntity<>(productService.update(product, id), HttpStatus.valueOf(201));
        }
        return new ResponseEntity<>("update false", HttpStatus.valueOf(400));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(productService.delete(id),HttpStatus.valueOf(200));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(value="page") int page,
            @RequestParam(value="page_size") int pageSize,
            @RequestParam(value="price") int price,
            @RequestParam(value="name") String name
    ){
        Page<ProductEntity> products=productService.findByNameContainingAndPriceGreaterThanOrderByPriceDesc(PageRequest.of(page,pageSize),name,price);
        return new ResponseEntity<>(products,HttpStatus.valueOf(200));
    }
}
