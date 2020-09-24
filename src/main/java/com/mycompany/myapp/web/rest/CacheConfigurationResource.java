package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.CacheConfiguration;
import com.mycompany.myapp.service.CacheConfigurationService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.CacheConfiguration}.
 */
@RestController
@RequestMapping("/api")
public class CacheConfigurationResource {

    private final Logger log = LoggerFactory.getLogger(CacheConfigurationResource.class);

    private static final String ENTITY_NAME = "cacheConfiguration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CacheConfigurationService cacheConfigurationService;

    public CacheConfigurationResource(CacheConfigurationService cacheConfigurationService) {
        this.cacheConfigurationService = cacheConfigurationService;
    }

    /**
     * {@code POST  /cache-configurations} : Create a new cacheConfiguration.
     *
     * @param cacheConfiguration the cacheConfiguration to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cacheConfiguration, or with status {@code 400 (Bad Request)} if the cacheConfiguration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cache-configurations")
    public ResponseEntity<CacheConfiguration> createCacheConfiguration(@RequestBody CacheConfiguration cacheConfiguration) throws URISyntaxException {
        log.debug("REST request to save CacheConfiguration : {}", cacheConfiguration);
        if (cacheConfiguration.getId() != null) {
            throw new BadRequestAlertException("A new cacheConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CacheConfiguration result = cacheConfigurationService.save(cacheConfiguration);
        return ResponseEntity.created(new URI("/api/cache-configurations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cache-configurations} : Updates an existing cacheConfiguration.
     *
     * @param cacheConfiguration the cacheConfiguration to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cacheConfiguration,
     * or with status {@code 400 (Bad Request)} if the cacheConfiguration is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cacheConfiguration couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cache-configurations")
    public ResponseEntity<CacheConfiguration> updateCacheConfiguration(@RequestBody CacheConfiguration cacheConfiguration) throws URISyntaxException {
        log.debug("REST request to update CacheConfiguration : {}", cacheConfiguration);
        if (cacheConfiguration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CacheConfiguration result = cacheConfigurationService.save(cacheConfiguration);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cacheConfiguration.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cache-configurations} : get all the cacheConfigurations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cacheConfigurations in body.
     */
    @GetMapping("/cache-configurations")
    public List<CacheConfiguration> getAllCacheConfigurations() {
        log.debug("REST request to get all CacheConfigurations");
        return cacheConfigurationService.findAll();
    }

    /**
     * {@code GET  /cache-configurations/:id} : get the "id" cacheConfiguration.
     *
     * @param id the id of the cacheConfiguration to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cacheConfiguration, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cache-configurations/{id}")
    public ResponseEntity<CacheConfiguration> getCacheConfiguration(@PathVariable Long id) {
        log.debug("REST request to get CacheConfiguration : {}", id);
        Optional<CacheConfiguration> cacheConfiguration = cacheConfigurationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cacheConfiguration);
    }

    /**
     * {@code DELETE  /cache-configurations/:id} : delete the "id" cacheConfiguration.
     *
     * @param id the id of the cacheConfiguration to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cache-configurations/{id}")
    public ResponseEntity<Void> deleteCacheConfiguration(@PathVariable Long id) {
        log.debug("REST request to delete CacheConfiguration : {}", id);
        cacheConfigurationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
