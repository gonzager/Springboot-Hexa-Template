package com.fluxit.microexa.template.application.usecases;

import com.fluxit.microexa.template.domain.models.Product;
import com.fluxit.microexa.template.domain.ports.in.IGetProduct;
import com.fluxit.microexa.template.domain.ports.out.IProductRepositoryPort;

import java.util.List;

public class GetProductUseCase implements IGetProduct {
    private final IProductRepositoryPort productServicePort;

    public GetProductUseCase(IProductRepositoryPort productServicePort) {
        this.productServicePort = productServicePort;
    }

    @Override
    public List<Product> getProducts() {
      return this.productServicePort.getProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return this.productServicePort.getProductById(id);
    }
}
