package com.ghtk.tuanba59.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="warehouse_product")
public class WareHouseProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long warehouseId;
    private Integer inventory;
    private Integer totalImport;
    private Integer totalExport;
    private LocalDate startDate;
    private LocalDate expiredDate;
}
