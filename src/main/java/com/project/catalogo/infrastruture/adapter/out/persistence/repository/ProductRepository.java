package com.project.catalogo.infrastruture.adapter.out.persistence.repository;



import com.project.catalogo.infrastruture.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    Page<ProductEntity> findByCategoryIdAndStockGreaterThan(
            Integer categoryId,
            Integer stock,
            Pageable pageable
    );


    Page<ProductEntity> findByPriorityAndStockGreaterThan(
            Integer priority,
            Integer stock,
            Pageable pageable
    );



}
