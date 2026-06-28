package com.project.catalogo.application.service;

import com.project.catalogo.application.port.in.ProductPortIn;
import com.project.catalogo.application.port.out.ProductPortOut;
import com.project.catalogo.domain.model.ProductModel;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.AvailabilityProductDto;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.DiscountRequestDto;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.ProductUpdateRequest;
import com.project.catalogo.infrastruture.adapter.in.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements ProductPortIn {

    private final static BigDecimal MIN_PRICE = BigDecimal.ONE;
    private final static BigDecimal HUNDRED = BigDecimal.valueOf(100);

    private final ProductPortOut productPortOut;

    @Override
    public List<ProductModel> findAllProducts(){
        return productPortOut.findAll();
    }

    @Override
    public ProductModel createProduct(ProductModel model) {
        return productPortOut.create(model);
    }

    @Override
    public ProductModel deleteProduct(ProductModel model) {
        return productPortOut.delete(model);
    }

    @Override
    public ProductModel updateProduct(ProductModel model, ProductUpdateRequest request) {
        return productPortOut.update(model, request);
    }

    @Override
    public ProductModel discountValue(ProductModel productModel, DiscountRequestDto discount){
    ProductModel product = productPortOut.findOne(productModel);
    BigDecimal newSalePrice = this.calculateDiscount(product.getPrice(), discount.getDiscount());

        if (newSalePrice.compareTo(MIN_PRICE) < 0){
            throw new BusinessException("El precio resultante es menor al mínimo permitido");
        }
        ProductUpdateRequest request = ProductUpdateRequest.builder().salePrice(String.valueOf(newSalePrice)).build();
        return this.updateProduct(productModel, request);

    }

    private BigDecimal calculateDiscount(BigDecimal price, Integer discountPercent){
        BigDecimal discount = BigDecimal.valueOf(discountPercent).divide(HUNDRED, 2, RoundingMode.HALF_UP);
        return price.multiply(BigDecimal.ONE.subtract(discount));
    }

    @Override
    public ProductModel availability(ProductModel productModel){
        ProductModel product = productPortOut.findOne(productModel);
        if (product.getIsActive() == false){
            throw new BusinessException("No disponible por producto inactivo");
        }
        if (product.getStock() <= 0 ){
            throw new BusinessException("Producto no Disponible por falta de Stock");
        }

        return product;


    }

}
