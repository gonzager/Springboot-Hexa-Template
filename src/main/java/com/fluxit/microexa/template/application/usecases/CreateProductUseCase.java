package com.fluxit.microexa.template.application.usecases;

import com.fluxit.microexa.template.domain.models.Product;
import com.fluxit.microexa.template.domain.ports.in.ICreateProduct;
import com.fluxit.microexa.template.domain.ports.out.IProductRepositoryPort;

public class CreateProductUseCase implements ICreateProduct{

    private final IProductRepositoryPort productServicePort;


    public CreateProductUseCase(IProductRepositoryPort productServicePort) {
        this.productServicePort = productServicePort;
    }


    @Override
    public Product saveProduct(Product product) {
        return this.productServicePort.saveProduct(product);
    }
}
