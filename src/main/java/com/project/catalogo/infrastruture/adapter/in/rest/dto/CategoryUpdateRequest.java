package com.project.catalogo.infrastruture.adapter.in.rest.dto;

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
public class CategoryUpdateRequest {

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


}
