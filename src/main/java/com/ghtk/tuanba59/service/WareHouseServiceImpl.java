package com.ghtk.tuanba59.service;

import com.ghtk.tuanba59.constants.StatusEnum;
import com.ghtk.tuanba59.model.dto.WareHouseDto;
import com.ghtk.tuanba59.model.entity.WareHouseEntity;
import com.ghtk.tuanba59.model.respone.Data;
import com.ghtk.tuanba59.model.respone.ListData;
import com.ghtk.tuanba59.model.respone.Pagination;
import com.ghtk.tuanba59.repository.WareHouseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WareHouseServiceImpl implements WareHouseService {

    @Autowired
    WareHouseRepository wareHouseRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ListData getAll(int page, int pageSize) {
        Page<WareHouseEntity> wareHousePage = wareHouseRepository.findAllByStatus(PageRequest.of(page, pageSize),StatusEnum.ACTIVE);
        Pagination pagination = new Pagination(wareHousePage.getNumber(), wareHousePage.getSize(), wareHousePage.getTotalPages(), (int) wareHousePage.getTotalElements());
        List<WareHouseDto> wareHouseDtoList = wareHousePage.stream().map(w -> modelMapper.map(w, WareHouseDto.class)).collect(Collectors.toList());
        return new ListData(true, "success", wareHouseDtoList, pagination);
    }

    @Override
    public Data add(WareHouseDto wareHouseDto) {
        WareHouseEntity wareHouse = modelMapper.map(wareHouseDto, WareHouseEntity.class);
        wareHouse.setStatus(StatusEnum.ACTIVE);
        return new Data(true, "success", modelMapper.map(wareHouseRepository.save(wareHouse), WareHouseDto.class));
    }

    @Override
    public Data update(WareHouseDto wareHouseDto, Long id) {
        WareHouseEntity wareHouse = wareHouseRepository.findById(id).get().setValueFromDto(wareHouseDto);
        wareHouse.setId(id);
        return new Data(true, "success", modelMapper.map(wareHouseRepository.save(wareHouse), WareHouseDto.class));
    }

    @Override
    public Data delete(Long id) {
        WareHouseEntity wareHouse = wareHouseRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        wareHouse.setStatus(StatusEnum.INACTIVE);
        wareHouseRepository.save(wareHouse);
        return new Data(true, "success", modelMapper.map(wareHouse, WareHouseDto.class));
    }

    @Override
    public Data getById(Long id) {
        WareHouseDto wareHouseDto = modelMapper.map(wareHouseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException()), WareHouseDto.class);
        return new Data(true, "success", wareHouseDto);
    }
}
