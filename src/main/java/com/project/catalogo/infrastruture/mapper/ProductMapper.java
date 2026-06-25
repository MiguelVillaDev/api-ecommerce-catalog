package com.project.catalogo.infrastruture.mapper;


import com.project.catalogo.domain.model.ProductModel;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.ProductUpdateRequest;
import com.project.catalogo.infrastruture.adapter.out.persistence.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@AllArgsConstructor
@Component
public class ProductMapper extends CommonMapper<ProductEntity, ProductModel> {

    private final CategoryMapper categoryMapper;


    @Override
    public ProductModel convertToModel(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        return ProductModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .brand(entity.getBrand())
                .price(entity.getPrice())
                .salePrice(entity.getSalePrice())
                .sku(entity.getSku())
                .stock(entity.getStock())
                .metaDescription(entity.getMetaDescription())
                .metaTitle(entity.getMetaTitle())
                .webName(entity.getWebName())
                .urlImage(entity.getUrlImage())
                .isActive(entity.getIsActive())
                .category(categoryMapper.convertToModel(entity.getCategory()))
                .updatedAt(entity.getUpdatedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Override
    public ProductEntity convertToEntity(ProductModel model) {
        if (model == null) {
            return null;
        }

        return ProductEntity.builder()
                .id(model.getId())
                .categoryId(model.getCategoryId())
                .name(model.getName())
                .description(model.getDescription())
                .brand(model.getBrand())
                .price(model.getPrice())
                .salePrice(model.getSalePrice())
                .sku(model.getSku())
                .stock(model.getStock())
                .metaDescription(model.getMetaDescription())
                .metaTitle(model.getMetaTitle())
                .webName(model.getWebName())
                .urlImage(model.getUrlImage())
                .isActive(model.getIsActive())
                .build();
    }

    public void updateEntityFromRequest(ProductEntity entity, ProductUpdateRequest request) {
        if (request.getName() != null) entity.setName(request.getName());
        if (request.getDescription() != null) entity.setDescription(request.getDescription());
        if (request.getCategoryId() != null) entity.setCategoryId(Integer.valueOf(request.getCategoryId()));
        if (request.getWebName() != null) entity.setWebName(request.getWebName());
        if (request.getBrand() != null) entity.setBrand(request.getBrand());
        if (request.getPrice() != null) entity.setPrice(new BigDecimal(request.getPrice()));
        if (request.getSalePrice() != null) entity.setSalePrice(new BigDecimal(request.getSalePrice()));
        if (request.getSku() != null) entity.setSku(request.getSku());
        if (request.getStock() != null) entity.setStock(Integer.valueOf(request.getStock()));
        if (request.getUrlImage() != null) entity.setUrlImage(request.getUrlImage());
        if (request.getMetaTitle() != null) entity.setMetaTitle(request.getMetaTitle());
        if (request.getMetaDescription() != null) entity.setMetaDescription(request.getMetaDescription());
        if (request.getIsActive() != null) entity.setIsActive(request.getIsActive());
    }
}
