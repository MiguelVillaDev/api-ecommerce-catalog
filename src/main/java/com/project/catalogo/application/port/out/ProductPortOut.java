package com.project.catalogo.application.port.out;

import com.project.catalogo.domain.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductPortOut {
    List<ProductModel> findAll();
    ProductModel create(ProductModel model);
    ProductModel delete(ProductModel model);
    Page<ProductModel> findByCategoryId(ProductModel model, Pageable pageable);
    Page<ProductModel> findByPriority(ProductModel model, Pageable pageable);
}
