package com.project.catalogo.infrastructure.adapter.in.rest;

import com.project.catalogo.application.port.in.ProductPortIn;
import com.project.catalogo.domain.model.ProductModel;
import com.project.catalogo.infrastructure.adapter.in.rest.dto.ProductDto;
import com.project.catalogo.infrastructure.adapter.in.rest.dto.ProductRequest;
import com.project.catalogo.infrastructure.adapter.in.rest.mapper.PageProductDtoMapper;
import com.project.catalogo.infrastructure.adapter.in.rest.mapper.ProductDtoMapper;
import com.project.catalogo.infrastructure.adapter.in.rest.response.CommonListResponse;
import com.project.catalogo.infrastructure.adapter.in.rest.response.CommonResponse;
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
    private final PageProductDtoMapper pageProductDtoMapper;


    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ProductDto>> findById(@PathVariable Integer id){
        ProductModel productModel = ProductModel.builder().id(id).build();

        return ResponseEntity.ok(
                productDtoMapper.buildResponse(
                        productPortIn.findById(productModel),
                        "Producto obtenido exitosamente"
                )
        );
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<ProductDto>> updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductRequest request){
        ProductModel productModel = productDtoMapper.convertToModel(request);
        productModel.setId(id);

        return ResponseEntity.ok(productDtoMapper.buildResponse(productPortIn.updateProduct(productModel),
                "Producto actualizado correctamente")

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

        if (sortBy == null){
            sortBy = "priority";
        }
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).ascending()
        );
        ProductModel productModel = ProductModel.builder().categoryId(categoryId).build();


        return ResponseEntity.ok(
                pageProductDtoMapper.buildResponse(
                        productPortIn.findByCategoryId(productModel, pageable),
                        "Productos obtenidos exitosamente"
                )
        );
    }


    @GetMapping("/priority")
    public ResponseEntity<CommonResponse<Page<ProductDto>>> findProductsByPriority(
            @RequestParam(defaultValue = "1") Integer priority,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "12") Integer size) {


        Pageable pageable = PageRequest.of(
                page,
                size
        );
        ProductModel productModel = ProductModel.builder().priority(priority).build();


        return ResponseEntity.ok(
                pageProductDtoMapper.buildResponse(
                        productPortIn.findByPriority(productModel, pageable),
                        "Productos obtenidos exitosamente"
                )
        );
    }




}
