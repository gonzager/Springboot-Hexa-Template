package com.fluxit.microexa.template.infraestructure.adapters.out.persistence.repositories;

import com.fluxit.microexa.template.domain.mappers.ProductMapper;
import com.fluxit.microexa.template.domain.models.Product;
import com.fluxit.microexa.template.domain.ports.out.IProductRepositoryPort;
import com.fluxit.microexa.template.infraestructure.adapters.out.persistence.entities.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductRepositoryAdapter implements IProductRepositoryPort {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductRepositoryAdapter(IProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity=  this.productRepository.save(productMapper.toProductEnity(product));
        return productMapper.toProduct(productEntity);
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }
}
