package com.project.catalogo.infrastruture.mapper;


import com.project.catalogo.domain.model.ProductModel;
import com.project.catalogo.infrastruture.adapter.out.persistence.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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
}
