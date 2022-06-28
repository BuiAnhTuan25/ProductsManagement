package com.ghtk.tuanba59.service;

import com.ghtk.tuanba59.model.dto.ProductDto;
import com.ghtk.tuanba59.model.entity.ProductEntity;
import com.ghtk.tuanba59.model.respone.Data;
import com.ghtk.tuanba59.model.respone.ListData;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ListData getAll(int page,int pageSize);
    Data add(ProductDto productDto);
    Data update(ProductDto productDto,Long id);
    Data delete(Long id);
    Data getById(Long id);
    ListData findByNameAndPrice(Pageable pageable,String name,int price);
}
