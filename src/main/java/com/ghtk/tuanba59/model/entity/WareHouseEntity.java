package com.ghtk.tuanba59.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghtk.tuanba59.constants.StatusEnum;
import com.ghtk.tuanba59.model.dto.WareHouseDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "warehouse")
public class WareHouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    @JsonProperty("province_id")
    private Long provinceId;
    @JsonProperty("district_id")
    private Long districtId;
    private StatusEnum status;


    public WareHouseEntity setValueFromDto(WareHouseDto wareHouseDto) {
        this.id = wareHouseDto.getId();
        this.name = wareHouseDto.getName();
        this.address = wareHouseDto.getAddress();
        this.districtId = wareHouseDto.getDistrictId();
        this.provinceId = wareHouseDto.getProvinceId();

        return this;
    }
}
