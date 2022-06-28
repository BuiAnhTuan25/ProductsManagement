package com.ghtk.tuanba59.controller;

import com.ghtk.tuanba59.model.dto.WareHouseDto;
import com.ghtk.tuanba59.model.respone.ListData;
import com.ghtk.tuanba59.service.WareHouseService;
import com.ghtk.tuanba59.service.WareHouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/warehouse")
public class WareHouseController {

    @Autowired
    WareHouseServiceImpl wareHouseService;

    @GetMapping("")
    public ResponseEntity<?> getALL(
            @RequestParam(value="page") int page,
            @RequestParam(value="page_size") int pageSize
    ){
        return new ResponseEntity<>(wareHouseService.getAll(page, pageSize), HttpStatus.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(wareHouseService.getById(id),HttpStatus.valueOf(200));
    }

    @PostMapping("")
    public ResponseEntity<?> add(
            @RequestBody WareHouseDto wareHouseDto
            ){
        return new ResponseEntity<>(wareHouseService.add(wareHouseDto),HttpStatus.valueOf(201));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestBody WareHouseDto wareHouseDto,
            @PathVariable Long id
    ){
        return new ResponseEntity<>(wareHouseService.update(wareHouseDto,id),HttpStatus.valueOf(201));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(wareHouseService.delete(id),HttpStatus.valueOf(200));
    }
}
