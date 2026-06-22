package com.project.catalogo.application.port.out;

import com.project.catalogo.domain.model.ProductModel;

import java.util.List;

public interface ProductPortOut {
    List<ProductModel> findAll();
    ProductModel create(ProductModel model);
    ProductModel delete(ProductModel model);
}
