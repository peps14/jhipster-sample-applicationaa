package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationaaApp;
import com.mycompany.myapp.domain.AttributeAuthorityDefinition;
import com.mycompany.myapp.repository.AttributeAuthorityDefinitionRepository;
import com.mycompany.myapp.service.AttributeAuthorityDefinitionService;

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
 * Integration tests for the {@link AttributeAuthorityDefinitionResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationaaApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AttributeAuthorityDefinitionResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENABLED = false;
    private static final Boolean UPDATED_ENABLED = true;

    private static final String DEFAULT_SPID_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_SPID_LEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTES_URL = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTES_URL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CONSENT_REQUIRED = false;
    private static final Boolean UPDATED_CONSENT_REQUIRED = true;

    private static final String DEFAULT_CONSENT_URL = "AAAAAAAAAA";
    private static final String UPDATED_CONSENT_URL = "BBBBBBBBBB";

    private static final String DEFAULT_CHECK_CONSENT_URL = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_CONSENT_URL = "BBBBBBBBBB";

    private static final String DEFAULT_ERROR_REDIRECT_URL = "AAAAAAAAAA";
    private static final String UPDATED_ERROR_REDIRECT_URL = "BBBBBBBBBB";

    private static final String DEFAULT_SPID_SCHEMA_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_SPID_SCHEMA_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_API_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_API_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_TEST_URL = "AAAAAAAAAA";
    private static final String UPDATED_TEST_URL = "BBBBBBBBBB";

    @Autowired
    private AttributeAuthorityDefinitionRepository attributeAuthorityDefinitionRepository;

    @Autowired
    private AttributeAuthorityDefinitionService attributeAuthorityDefinitionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAttributeAuthorityDefinitionMockMvc;

    private AttributeAuthorityDefinition attributeAuthorityDefinition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AttributeAuthorityDefinition createEntity(EntityManager em) {
        AttributeAuthorityDefinition attributeAuthorityDefinition = new AttributeAuthorityDefinition()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .enabled(DEFAULT_ENABLED)
            .spidLevel(DEFAULT_SPID_LEVEL)
            .attributesUrl(DEFAULT_ATTRIBUTES_URL)
            .consentRequired(DEFAULT_CONSENT_REQUIRED)
            .consentUrl(DEFAULT_CONSENT_URL)
            .checkConsentUrl(DEFAULT_CHECK_CONSENT_URL)
            .errorRedirectUrl(DEFAULT_ERROR_REDIRECT_URL)
            .spidSchemaVersion(DEFAULT_SPID_SCHEMA_VERSION)
            .apiVersion(DEFAULT_API_VERSION)
            .testUrl(DEFAULT_TEST_URL);
        return attributeAuthorityDefinition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AttributeAuthorityDefinition createUpdatedEntity(EntityManager em) {
        AttributeAuthorityDefinition attributeAuthorityDefinition = new AttributeAuthorityDefinition()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .enabled(UPDATED_ENABLED)
            .spidLevel(UPDATED_SPID_LEVEL)
            .attributesUrl(UPDATED_ATTRIBUTES_URL)
            .consentRequired(UPDATED_CONSENT_REQUIRED)
            .consentUrl(UPDATED_CONSENT_URL)
            .checkConsentUrl(UPDATED_CHECK_CONSENT_URL)
            .errorRedirectUrl(UPDATED_ERROR_REDIRECT_URL)
            .spidSchemaVersion(UPDATED_SPID_SCHEMA_VERSION)
            .apiVersion(UPDATED_API_VERSION)
            .testUrl(UPDATED_TEST_URL);
        return attributeAuthorityDefinition;
    }

    @BeforeEach
    public void initTest() {
        attributeAuthorityDefinition = createEntity(em);
    }

    @Test
    @Transactional
    public void createAttributeAuthorityDefinition() throws Exception {
        int databaseSizeBeforeCreate = attributeAuthorityDefinitionRepository.findAll().size();
        // Create the AttributeAuthorityDefinition
        restAttributeAuthorityDefinitionMockMvc.perform(post("/api/attribute-authority-definitions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(attributeAuthorityDefinition)))
            .andExpect(status().isCreated());

        // Validate the AttributeAuthorityDefinition in the database
        List<AttributeAuthorityDefinition> attributeAuthorityDefinitionList = attributeAuthorityDefinitionRepository.findAll();
        assertThat(attributeAuthorityDefinitionList).hasSize(databaseSizeBeforeCreate + 1);
        AttributeAuthorityDefinition testAttributeAuthorityDefinition = attributeAuthorityDefinitionList.get(attributeAuthorityDefinitionList.size() - 1);
        assertThat(testAttributeAuthorityDefinition.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testAttributeAuthorityDefinition.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAttributeAuthorityDefinition.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAttributeAuthorityDefinition.isEnabled()).isEqualTo(DEFAULT_ENABLED);
        assertThat(testAttributeAuthorityDefinition.getSpidLevel()).isEqualTo(DEFAULT_SPID_LEVEL);
        assertThat(testAttributeAuthorityDefinition.getAttributesUrl()).isEqualTo(DEFAULT_ATTRIBUTES_URL);
        assertThat(testAttributeAuthorityDefinition.isConsentRequired()).isEqualTo(DEFAULT_CONSENT_REQUIRED);
        assertThat(testAttributeAuthorityDefinition.getConsentUrl()).isEqualTo(DEFAULT_CONSENT_URL);
        assertThat(testAttributeAuthorityDefinition.getCheckConsentUrl()).isEqualTo(DEFAULT_CHECK_CONSENT_URL);
        assertThat(testAttributeAuthorityDefinition.getErrorRedirectUrl()).isEqualTo(DEFAULT_ERROR_REDIRECT_URL);
        assertThat(testAttributeAuthorityDefinition.getSpidSchemaVersion()).isEqualTo(DEFAULT_SPID_SCHEMA_VERSION);
        assertThat(testAttributeAuthorityDefinition.getApiVersion()).isEqualTo(DEFAULT_API_VERSION);
        assertThat(testAttributeAuthorityDefinition.getTestUrl()).isEqualTo(DEFAULT_TEST_URL);
    }

    @Test
    @Transactional
    public void createAttributeAuthorityDefinitionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attributeAuthorityDefinitionRepository.findAll().size();

        // Create the AttributeAuthorityDefinition with an existing ID
        attributeAuthorityDefinition.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttributeAuthorityDefinitionMockMvc.perform(post("/api/attribute-authority-definitions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(attributeAuthorityDefinition)))
            .andExpect(status().isBadRequest());

        // Validate the AttributeAuthorityDefinition in the database
        List<AttributeAuthorityDefinition> attributeAuthorityDefinitionList = attributeAuthorityDefinitionRepository.findAll();
        assertThat(attributeAuthorityDefinitionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAttributeAuthorityDefinitions() throws Exception {
        // Initialize the database
        attributeAuthorityDefinitionRepository.saveAndFlush(attributeAuthorityDefinition);

        // Get all the attributeAuthorityDefinitionList
        restAttributeAuthorityDefinitionMockMvc.perform(get("/api/attribute-authority-definitions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attributeAuthorityDefinition.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].enabled").value(hasItem(DEFAULT_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].spidLevel").value(hasItem(DEFAULT_SPID_LEVEL)))
            .andExpect(jsonPath("$.[*].attributesUrl").value(hasItem(DEFAULT_ATTRIBUTES_URL)))
            .andExpect(jsonPath("$.[*].consentRequired").value(hasItem(DEFAULT_CONSENT_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].consentUrl").value(hasItem(DEFAULT_CONSENT_URL)))
            .andExpect(jsonPath("$.[*].checkConsentUrl").value(hasItem(DEFAULT_CHECK_CONSENT_URL)))
            .andExpect(jsonPath("$.[*].errorRedirectUrl").value(hasItem(DEFAULT_ERROR_REDIRECT_URL)))
            .andExpect(jsonPath("$.[*].spidSchemaVersion").value(hasItem(DEFAULT_SPID_SCHEMA_VERSION)))
            .andExpect(jsonPath("$.[*].apiVersion").value(hasItem(DEFAULT_API_VERSION)))
            .andExpect(jsonPath("$.[*].testUrl").value(hasItem(DEFAULT_TEST_URL)));
    }
    
    @Test
    @Transactional
    public void getAttributeAuthorityDefinition() throws Exception {
        // Initialize the database
        attributeAuthorityDefinitionRepository.saveAndFlush(attributeAuthorityDefinition);

        // Get the attributeAuthorityDefinition
        restAttributeAuthorityDefinitionMockMvc.perform(get("/api/attribute-authority-definitions/{id}", attributeAuthorityDefinition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(attributeAuthorityDefinition.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.enabled").value(DEFAULT_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.spidLevel").value(DEFAULT_SPID_LEVEL))
            .andExpect(jsonPath("$.attributesUrl").value(DEFAULT_ATTRIBUTES_URL))
            .andExpect(jsonPath("$.consentRequired").value(DEFAULT_CONSENT_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.consentUrl").value(DEFAULT_CONSENT_URL))
            .andExpect(jsonPath("$.checkConsentUrl").value(DEFAULT_CHECK_CONSENT_URL))
            .andExpect(jsonPath("$.errorRedirectUrl").value(DEFAULT_ERROR_REDIRECT_URL))
            .andExpect(jsonPath("$.spidSchemaVersion").value(DEFAULT_SPID_SCHEMA_VERSION))
            .andExpect(jsonPath("$.apiVersion").value(DEFAULT_API_VERSION))
            .andExpect(jsonPath("$.testUrl").value(DEFAULT_TEST_URL));
    }
    @Test
    @Transactional
    public void getNonExistingAttributeAuthorityDefinition() throws Exception {
        // Get the attributeAuthorityDefinition
        restAttributeAuthorityDefinitionMockMvc.perform(get("/api/attribute-authority-definitions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAttributeAuthorityDefinition() throws Exception {
        // Initialize the database
        attributeAuthorityDefinitionService.save(attributeAuthorityDefinition);

        int databaseSizeBeforeUpdate = attributeAuthorityDefinitionRepository.findAll().size();

        // Update the attributeAuthorityDefinition
        AttributeAuthorityDefinition updatedAttributeAuthorityDefinition = attributeAuthorityDefinitionRepository.findById(attributeAuthorityDefinition.getId()).get();
        // Disconnect from session so that the updates on updatedAttributeAuthorityDefinition are not directly saved in db
        em.detach(updatedAttributeAuthorityDefinition);
        updatedAttributeAuthorityDefinition
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .enabled(UPDATED_ENABLED)
            .spidLevel(UPDATED_SPID_LEVEL)
            .attributesUrl(UPDATED_ATTRIBUTES_URL)
            .consentRequired(UPDATED_CONSENT_REQUIRED)
            .consentUrl(UPDATED_CONSENT_URL)
            .checkConsentUrl(UPDATED_CHECK_CONSENT_URL)
            .errorRedirectUrl(UPDATED_ERROR_REDIRECT_URL)
            .spidSchemaVersion(UPDATED_SPID_SCHEMA_VERSION)
            .apiVersion(UPDATED_API_VERSION)
            .testUrl(UPDATED_TEST_URL);

        restAttributeAuthorityDefinitionMockMvc.perform(put("/api/attribute-authority-definitions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAttributeAuthorityDefinition)))
            .andExpect(status().isOk());

        // Validate the AttributeAuthorityDefinition in the database
        List<AttributeAuthorityDefinition> attributeAuthorityDefinitionList = attributeAuthorityDefinitionRepository.findAll();
        assertThat(attributeAuthorityDefinitionList).hasSize(databaseSizeBeforeUpdate);
        AttributeAuthorityDefinition testAttributeAuthorityDefinition = attributeAuthorityDefinitionList.get(attributeAuthorityDefinitionList.size() - 1);
        assertThat(testAttributeAuthorityDefinition.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testAttributeAuthorityDefinition.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAttributeAuthorityDefinition.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAttributeAuthorityDefinition.isEnabled()).isEqualTo(UPDATED_ENABLED);
        assertThat(testAttributeAuthorityDefinition.getSpidLevel()).isEqualTo(UPDATED_SPID_LEVEL);
        assertThat(testAttributeAuthorityDefinition.getAttributesUrl()).isEqualTo(UPDATED_ATTRIBUTES_URL);
        assertThat(testAttributeAuthorityDefinition.isConsentRequired()).isEqualTo(UPDATED_CONSENT_REQUIRED);
        assertThat(testAttributeAuthorityDefinition.getConsentUrl()).isEqualTo(UPDATED_CONSENT_URL);
        assertThat(testAttributeAuthorityDefinition.getCheckConsentUrl()).isEqualTo(UPDATED_CHECK_CONSENT_URL);
        assertThat(testAttributeAuthorityDefinition.getErrorRedirectUrl()).isEqualTo(UPDATED_ERROR_REDIRECT_URL);
        assertThat(testAttributeAuthorityDefinition.getSpidSchemaVersion()).isEqualTo(UPDATED_SPID_SCHEMA_VERSION);
        assertThat(testAttributeAuthorityDefinition.getApiVersion()).isEqualTo(UPDATED_API_VERSION);
        assertThat(testAttributeAuthorityDefinition.getTestUrl()).isEqualTo(UPDATED_TEST_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingAttributeAuthorityDefinition() throws Exception {
        int databaseSizeBeforeUpdate = attributeAuthorityDefinitionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAttributeAuthorityDefinitionMockMvc.perform(put("/api/attribute-authority-definitions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(attributeAuthorityDefinition)))
            .andExpect(status().isBadRequest());

        // Validate the AttributeAuthorityDefinition in the database
        List<AttributeAuthorityDefinition> attributeAuthorityDefinitionList = attributeAuthorityDefinitionRepository.findAll();
        assertThat(attributeAuthorityDefinitionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAttributeAuthorityDefinition() throws Exception {
        // Initialize the database
        attributeAuthorityDefinitionService.save(attributeAuthorityDefinition);

        int databaseSizeBeforeDelete = attributeAuthorityDefinitionRepository.findAll().size();

        // Delete the attributeAuthorityDefinition
        restAttributeAuthorityDefinitionMockMvc.perform(delete("/api/attribute-authority-definitions/{id}", attributeAuthorityDefinition.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AttributeAuthorityDefinition> attributeAuthorityDefinitionList = attributeAuthorityDefinitionRepository.findAll();
        assertThat(attributeAuthorityDefinitionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
