package com.project.catalogo.application.port.in;

import com.project.catalogo.domain.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductPortIn {

    ProductModel findById (ProductModel model);
    List<ProductModel> findAllProducts();
    ProductModel createProduct(ProductModel model);
    ProductModel updateProduct(ProductModel model);
    ProductModel deleteProduct(ProductModel model);
    Page<ProductModel> findByCategoryId(ProductModel model, Pageable pageable);
    Page<ProductModel> findByPriority(ProductModel model, Pageable pageable);
}
