package com.aa.blanat.web.rest;

import com.aa.blanat.BlanatApp;
import com.aa.blanat.domain.DealComment;
import com.aa.blanat.domain.DealUser;
import com.aa.blanat.domain.Deal;
import com.aa.blanat.repository.DealCommentRepository;
import com.aa.blanat.service.DealCommentService;
import com.aa.blanat.service.dto.DealCommentDTO;
import com.aa.blanat.service.mapper.DealCommentMapper;

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
 * Integration tests for the {@link DealCommentResource} REST controller.
 */
@SpringBootTest(classes = BlanatApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DealCommentResourceIT {

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_COMMENT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_COMMENT = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final Long DEFAULT_LIKE = 1L;
    private static final Long UPDATED_LIKE = 2L;

    private static final Long DEFAULT_DISLIKE = 1L;
    private static final Long UPDATED_DISLIKE = 2L;

    private static final LocalDate DEFAULT_DATE_LAST_MODIFICATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_LAST_MODIFICATION = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private DealCommentRepository dealCommentRepository;

    @Autowired
    private DealCommentMapper dealCommentMapper;

    @Autowired
    private DealCommentService dealCommentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDealCommentMockMvc;

    private DealComment dealComment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealComment createEntity(EntityManager em) {
        DealComment dealComment = new DealComment()
            .comment(DEFAULT_COMMENT)
            .dateComment(DEFAULT_DATE_COMMENT)
            .isActive(DEFAULT_IS_ACTIVE)
            .isDeleted(DEFAULT_IS_DELETED)
            .like(DEFAULT_LIKE)
            .dislike(DEFAULT_DISLIKE)
            .dateLastModification(DEFAULT_DATE_LAST_MODIFICATION);
        // Add required entity
        DealUser dealUser;
        if (TestUtil.findAll(em, DealUser.class).isEmpty()) {
            dealUser = DealUserResourceIT.createEntity(em);
            em.persist(dealUser);
            em.flush();
        } else {
            dealUser = TestUtil.findAll(em, DealUser.class).get(0);
        }
        dealComment.setAssignedTo(dealUser);
        // Add required entity
        Deal deal;
        if (TestUtil.findAll(em, Deal.class).isEmpty()) {
            deal = DealResourceIT.createEntity(em);
            em.persist(deal);
            em.flush();
        } else {
            deal = TestUtil.findAll(em, Deal.class).get(0);
        }
        dealComment.setDeal(deal);
        return dealComment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealComment createUpdatedEntity(EntityManager em) {
        DealComment dealComment = new DealComment()
            .comment(UPDATED_COMMENT)
            .dateComment(UPDATED_DATE_COMMENT)
            .isActive(UPDATED_IS_ACTIVE)
            .isDeleted(UPDATED_IS_DELETED)
            .like(UPDATED_LIKE)
            .dislike(UPDATED_DISLIKE)
            .dateLastModification(UPDATED_DATE_LAST_MODIFICATION);
        // Add required entity
        DealUser dealUser;
        if (TestUtil.findAll(em, DealUser.class).isEmpty()) {
            dealUser = DealUserResourceIT.createUpdatedEntity(em);
            em.persist(dealUser);
            em.flush();
        } else {
            dealUser = TestUtil.findAll(em, DealUser.class).get(0);
        }
        dealComment.setAssignedTo(dealUser);
        // Add required entity
        Deal deal;
        if (TestUtil.findAll(em, Deal.class).isEmpty()) {
            deal = DealResourceIT.createUpdatedEntity(em);
            em.persist(deal);
            em.flush();
        } else {
            deal = TestUtil.findAll(em, Deal.class).get(0);
        }
        dealComment.setDeal(deal);
        return dealComment;
    }

    @BeforeEach
    public void initTest() {
        dealComment = createEntity(em);
    }

    @Test
    @Transactional
    public void createDealComment() throws Exception {
        int databaseSizeBeforeCreate = dealCommentRepository.findAll().size();
        // Create the DealComment
        DealCommentDTO dealCommentDTO = dealCommentMapper.toDto(dealComment);
        restDealCommentMockMvc.perform(post("/api/deal-comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealCommentDTO)))
            .andExpect(status().isCreated());

        // Validate the DealComment in the database
        List<DealComment> dealCommentList = dealCommentRepository.findAll();
        assertThat(dealCommentList).hasSize(databaseSizeBeforeCreate + 1);
        DealComment testDealComment = dealCommentList.get(dealCommentList.size() - 1);
        assertThat(testDealComment.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testDealComment.getDateComment()).isEqualTo(DEFAULT_DATE_COMMENT);
        assertThat(testDealComment.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testDealComment.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testDealComment.getLike()).isEqualTo(DEFAULT_LIKE);
        assertThat(testDealComment.getDislike()).isEqualTo(DEFAULT_DISLIKE);
        assertThat(testDealComment.getDateLastModification()).isEqualTo(DEFAULT_DATE_LAST_MODIFICATION);
    }

    @Test
    @Transactional
    public void createDealCommentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dealCommentRepository.findAll().size();

        // Create the DealComment with an existing ID
        dealComment.setId(1L);
        DealCommentDTO dealCommentDTO = dealCommentMapper.toDto(dealComment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDealCommentMockMvc.perform(post("/api/deal-comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealCommentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealComment in the database
        List<DealComment> dealCommentList = dealCommentRepository.findAll();
        assertThat(dealCommentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCommentIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealCommentRepository.findAll().size();
        // set the field null
        dealComment.setComment(null);

        // Create the DealComment, which fails.
        DealCommentDTO dealCommentDTO = dealCommentMapper.toDto(dealComment);


        restDealCommentMockMvc.perform(post("/api/deal-comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealCommentDTO)))
            .andExpect(status().isBadRequest());

        List<DealComment> dealCommentList = dealCommentRepository.findAll();
        assertThat(dealCommentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDealComments() throws Exception {
        // Initialize the database
        dealCommentRepository.saveAndFlush(dealComment);

        // Get all the dealCommentList
        restDealCommentMockMvc.perform(get("/api/deal-comments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dealComment.getId().intValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateComment").value(hasItem(DEFAULT_DATE_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].like").value(hasItem(DEFAULT_LIKE.intValue())))
            .andExpect(jsonPath("$.[*].dislike").value(hasItem(DEFAULT_DISLIKE.intValue())))
            .andExpect(jsonPath("$.[*].dateLastModification").value(hasItem(DEFAULT_DATE_LAST_MODIFICATION.toString())));
    }
    
    @Test
    @Transactional
    public void getDealComment() throws Exception {
        // Initialize the database
        dealCommentRepository.saveAndFlush(dealComment);

        // Get the dealComment
        restDealCommentMockMvc.perform(get("/api/deal-comments/{id}", dealComment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dealComment.getId().intValue()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.dateComment").value(DEFAULT_DATE_COMMENT.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.like").value(DEFAULT_LIKE.intValue()))
            .andExpect(jsonPath("$.dislike").value(DEFAULT_DISLIKE.intValue()))
            .andExpect(jsonPath("$.dateLastModification").value(DEFAULT_DATE_LAST_MODIFICATION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDealComment() throws Exception {
        // Get the dealComment
        restDealCommentMockMvc.perform(get("/api/deal-comments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDealComment() throws Exception {
        // Initialize the database
        dealCommentRepository.saveAndFlush(dealComment);

        int databaseSizeBeforeUpdate = dealCommentRepository.findAll().size();

        // Update the dealComment
        DealComment updatedDealComment = dealCommentRepository.findById(dealComment.getId()).get();
        // Disconnect from session so that the updates on updatedDealComment are not directly saved in db
        em.detach(updatedDealComment);
        updatedDealComment
            .comment(UPDATED_COMMENT)
            .dateComment(UPDATED_DATE_COMMENT)
            .isActive(UPDATED_IS_ACTIVE)
            .isDeleted(UPDATED_IS_DELETED)
            .like(UPDATED_LIKE)
            .dislike(UPDATED_DISLIKE)
            .dateLastModification(UPDATED_DATE_LAST_MODIFICATION);
        DealCommentDTO dealCommentDTO = dealCommentMapper.toDto(updatedDealComment);

        restDealCommentMockMvc.perform(put("/api/deal-comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealCommentDTO)))
            .andExpect(status().isOk());

        // Validate the DealComment in the database
        List<DealComment> dealCommentList = dealCommentRepository.findAll();
        assertThat(dealCommentList).hasSize(databaseSizeBeforeUpdate);
        DealComment testDealComment = dealCommentList.get(dealCommentList.size() - 1);
        assertThat(testDealComment.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testDealComment.getDateComment()).isEqualTo(UPDATED_DATE_COMMENT);
        assertThat(testDealComment.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testDealComment.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testDealComment.getLike()).isEqualTo(UPDATED_LIKE);
        assertThat(testDealComment.getDislike()).isEqualTo(UPDATED_DISLIKE);
        assertThat(testDealComment.getDateLastModification()).isEqualTo(UPDATED_DATE_LAST_MODIFICATION);
    }

    @Test
    @Transactional
    public void updateNonExistingDealComment() throws Exception {
        int databaseSizeBeforeUpdate = dealCommentRepository.findAll().size();

        // Create the DealComment
        DealCommentDTO dealCommentDTO = dealCommentMapper.toDto(dealComment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDealCommentMockMvc.perform(put("/api/deal-comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealCommentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealComment in the database
        List<DealComment> dealCommentList = dealCommentRepository.findAll();
        assertThat(dealCommentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDealComment() throws Exception {
        // Initialize the database
        dealCommentRepository.saveAndFlush(dealComment);

        int databaseSizeBeforeDelete = dealCommentRepository.findAll().size();

        // Delete the dealComment
        restDealCommentMockMvc.perform(delete("/api/deal-comments/{id}", dealComment.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DealComment> dealCommentList = dealCommentRepository.findAll();
        assertThat(dealCommentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
