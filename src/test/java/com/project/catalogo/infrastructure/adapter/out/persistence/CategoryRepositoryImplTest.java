package com.project.catalogo.infrastructure.adapter.out.persistence;


import com.project.catalogo.domain.model.CategoryModel;
import com.project.catalogo.infrastructure.adapter.in.rest.dto.CategoryRequest;
import com.project.catalogo.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import com.project.catalogo.infrastructure.adapter.out.persistence.repository.CategoryRepository;
import com.project.catalogo.infrastructure.mapper.CategoryMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryRepositoryImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryRepositoryImpl categoryRepositoryImpl;

    private CategoryModel categoryModel;
    private CategoryEntity categoryEntity;
    private CategoryRequest categoryRequest;

    @BeforeEach
    void setup() {

        categoryModel = CategoryModel.builder()
                .id(1)
                .name("Guitarras")
                .description("Guitarras eléctricas")
                .build();

        categoryEntity = CategoryEntity.builder()
                .id(1)
                .name("Guitarras")
                .description("Guitarras eléctricas")
                .build();

        categoryRequest = CategoryRequest.builder()
                .id("1")
                .name("Guitarras")
                .description("Guitarras eléctricas")
                .build();
    }

    @Test
    void shouldFindAllCategories() {

        when(categoryRepository.findAll())
                .thenReturn(List.of(categoryEntity));

        when(categoryMapper.convertToListModel(List.of(categoryEntity)))
                .thenReturn(List.of(categoryModel));

        List<CategoryModel> response = categoryRepositoryImpl.findAll();

        assertEquals(List.of(categoryModel), response);

        verify(categoryRepository).findAll();
        verify(categoryMapper).convertToListModel(List.of(categoryEntity));
    }

    @Test
    void shouldFindCategoryById() {

        when(categoryRepository.findById(1))
                .thenReturn(Optional.of(categoryEntity));

        when(categoryMapper.convertToModel(categoryEntity))
                .thenReturn(categoryModel);

        CategoryModel response = categoryRepositoryImpl.findOne(categoryModel);

        assertEquals(categoryModel, response);

        verify(categoryRepository).findById(1);
        verify(categoryMapper).convertToModel(categoryEntity);
    }


    @Test
    void shouldCreateCategory() {

        when(categoryMapper.convertToEntity(categoryModel))
                .thenReturn(categoryEntity);

        when(categoryRepository.save(categoryEntity))
                .thenReturn(categoryEntity);

        when(categoryMapper.convertToModel(categoryEntity))
                .thenReturn(categoryModel);

        CategoryModel response = categoryRepositoryImpl.create(categoryModel);

        assertEquals(categoryModel, response);

        verify(categoryMapper).convertToEntity(categoryModel);
        verify(categoryRepository).save(categoryEntity);
    }

    @Test
    void shouldDeleteCategory() {

        when(categoryRepository.findById(1))
                .thenReturn(Optional.of(categoryEntity));

        when(categoryMapper.convertToModel(categoryEntity))
                .thenReturn(categoryModel);

        when(categoryMapper.convertToEntity(categoryModel))
                .thenReturn(categoryEntity);

        CategoryModel response = categoryRepositoryImpl.delete(categoryModel);

        assertEquals(categoryModel, response);

        verify(categoryRepository).delete(categoryEntity);
    }

    @Test
    void shouldUpdateCategory() {

        when(categoryRepository.findById(1))
                .thenReturn(Optional.of(categoryEntity));

        when(categoryMapper.convertToModel(categoryEntity))
                .thenReturn(categoryModel);

        when(categoryMapper.convertToEntity(categoryModel))
                .thenReturn(categoryEntity);

        doNothing().when(categoryMapper)
                .updateEntityFromRequest(categoryEntity, categoryRequest);

        when(categoryRepository.save(categoryEntity))
                .thenReturn(categoryEntity);

        when(categoryMapper.convertToModel(categoryEntity))
                .thenReturn(categoryModel);

        CategoryModel response =
                categoryRepositoryImpl.update(categoryModel, categoryRequest);

        assertEquals(categoryModel, response);

        verify(categoryMapper)
                .updateEntityFromRequest(categoryEntity, categoryRequest);
        verify(categoryRepository).save(categoryEntity);
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound() {

        when(categoryRepository.findById(1))
                .thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> categoryRepositoryImpl.findOne(categoryModel)
        );

        assertEquals("Category no existe con id: 1", exception.getMessage());

        verify(categoryRepository).findById(1);
        verify(categoryMapper, never()).convertToModel(any());
    }
}