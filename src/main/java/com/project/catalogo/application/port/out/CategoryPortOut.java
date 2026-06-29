package com.project.catalogo.application.port.out;

import com.project.catalogo.domain.model.CategoryModel;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.CategoryRequest;


import java.util.List;

public interface  CategoryPortOut {
    List<CategoryModel> findAll();
    CategoryModel findOne(CategoryModel mode);
    CategoryModel create(CategoryModel model);
    CategoryModel delete(CategoryModel model);
    CategoryModel update(CategoryModel model, CategoryRequest request);
}
