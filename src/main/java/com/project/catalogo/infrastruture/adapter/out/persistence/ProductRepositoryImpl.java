package com.project.catalogo.infrastruture.adapter.out.persistence;

import com.project.catalogo.application.port.out.ProductPortOut;
import com.project.catalogo.domain.model.ProductModel;
import com.project.catalogo.infrastruture.adapter.out.persistence.entity.ProductEntity;
import com.project.catalogo.infrastruture.adapter.out.persistence.repository.ProductRepository;
import com.project.catalogo.infrastruture.mapper.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ProductRepositoryAdapter")
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductPortOut {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductModel> findAll() {
        return productMapper.convertToListModel(productRepository.findAll());

    }

    @Override
    public ProductModel create(ProductModel model) {
        ProductEntity entity = productMapper.convertToEntity(model);
        return productMapper.convertToModel(productRepository.save(entity)) ;
    }

    @Override
    public ProductModel delete(ProductModel model) {

        ProductEntity entity = productRepository.findById(model.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Product no existe con id: " + model.getId()
                ));

        productRepository.delete(entity);

        return productMapper.convertToModel(entity);
    }

    @Override
    public Page<ProductModel> findByCategoryId(
            ProductModel model,
            Pageable pageable
    ) {
        return productRepository
                .findByCategoryIdAndStockGreaterThan(model.getCategoryId(), 0, pageable)
                .map(productMapper::convertToModel);
    }



}
