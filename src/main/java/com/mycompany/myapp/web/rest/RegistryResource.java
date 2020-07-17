package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Registry;
import com.mycompany.myapp.service.RegistryService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Registry}.
 */
@RestController
@RequestMapping("/api")
public class RegistryResource {

    private final Logger log = LoggerFactory.getLogger(RegistryResource.class);

    private static final String ENTITY_NAME = "registry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RegistryService registryService;

    public RegistryResource(RegistryService registryService) {
        this.registryService = registryService;
    }

    /**
     * {@code POST  /registries} : Create a new registry.
     *
     * @param registry the registry to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new registry, or with status {@code 400 (Bad Request)} if the registry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/registries")
    public ResponseEntity<Registry> createRegistry(@RequestBody Registry registry) throws URISyntaxException {
        log.debug("REST request to save Registry : {}", registry);
        if (registry.getId() != null) {
            throw new BadRequestAlertException("A new registry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Registry result = registryService.save(registry);
        return ResponseEntity.created(new URI("/api/registries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /registries} : Updates an existing registry.
     *
     * @param registry the registry to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated registry,
     * or with status {@code 400 (Bad Request)} if the registry is not valid,
     * or with status {@code 500 (Internal Server Error)} if the registry couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/registries")
    public ResponseEntity<Registry> updateRegistry(@RequestBody Registry registry) throws URISyntaxException {
        log.debug("REST request to update Registry : {}", registry);
        if (registry.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Registry result = registryService.save(registry);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, registry.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /registries} : get all the registries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of registries in body.
     */
    @GetMapping("/registries")
    public List<Registry> getAllRegistries() {
        log.debug("REST request to get all Registries");
        return registryService.findAll();
    }

    /**
     * {@code GET  /registries/:id} : get the "id" registry.
     *
     * @param id the id of the registry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the registry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/registries/{id}")
    public ResponseEntity<Registry> getRegistry(@PathVariable Long id) {
        log.debug("REST request to get Registry : {}", id);
        Optional<Registry> registry = registryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(registry);
    }

    /**
     * {@code DELETE  /registries/:id} : delete the "id" registry.
     *
     * @param id the id of the registry to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/registries/{id}")
    public ResponseEntity<Void> deleteRegistry(@PathVariable Long id) {
        log.debug("REST request to delete Registry : {}", id);
        registryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
