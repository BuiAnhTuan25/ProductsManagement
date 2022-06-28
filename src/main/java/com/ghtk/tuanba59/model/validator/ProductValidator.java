package com.ghtk.tuanba59.model.validator;

import com.ghtk.tuanba59.model.entity.ProductEntity;


import java.util.Optional;

public class ProductValidator {
    public boolean isValid(ProductEntity product) {
        return Optional.ofNullable(product)
                .filter(p -> !p.getName().equals(""))
                .filter(p -> p.getName().length() <= 100)
                .filter(p -> p.getPrice() > 0)
                .isPresent();
    }
}
