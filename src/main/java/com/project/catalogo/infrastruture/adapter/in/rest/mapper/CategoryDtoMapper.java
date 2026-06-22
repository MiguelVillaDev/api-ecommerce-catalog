package com.project.catalogo.infrastruture.adapter.in.rest.mapper;

import com.project.catalogo.domain.model.CategoryModel;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.CategoryDto;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.CategoryRequest;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoMapper extends CommonDtoMapper<CategoryModel, CategoryDto, CategoryRequest> {

    @Override
    public CategoryDto convertToDto(CategoryModel model) {

        if (model == null) {
            return null;
        }

        return CategoryDto.builder()
                .id(model.getId())
                .name(model.getName())
                .description(model.getDescription())
                .createdAt(formatDate(model.getCreatedAt()))
                .updatedAt(formatDate(model.getUpdatedAt()))
                .build();
    }

    @Override
    public CategoryModel convertToModel(CategoryRequest request) {
        if (request == null) {
            return null;
        }

        return CategoryModel.builder()
                .id(parseOptionalToInteger(request.getId()))
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }
}

