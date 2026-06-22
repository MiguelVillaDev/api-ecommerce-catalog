package com.project.catalogo.infrastruture.mapper;


import com.project.catalogo.domain.model.CategoryModel;
import com.project.catalogo.infrastruture.adapter.out.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends CommonMapper<CategoryEntity, CategoryModel> {


    @Override
    public CategoryModel convertToModel(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return CategoryModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .updatedAt(entity.getUpdatedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Override
    public CategoryEntity convertToEntity(CategoryModel model) {
        if (model == null) {
            return null;
        }
        return CategoryEntity.builder()
                .id(model.getId())
                .name(model.getName())
                .description(model.getDescription())
                .build();
    }
}
