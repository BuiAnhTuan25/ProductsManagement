package com.ghtk.tuanba59.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghtk.tuanba59.constants.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WareHouseDto {
    private Long id;
    private String name;
    private String address;
    @JsonProperty("province_id")
    private Long provinceId;
    @JsonProperty("district_id")
    private Long districtId;
}
