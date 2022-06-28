package com.ghtk.tuanba59.repository;

import com.ghtk.tuanba59.constants.StatusEnum;
import com.ghtk.tuanba59.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
//    @Query("select p from ProductEntity p where p.price>:price and p.name like %:name% order by p.price desc ")
//    Page<ProductEntity> findByNameAndOrderByPriceDesc(Pageable pageable,@Param("price") int price, @Param("name") String name);

    /*
     Covert save() -> native query
     REPLACE INTO product (id, description, name, price, sku, status, category_id)
     VALUES (1001,'Ownsworth','Beer - Camerons Cream Ale',50001,'Haleigh',0,5);

     */
    @Query(value = "SELECT p FROM ProductEntity p WHERE p.name like %:name% AND p.price >= :price")
    Page<ProductEntity> findByNameAndPrice(Pageable pageable, @Param("name") String name, @Param("price") int price);

    Page<ProductEntity> findByNameContainingAndPriceGreaterThanOrderByPriceDesc(Pageable pageable, String name, int price);

    Page<ProductEntity> findAllByStatus(Pageable pageable, StatusEnum status);
}
