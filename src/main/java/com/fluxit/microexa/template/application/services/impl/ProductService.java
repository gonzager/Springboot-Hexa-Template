package com.fluxit.microexa.template.application.services.impl;

import com.fluxit.microexa.template.application.services.IProductService;
import com.fluxit.microexa.template.domain.models.Product;
import com.fluxit.microexa.template.domain.ports.in.ICreateProduct;
import com.fluxit.microexa.template.domain.ports.in.IGetProduct;

import java.util.List;
import java.util.Optional;

public class ProductService implements IProductService {

    private final ICreateProduct createProduct;
    private final IGetProduct getProduct;

    public ProductService(ICreateProduct createProduct, IGetProduct getProduct) {
        this.createProduct = createProduct;
        this.getProduct = getProduct;
    }


    @Override
    public List<Product> getProducts() {
        return this.getProduct.getProducts();
    }


    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = Optional.ofNullable(this.getProduct.getProductById(id));
        return product.orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
       return this.createProduct.saveProduct(product);
    }
}
