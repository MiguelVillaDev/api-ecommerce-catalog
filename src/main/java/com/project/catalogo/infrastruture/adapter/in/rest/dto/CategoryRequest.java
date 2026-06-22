package com.project.catalogo.infrastruture.adapter.in.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CategoryRequest {

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


}
