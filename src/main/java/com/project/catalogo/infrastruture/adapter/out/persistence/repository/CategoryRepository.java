package com.project.catalogo.infrastruture.adapter.out.persistence.repository;


import com.project.catalogo.infrastruture.adapter.out.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {



}
