package com.project.catalogo.application.port.out;

import com.project.catalogo.domain.model.ProductModel;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.ProductUpdateRequest;

import java.util.List;

public interface ProductPortOut {
    List<ProductModel> findAll();
    ProductModel create(ProductModel model);
    ProductModel delete(ProductModel model);
    ProductModel update(ProductModel model, ProductUpdateRequest request);
    ProductModel findOne(ProductModel model);
}
