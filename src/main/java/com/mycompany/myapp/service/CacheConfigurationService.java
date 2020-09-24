package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.CacheConfiguration;
import com.mycompany.myapp.repository.CacheConfigurationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link CacheConfiguration}.
 */
@Service
@Transactional
public class CacheConfigurationService {

    private final Logger log = LoggerFactory.getLogger(CacheConfigurationService.class);

    private final CacheConfigurationRepository cacheConfigurationRepository;

    public CacheConfigurationService(CacheConfigurationRepository cacheConfigurationRepository) {
        this.cacheConfigurationRepository = cacheConfigurationRepository;
    }

    /**
     * Save a cacheConfiguration.
     *
     * @param cacheConfiguration the entity to save.
     * @return the persisted entity.
     */
    public CacheConfiguration save(CacheConfiguration cacheConfiguration) {
        log.debug("Request to save CacheConfiguration : {}", cacheConfiguration);
        return cacheConfigurationRepository.save(cacheConfiguration);
    }

    /**
     * Get all the cacheConfigurations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CacheConfiguration> findAll() {
        log.debug("Request to get all CacheConfigurations");
        return cacheConfigurationRepository.findAll();
    }


    /**
     * Get one cacheConfiguration by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CacheConfiguration> findOne(Long id) {
        log.debug("Request to get CacheConfiguration : {}", id);
        return cacheConfigurationRepository.findById(id);
    }

    /**
     * Delete the cacheConfiguration by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CacheConfiguration : {}", id);
        cacheConfigurationRepository.deleteById(id);
    }
}
