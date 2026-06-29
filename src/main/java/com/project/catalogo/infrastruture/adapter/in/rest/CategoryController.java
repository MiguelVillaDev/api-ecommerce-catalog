package com.project.catalogo.infrastruture.adapter.in.rest;

import com.project.catalogo.application.port.in.CategoryPortIn;
import com.project.catalogo.domain.model.CategoryModel;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.CategoryDto;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.CategoryRequest;
import com.project.catalogo.infrastruture.adapter.in.rest.mapper.CategoryDtoMapper;
import com.project.catalogo.infrastruture.adapter.in.rest.response.CommonListResponse;
import com.project.catalogo.infrastruture.adapter.in.rest.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryPortIn categoryPortIn;
    private final CategoryDtoMapper categoryDtoMapper;

    @GetMapping
    public ResponseEntity<CommonListResponse<CategoryDto>> findAll() {

        return ResponseEntity.ok(
                categoryDtoMapper.buildListResponse(
                        categoryPortIn.findAllCategories(),
                        "Categorías obtenidas exitosamente"
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<CategoryDto>> findById(@PathVariable Integer id){
        CategoryModel categoryModel = CategoryModel.builder().id(id).build();
        return ResponseEntity.ok(
                categoryDtoMapper.buildResponse(
                        categoryPortIn.findById(categoryModel),
                        "Categoría obtenida exitosamente"
                )
        );

    }

    @PostMapping()
    public ResponseEntity<CommonResponse<CategoryDto>> createCategory(
            @Valid @RequestBody CategoryRequest categoryRequest) {

        CategoryModel categoryModel = categoryDtoMapper.convertToModel(categoryRequest);

        return ResponseEntity.ok(
                categoryDtoMapper.buildResponse(
                        categoryPortIn.createCategory(categoryModel),
                        "Categoria creada exitosamente"
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<CategoryDto>> deleteCategory(
            @PathVariable Integer id) {

        CategoryModel categoryModel = CategoryModel.builder().id(id).build();

        return ResponseEntity.ok(
                categoryDtoMapper.buildResponse(
                        categoryPortIn.deleteCategory(categoryModel),
                        "Categoria eliminada exitosamente"
                )
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponse<CategoryDto>> updateCategory(@PathVariable  Integer id, @Valid @RequestBody CategoryRequest request ){
        CategoryModel categoryModel = CategoryModel.builder().id(id).build();

        return ResponseEntity.ok(
                categoryDtoMapper.buildResponse(
                        categoryPortIn.updateCategory(categoryModel, request),
                        "Categoria actualizada exitosamente"
                )
        );
    }



}
