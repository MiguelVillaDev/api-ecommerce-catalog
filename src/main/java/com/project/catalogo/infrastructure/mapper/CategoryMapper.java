package com.project.catalogo.infrastructure.mapper;


import com.project.catalogo.domain.model.CategoryModel;
import com.project.catalogo.infrastructure.adapter.in.rest.dto.CategoryRequest;
import com.project.catalogo.infrastructure.adapter.out.persistence.entity.CategoryEntity;
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

    public void updateEntityFromRequest(CategoryEntity entity, CategoryRequest request) {
        if (request.getName() != null) entity.setName(request.getName());
        if (request.getDescription() != null) entity.setDescription(request.getDescription());

    }
}
