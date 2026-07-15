package com.project.catalogo.application.service;

import com.project.catalogo.application.port.in.ProductPortIn;
import com.project.catalogo.application.port.out.ProductPortOut;
import com.project.catalogo.domain.model.ProductModel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements ProductPortIn {

    private final ProductPortOut productPortOut;

    @Override
    public ProductModel findById (ProductModel model){
        return productPortOut.findByID(model);
    }

    @Override
    public List<ProductModel> findAllProducts(){
        return productPortOut.findAll();
    }

    @Override
    public ProductModel createProduct(ProductModel model) {
        return productPortOut.create(model);
    }

    @Override
    public ProductModel updateProduct(ProductModel model){
        return productPortOut.update(model);
    }

    @Override
    public ProductModel deleteProduct(ProductModel model) {
        return productPortOut.delete(model);
    }

    @Override
    public Page<ProductModel> findByCategoryId(ProductModel model, Pageable pageable){
        return  productPortOut.findByCategoryId(model, pageable);
    }

    @Override
    public Page<ProductModel> findByPriority(ProductModel model, Pageable pageable){
        return  productPortOut.findByPriority(model, pageable);
    }

}
