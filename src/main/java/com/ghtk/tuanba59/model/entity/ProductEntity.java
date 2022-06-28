package com.ghtk.tuanba59.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghtk.tuanba59.constants.StatusEnum;
import com.ghtk.tuanba59.model.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String sku;
    private StatusEnum status;
    private String description;
    @JsonProperty("category_id")
    private Long categoryId;
    private String code;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("modified_at")
    private LocalDateTime modifiedAt;

    public ProductEntity setValueFromDto(ProductDto productDto) {
        this.id=productDto.getId();
        this.name=productDto.getName();
        this.price=productDto.getPrice();
        this.sku=productDto.getSku();
        this.description=productDto.getDescription();
        this.categoryId=productDto.getCategoryId();
        return this;
    }
}
