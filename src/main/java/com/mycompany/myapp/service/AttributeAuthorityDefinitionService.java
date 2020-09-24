package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.AttributeAuthorityDefinition;
import com.mycompany.myapp.repository.AttributeAuthorityDefinitionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link AttributeAuthorityDefinition}.
 */
@Service
@Transactional
public class AttributeAuthorityDefinitionService {

    private final Logger log = LoggerFactory.getLogger(AttributeAuthorityDefinitionService.class);

    private final AttributeAuthorityDefinitionRepository attributeAuthorityDefinitionRepository;

    public AttributeAuthorityDefinitionService(AttributeAuthorityDefinitionRepository attributeAuthorityDefinitionRepository) {
        this.attributeAuthorityDefinitionRepository = attributeAuthorityDefinitionRepository;
    }

    /**
     * Save a attributeAuthorityDefinition.
     *
     * @param attributeAuthorityDefinition the entity to save.
     * @return the persisted entity.
     */
    public AttributeAuthorityDefinition save(AttributeAuthorityDefinition attributeAuthorityDefinition) {
        log.debug("Request to save AttributeAuthorityDefinition : {}", attributeAuthorityDefinition);
        return attributeAuthorityDefinitionRepository.save(attributeAuthorityDefinition);
    }

    /**
     * Get all the attributeAuthorityDefinitions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AttributeAuthorityDefinition> findAll() {
        log.debug("Request to get all AttributeAuthorityDefinitions");
        return attributeAuthorityDefinitionRepository.findAll();
    }


    /**
     * Get one attributeAuthorityDefinition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AttributeAuthorityDefinition> findOne(Long id) {
        log.debug("Request to get AttributeAuthorityDefinition : {}", id);
        return attributeAuthorityDefinitionRepository.findById(id);
    }

    /**
     * Delete the attributeAuthorityDefinition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AttributeAuthorityDefinition : {}", id);
        attributeAuthorityDefinitionRepository.deleteById(id);
    }
}
