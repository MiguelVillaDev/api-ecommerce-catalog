package com.project.catalogo.infrastruture.adapter.in.rest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountRequestDto {

    @NotNull(message = "El descuento es obligatorio")
    @Min(value = 1, message = "El descuento mínimo es 1%")
    @Max(value = 70, message = "El descuento máximo es 70%")
    private Integer discount;
}
