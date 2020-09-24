package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Registry;
import com.mycompany.myapp.repository.RegistryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Registry}.
 */
@Service
@Transactional
public class RegistryService {

    private final Logger log = LoggerFactory.getLogger(RegistryService.class);

    private final RegistryRepository registryRepository;

    public RegistryService(RegistryRepository registryRepository) {
        this.registryRepository = registryRepository;
    }

    /**
     * Save a registry.
     *
     * @param registry the entity to save.
     * @return the persisted entity.
     */
    public Registry save(Registry registry) {
        log.debug("Request to save Registry : {}", registry);
        return registryRepository.save(registry);
    }

    /**
     * Get all the registries.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Registry> findAll() {
        log.debug("Request to get all Registries");
        return registryRepository.findAll();
    }


    /**
     * Get one registry by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Registry> findOne(Long id) {
        log.debug("Request to get Registry : {}", id);
        return registryRepository.findById(id);
    }

    /**
     * Delete the registry by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Registry : {}", id);
        registryRepository.deleteById(id);
    }
}
