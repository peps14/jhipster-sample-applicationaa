package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.AttributeAuthorityDefinition;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AttributeAuthorityDefinition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttributeAuthorityDefinitionRepository extends JpaRepository<AttributeAuthorityDefinition, Long> {
}
