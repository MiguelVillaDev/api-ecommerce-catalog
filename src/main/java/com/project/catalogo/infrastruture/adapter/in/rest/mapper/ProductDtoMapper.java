package com.project.catalogo.infrastruture.adapter.in.rest.mapper;

import com.project.catalogo.domain.model.ProductModel;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.AvailabilityProductDto;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.ProductDto;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.ProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductDtoMapper extends CommonDtoMapper<ProductModel, ProductDto, ProductRequest> {

    private final CategoryDtoMapper categoryDtoMapper;

    @Override
    public ProductDto convertToDto(ProductModel model) {

        if (model == null) {
            return null;
        }

        return ProductDto.builder()
                .id(model.getId())
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
                .category(categoryDtoMapper.convertToDto(model.getCategory()))
                .createdAt(formatDate(model.getCreatedAt()))
                .updatedAt(formatDate(model.getUpdatedAt()))
                .build();
    }

    @Override
    public ProductModel convertToModel(ProductRequest request) {
        if (request == null) {
            return null;
        }

        return ProductModel.builder()
                .id(parseOptionalToInteger(request.getId()))
                .categoryId(parseOptionalToInteger(request.getCategoryId()))
                .name(request.getName())
                .description(request.getDescription())
                .brand(request.getBrand())
                .price(parseOptionalToBigDecimal(request.getPrice()))
                .salePrice(parseOptionalToBigDecimal(request.getSalePrice()))
                .sku(request.getSku())
                .stock(parseOptionalToInteger(request.getStock()))
                .metaDescription(request.getMetaDescription())
                .metaTitle(request.getMetaTitle())
                .webName(request.getWebName())
                .urlImage(request.getUrlImage())
                .isActive(request.getIsActive())
                .build();
    }

    public AvailabilityProductDto convertToAvailability(ProductModel model){
        if (model == null) {
            return null;
        }

        return AvailabilityProductDto.builder()
                .name(model.getName())
                .price(model.getPrice())
                .stock(model.getStock())
                .build();

    }
}

