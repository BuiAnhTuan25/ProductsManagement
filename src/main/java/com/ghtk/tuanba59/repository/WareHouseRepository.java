package com.ghtk.tuanba59.repository;

import com.ghtk.tuanba59.constants.StatusEnum;
import com.ghtk.tuanba59.model.entity.WareHouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WareHouseRepository extends JpaRepository<WareHouseEntity, Long> {
    Page<WareHouseEntity> findAllByStatus(Pageable pageable, StatusEnum status);
}
