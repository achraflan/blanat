package com.aa.blanat.web.rest;

import com.aa.blanat.BlanatApp;
import com.aa.blanat.domain.DealTrack;
import com.aa.blanat.domain.Deal;
import com.aa.blanat.repository.DealTrackRepository;
import com.aa.blanat.service.DealTrackService;
import com.aa.blanat.service.dto.DealTrackDTO;
import com.aa.blanat.service.mapper.DealTrackMapper;

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
 * Integration tests for the {@link DealTrackResource} REST controller.
 */
@SpringBootTest(classes = BlanatApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DealTrackResourceIT {

    private static final String DEFAULT_IP_LOCALISATION = "AAAAAAAAAA";
    private static final String UPDATED_IP_LOCALISATION = "BBBBBBBBBB";

    private static final String DEFAULT_LOCALISATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCALISATION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_AUTHENTIFIED = false;
    private static final Boolean UPDATED_IS_AUTHENTIFIED = true;

    @Autowired
    private DealTrackRepository dealTrackRepository;

    @Autowired
    private DealTrackMapper dealTrackMapper;

    @Autowired
    private DealTrackService dealTrackService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDealTrackMockMvc;

    private DealTrack dealTrack;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealTrack createEntity(EntityManager em) {
        DealTrack dealTrack = new DealTrack()
            .ipLocalisation(DEFAULT_IP_LOCALISATION)
            .localisation(DEFAULT_LOCALISATION)
            .isAuthentified(DEFAULT_IS_AUTHENTIFIED);
        // Add required entity
        Deal deal;
        if (TestUtil.findAll(em, Deal.class).isEmpty()) {
            deal = DealResourceIT.createEntity(em);
            em.persist(deal);
            em.flush();
        } else {
            deal = TestUtil.findAll(em, Deal.class).get(0);
        }
        dealTrack.setDeal(deal);
        return dealTrack;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealTrack createUpdatedEntity(EntityManager em) {
        DealTrack dealTrack = new DealTrack()
            .ipLocalisation(UPDATED_IP_LOCALISATION)
            .localisation(UPDATED_LOCALISATION)
            .isAuthentified(UPDATED_IS_AUTHENTIFIED);
        // Add required entity
        Deal deal;
        if (TestUtil.findAll(em, Deal.class).isEmpty()) {
            deal = DealResourceIT.createUpdatedEntity(em);
            em.persist(deal);
            em.flush();
        } else {
            deal = TestUtil.findAll(em, Deal.class).get(0);
        }
        dealTrack.setDeal(deal);
        return dealTrack;
    }

    @BeforeEach
    public void initTest() {
        dealTrack = createEntity(em);
    }

    @Test
    @Transactional
    public void createDealTrack() throws Exception {
        int databaseSizeBeforeCreate = dealTrackRepository.findAll().size();
        // Create the DealTrack
        DealTrackDTO dealTrackDTO = dealTrackMapper.toDto(dealTrack);
        restDealTrackMockMvc.perform(post("/api/deal-tracks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealTrackDTO)))
            .andExpect(status().isCreated());

        // Validate the DealTrack in the database
        List<DealTrack> dealTrackList = dealTrackRepository.findAll();
        assertThat(dealTrackList).hasSize(databaseSizeBeforeCreate + 1);
        DealTrack testDealTrack = dealTrackList.get(dealTrackList.size() - 1);
        assertThat(testDealTrack.getIpLocalisation()).isEqualTo(DEFAULT_IP_LOCALISATION);
        assertThat(testDealTrack.getLocalisation()).isEqualTo(DEFAULT_LOCALISATION);
        assertThat(testDealTrack.isIsAuthentified()).isEqualTo(DEFAULT_IS_AUTHENTIFIED);
    }

    @Test
    @Transactional
    public void createDealTrackWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dealTrackRepository.findAll().size();

        // Create the DealTrack with an existing ID
        dealTrack.setId(1L);
        DealTrackDTO dealTrackDTO = dealTrackMapper.toDto(dealTrack);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDealTrackMockMvc.perform(post("/api/deal-tracks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealTrackDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealTrack in the database
        List<DealTrack> dealTrackList = dealTrackRepository.findAll();
        assertThat(dealTrackList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIpLocalisationIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealTrackRepository.findAll().size();
        // set the field null
        dealTrack.setIpLocalisation(null);

        // Create the DealTrack, which fails.
        DealTrackDTO dealTrackDTO = dealTrackMapper.toDto(dealTrack);


        restDealTrackMockMvc.perform(post("/api/deal-tracks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealTrackDTO)))
            .andExpect(status().isBadRequest());

        List<DealTrack> dealTrackList = dealTrackRepository.findAll();
        assertThat(dealTrackList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsAuthentifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealTrackRepository.findAll().size();
        // set the field null
        dealTrack.setIsAuthentified(null);

        // Create the DealTrack, which fails.
        DealTrackDTO dealTrackDTO = dealTrackMapper.toDto(dealTrack);


        restDealTrackMockMvc.perform(post("/api/deal-tracks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealTrackDTO)))
            .andExpect(status().isBadRequest());

        List<DealTrack> dealTrackList = dealTrackRepository.findAll();
        assertThat(dealTrackList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDealTracks() throws Exception {
        // Initialize the database
        dealTrackRepository.saveAndFlush(dealTrack);

        // Get all the dealTrackList
        restDealTrackMockMvc.perform(get("/api/deal-tracks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dealTrack.getId().intValue())))
            .andExpect(jsonPath("$.[*].ipLocalisation").value(hasItem(DEFAULT_IP_LOCALISATION)))
            .andExpect(jsonPath("$.[*].localisation").value(hasItem(DEFAULT_LOCALISATION)))
            .andExpect(jsonPath("$.[*].isAuthentified").value(hasItem(DEFAULT_IS_AUTHENTIFIED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDealTrack() throws Exception {
        // Initialize the database
        dealTrackRepository.saveAndFlush(dealTrack);

        // Get the dealTrack
        restDealTrackMockMvc.perform(get("/api/deal-tracks/{id}", dealTrack.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dealTrack.getId().intValue()))
            .andExpect(jsonPath("$.ipLocalisation").value(DEFAULT_IP_LOCALISATION))
            .andExpect(jsonPath("$.localisation").value(DEFAULT_LOCALISATION))
            .andExpect(jsonPath("$.isAuthentified").value(DEFAULT_IS_AUTHENTIFIED.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDealTrack() throws Exception {
        // Get the dealTrack
        restDealTrackMockMvc.perform(get("/api/deal-tracks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDealTrack() throws Exception {
        // Initialize the database
        dealTrackRepository.saveAndFlush(dealTrack);

        int databaseSizeBeforeUpdate = dealTrackRepository.findAll().size();

        // Update the dealTrack
        DealTrack updatedDealTrack = dealTrackRepository.findById(dealTrack.getId()).get();
        // Disconnect from session so that the updates on updatedDealTrack are not directly saved in db
        em.detach(updatedDealTrack);
        updatedDealTrack
            .ipLocalisation(UPDATED_IP_LOCALISATION)
            .localisation(UPDATED_LOCALISATION)
            .isAuthentified(UPDATED_IS_AUTHENTIFIED);
        DealTrackDTO dealTrackDTO = dealTrackMapper.toDto(updatedDealTrack);

        restDealTrackMockMvc.perform(put("/api/deal-tracks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealTrackDTO)))
            .andExpect(status().isOk());

        // Validate the DealTrack in the database
        List<DealTrack> dealTrackList = dealTrackRepository.findAll();
        assertThat(dealTrackList).hasSize(databaseSizeBeforeUpdate);
        DealTrack testDealTrack = dealTrackList.get(dealTrackList.size() - 1);
        assertThat(testDealTrack.getIpLocalisation()).isEqualTo(UPDATED_IP_LOCALISATION);
        assertThat(testDealTrack.getLocalisation()).isEqualTo(UPDATED_LOCALISATION);
        assertThat(testDealTrack.isIsAuthentified()).isEqualTo(UPDATED_IS_AUTHENTIFIED);
    }

    @Test
    @Transactional
    public void updateNonExistingDealTrack() throws Exception {
        int databaseSizeBeforeUpdate = dealTrackRepository.findAll().size();

        // Create the DealTrack
        DealTrackDTO dealTrackDTO = dealTrackMapper.toDto(dealTrack);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDealTrackMockMvc.perform(put("/api/deal-tracks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealTrackDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealTrack in the database
        List<DealTrack> dealTrackList = dealTrackRepository.findAll();
        assertThat(dealTrackList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDealTrack() throws Exception {
        // Initialize the database
        dealTrackRepository.saveAndFlush(dealTrack);

        int databaseSizeBeforeDelete = dealTrackRepository.findAll().size();

        // Delete the dealTrack
        restDealTrackMockMvc.perform(delete("/api/deal-tracks/{id}", dealTrack.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DealTrack> dealTrackList = dealTrackRepository.findAll();
        assertThat(dealTrackList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
