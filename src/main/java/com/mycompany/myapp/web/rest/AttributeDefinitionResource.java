package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.AttributeDefinition;
import com.mycompany.myapp.service.AttributeDefinitionService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.AttributeDefinition}.
 */
@RestController
@RequestMapping("/api")
public class AttributeDefinitionResource {

    private final Logger log = LoggerFactory.getLogger(AttributeDefinitionResource.class);

    private static final String ENTITY_NAME = "attributeDefinition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AttributeDefinitionService attributeDefinitionService;

    public AttributeDefinitionResource(AttributeDefinitionService attributeDefinitionService) {
        this.attributeDefinitionService = attributeDefinitionService;
    }

    /**
     * {@code POST  /attribute-definitions} : Create a new attributeDefinition.
     *
     * @param attributeDefinition the attributeDefinition to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new attributeDefinition, or with status {@code 400 (Bad Request)} if the attributeDefinition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/attribute-definitions")
    public ResponseEntity<AttributeDefinition> createAttributeDefinition(@Valid @RequestBody AttributeDefinition attributeDefinition) throws URISyntaxException {
        log.debug("REST request to save AttributeDefinition : {}", attributeDefinition);
        if (attributeDefinition.getId() != null) {
            throw new BadRequestAlertException("A new attributeDefinition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttributeDefinition result = attributeDefinitionService.save(attributeDefinition);
        return ResponseEntity.created(new URI("/api/attribute-definitions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /attribute-definitions} : Updates an existing attributeDefinition.
     *
     * @param attributeDefinition the attributeDefinition to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated attributeDefinition,
     * or with status {@code 400 (Bad Request)} if the attributeDefinition is not valid,
     * or with status {@code 500 (Internal Server Error)} if the attributeDefinition couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/attribute-definitions")
    public ResponseEntity<AttributeDefinition> updateAttributeDefinition(@Valid @RequestBody AttributeDefinition attributeDefinition) throws URISyntaxException {
        log.debug("REST request to update AttributeDefinition : {}", attributeDefinition);
        if (attributeDefinition.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AttributeDefinition result = attributeDefinitionService.save(attributeDefinition);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, attributeDefinition.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /attribute-definitions} : get all the attributeDefinitions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of attributeDefinitions in body.
     */
    @GetMapping("/attribute-definitions")
    public List<AttributeDefinition> getAllAttributeDefinitions() {
        log.debug("REST request to get all AttributeDefinitions");
        return attributeDefinitionService.findAll();
    }

    /**
     * {@code GET  /attribute-definitions/:id} : get the "id" attributeDefinition.
     *
     * @param id the id of the attributeDefinition to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the attributeDefinition, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/attribute-definitions/{id}")
    public ResponseEntity<AttributeDefinition> getAttributeDefinition(@PathVariable Long id) {
        log.debug("REST request to get AttributeDefinition : {}", id);
        Optional<AttributeDefinition> attributeDefinition = attributeDefinitionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(attributeDefinition);
    }

    /**
     * {@code DELETE  /attribute-definitions/:id} : delete the "id" attributeDefinition.
     *
     * @param id the id of the attributeDefinition to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/attribute-definitions/{id}")
    public ResponseEntity<Void> deleteAttributeDefinition(@PathVariable Long id) {
        log.debug("REST request to delete AttributeDefinition : {}", id);
        attributeDefinitionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
