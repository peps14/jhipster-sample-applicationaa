package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationaaApp;
import com.mycompany.myapp.domain.Registry;
import com.mycompany.myapp.repository.RegistryRepository;
import com.mycompany.myapp.service.RegistryService;

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

/**
 * Integration tests for the {@link RegistryResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationaaApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RegistryResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENABLED = false;
    private static final Boolean UPDATED_ENABLED = true;

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    @Autowired
    private RegistryRepository registryRepository;

    @Autowired
    private RegistryService registryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRegistryMockMvc;

    private Registry registry;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Registry createEntity(EntityManager em) {
        Registry registry = new Registry()
            .name(DEFAULT_NAME)
            .enabled(DEFAULT_ENABLED)
            .url(DEFAULT_URL);
        return registry;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Registry createUpdatedEntity(EntityManager em) {
        Registry registry = new Registry()
            .name(UPDATED_NAME)
            .enabled(UPDATED_ENABLED)
            .url(UPDATED_URL);
        return registry;
    }

    @BeforeEach
    public void initTest() {
        registry = createEntity(em);
    }

    @Test
    @Transactional
    public void createRegistry() throws Exception {
        int databaseSizeBeforeCreate = registryRepository.findAll().size();
        // Create the Registry
        restRegistryMockMvc.perform(post("/api/registries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registry)))
            .andExpect(status().isCreated());

        // Validate the Registry in the database
        List<Registry> registryList = registryRepository.findAll();
        assertThat(registryList).hasSize(databaseSizeBeforeCreate + 1);
        Registry testRegistry = registryList.get(registryList.size() - 1);
        assertThat(testRegistry.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRegistry.isEnabled()).isEqualTo(DEFAULT_ENABLED);
        assertThat(testRegistry.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    public void createRegistryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = registryRepository.findAll().size();

        // Create the Registry with an existing ID
        registry.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegistryMockMvc.perform(post("/api/registries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registry)))
            .andExpect(status().isBadRequest());

        // Validate the Registry in the database
        List<Registry> registryList = registryRepository.findAll();
        assertThat(registryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRegistries() throws Exception {
        // Initialize the database
        registryRepository.saveAndFlush(registry);

        // Get all the registryList
        restRegistryMockMvc.perform(get("/api/registries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(registry.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].enabled").value(hasItem(DEFAULT_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)));
    }
    
    @Test
    @Transactional
    public void getRegistry() throws Exception {
        // Initialize the database
        registryRepository.saveAndFlush(registry);

        // Get the registry
        restRegistryMockMvc.perform(get("/api/registries/{id}", registry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(registry.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.enabled").value(DEFAULT_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL));
    }
    @Test
    @Transactional
    public void getNonExistingRegistry() throws Exception {
        // Get the registry
        restRegistryMockMvc.perform(get("/api/registries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRegistry() throws Exception {
        // Initialize the database
        registryService.save(registry);

        int databaseSizeBeforeUpdate = registryRepository.findAll().size();

        // Update the registry
        Registry updatedRegistry = registryRepository.findById(registry.getId()).get();
        // Disconnect from session so that the updates on updatedRegistry are not directly saved in db
        em.detach(updatedRegistry);
        updatedRegistry
            .name(UPDATED_NAME)
            .enabled(UPDATED_ENABLED)
            .url(UPDATED_URL);

        restRegistryMockMvc.perform(put("/api/registries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRegistry)))
            .andExpect(status().isOk());

        // Validate the Registry in the database
        List<Registry> registryList = registryRepository.findAll();
        assertThat(registryList).hasSize(databaseSizeBeforeUpdate);
        Registry testRegistry = registryList.get(registryList.size() - 1);
        assertThat(testRegistry.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRegistry.isEnabled()).isEqualTo(UPDATED_ENABLED);
        assertThat(testRegistry.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingRegistry() throws Exception {
        int databaseSizeBeforeUpdate = registryRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegistryMockMvc.perform(put("/api/registries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registry)))
            .andExpect(status().isBadRequest());

        // Validate the Registry in the database
        List<Registry> registryList = registryRepository.findAll();
        assertThat(registryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRegistry() throws Exception {
        // Initialize the database
        registryService.save(registry);

        int databaseSizeBeforeDelete = registryRepository.findAll().size();

        // Delete the registry
        restRegistryMockMvc.perform(delete("/api/registries/{id}", registry.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Registry> registryList = registryRepository.findAll();
        assertThat(registryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
