package com.project.catalogo.domain.model;


import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ProductModel {

    private Integer id;
    private Integer categoryId;
    private CategoryModel category;
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
    private Integer priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
