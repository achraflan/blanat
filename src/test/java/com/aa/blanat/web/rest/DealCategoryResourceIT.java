package com.aa.blanat.web.rest;

import com.aa.blanat.BlanatApp;
import com.aa.blanat.domain.DealCategory;
import com.aa.blanat.repository.DealCategoryRepository;
import com.aa.blanat.service.DealCategoryService;
import com.aa.blanat.service.dto.DealCategoryDTO;
import com.aa.blanat.service.mapper.DealCategoryMapper;

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
 * Integration tests for the {@link DealCategoryResource} REST controller.
 */
@SpringBootTest(classes = BlanatApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DealCategoryResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_PARENT = false;
    private static final Boolean UPDATED_IS_PARENT = true;

    @Autowired
    private DealCategoryRepository dealCategoryRepository;

    @Autowired
    private DealCategoryMapper dealCategoryMapper;

    @Autowired
    private DealCategoryService dealCategoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDealCategoryMockMvc;

    private DealCategory dealCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealCategory createEntity(EntityManager em) {
        DealCategory dealCategory = new DealCategory()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .isParent(DEFAULT_IS_PARENT);
        return dealCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealCategory createUpdatedEntity(EntityManager em) {
        DealCategory dealCategory = new DealCategory()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .isParent(UPDATED_IS_PARENT);
        return dealCategory;
    }

    @BeforeEach
    public void initTest() {
        dealCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createDealCategory() throws Exception {
        int databaseSizeBeforeCreate = dealCategoryRepository.findAll().size();
        // Create the DealCategory
        DealCategoryDTO dealCategoryDTO = dealCategoryMapper.toDto(dealCategory);
        restDealCategoryMockMvc.perform(post("/api/deal-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the DealCategory in the database
        List<DealCategory> dealCategoryList = dealCategoryRepository.findAll();
        assertThat(dealCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        DealCategory testDealCategory = dealCategoryList.get(dealCategoryList.size() - 1);
        assertThat(testDealCategory.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDealCategory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDealCategory.isIsParent()).isEqualTo(DEFAULT_IS_PARENT);
    }

    @Test
    @Transactional
    public void createDealCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dealCategoryRepository.findAll().size();

        // Create the DealCategory with an existing ID
        dealCategory.setId(1L);
        DealCategoryDTO dealCategoryDTO = dealCategoryMapper.toDto(dealCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDealCategoryMockMvc.perform(post("/api/deal-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealCategory in the database
        List<DealCategory> dealCategoryList = dealCategoryRepository.findAll();
        assertThat(dealCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealCategoryRepository.findAll().size();
        // set the field null
        dealCategory.setName(null);

        // Create the DealCategory, which fails.
        DealCategoryDTO dealCategoryDTO = dealCategoryMapper.toDto(dealCategory);


        restDealCategoryMockMvc.perform(post("/api/deal-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<DealCategory> dealCategoryList = dealCategoryRepository.findAll();
        assertThat(dealCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDealCategories() throws Exception {
        // Initialize the database
        dealCategoryRepository.saveAndFlush(dealCategory);

        // Get all the dealCategoryList
        restDealCategoryMockMvc.perform(get("/api/deal-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dealCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].isParent").value(hasItem(DEFAULT_IS_PARENT.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDealCategory() throws Exception {
        // Initialize the database
        dealCategoryRepository.saveAndFlush(dealCategory);

        // Get the dealCategory
        restDealCategoryMockMvc.perform(get("/api/deal-categories/{id}", dealCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dealCategory.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.isParent").value(DEFAULT_IS_PARENT.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDealCategory() throws Exception {
        // Get the dealCategory
        restDealCategoryMockMvc.perform(get("/api/deal-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDealCategory() throws Exception {
        // Initialize the database
        dealCategoryRepository.saveAndFlush(dealCategory);

        int databaseSizeBeforeUpdate = dealCategoryRepository.findAll().size();

        // Update the dealCategory
        DealCategory updatedDealCategory = dealCategoryRepository.findById(dealCategory.getId()).get();
        // Disconnect from session so that the updates on updatedDealCategory are not directly saved in db
        em.detach(updatedDealCategory);
        updatedDealCategory
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .isParent(UPDATED_IS_PARENT);
        DealCategoryDTO dealCategoryDTO = dealCategoryMapper.toDto(updatedDealCategory);

        restDealCategoryMockMvc.perform(put("/api/deal-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the DealCategory in the database
        List<DealCategory> dealCategoryList = dealCategoryRepository.findAll();
        assertThat(dealCategoryList).hasSize(databaseSizeBeforeUpdate);
        DealCategory testDealCategory = dealCategoryList.get(dealCategoryList.size() - 1);
        assertThat(testDealCategory.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDealCategory.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDealCategory.isIsParent()).isEqualTo(UPDATED_IS_PARENT);
    }

    @Test
    @Transactional
    public void updateNonExistingDealCategory() throws Exception {
        int databaseSizeBeforeUpdate = dealCategoryRepository.findAll().size();

        // Create the DealCategory
        DealCategoryDTO dealCategoryDTO = dealCategoryMapper.toDto(dealCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDealCategoryMockMvc.perform(put("/api/deal-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealCategory in the database
        List<DealCategory> dealCategoryList = dealCategoryRepository.findAll();
        assertThat(dealCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDealCategory() throws Exception {
        // Initialize the database
        dealCategoryRepository.saveAndFlush(dealCategory);

        int databaseSizeBeforeDelete = dealCategoryRepository.findAll().size();

        // Delete the dealCategory
        restDealCategoryMockMvc.perform(delete("/api/deal-categories/{id}", dealCategory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DealCategory> dealCategoryList = dealCategoryRepository.findAll();
        assertThat(dealCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
