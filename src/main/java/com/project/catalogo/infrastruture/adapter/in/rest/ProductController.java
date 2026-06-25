package com.project.catalogo.infrastruture.adapter.in.rest;

import com.project.catalogo.application.port.in.ProductPortIn;
import com.project.catalogo.domain.model.ProductModel;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.*;
import com.project.catalogo.infrastruture.adapter.in.rest.mapper.ProductDtoMapper;
import com.project.catalogo.infrastruture.adapter.in.rest.response.CommonListResponse;
import com.project.catalogo.infrastruture.adapter.in.rest.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponse<ProductDto>> updateProduct(@PathVariable  Integer id, @Valid @RequestBody ProductUpdateRequest request ){
        ProductModel productModel = ProductModel.builder().id(id).build();

        return ResponseEntity.ok(
                productDtoMapper.buildResponse(
                        productPortIn.updateProduct(productModel, request),
                        "Producto actualizado exitosamente"
                )
        );
    }

    @PatchMapping("/{id}/discount/{val}")
    public ResponseEntity<CommonResponse<ProductDto>> setDiscountProduct(@PathVariable  Integer id, @PathVariable Integer val ){
        return ResponseEntity.ok(
                productDtoMapper.buildResponse(
                        productPortIn.discountValue(id, val),
                        "Descuento actualizado exitosamente"
                )
        );
    }



}
