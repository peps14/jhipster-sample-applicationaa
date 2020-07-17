package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationaaApp;
import com.mycompany.myapp.domain.AttributeDefinition;
import com.mycompany.myapp.domain.AttributeAuthorityDefinition;
import com.mycompany.myapp.repository.AttributeDefinitionRepository;
import com.mycompany.myapp.service.AttributeDefinitionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AttributeDefinitionResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationaaApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AttributeDefinitionResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PUBLIC_AVALABLE = "AAAAAAAAAA";
    private static final String UPDATED_PUBLIC_AVALABLE = "BBBBBBBBBB";

    private static final String DEFAULT_SPID_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_SPID_LEVEL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MULTIVALUE = false;
    private static final Boolean UPDATED_MULTIVALUE = true;

    private static final String DEFAULT_DEFAULT_VALUES = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_VALUES = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CONSENT_REQUIRED = false;
    private static final Boolean UPDATED_CONSENT_REQUIRED = true;

    private static final String DEFAULT_AA_CODE = "AAAAAAAAAA";
    private static final String UPDATED_AA_CODE = "BBBBBBBBBB";

    @Autowired
    private AttributeDefinitionRepository attributeDefinitionRepository;

    @Autowired
    private AttributeDefinitionService attributeDefinitionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAttributeDefinitionMockMvc;

    private AttributeDefinition attributeDefinition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AttributeDefinition createEntity(EntityManager em) {
        AttributeDefinition attributeDefinition = new AttributeDefinition()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .publicAvalable(DEFAULT_PUBLIC_AVALABLE)
            .spidLevel(DEFAULT_SPID_LEVEL)
            .multivalue(DEFAULT_MULTIVALUE)
            .defaultValues(DEFAULT_DEFAULT_VALUES)
            .consentRequired(DEFAULT_CONSENT_REQUIRED)
            .aaCode(DEFAULT_AA_CODE);
        // Add required entity
        AttributeAuthorityDefinition attributeAuthorityDefinition;
        if (TestUtil.findAll(em, AttributeAuthorityDefinition.class).isEmpty()) {
            attributeAuthorityDefinition = AttributeAuthorityDefinitionResourceIT.createEntity(em);
            em.persist(attributeAuthorityDefinition);
            em.flush();
        } else {
            attributeAuthorityDefinition = TestUtil.findAll(em, AttributeAuthorityDefinition.class).get(0);
        }
        attributeDefinition.setAttributeAuthority(attributeAuthorityDefinition);
        return attributeDefinition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AttributeDefinition createUpdatedEntity(EntityManager em) {
        AttributeDefinition attributeDefinition = new AttributeDefinition()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .publicAvalable(UPDATED_PUBLIC_AVALABLE)
            .spidLevel(UPDATED_SPID_LEVEL)
            .multivalue(UPDATED_MULTIVALUE)
            .defaultValues(UPDATED_DEFAULT_VALUES)
            .consentRequired(UPDATED_CONSENT_REQUIRED)
            .aaCode(UPDATED_AA_CODE);
        // Add required entity
        AttributeAuthorityDefinition attributeAuthorityDefinition;
        if (TestUtil.findAll(em, AttributeAuthorityDefinition.class).isEmpty()) {
            attributeAuthorityDefinition = AttributeAuthorityDefinitionResourceIT.createUpdatedEntity(em);
            em.persist(attributeAuthorityDefinition);
            em.flush();
        } else {
            attributeAuthorityDefinition = TestUtil.findAll(em, AttributeAuthorityDefinition.class).get(0);
        }
        attributeDefinition.setAttributeAuthority(attributeAuthorityDefinition);
        return attributeDefinition;
    }

    @BeforeEach
    public void initTest() {
        attributeDefinition = createEntity(em);
    }

    @Test
    @Transactional
    public void createAttributeDefinition() throws Exception {
        int databaseSizeBeforeCreate = attributeDefinitionRepository.findAll().size();
        // Create the AttributeDefinition
        restAttributeDefinitionMockMvc.perform(post("/api/attribute-definitions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(attributeDefinition)))
            .andExpect(status().isCreated());

        // Validate the AttributeDefinition in the database
        List<AttributeDefinition> attributeDefinitionList = attributeDefinitionRepository.findAll();
        assertThat(attributeDefinitionList).hasSize(databaseSizeBeforeCreate + 1);
        AttributeDefinition testAttributeDefinition = attributeDefinitionList.get(attributeDefinitionList.size() - 1);
        assertThat(testAttributeDefinition.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testAttributeDefinition.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAttributeDefinition.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAttributeDefinition.getPublicAvalable()).isEqualTo(DEFAULT_PUBLIC_AVALABLE);
        assertThat(testAttributeDefinition.getSpidLevel()).isEqualTo(DEFAULT_SPID_LEVEL);
        assertThat(testAttributeDefinition.isMultivalue()).isEqualTo(DEFAULT_MULTIVALUE);
        assertThat(testAttributeDefinition.getDefaultValues()).isEqualTo(DEFAULT_DEFAULT_VALUES);
        assertThat(testAttributeDefinition.isConsentRequired()).isEqualTo(DEFAULT_CONSENT_REQUIRED);
        assertThat(testAttributeDefinition.getAaCode()).isEqualTo(DEFAULT_AA_CODE);
    }

    @Test
    @Transactional
    public void createAttributeDefinitionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attributeDefinitionRepository.findAll().size();

        // Create the AttributeDefinition with an existing ID
        attributeDefinition.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttributeDefinitionMockMvc.perform(post("/api/attribute-definitions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(attributeDefinition)))
            .andExpect(status().isBadRequest());

        // Validate the AttributeDefinition in the database
        List<AttributeDefinition> attributeDefinitionList = attributeDefinitionRepository.findAll();
        assertThat(attributeDefinitionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAttributeDefinitions() throws Exception {
        // Initialize the database
        attributeDefinitionRepository.saveAndFlush(attributeDefinition);

        // Get all the attributeDefinitionList
        restAttributeDefinitionMockMvc.perform(get("/api/attribute-definitions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attributeDefinition.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].publicAvalable").value(hasItem(DEFAULT_PUBLIC_AVALABLE)))
            .andExpect(jsonPath("$.[*].spidLevel").value(hasItem(DEFAULT_SPID_LEVEL)))
            .andExpect(jsonPath("$.[*].multivalue").value(hasItem(DEFAULT_MULTIVALUE.booleanValue())))
            .andExpect(jsonPath("$.[*].defaultValues").value(hasItem(DEFAULT_DEFAULT_VALUES.toString())))
            .andExpect(jsonPath("$.[*].consentRequired").value(hasItem(DEFAULT_CONSENT_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].aaCode").value(hasItem(DEFAULT_AA_CODE)));
    }
    
    @Test
    @Transactional
    public void getAttributeDefinition() throws Exception {
        // Initialize the database
        attributeDefinitionRepository.saveAndFlush(attributeDefinition);

        // Get the attributeDefinition
        restAttributeDefinitionMockMvc.perform(get("/api/attribute-definitions/{id}", attributeDefinition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(attributeDefinition.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.publicAvalable").value(DEFAULT_PUBLIC_AVALABLE))
            .andExpect(jsonPath("$.spidLevel").value(DEFAULT_SPID_LEVEL))
            .andExpect(jsonPath("$.multivalue").value(DEFAULT_MULTIVALUE.booleanValue()))
            .andExpect(jsonPath("$.defaultValues").value(DEFAULT_DEFAULT_VALUES.toString()))
            .andExpect(jsonPath("$.consentRequired").value(DEFAULT_CONSENT_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.aaCode").value(DEFAULT_AA_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingAttributeDefinition() throws Exception {
        // Get the attributeDefinition
        restAttributeDefinitionMockMvc.perform(get("/api/attribute-definitions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAttributeDefinition() throws Exception {
        // Initialize the database
        attributeDefinitionService.save(attributeDefinition);

        int databaseSizeBeforeUpdate = attributeDefinitionRepository.findAll().size();

        // Update the attributeDefinition
        AttributeDefinition updatedAttributeDefinition = attributeDefinitionRepository.findById(attributeDefinition.getId()).get();
        // Disconnect from session so that the updates on updatedAttributeDefinition are not directly saved in db
        em.detach(updatedAttributeDefinition);
        updatedAttributeDefinition
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .publicAvalable(UPDATED_PUBLIC_AVALABLE)
            .spidLevel(UPDATED_SPID_LEVEL)
            .multivalue(UPDATED_MULTIVALUE)
            .defaultValues(UPDATED_DEFAULT_VALUES)
            .consentRequired(UPDATED_CONSENT_REQUIRED)
            .aaCode(UPDATED_AA_CODE);

        restAttributeDefinitionMockMvc.perform(put("/api/attribute-definitions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAttributeDefinition)))
            .andExpect(status().isOk());

        // Validate the AttributeDefinition in the database
        List<AttributeDefinition> attributeDefinitionList = attributeDefinitionRepository.findAll();
        assertThat(attributeDefinitionList).hasSize(databaseSizeBeforeUpdate);
        AttributeDefinition testAttributeDefinition = attributeDefinitionList.get(attributeDefinitionList.size() - 1);
        assertThat(testAttributeDefinition.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testAttributeDefinition.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAttributeDefinition.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAttributeDefinition.getPublicAvalable()).isEqualTo(UPDATED_PUBLIC_AVALABLE);
        assertThat(testAttributeDefinition.getSpidLevel()).isEqualTo(UPDATED_SPID_LEVEL);
        assertThat(testAttributeDefinition.isMultivalue()).isEqualTo(UPDATED_MULTIVALUE);
        assertThat(testAttributeDefinition.getDefaultValues()).isEqualTo(UPDATED_DEFAULT_VALUES);
        assertThat(testAttributeDefinition.isConsentRequired()).isEqualTo(UPDATED_CONSENT_REQUIRED);
        assertThat(testAttributeDefinition.getAaCode()).isEqualTo(UPDATED_AA_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingAttributeDefinition() throws Exception {
        int databaseSizeBeforeUpdate = attributeDefinitionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAttributeDefinitionMockMvc.perform(put("/api/attribute-definitions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(attributeDefinition)))
            .andExpect(status().isBadRequest());

        // Validate the AttributeDefinition in the database
        List<AttributeDefinition> attributeDefinitionList = attributeDefinitionRepository.findAll();
        assertThat(attributeDefinitionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAttributeDefinition() throws Exception {
        // Initialize the database
        attributeDefinitionService.save(attributeDefinition);

        int databaseSizeBeforeDelete = attributeDefinitionRepository.findAll().size();

        // Delete the attributeDefinition
        restAttributeDefinitionMockMvc.perform(delete("/api/attribute-definitions/{id}", attributeDefinition.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AttributeDefinition> attributeDefinitionList = attributeDefinitionRepository.findAll();
        assertThat(attributeDefinitionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
