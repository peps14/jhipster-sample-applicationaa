package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.AttributeDefinition;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AttributeDefinition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttributeDefinitionRepository extends JpaRepository<AttributeDefinition, Long> {
}
