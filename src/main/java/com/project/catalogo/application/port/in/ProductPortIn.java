package com.project.catalogo.application.port.in;

import com.project.catalogo.domain.model.ProductModel;

import java.util.List;

public interface ProductPortIn {

    List<ProductModel> findAllProducts();
    ProductModel createProduct(ProductModel model);
    ProductModel deleteProduct(ProductModel model);
}
