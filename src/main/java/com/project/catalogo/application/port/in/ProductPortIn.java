package com.project.catalogo.application.port.in;

import com.project.catalogo.domain.model.ProductModel;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.ProductUpdateRequest;

import java.util.List;

public interface ProductPortIn {

    List<ProductModel> findAllProducts();
    ProductModel createProduct(ProductModel model);
    ProductModel deleteProduct(ProductModel model);
    ProductModel updateProduct(ProductModel productModel, ProductUpdateRequest request);
    ProductModel discountValue(Integer id, Integer val);
}
