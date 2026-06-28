package com.project.catalogo.infrastruture.adapter.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvailabilityProductDto {

    private String name;
    private BigDecimal price;
    private Integer stock;
}
