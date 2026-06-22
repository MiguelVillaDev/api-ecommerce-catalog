package com.project.catalogo.application.port.in;

import com.project.catalogo.domain.model.CategoryModel;

import java.util.List;

public interface CategoryPortIn {

    List<CategoryModel> findAllCategories();
    CategoryModel createCategory(CategoryModel model);
    CategoryModel deleteCategory(CategoryModel model);
}
