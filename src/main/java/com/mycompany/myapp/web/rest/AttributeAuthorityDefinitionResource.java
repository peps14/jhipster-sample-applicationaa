package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.AttributeAuthorityDefinition;
import com.mycompany.myapp.service.AttributeAuthorityDefinitionService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.AttributeAuthorityDefinition}.
 */
@RestController
@RequestMapping("/api")
public class AttributeAuthorityDefinitionResource {

    private final Logger log = LoggerFactory.getLogger(AttributeAuthorityDefinitionResource.class);

    private static final String ENTITY_NAME = "attributeAuthorityDefinition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AttributeAuthorityDefinitionService attributeAuthorityDefinitionService;

    public AttributeAuthorityDefinitionResource(AttributeAuthorityDefinitionService attributeAuthorityDefinitionService) {
        this.attributeAuthorityDefinitionService = attributeAuthorityDefinitionService;
    }

    /**
     * {@code POST  /attribute-authority-definitions} : Create a new attributeAuthorityDefinition.
     *
     * @param attributeAuthorityDefinition the attributeAuthorityDefinition to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new attributeAuthorityDefinition, or with status {@code 400 (Bad Request)} if the attributeAuthorityDefinition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/attribute-authority-definitions")
    public ResponseEntity<AttributeAuthorityDefinition> createAttributeAuthorityDefinition(@RequestBody AttributeAuthorityDefinition attributeAuthorityDefinition) throws URISyntaxException {
        log.debug("REST request to save AttributeAuthorityDefinition : {}", attributeAuthorityDefinition);
        if (attributeAuthorityDefinition.getId() != null) {
            throw new BadRequestAlertException("A new attributeAuthorityDefinition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttributeAuthorityDefinition result = attributeAuthorityDefinitionService.save(attributeAuthorityDefinition);
        return ResponseEntity.created(new URI("/api/attribute-authority-definitions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /attribute-authority-definitions} : Updates an existing attributeAuthorityDefinition.
     *
     * @param attributeAuthorityDefinition the attributeAuthorityDefinition to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated attributeAuthorityDefinition,
     * or with status {@code 400 (Bad Request)} if the attributeAuthorityDefinition is not valid,
     * or with status {@code 500 (Internal Server Error)} if the attributeAuthorityDefinition couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/attribute-authority-definitions")
    public ResponseEntity<AttributeAuthorityDefinition> updateAttributeAuthorityDefinition(@RequestBody AttributeAuthorityDefinition attributeAuthorityDefinition) throws URISyntaxException {
        log.debug("REST request to update AttributeAuthorityDefinition : {}", attributeAuthorityDefinition);
        if (attributeAuthorityDefinition.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AttributeAuthorityDefinition result = attributeAuthorityDefinitionService.save(attributeAuthorityDefinition);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, attributeAuthorityDefinition.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /attribute-authority-definitions} : get all the attributeAuthorityDefinitions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of attributeAuthorityDefinitions in body.
     */
    @GetMapping("/attribute-authority-definitions")
    public List<AttributeAuthorityDefinition> getAllAttributeAuthorityDefinitions() {
        log.debug("REST request to get all AttributeAuthorityDefinitions");
        return attributeAuthorityDefinitionService.findAll();
    }

    /**
     * {@code GET  /attribute-authority-definitions/:id} : get the "id" attributeAuthorityDefinition.
     *
     * @param id the id of the attributeAuthorityDefinition to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the attributeAuthorityDefinition, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/attribute-authority-definitions/{id}")
    public ResponseEntity<AttributeAuthorityDefinition> getAttributeAuthorityDefinition(@PathVariable Long id) {
        log.debug("REST request to get AttributeAuthorityDefinition : {}", id);
        Optional<AttributeAuthorityDefinition> attributeAuthorityDefinition = attributeAuthorityDefinitionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(attributeAuthorityDefinition);
    }

    /**
     * {@code DELETE  /attribute-authority-definitions/:id} : delete the "id" attributeAuthorityDefinition.
     *
     * @param id the id of the attributeAuthorityDefinition to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/attribute-authority-definitions/{id}")
    public ResponseEntity<Void> deleteAttributeAuthorityDefinition(@PathVariable Long id) {
        log.debug("REST request to delete AttributeAuthorityDefinition : {}", id);
        attributeAuthorityDefinitionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
