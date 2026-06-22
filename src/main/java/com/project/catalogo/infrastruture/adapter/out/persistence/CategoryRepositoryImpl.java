package com.project.catalogo.infrastruture.adapter.out.persistence;

import com.project.catalogo.application.port.out.CategoryPortOut;
import com.project.catalogo.domain.model.CategoryModel;
import com.project.catalogo.infrastruture.adapter.out.persistence.entity.CategoryEntity;
import com.project.catalogo.infrastruture.adapter.out.persistence.repository.CategoryRepository;
import com.project.catalogo.infrastruture.mapper.CategoryMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CategoryRepositoryAdapter")
@AllArgsConstructor
public class CategoryRepositoryImpl implements CategoryPortOut {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryModel> findAll() {
        return categoryMapper.convertToListModel(categoryRepository.findAll());

    }

    @Override
    public CategoryModel create(CategoryModel model) {
        CategoryEntity entity = categoryMapper.convertToEntity(model);
        return categoryMapper.convertToModel(categoryRepository.save(entity)) ;
    }

    @Override
    public CategoryModel delete(CategoryModel model) {

        CategoryEntity entity = categoryRepository.findById(model.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Category no existe con id: " + model.getId()
                ));

        categoryRepository.delete(entity);

        return categoryMapper.convertToModel(entity);
    }


}
