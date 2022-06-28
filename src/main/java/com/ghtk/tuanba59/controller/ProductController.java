package com.ghtk.tuanba59.controller;

import com.ghtk.tuanba59.model.dto.ProductDto;
import com.ghtk.tuanba59.model.respone.ListData;
import com.ghtk.tuanba59.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;


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
    ) {
        return new ResponseEntity<>(productService.getById(id), HttpStatus.valueOf(200));
    }

    @PostMapping("")
    public ResponseEntity<?> add(
            @RequestBody ProductDto productdto
    ) {
        return new ResponseEntity<>(productService.add(productdto), HttpStatus.valueOf(201));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestBody ProductDto productDto,
            @PathVariable Long id
    ) {
        return  new ResponseEntity<>(productService.update(productDto, id), HttpStatus.valueOf(201));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(productService.delete(id), HttpStatus.valueOf(200));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page_size") int pageSize,
            @RequestParam(value = "price") int price,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "sort_by") String sortBy
    ) {
        Pageable pageable = sortBy.equals("") ? PageRequest.of(page, pageSize) : PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        ListData products = productService.findByNameAndPrice(pageable, name, price);
        return new ResponseEntity<>(products, HttpStatus.valueOf(200));
    }
}
