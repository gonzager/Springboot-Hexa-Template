package com.fluxit.microexa.template.domain.ports.in;

import com.fluxit.microexa.template.domain.models.Product;

import java.util.List;

public interface IGetProduct {

    List<Product> getProducts();
    Product getProductById(Long id);
}
