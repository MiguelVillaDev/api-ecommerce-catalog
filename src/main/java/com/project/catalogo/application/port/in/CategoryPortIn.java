package com.project.catalogo.application.port.in;

import com.project.catalogo.domain.model.CategoryModel;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.CategoryRequest;


import java.util.List;

public interface CategoryPortIn {

    List<CategoryModel> findAllCategories();
    CategoryModel findById(CategoryModel model);
    CategoryModel createCategory(CategoryModel model);
    CategoryModel deleteCategory(CategoryModel model);
    CategoryModel updateCategory(CategoryModel model, CategoryRequest request);
}
