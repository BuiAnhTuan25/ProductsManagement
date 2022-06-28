package com.ghtk.tuanba59.service;

import com.ghtk.tuanba59.constants.StatusEnum;
import com.ghtk.tuanba59.model.dto.ProductDto;
import com.ghtk.tuanba59.model.entity.CategoryEntity;
import com.ghtk.tuanba59.model.entity.ProductEntity;
import com.ghtk.tuanba59.model.respone.Data;
import com.ghtk.tuanba59.model.respone.ListData;
import com.ghtk.tuanba59.model.respone.Pagination;
import com.ghtk.tuanba59.model.validator.ProductValidator;
import com.ghtk.tuanba59.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductValidator productValidator;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ListData getAll(int page, int pageSize) {
        Page<ProductEntity> pageProduct = productRepository.findAllByStatus(PageRequest.of(page, pageSize), StatusEnum.ACTIVE);
        Pagination pagination = new Pagination(pageProduct.getNumber(), pageProduct.getSize(), pageProduct.getTotalPages(), (int) pageProduct.getTotalElements());
        List<ProductDto> productDtoList = pageProduct.getContent().stream().map(p -> modelMapper.map(p, ProductDto.class)).collect(Collectors.toList());
        return new ListData(true, "success", productDtoList, pagination);
    }

    @Override
    public Data add(ProductDto productDto) {
        ProductEntity product = modelMapper.map(productDto, ProductEntity.class);
        SimpleDateFormat dateFor = new SimpleDateFormat("yyyyMMdd");
        Optional<CategoryEntity> category = categoryService.getById(product.getCategoryId());
        if (category.isPresent() && productValidator.isValid(product)) {
            product.setCreatedAt(LocalDateTime.now());
            product.setModifiedAt(LocalDateTime.now());
            product.setStatus(StatusEnum.ACTIVE);
            product.setCode(product.getCategoryId() + "." + product.getSku() + "." + dateFor.format(Date.valueOf(product.getCreatedAt().toLocalDate())));
            return new Data(true, "success", modelMapper.map(productRepository.save(product), ProductDto.class));
        }
        return new Data(false, "add false", null);
    }

    @Override
    public Data update(ProductDto productDto, Long id) {
        ProductEntity product = productRepository.findById(id).get().setValueFromDto(productDto);
        Optional<CategoryEntity> category = categoryService.getById(product.getCategoryId());
        if (category.isPresent() && productValidator.isValid(product)) {
            product.setId(id);
            product.setModifiedAt(LocalDateTime.now());
            return new Data(true, "success", modelMapper.map(productRepository.save(product), ProductDto.class));
        }
        return new Data(false, "update false", null);
    }

    @Override
    public Data delete(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        product.setStatus(StatusEnum.INACTIVE);
        productRepository.save(product);
        return new Data(true, "success", modelMapper.map(product, ProductDto.class));
    }

    @Override
    public Data getById(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return new Data(true, "success", modelMapper.map(product, ProductDto.class));
    }

    @Override
    public ListData findByNameAndPrice(Pageable pageable, String name, int price) {
        Page<ProductEntity> pageProduct = productRepository.findByNameAndPrice(pageable, name, price);
        Pagination pagination = new Pagination(pageProduct.getNumber(), pageProduct.getSize(), pageProduct.getTotalPages(), (int) pageProduct.getTotalElements());
        List<ProductDto> productDtoList = pageProduct.getContent().stream().map(p -> modelMapper.map(p, ProductDto.class)).collect(Collectors.toList());
        return new ListData(true, "success", productDtoList, pagination);
    }
}
