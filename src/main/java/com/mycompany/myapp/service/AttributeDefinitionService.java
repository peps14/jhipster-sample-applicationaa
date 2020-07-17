package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.AttributeDefinition;
import com.mycompany.myapp.repository.AttributeDefinitionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link AttributeDefinition}.
 */
@Service
@Transactional
public class AttributeDefinitionService {

    private final Logger log = LoggerFactory.getLogger(AttributeDefinitionService.class);

    private final AttributeDefinitionRepository attributeDefinitionRepository;

    public AttributeDefinitionService(AttributeDefinitionRepository attributeDefinitionRepository) {
        this.attributeDefinitionRepository = attributeDefinitionRepository;
    }

    /**
     * Save a attributeDefinition.
     *
     * @param attributeDefinition the entity to save.
     * @return the persisted entity.
     */
    public AttributeDefinition save(AttributeDefinition attributeDefinition) {
        log.debug("Request to save AttributeDefinition : {}", attributeDefinition);
        return attributeDefinitionRepository.save(attributeDefinition);
    }

    /**
     * Get all the attributeDefinitions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AttributeDefinition> findAll() {
        log.debug("Request to get all AttributeDefinitions");
        return attributeDefinitionRepository.findAll();
    }


    /**
     * Get one attributeDefinition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AttributeDefinition> findOne(Long id) {
        log.debug("Request to get AttributeDefinition : {}", id);
        return attributeDefinitionRepository.findById(id);
    }

    /**
     * Delete the attributeDefinition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AttributeDefinition : {}", id);
        attributeDefinitionRepository.deleteById(id);
    }
}
