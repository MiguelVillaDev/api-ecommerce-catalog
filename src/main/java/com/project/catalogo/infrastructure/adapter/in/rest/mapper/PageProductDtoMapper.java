package com.project.catalogo.infrastructure.adapter.in.rest.mapper;

import com.project.catalogo.domain.model.ProductModel;
import com.project.catalogo.infrastructure.adapter.in.rest.dto.ProductDto;
import com.project.catalogo.infrastructure.adapter.in.rest.dto.ProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PageProductDtoMapper extends CommonDtoMapper<Page<ProductModel>, Page<ProductDto>, ProductRequest> {

    private final ProductDtoMapper productDtoMapper;

    @Override
    public Page<ProductDto> convertToDto(Page<ProductModel> model) {

        if (model == null) {
            return Page.empty();
        }

        return model.map(productDtoMapper::convertToDto);
    }

    @Override
    public Page<ProductModel> convertToModel(ProductRequest request) {
        return null;
    }
}