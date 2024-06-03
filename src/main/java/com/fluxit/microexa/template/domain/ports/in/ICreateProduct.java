package com.fluxit.microexa.template.domain.ports.in;

import com.fluxit.microexa.template.domain.models.Product;

public interface ICreateProduct {
    Product saveProduct (Product product);
}
