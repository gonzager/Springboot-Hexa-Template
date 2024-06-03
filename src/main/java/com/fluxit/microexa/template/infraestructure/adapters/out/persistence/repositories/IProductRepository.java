package com.fluxit.microexa.template.infraestructure.adapters.out.persistence.repositories;

import com.fluxit.microexa.template.infraestructure.adapters.out.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

}
