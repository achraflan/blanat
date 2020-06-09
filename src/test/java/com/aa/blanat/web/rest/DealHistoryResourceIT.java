package com.aa.blanat.web.rest;

import com.aa.blanat.BlanatApp;
import com.aa.blanat.domain.DealHistory;
import com.aa.blanat.domain.Deal;
import com.aa.blanat.repository.DealHistoryRepository;
import com.aa.blanat.service.DealHistoryService;
import com.aa.blanat.service.dto.DealHistoryDTO;
import com.aa.blanat.service.mapper.DealHistoryMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DealHistoryResource} REST controller.
 */
@SpringBootTest(classes = BlanatApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DealHistoryResourceIT {

    private static final String DEFAULT_ATTRIBUT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUT_LAST_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUT_LAST_VALUE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_MODIFICATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MODIFICATION = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private DealHistoryRepository dealHistoryRepository;

    @Autowired
    private DealHistoryMapper dealHistoryMapper;

    @Autowired
    private DealHistoryService dealHistoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDealHistoryMockMvc;

    private DealHistory dealHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealHistory createEntity(EntityManager em) {
        DealHistory dealHistory = new DealHistory()
            .attributName(DEFAULT_ATTRIBUT_NAME)
            .attributLastValue(DEFAULT_ATTRIBUT_LAST_VALUE)
            .dateModification(DEFAULT_DATE_MODIFICATION);
        // Add required entity
        Deal deal;
        if (TestUtil.findAll(em, Deal.class).isEmpty()) {
            deal = DealResourceIT.createEntity(em);
            em.persist(deal);
            em.flush();
        } else {
            deal = TestUtil.findAll(em, Deal.class).get(0);
        }
        dealHistory.setDeal(deal);
        return dealHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealHistory createUpdatedEntity(EntityManager em) {
        DealHistory dealHistory = new DealHistory()
            .attributName(UPDATED_ATTRIBUT_NAME)
            .attributLastValue(UPDATED_ATTRIBUT_LAST_VALUE)
            .dateModification(UPDATED_DATE_MODIFICATION);
        // Add required entity
        Deal deal;
        if (TestUtil.findAll(em, Deal.class).isEmpty()) {
            deal = DealResourceIT.createUpdatedEntity(em);
            em.persist(deal);
            em.flush();
        } else {
            deal = TestUtil.findAll(em, Deal.class).get(0);
        }
        dealHistory.setDeal(deal);
        return dealHistory;
    }

    @BeforeEach
    public void initTest() {
        dealHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createDealHistory() throws Exception {
        int databaseSizeBeforeCreate = dealHistoryRepository.findAll().size();
        // Create the DealHistory
        DealHistoryDTO dealHistoryDTO = dealHistoryMapper.toDto(dealHistory);
        restDealHistoryMockMvc.perform(post("/api/deal-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the DealHistory in the database
        List<DealHistory> dealHistoryList = dealHistoryRepository.findAll();
        assertThat(dealHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        DealHistory testDealHistory = dealHistoryList.get(dealHistoryList.size() - 1);
        assertThat(testDealHistory.getAttributName()).isEqualTo(DEFAULT_ATTRIBUT_NAME);
        assertThat(testDealHistory.getAttributLastValue()).isEqualTo(DEFAULT_ATTRIBUT_LAST_VALUE);
        assertThat(testDealHistory.getDateModification()).isEqualTo(DEFAULT_DATE_MODIFICATION);
    }

    @Test
    @Transactional
    public void createDealHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dealHistoryRepository.findAll().size();

        // Create the DealHistory with an existing ID
        dealHistory.setId(1L);
        DealHistoryDTO dealHistoryDTO = dealHistoryMapper.toDto(dealHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDealHistoryMockMvc.perform(post("/api/deal-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealHistory in the database
        List<DealHistory> dealHistoryList = dealHistoryRepository.findAll();
        assertThat(dealHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAttributNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealHistoryRepository.findAll().size();
        // set the field null
        dealHistory.setAttributName(null);

        // Create the DealHistory, which fails.
        DealHistoryDTO dealHistoryDTO = dealHistoryMapper.toDto(dealHistory);


        restDealHistoryMockMvc.perform(post("/api/deal-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<DealHistory> dealHistoryList = dealHistoryRepository.findAll();
        assertThat(dealHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAttributLastValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealHistoryRepository.findAll().size();
        // set the field null
        dealHistory.setAttributLastValue(null);

        // Create the DealHistory, which fails.
        DealHistoryDTO dealHistoryDTO = dealHistoryMapper.toDto(dealHistory);


        restDealHistoryMockMvc.perform(post("/api/deal-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<DealHistory> dealHistoryList = dealHistoryRepository.findAll();
        assertThat(dealHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateModificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealHistoryRepository.findAll().size();
        // set the field null
        dealHistory.setDateModification(null);

        // Create the DealHistory, which fails.
        DealHistoryDTO dealHistoryDTO = dealHistoryMapper.toDto(dealHistory);


        restDealHistoryMockMvc.perform(post("/api/deal-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<DealHistory> dealHistoryList = dealHistoryRepository.findAll();
        assertThat(dealHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDealHistories() throws Exception {
        // Initialize the database
        dealHistoryRepository.saveAndFlush(dealHistory);

        // Get all the dealHistoryList
        restDealHistoryMockMvc.perform(get("/api/deal-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dealHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].attributName").value(hasItem(DEFAULT_ATTRIBUT_NAME)))
            .andExpect(jsonPath("$.[*].attributLastValue").value(hasItem(DEFAULT_ATTRIBUT_LAST_VALUE)))
            .andExpect(jsonPath("$.[*].dateModification").value(hasItem(DEFAULT_DATE_MODIFICATION.toString())));
    }
    
    @Test
    @Transactional
    public void getDealHistory() throws Exception {
        // Initialize the database
        dealHistoryRepository.saveAndFlush(dealHistory);

        // Get the dealHistory
        restDealHistoryMockMvc.perform(get("/api/deal-histories/{id}", dealHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dealHistory.getId().intValue()))
            .andExpect(jsonPath("$.attributName").value(DEFAULT_ATTRIBUT_NAME))
            .andExpect(jsonPath("$.attributLastValue").value(DEFAULT_ATTRIBUT_LAST_VALUE))
            .andExpect(jsonPath("$.dateModification").value(DEFAULT_DATE_MODIFICATION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDealHistory() throws Exception {
        // Get the dealHistory
        restDealHistoryMockMvc.perform(get("/api/deal-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDealHistory() throws Exception {
        // Initialize the database
        dealHistoryRepository.saveAndFlush(dealHistory);

        int databaseSizeBeforeUpdate = dealHistoryRepository.findAll().size();

        // Update the dealHistory
        DealHistory updatedDealHistory = dealHistoryRepository.findById(dealHistory.getId()).get();
        // Disconnect from session so that the updates on updatedDealHistory are not directly saved in db
        em.detach(updatedDealHistory);
        updatedDealHistory
            .attributName(UPDATED_ATTRIBUT_NAME)
            .attributLastValue(UPDATED_ATTRIBUT_LAST_VALUE)
            .dateModification(UPDATED_DATE_MODIFICATION);
        DealHistoryDTO dealHistoryDTO = dealHistoryMapper.toDto(updatedDealHistory);

        restDealHistoryMockMvc.perform(put("/api/deal-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the DealHistory in the database
        List<DealHistory> dealHistoryList = dealHistoryRepository.findAll();
        assertThat(dealHistoryList).hasSize(databaseSizeBeforeUpdate);
        DealHistory testDealHistory = dealHistoryList.get(dealHistoryList.size() - 1);
        assertThat(testDealHistory.getAttributName()).isEqualTo(UPDATED_ATTRIBUT_NAME);
        assertThat(testDealHistory.getAttributLastValue()).isEqualTo(UPDATED_ATTRIBUT_LAST_VALUE);
        assertThat(testDealHistory.getDateModification()).isEqualTo(UPDATED_DATE_MODIFICATION);
    }

    @Test
    @Transactional
    public void updateNonExistingDealHistory() throws Exception {
        int databaseSizeBeforeUpdate = dealHistoryRepository.findAll().size();

        // Create the DealHistory
        DealHistoryDTO dealHistoryDTO = dealHistoryMapper.toDto(dealHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDealHistoryMockMvc.perform(put("/api/deal-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealHistory in the database
        List<DealHistory> dealHistoryList = dealHistoryRepository.findAll();
        assertThat(dealHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDealHistory() throws Exception {
        // Initialize the database
        dealHistoryRepository.saveAndFlush(dealHistory);

        int databaseSizeBeforeDelete = dealHistoryRepository.findAll().size();

        // Delete the dealHistory
        restDealHistoryMockMvc.perform(delete("/api/deal-histories/{id}", dealHistory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DealHistory> dealHistoryList = dealHistoryRepository.findAll();
        assertThat(dealHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
