package com.aa.blanat.web.rest;

import com.aa.blanat.BlanatApp;
import com.aa.blanat.domain.DealLocation;
import com.aa.blanat.repository.DealLocationRepository;
import com.aa.blanat.service.DealLocationService;
import com.aa.blanat.service.dto.DealLocationDTO;
import com.aa.blanat.service.mapper.DealLocationMapper;

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
 * Integration tests for the {@link DealLocationResource} REST controller.
 */
@SpringBootTest(classes = BlanatApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DealLocationResourceIT {

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    @Autowired
    private DealLocationRepository dealLocationRepository;

    @Autowired
    private DealLocationMapper dealLocationMapper;

    @Autowired
    private DealLocationService dealLocationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDealLocationMockMvc;

    private DealLocation dealLocation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealLocation createEntity(EntityManager em) {
        DealLocation dealLocation = new DealLocation()
            .city(DEFAULT_CITY)
            .country(DEFAULT_COUNTRY);
        return dealLocation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealLocation createUpdatedEntity(EntityManager em) {
        DealLocation dealLocation = new DealLocation()
            .city(UPDATED_CITY)
            .country(UPDATED_COUNTRY);
        return dealLocation;
    }

    @BeforeEach
    public void initTest() {
        dealLocation = createEntity(em);
    }

    @Test
    @Transactional
    public void createDealLocation() throws Exception {
        int databaseSizeBeforeCreate = dealLocationRepository.findAll().size();
        // Create the DealLocation
        DealLocationDTO dealLocationDTO = dealLocationMapper.toDto(dealLocation);
        restDealLocationMockMvc.perform(post("/api/deal-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealLocationDTO)))
            .andExpect(status().isCreated());

        // Validate the DealLocation in the database
        List<DealLocation> dealLocationList = dealLocationRepository.findAll();
        assertThat(dealLocationList).hasSize(databaseSizeBeforeCreate + 1);
        DealLocation testDealLocation = dealLocationList.get(dealLocationList.size() - 1);
        assertThat(testDealLocation.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testDealLocation.getCountry()).isEqualTo(DEFAULT_COUNTRY);
    }

    @Test
    @Transactional
    public void createDealLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dealLocationRepository.findAll().size();

        // Create the DealLocation with an existing ID
        dealLocation.setId(1L);
        DealLocationDTO dealLocationDTO = dealLocationMapper.toDto(dealLocation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDealLocationMockMvc.perform(post("/api/deal-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealLocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealLocation in the database
        List<DealLocation> dealLocationList = dealLocationRepository.findAll();
        assertThat(dealLocationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealLocationRepository.findAll().size();
        // set the field null
        dealLocation.setCity(null);

        // Create the DealLocation, which fails.
        DealLocationDTO dealLocationDTO = dealLocationMapper.toDto(dealLocation);


        restDealLocationMockMvc.perform(post("/api/deal-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealLocationDTO)))
            .andExpect(status().isBadRequest());

        List<DealLocation> dealLocationList = dealLocationRepository.findAll();
        assertThat(dealLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDealLocations() throws Exception {
        // Initialize the database
        dealLocationRepository.saveAndFlush(dealLocation);

        // Get all the dealLocationList
        restDealLocationMockMvc.perform(get("/api/deal-locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dealLocation.getId().intValue())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)));
    }
    
    @Test
    @Transactional
    public void getDealLocation() throws Exception {
        // Initialize the database
        dealLocationRepository.saveAndFlush(dealLocation);

        // Get the dealLocation
        restDealLocationMockMvc.perform(get("/api/deal-locations/{id}", dealLocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dealLocation.getId().intValue()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY));
    }
    @Test
    @Transactional
    public void getNonExistingDealLocation() throws Exception {
        // Get the dealLocation
        restDealLocationMockMvc.perform(get("/api/deal-locations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDealLocation() throws Exception {
        // Initialize the database
        dealLocationRepository.saveAndFlush(dealLocation);

        int databaseSizeBeforeUpdate = dealLocationRepository.findAll().size();

        // Update the dealLocation
        DealLocation updatedDealLocation = dealLocationRepository.findById(dealLocation.getId()).get();
        // Disconnect from session so that the updates on updatedDealLocation are not directly saved in db
        em.detach(updatedDealLocation);
        updatedDealLocation
            .city(UPDATED_CITY)
            .country(UPDATED_COUNTRY);
        DealLocationDTO dealLocationDTO = dealLocationMapper.toDto(updatedDealLocation);

        restDealLocationMockMvc.perform(put("/api/deal-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealLocationDTO)))
            .andExpect(status().isOk());

        // Validate the DealLocation in the database
        List<DealLocation> dealLocationList = dealLocationRepository.findAll();
        assertThat(dealLocationList).hasSize(databaseSizeBeforeUpdate);
        DealLocation testDealLocation = dealLocationList.get(dealLocationList.size() - 1);
        assertThat(testDealLocation.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testDealLocation.getCountry()).isEqualTo(UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void updateNonExistingDealLocation() throws Exception {
        int databaseSizeBeforeUpdate = dealLocationRepository.findAll().size();

        // Create the DealLocation
        DealLocationDTO dealLocationDTO = dealLocationMapper.toDto(dealLocation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDealLocationMockMvc.perform(put("/api/deal-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealLocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealLocation in the database
        List<DealLocation> dealLocationList = dealLocationRepository.findAll();
        assertThat(dealLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDealLocation() throws Exception {
        // Initialize the database
        dealLocationRepository.saveAndFlush(dealLocation);

        int databaseSizeBeforeDelete = dealLocationRepository.findAll().size();

        // Delete the dealLocation
        restDealLocationMockMvc.perform(delete("/api/deal-locations/{id}", dealLocation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DealLocation> dealLocationList = dealLocationRepository.findAll();
        assertThat(dealLocationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
