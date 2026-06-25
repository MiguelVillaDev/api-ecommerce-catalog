package com.project.catalogo.application.service;

import com.project.catalogo.application.port.in.ProductPortIn;
import com.project.catalogo.application.port.out.ProductPortOut;
import com.project.catalogo.domain.model.ProductModel;
import com.project.catalogo.infrastruture.adapter.in.rest.dto.ProductUpdateRequest;
import com.project.catalogo.infrastruture.adapter.in.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements ProductPortIn {

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
    public ProductModel discountValue(Integer id, Integer val){
        ProductModel productModel = ProductModel.builder().id(id).build();
        ProductModel modelCapt = productPortOut.findOne(productModel);
        BigDecimal num = BigDecimal.valueOf(1);



        if (val >1 && val <= 70){
            BigDecimal value = BigDecimal.valueOf(val);
            BigDecimal newPrice = modelCapt.getPrice().multiply(BigDecimal.ONE.subtract(value.divide(BigDecimal.valueOf(100))));
            if (newPrice.compareTo(num) >= 1){
                ProductUpdateRequest request = ProductUpdateRequest.builder().salePrice(String.valueOf(newPrice)).build();
                return this.updateProduct(productModel, request);
            }
            throw new BusinessException("El precio resultante es menor al mínimo permitido");

        }else {
            throw new BusinessException("El descuento debe estar entre 1 y 70");
        }


    }

}
