package com.project.catalogo.infrastructure.adapter.in.rest;

import com.project.catalogo.application.port.in.CategoryPortIn;
import com.project.catalogo.domain.model.CategoryModel;
import com.project.catalogo.infrastructure.adapter.in.rest.dto.CategoryDto;
import com.project.catalogo.infrastructure.adapter.in.rest.dto.CategoryRequest;
import com.project.catalogo.infrastructure.adapter.in.rest.mapper.CategoryDtoMapper;
import com.project.catalogo.infrastructure.adapter.in.rest.response.CommonListResponse;
import com.project.catalogo.infrastructure.adapter.in.rest.response.CommonResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryPortIn categoryPortIn;

    @Mock
    private CategoryDtoMapper categoryDtoMapper;

    @InjectMocks
    private CategoryController categoryController;

    private CategoryModel categoryModel;
    private CategoryDto categoryDto;
    private CategoryRequest categoryRequest;
    private CommonResponse<CategoryDto> commonResponse;
    private CommonListResponse<CategoryDto> commonListResponse;

    @BeforeEach
    void setup() {

        categoryModel = CategoryModel.builder()
                .id(1)
                .name("Guitarras")
                .description("Guitarras eléctricas")
                .build();

        categoryDto = CategoryDto.builder()
                .id(1)
                .name("Guitarras")
                .description("Guitarras eléctricas")
                .createdAt("2026-06-14T01:47:56")
                .updatedAt("2026-06-14T01:47:56")
                .build();

        categoryRequest = CategoryRequest.builder()
                .id("1")
                .name("Guitarras")
                .description("Guitarras eléctricas")
                .build();

        commonResponse = CommonResponse.<CategoryDto>builder()
                .message("ok")
                .response(categoryDto)
                .build();

        commonListResponse = CommonListResponse.<CategoryDto>builder()
                .message("ok")
                .response(List.of(categoryDto))
                .build();
    }

    @Test
    void shouldFindAllCategories() {

        when(categoryPortIn.findAllCategories())
                .thenReturn(List.of(categoryModel));

        when(categoryDtoMapper.buildListResponse(
                List.of(categoryModel),
                "Categorías obtenidas exitosamente"))
                .thenReturn(commonListResponse);

        ResponseEntity<CommonListResponse<CategoryDto>> response =
                categoryController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commonListResponse, response.getBody());

        verify(categoryPortIn).findAllCategories();
        verify(categoryDtoMapper)
                .buildListResponse(List.of(categoryModel),
                        "Categorías obtenidas exitosamente");
    }

    @Test
    void shouldFindCategoryById() {

        when(categoryPortIn.findById(any(CategoryModel.class)))
                .thenReturn(categoryModel);

        when(categoryDtoMapper.buildResponse(
                categoryModel,
                "Categoría obtenida exitosamente"))
                .thenReturn(commonResponse);

        ResponseEntity<CommonResponse<CategoryDto>> response =
                categoryController.findById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commonResponse, response.getBody());

        verify(categoryPortIn).findById(any(CategoryModel.class));
    }

    @Test
    void shouldCreateCategory() {

        when(categoryDtoMapper.convertToModel(categoryRequest))
                .thenReturn(categoryModel);

        when(categoryPortIn.createCategory(categoryModel))
                .thenReturn(categoryModel);

        when(categoryDtoMapper.buildResponse(
                categoryModel,
                "Categoria creada exitosamente"))
                .thenReturn(commonResponse);

        ResponseEntity<CommonResponse<CategoryDto>> response =
                categoryController.createCategory(categoryRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commonResponse, response.getBody());

        verify(categoryDtoMapper).convertToModel(categoryRequest);
        verify(categoryPortIn).createCategory(categoryModel);
    }

    @Test
    void shouldDeleteCategory() {

        when(categoryPortIn.deleteCategory(any(CategoryModel.class)))
                .thenReturn(categoryModel);

        when(categoryDtoMapper.buildResponse(
                categoryModel,
                "Categoria eliminada exitosamente"))
                .thenReturn(commonResponse);

        ResponseEntity<CommonResponse<CategoryDto>> response =
                categoryController.deleteCategory(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commonResponse, response.getBody());

        verify(categoryPortIn).deleteCategory(any(CategoryModel.class));
    }

    @Test
    void shouldUpdateCategory() {

        CategoryRequest updateRequest = CategoryRequest.builder()
                .id(categoryRequest.getId())
                .name("Bajos")
                .description(categoryRequest.getDescription())
                .build();

        CategoryModel updatedModel = CategoryModel.builder()
                .id(categoryModel.getId())
                .name("Bajos")
                .description(categoryModel.getDescription())
                .build();

        CategoryDto updatedDto = CategoryDto.builder()
                .id(categoryDto.getId())
                .name("Bajos")
                .description(categoryDto.getDescription())
                .createdAt(categoryDto.getCreatedAt())
                .updatedAt(categoryDto.getUpdatedAt())
                .build();

        CommonResponse<CategoryDto> updatedResponse = CommonResponse.<CategoryDto>builder()
                .message(commonResponse.getMessage())
                .response(updatedDto)
                .build();

        when(categoryPortIn.updateCategory(any(CategoryModel.class), eq(updateRequest)))
                .thenReturn(updatedModel);

        when(categoryDtoMapper.buildResponse(
                updatedModel,
                "Categoria actualizada exitosamente"))
                .thenReturn(updatedResponse);

        ResponseEntity<CommonResponse<CategoryDto>> response =
                categoryController.updateCategory(1, updateRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedResponse, response.getBody());

        verify(categoryPortIn).updateCategory(any(CategoryModel.class), eq(updateRequest));
        verify(categoryDtoMapper).buildResponse(
                updatedModel,
                "Categoria actualizada exitosamente");
    }
}