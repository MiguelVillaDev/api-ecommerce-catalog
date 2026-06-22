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
public class ProductRequest {

    @Pattern(
            regexp = "^\\d+$",
            message = "El campo id solo puede contener numeros"
    )
    private String id;


    @NotBlank(message = "El campo name es obligatorio")
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El campo name solo puede contener letras y espacios"
    )
    private String name;


    @NotBlank(message = "El campo description es obligatoria")
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El campo description solo puede contener letras y espacios"
    )
    private String description;

    @NotBlank(message = "El campo category_id es obligatorio")
    @Pattern(
            regexp = "^\\d+$",
            message = "El campo category_id solo puede contener numeros"
    )
    private String categoryId;

    @NotBlank(message = "El campo web_name es obligatoria")
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El campo web_name solo puede contener letras y espacios"
    )
    private String webName;

    @NotBlank(message = "El campo brand es obligatoria")
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El campo brand solo puede contener letras y espacios"
    )
    private String brand;

    @NotBlank(message = "El campo price es obligatorio")
    @Pattern(
            regexp = "^\\d+$",
            message = "El campo price solo puede contener numeros"
    )
    private String price;

    @NotBlank(message = "El campo sale_price es obligatorio")
    @Pattern(
            regexp = "^\\d+$",
            message = "El campo sale_price solo puede contener numeros"
    )
    private String salePrice;

    @NotBlank(message = "El campo sku es obligatorio")
    @Pattern(
            regexp = "^[A-Za-z0-9_-]+$",
            message = "El campo sku solo puede contener letras, números, guiones y guiones bajos"
    )
    private String sku;

    @NotBlank(message = "El campo stock es obligatorio")
    @Pattern(
            regexp = "^\\d+$",
            message = "El campo stock solo puede contener numeros"
    )
    private String stock;

    @NotBlank(message = "El campo url_image es obligatorio")
    @Pattern(
            regexp = "^(https?://)([\\w.-]+)\\.([a-zA-Z]{2,})([/\\w .-]*)*/?$",
            message = "El campo url_image debe tener un formato válido"
    )
    private String urlImage;

    @NotBlank(message = "El campo meta_title es obligatoria")
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El campo meta_title solo puede contener letras y espacios"
    )
    private String metaTitle;

    @NotBlank(message = "El campo meta_description es obligatoria")
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El campo meta_description solo puede contener letras y espacios"
    )
    private String metaDescription;

    private Boolean isActive;


}
