package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationaaApp;
import com.mycompany.myapp.domain.CacheConfiguration;
import com.mycompany.myapp.repository.CacheConfigurationRepository;
import com.mycompany.myapp.service.CacheConfigurationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.CacheType;
/**
 * Integration tests for the {@link CacheConfigurationResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationaaApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CacheConfigurationResourceIT {

    private static final Boolean DEFAULT_ENABLED = false;
    private static final Boolean UPDATED_ENABLED = true;

    private static final Boolean DEFAULT_FORCE_DEFAULT = false;
    private static final Boolean UPDATED_FORCE_DEFAULT = true;

    private static final Boolean DEFAULT_AUTO_REFRESH = false;
    private static final Boolean UPDATED_AUTO_REFRESH = true;

    private static final Boolean DEFAULT_AUTO_CLEAN = false;
    private static final Boolean UPDATED_AUTO_CLEAN = true;

    private static final Long DEFAULT_DURATION = 1L;
    private static final Long UPDATED_DURATION = 2L;

    private static final CacheType DEFAULT_CACHE_TYPE = CacheType.ATTRIBUTE_GLOBAL;
    private static final CacheType UPDATED_CACHE_TYPE = CacheType.CONSENT_GLOBAL;

    @Autowired
    private CacheConfigurationRepository cacheConfigurationRepository;

    @Autowired
    private CacheConfigurationService cacheConfigurationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCacheConfigurationMockMvc;

    private CacheConfiguration cacheConfiguration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CacheConfiguration createEntity(EntityManager em) {
        CacheConfiguration cacheConfiguration = new CacheConfiguration()
            .enabled(DEFAULT_ENABLED)
            .forceDefault(DEFAULT_FORCE_DEFAULT)
            .autoRefresh(DEFAULT_AUTO_REFRESH)
            .autoClean(DEFAULT_AUTO_CLEAN)
            .duration(DEFAULT_DURATION)
            .cacheType(DEFAULT_CACHE_TYPE);
        return cacheConfiguration;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CacheConfiguration createUpdatedEntity(EntityManager em) {
        CacheConfiguration cacheConfiguration = new CacheConfiguration()
            .enabled(UPDATED_ENABLED)
            .forceDefault(UPDATED_FORCE_DEFAULT)
            .autoRefresh(UPDATED_AUTO_REFRESH)
            .autoClean(UPDATED_AUTO_CLEAN)
            .duration(UPDATED_DURATION)
            .cacheType(UPDATED_CACHE_TYPE);
        return cacheConfiguration;
    }

    @BeforeEach
    public void initTest() {
        cacheConfiguration = createEntity(em);
    }

    @Test
    @Transactional
    public void createCacheConfiguration() throws Exception {
        int databaseSizeBeforeCreate = cacheConfigurationRepository.findAll().size();
        // Create the CacheConfiguration
        restCacheConfigurationMockMvc.perform(post("/api/cache-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cacheConfiguration)))
            .andExpect(status().isCreated());

        // Validate the CacheConfiguration in the database
        List<CacheConfiguration> cacheConfigurationList = cacheConfigurationRepository.findAll();
        assertThat(cacheConfigurationList).hasSize(databaseSizeBeforeCreate + 1);
        CacheConfiguration testCacheConfiguration = cacheConfigurationList.get(cacheConfigurationList.size() - 1);
        assertThat(testCacheConfiguration.isEnabled()).isEqualTo(DEFAULT_ENABLED);
        assertThat(testCacheConfiguration.isForceDefault()).isEqualTo(DEFAULT_FORCE_DEFAULT);
        assertThat(testCacheConfiguration.isAutoRefresh()).isEqualTo(DEFAULT_AUTO_REFRESH);
        assertThat(testCacheConfiguration.isAutoClean()).isEqualTo(DEFAULT_AUTO_CLEAN);
        assertThat(testCacheConfiguration.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testCacheConfiguration.getCacheType()).isEqualTo(DEFAULT_CACHE_TYPE);
    }

    @Test
    @Transactional
    public void createCacheConfigurationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cacheConfigurationRepository.findAll().size();

        // Create the CacheConfiguration with an existing ID
        cacheConfiguration.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCacheConfigurationMockMvc.perform(post("/api/cache-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cacheConfiguration)))
            .andExpect(status().isBadRequest());

        // Validate the CacheConfiguration in the database
        List<CacheConfiguration> cacheConfigurationList = cacheConfigurationRepository.findAll();
        assertThat(cacheConfigurationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCacheConfigurations() throws Exception {
        // Initialize the database
        cacheConfigurationRepository.saveAndFlush(cacheConfiguration);

        // Get all the cacheConfigurationList
        restCacheConfigurationMockMvc.perform(get("/api/cache-configurations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cacheConfiguration.getId().intValue())))
            .andExpect(jsonPath("$.[*].enabled").value(hasItem(DEFAULT_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].forceDefault").value(hasItem(DEFAULT_FORCE_DEFAULT.booleanValue())))
            .andExpect(jsonPath("$.[*].autoRefresh").value(hasItem(DEFAULT_AUTO_REFRESH.booleanValue())))
            .andExpect(jsonPath("$.[*].autoClean").value(hasItem(DEFAULT_AUTO_CLEAN.booleanValue())))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION.intValue())))
            .andExpect(jsonPath("$.[*].cacheType").value(hasItem(DEFAULT_CACHE_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getCacheConfiguration() throws Exception {
        // Initialize the database
        cacheConfigurationRepository.saveAndFlush(cacheConfiguration);

        // Get the cacheConfiguration
        restCacheConfigurationMockMvc.perform(get("/api/cache-configurations/{id}", cacheConfiguration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cacheConfiguration.getId().intValue()))
            .andExpect(jsonPath("$.enabled").value(DEFAULT_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.forceDefault").value(DEFAULT_FORCE_DEFAULT.booleanValue()))
            .andExpect(jsonPath("$.autoRefresh").value(DEFAULT_AUTO_REFRESH.booleanValue()))
            .andExpect(jsonPath("$.autoClean").value(DEFAULT_AUTO_CLEAN.booleanValue()))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION.intValue()))
            .andExpect(jsonPath("$.cacheType").value(DEFAULT_CACHE_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCacheConfiguration() throws Exception {
        // Get the cacheConfiguration
        restCacheConfigurationMockMvc.perform(get("/api/cache-configurations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCacheConfiguration() throws Exception {
        // Initialize the database
        cacheConfigurationService.save(cacheConfiguration);

        int databaseSizeBeforeUpdate = cacheConfigurationRepository.findAll().size();

        // Update the cacheConfiguration
        CacheConfiguration updatedCacheConfiguration = cacheConfigurationRepository.findById(cacheConfiguration.getId()).get();
        // Disconnect from session so that the updates on updatedCacheConfiguration are not directly saved in db
        em.detach(updatedCacheConfiguration);
        updatedCacheConfiguration
            .enabled(UPDATED_ENABLED)
            .forceDefault(UPDATED_FORCE_DEFAULT)
            .autoRefresh(UPDATED_AUTO_REFRESH)
            .autoClean(UPDATED_AUTO_CLEAN)
            .duration(UPDATED_DURATION)
            .cacheType(UPDATED_CACHE_TYPE);

        restCacheConfigurationMockMvc.perform(put("/api/cache-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCacheConfiguration)))
            .andExpect(status().isOk());

        // Validate the CacheConfiguration in the database
        List<CacheConfiguration> cacheConfigurationList = cacheConfigurationRepository.findAll();
        assertThat(cacheConfigurationList).hasSize(databaseSizeBeforeUpdate);
        CacheConfiguration testCacheConfiguration = cacheConfigurationList.get(cacheConfigurationList.size() - 1);
        assertThat(testCacheConfiguration.isEnabled()).isEqualTo(UPDATED_ENABLED);
        assertThat(testCacheConfiguration.isForceDefault()).isEqualTo(UPDATED_FORCE_DEFAULT);
        assertThat(testCacheConfiguration.isAutoRefresh()).isEqualTo(UPDATED_AUTO_REFRESH);
        assertThat(testCacheConfiguration.isAutoClean()).isEqualTo(UPDATED_AUTO_CLEAN);
        assertThat(testCacheConfiguration.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testCacheConfiguration.getCacheType()).isEqualTo(UPDATED_CACHE_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCacheConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = cacheConfigurationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCacheConfigurationMockMvc.perform(put("/api/cache-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cacheConfiguration)))
            .andExpect(status().isBadRequest());

        // Validate the CacheConfiguration in the database
        List<CacheConfiguration> cacheConfigurationList = cacheConfigurationRepository.findAll();
        assertThat(cacheConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCacheConfiguration() throws Exception {
        // Initialize the database
        cacheConfigurationService.save(cacheConfiguration);

        int databaseSizeBeforeDelete = cacheConfigurationRepository.findAll().size();

        // Delete the cacheConfiguration
        restCacheConfigurationMockMvc.perform(delete("/api/cache-configurations/{id}", cacheConfiguration.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CacheConfiguration> cacheConfigurationList = cacheConfigurationRepository.findAll();
        assertThat(cacheConfigurationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
