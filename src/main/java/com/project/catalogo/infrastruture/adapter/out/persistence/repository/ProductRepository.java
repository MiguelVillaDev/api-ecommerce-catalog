package com.project.catalogo.infrastruture.adapter.out.persistence.repository;



import com.project.catalogo.infrastruture.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {



}
