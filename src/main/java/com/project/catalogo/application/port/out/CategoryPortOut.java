package com.project.catalogo.application.port.out;

import com.project.catalogo.domain.model.CategoryModel;

import java.util.List;

public interface  CategoryPortOut {
    List<CategoryModel> findAll();
    CategoryModel create(CategoryModel model);
    CategoryModel delete(CategoryModel model);
}
