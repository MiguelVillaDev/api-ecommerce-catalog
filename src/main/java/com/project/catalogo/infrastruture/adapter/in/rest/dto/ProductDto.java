package com.project.catalogo.infrastruture.adapter.in.rest.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductDto {

    private Integer id;
    private Integer categoryId;
    private CategoryDto category;
    private String name;
    private String webName;
    private String description;
    private String brand;
    private BigDecimal price;
    private BigDecimal salePrice;
    private String sku;
    private Integer stock;
    private String urlImage;
    private String metaTitle;
    private String metaDescription;
    private Boolean isActive;
    private String createdAt;
    private String updatedAt;
}
