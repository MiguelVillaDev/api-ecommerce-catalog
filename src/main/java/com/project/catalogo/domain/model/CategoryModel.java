package com.project.catalogo.domain.model;


import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Builder
public class CategoryModel {

    private Integer id;

    private String name;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
