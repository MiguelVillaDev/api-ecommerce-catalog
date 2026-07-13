package com.project.catalogo.application.service;

import com.project.catalogo.application.port.in.CategoryPortIn;
import com.project.catalogo.application.port.out.CategoryPortOut;
import com.project.catalogo.domain.model.CategoryModel;
import com.project.catalogo.infrastructure.adapter.in.rest.dto.CategoryRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements CategoryPortIn {

    private final CategoryPortOut categoryPortOut;

    public CategoryModel findById(CategoryModel model){
        return categoryPortOut.findOne(model);
    }

    public List<CategoryModel> findAllCategories(){
        return categoryPortOut.findAll();
    }

    @Override
    public CategoryModel createCategory(CategoryModel model) {
        return categoryPortOut.create(model);
    }

    @Override
    public CategoryModel deleteCategory(CategoryModel model) {
        return categoryPortOut.delete(model);
    }

    @Override
    public CategoryModel updateCategory(CategoryModel model, CategoryRequest request) {
        return categoryPortOut.update(model, request);
    }

}
