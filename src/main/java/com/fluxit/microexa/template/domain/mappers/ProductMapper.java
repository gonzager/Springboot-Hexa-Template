package com.fluxit.microexa.template.domain.mappers;

import com.fluxit.microexa.template.domain.models.Product;
import com.fluxit.microexa.template.infraestructure.adapters.out.persistence.entities.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "urlImage", target = "urlImage"),
    })
    Product toProduct(ProductEntity producto);
    Iterable<Product> toProducts(Iterable<ProductEntity> productoEntity);

    @InheritInverseConfiguration
    ProductEntity toProductEnity (Product product);

}
