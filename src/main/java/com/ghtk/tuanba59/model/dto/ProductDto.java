package com.ghtk.tuanba59.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghtk.tuanba59.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Integer price;
    private String sku;
    private String description;
    @JsonProperty("category_id")
    private Long categoryId;
}
