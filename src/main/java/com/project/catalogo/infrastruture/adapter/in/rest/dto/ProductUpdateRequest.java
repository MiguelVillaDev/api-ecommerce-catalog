package com.project.catalogo.infrastruture.adapter.in.rest.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductUpdateRequest {

    @Pattern(
            regexp = "^\\d+$",
            message = "El campo id solo puede contener numeros"
    )
    private String id;



    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El campo name solo puede contener letras y espacios"
    )
    private String name;



    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El campo description solo puede contener letras y espacios"
    )
    private String description;


    @Pattern(
            regexp = "^\\d+$",
            message = "El campo category_id solo puede contener numeros"
    )
    private String categoryId;


    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El campo web_name solo puede contener letras y espacios"
    )
    private String webName;


    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El campo brand solo puede contener letras y espacios"
    )
    private String brand;


    @Pattern(
            regexp = "^\\d+$",
            message = "El campo price solo puede contener numeros"
    )
    private String price;


    @Pattern(
            regexp = "^\\d+$",
            message = "El campo sale_price solo puede contener numeros"
    )
    private String salePrice;


    @Pattern(
            regexp = "^[A-Za-z0-9_-]+$",
            message = "El campo sku solo puede contener letras, números, guiones y guiones bajos"
    )
    private String sku;


    @Pattern(
            regexp = "^\\d+$",
            message = "El campo stock solo puede contener numeros"
    )
    private String stock;


    @Pattern(
            regexp = "^(https?://)([\\w.-]+)\\.([a-zA-Z]{2,})([/\\w .-]*)*/?$",
            message = "El campo url_image debe tener un formato válido"
    )
    private String urlImage;


    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El campo meta_title solo puede contener letras y espacios"
    )
    private String metaTitle;


    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El campo meta_description solo puede contener letras y espacios"
    )
    private String metaDescription;

    private Boolean isActive;


}
