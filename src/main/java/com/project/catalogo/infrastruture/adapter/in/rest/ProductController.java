package com.project.catalogo.infrastruture.adapter.in.rest;

import com.project.catalogo.application.port.in.ProductPortIn;
import com.project.catalogo.domain.model.ProductModel;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.ProductDto;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.ProductRequest;
import com.project.catalogo.infrastruture.adapter.in.rest.mapper.ProductDtoMapper;
import com.project.catalogo.infrastruture.adapter.in.rest.response.CommonListResponse;
import com.project.catalogo.infrastruture.adapter.in.rest.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductPortIn productPortIn;
    private final ProductDtoMapper productDtoMapper;

    @GetMapping
    public ResponseEntity<CommonListResponse<ProductDto>> findAll() {

        return ResponseEntity.ok(
                productDtoMapper.buildListResponse(
                        productPortIn.findAllProducts(),
                        "Productos obtenidos exitosamente"
                )
        );
    }

    @PostMapping()
    public ResponseEntity<CommonResponse<ProductDto>> createProduct(
            @Valid @RequestBody ProductRequest productRequest) {

        ProductModel productModel = productDtoMapper.convertToModel(productRequest);

        return ResponseEntity.ok(
                productDtoMapper.buildResponse(
                        productPortIn.createProduct(productModel),
                        "Producto creado exitosamente"
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<ProductDto>> deleteProduct(
            @PathVariable Integer id) {

        ProductModel productModel = ProductModel.builder().id(id).build();

        return ResponseEntity.ok(
                productDtoMapper.buildResponse(
                        productPortIn.deleteProduct(productModel),
                        "Producto eliminado exitosamente"
                )
        );
    }

    @GetMapping("/category")
    public ResponseEntity<CommonResponse<Page<ProductDto>>> findProductsByCategory(
            @RequestParam(name = "category_id") Integer categoryId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "12") Integer size,
            @RequestParam(name = "sort_by",required = false) String sortBy) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).ascending()
        );
        ProductModel productModel = ProductModel.builder().categoryId(categoryId).build();

        Page<ProductDto> response = productPortIn
                .findByCategoryId(productModel, pageable)
                .map(productDtoMapper::convertToDto);

        return ResponseEntity.ok(
                CommonResponse.<Page<ProductDto>>builder()
                        .response(response)
                        .message("Productos obtenidos exitosamente")
                        .build()
        );
    }





}
