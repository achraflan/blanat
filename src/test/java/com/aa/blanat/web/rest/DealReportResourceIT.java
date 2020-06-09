package com.aa.blanat.web.rest;

import com.aa.blanat.BlanatApp;
import com.aa.blanat.domain.DealReport;
import com.aa.blanat.domain.DealUser;
import com.aa.blanat.domain.Deal;
import com.aa.blanat.repository.DealReportRepository;
import com.aa.blanat.service.DealReportService;
import com.aa.blanat.service.dto.DealReportDTO;
import com.aa.blanat.service.mapper.DealReportMapper;

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
 * Integration tests for the {@link DealReportResource} REST controller.
 */
@SpringBootTest(classes = BlanatApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DealReportResourceIT {

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_REPORT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_REPORT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_CLOSE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CLOSE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_IS_VALID = false;
    private static final Boolean UPDATED_IS_VALID = true;

    @Autowired
    private DealReportRepository dealReportRepository;

    @Autowired
    private DealReportMapper dealReportMapper;

    @Autowired
    private DealReportService dealReportService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDealReportMockMvc;

    private DealReport dealReport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealReport createEntity(EntityManager em) {
        DealReport dealReport = new DealReport()
            .reason(DEFAULT_REASON)
            .dateReport(DEFAULT_DATE_REPORT)
            .dateClose(DEFAULT_DATE_CLOSE)
            .isValid(DEFAULT_IS_VALID);
        // Add required entity
        DealUser dealUser;
        if (TestUtil.findAll(em, DealUser.class).isEmpty()) {
            dealUser = DealUserResourceIT.createEntity(em);
            em.persist(dealUser);
            em.flush();
        } else {
            dealUser = TestUtil.findAll(em, DealUser.class).get(0);
        }
        dealReport.setAssignedTo(dealUser);
        // Add required entity
        Deal deal;
        if (TestUtil.findAll(em, Deal.class).isEmpty()) {
            deal = DealResourceIT.createEntity(em);
            em.persist(deal);
            em.flush();
        } else {
            deal = TestUtil.findAll(em, Deal.class).get(0);
        }
        dealReport.setDeal(deal);
        return dealReport;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealReport createUpdatedEntity(EntityManager em) {
        DealReport dealReport = new DealReport()
            .reason(UPDATED_REASON)
            .dateReport(UPDATED_DATE_REPORT)
            .dateClose(UPDATED_DATE_CLOSE)
            .isValid(UPDATED_IS_VALID);
        // Add required entity
        DealUser dealUser;
        if (TestUtil.findAll(em, DealUser.class).isEmpty()) {
            dealUser = DealUserResourceIT.createUpdatedEntity(em);
            em.persist(dealUser);
            em.flush();
        } else {
            dealUser = TestUtil.findAll(em, DealUser.class).get(0);
        }
        dealReport.setAssignedTo(dealUser);
        // Add required entity
        Deal deal;
        if (TestUtil.findAll(em, Deal.class).isEmpty()) {
            deal = DealResourceIT.createUpdatedEntity(em);
            em.persist(deal);
            em.flush();
        } else {
            deal = TestUtil.findAll(em, Deal.class).get(0);
        }
        dealReport.setDeal(deal);
        return dealReport;
    }

    @BeforeEach
    public void initTest() {
        dealReport = createEntity(em);
    }

    @Test
    @Transactional
    public void createDealReport() throws Exception {
        int databaseSizeBeforeCreate = dealReportRepository.findAll().size();
        // Create the DealReport
        DealReportDTO dealReportDTO = dealReportMapper.toDto(dealReport);
        restDealReportMockMvc.perform(post("/api/deal-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealReportDTO)))
            .andExpect(status().isCreated());

        // Validate the DealReport in the database
        List<DealReport> dealReportList = dealReportRepository.findAll();
        assertThat(dealReportList).hasSize(databaseSizeBeforeCreate + 1);
        DealReport testDealReport = dealReportList.get(dealReportList.size() - 1);
        assertThat(testDealReport.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testDealReport.getDateReport()).isEqualTo(DEFAULT_DATE_REPORT);
        assertThat(testDealReport.getDateClose()).isEqualTo(DEFAULT_DATE_CLOSE);
        assertThat(testDealReport.isIsValid()).isEqualTo(DEFAULT_IS_VALID);
    }

    @Test
    @Transactional
    public void createDealReportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dealReportRepository.findAll().size();

        // Create the DealReport with an existing ID
        dealReport.setId(1L);
        DealReportDTO dealReportDTO = dealReportMapper.toDto(dealReport);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDealReportMockMvc.perform(post("/api/deal-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealReport in the database
        List<DealReport> dealReportList = dealReportRepository.findAll();
        assertThat(dealReportList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkReasonIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealReportRepository.findAll().size();
        // set the field null
        dealReport.setReason(null);

        // Create the DealReport, which fails.
        DealReportDTO dealReportDTO = dealReportMapper.toDto(dealReport);


        restDealReportMockMvc.perform(post("/api/deal-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealReportDTO)))
            .andExpect(status().isBadRequest());

        List<DealReport> dealReportList = dealReportRepository.findAll();
        assertThat(dealReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateReportIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealReportRepository.findAll().size();
        // set the field null
        dealReport.setDateReport(null);

        // Create the DealReport, which fails.
        DealReportDTO dealReportDTO = dealReportMapper.toDto(dealReport);


        restDealReportMockMvc.perform(post("/api/deal-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealReportDTO)))
            .andExpect(status().isBadRequest());

        List<DealReport> dealReportList = dealReportRepository.findAll();
        assertThat(dealReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDealReports() throws Exception {
        // Initialize the database
        dealReportRepository.saveAndFlush(dealReport);

        // Get all the dealReportList
        restDealReportMockMvc.perform(get("/api/deal-reports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dealReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON)))
            .andExpect(jsonPath("$.[*].dateReport").value(hasItem(DEFAULT_DATE_REPORT.toString())))
            .andExpect(jsonPath("$.[*].dateClose").value(hasItem(DEFAULT_DATE_CLOSE.toString())))
            .andExpect(jsonPath("$.[*].isValid").value(hasItem(DEFAULT_IS_VALID.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDealReport() throws Exception {
        // Initialize the database
        dealReportRepository.saveAndFlush(dealReport);

        // Get the dealReport
        restDealReportMockMvc.perform(get("/api/deal-reports/{id}", dealReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dealReport.getId().intValue()))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON))
            .andExpect(jsonPath("$.dateReport").value(DEFAULT_DATE_REPORT.toString()))
            .andExpect(jsonPath("$.dateClose").value(DEFAULT_DATE_CLOSE.toString()))
            .andExpect(jsonPath("$.isValid").value(DEFAULT_IS_VALID.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDealReport() throws Exception {
        // Get the dealReport
        restDealReportMockMvc.perform(get("/api/deal-reports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDealReport() throws Exception {
        // Initialize the database
        dealReportRepository.saveAndFlush(dealReport);

        int databaseSizeBeforeUpdate = dealReportRepository.findAll().size();

        // Update the dealReport
        DealReport updatedDealReport = dealReportRepository.findById(dealReport.getId()).get();
        // Disconnect from session so that the updates on updatedDealReport are not directly saved in db
        em.detach(updatedDealReport);
        updatedDealReport
            .reason(UPDATED_REASON)
            .dateReport(UPDATED_DATE_REPORT)
            .dateClose(UPDATED_DATE_CLOSE)
            .isValid(UPDATED_IS_VALID);
        DealReportDTO dealReportDTO = dealReportMapper.toDto(updatedDealReport);

        restDealReportMockMvc.perform(put("/api/deal-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealReportDTO)))
            .andExpect(status().isOk());

        // Validate the DealReport in the database
        List<DealReport> dealReportList = dealReportRepository.findAll();
        assertThat(dealReportList).hasSize(databaseSizeBeforeUpdate);
        DealReport testDealReport = dealReportList.get(dealReportList.size() - 1);
        assertThat(testDealReport.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testDealReport.getDateReport()).isEqualTo(UPDATED_DATE_REPORT);
        assertThat(testDealReport.getDateClose()).isEqualTo(UPDATED_DATE_CLOSE);
        assertThat(testDealReport.isIsValid()).isEqualTo(UPDATED_IS_VALID);
    }

    @Test
    @Transactional
    public void updateNonExistingDealReport() throws Exception {
        int databaseSizeBeforeUpdate = dealReportRepository.findAll().size();

        // Create the DealReport
        DealReportDTO dealReportDTO = dealReportMapper.toDto(dealReport);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDealReportMockMvc.perform(put("/api/deal-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealReport in the database
        List<DealReport> dealReportList = dealReportRepository.findAll();
        assertThat(dealReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDealReport() throws Exception {
        // Initialize the database
        dealReportRepository.saveAndFlush(dealReport);

        int databaseSizeBeforeDelete = dealReportRepository.findAll().size();

        // Delete the dealReport
        restDealReportMockMvc.perform(delete("/api/deal-reports/{id}", dealReport.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DealReport> dealReportList = dealReportRepository.findAll();
        assertThat(dealReportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
