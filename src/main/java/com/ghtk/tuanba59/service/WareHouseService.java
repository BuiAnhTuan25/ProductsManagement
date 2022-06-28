package com.ghtk.tuanba59.service;

import com.ghtk.tuanba59.model.dto.WareHouseDto;
import com.ghtk.tuanba59.model.respone.Data;
import com.ghtk.tuanba59.model.respone.ListData;
import org.springframework.data.domain.Pageable;

public interface WareHouseService {
    ListData getAll(int page,int pageSize);
    Data add(WareHouseDto warehouseDto);
    Data update(WareHouseDto warehouseDto,Long id);
    Data delete(Long id);
    Data getById(Long id);
}
