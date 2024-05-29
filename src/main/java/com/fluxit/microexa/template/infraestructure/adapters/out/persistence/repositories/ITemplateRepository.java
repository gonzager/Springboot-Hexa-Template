package com.fluxit.microexa.template.infraestructure.adapters.out.persistence.repositories;

import com.fluxit.microexa.template.infraestructure.adapters.out.persistence.entities.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ITemplateRepository extends JpaRepository<TemplateEntity, UUID> {
}
