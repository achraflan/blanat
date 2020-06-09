package com.aa.blanat.web.rest;

import com.aa.blanat.BlanatApp;
import com.aa.blanat.domain.CommentHistory;
import com.aa.blanat.domain.DealComment;
import com.aa.blanat.repository.CommentHistoryRepository;
import com.aa.blanat.service.CommentHistoryService;
import com.aa.blanat.service.dto.CommentHistoryDTO;
import com.aa.blanat.service.mapper.CommentHistoryMapper;

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
 * Integration tests for the {@link CommentHistoryResource} REST controller.
 */
@SpringBootTest(classes = BlanatApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CommentHistoryResourceIT {

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_MODIFICATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MODIFICATION = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private CommentHistoryRepository commentHistoryRepository;

    @Autowired
    private CommentHistoryMapper commentHistoryMapper;

    @Autowired
    private CommentHistoryService commentHistoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommentHistoryMockMvc;

    private CommentHistory commentHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommentHistory createEntity(EntityManager em) {
        CommentHistory commentHistory = new CommentHistory()
            .comment(DEFAULT_COMMENT)
            .dateModification(DEFAULT_DATE_MODIFICATION);
        // Add required entity
        DealComment dealComment;
        if (TestUtil.findAll(em, DealComment.class).isEmpty()) {
            dealComment = DealCommentResourceIT.createEntity(em);
            em.persist(dealComment);
            em.flush();
        } else {
            dealComment = TestUtil.findAll(em, DealComment.class).get(0);
        }
        commentHistory.setDealComment(dealComment);
        return commentHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommentHistory createUpdatedEntity(EntityManager em) {
        CommentHistory commentHistory = new CommentHistory()
            .comment(UPDATED_COMMENT)
            .dateModification(UPDATED_DATE_MODIFICATION);
        // Add required entity
        DealComment dealComment;
        if (TestUtil.findAll(em, DealComment.class).isEmpty()) {
            dealComment = DealCommentResourceIT.createUpdatedEntity(em);
            em.persist(dealComment);
            em.flush();
        } else {
            dealComment = TestUtil.findAll(em, DealComment.class).get(0);
        }
        commentHistory.setDealComment(dealComment);
        return commentHistory;
    }

    @BeforeEach
    public void initTest() {
        commentHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommentHistory() throws Exception {
        int databaseSizeBeforeCreate = commentHistoryRepository.findAll().size();
        // Create the CommentHistory
        CommentHistoryDTO commentHistoryDTO = commentHistoryMapper.toDto(commentHistory);
        restCommentHistoryMockMvc.perform(post("/api/comment-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the CommentHistory in the database
        List<CommentHistory> commentHistoryList = commentHistoryRepository.findAll();
        assertThat(commentHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        CommentHistory testCommentHistory = commentHistoryList.get(commentHistoryList.size() - 1);
        assertThat(testCommentHistory.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testCommentHistory.getDateModification()).isEqualTo(DEFAULT_DATE_MODIFICATION);
    }

    @Test
    @Transactional
    public void createCommentHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commentHistoryRepository.findAll().size();

        // Create the CommentHistory with an existing ID
        commentHistory.setId(1L);
        CommentHistoryDTO commentHistoryDTO = commentHistoryMapper.toDto(commentHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommentHistoryMockMvc.perform(post("/api/comment-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommentHistory in the database
        List<CommentHistory> commentHistoryList = commentHistoryRepository.findAll();
        assertThat(commentHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCommentIsRequired() throws Exception {
        int databaseSizeBeforeTest = commentHistoryRepository.findAll().size();
        // set the field null
        commentHistory.setComment(null);

        // Create the CommentHistory, which fails.
        CommentHistoryDTO commentHistoryDTO = commentHistoryMapper.toDto(commentHistory);


        restCommentHistoryMockMvc.perform(post("/api/comment-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<CommentHistory> commentHistoryList = commentHistoryRepository.findAll();
        assertThat(commentHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCommentHistories() throws Exception {
        // Initialize the database
        commentHistoryRepository.saveAndFlush(commentHistory);

        // Get all the commentHistoryList
        restCommentHistoryMockMvc.perform(get("/api/comment-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commentHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateModification").value(hasItem(DEFAULT_DATE_MODIFICATION.toString())));
    }
    
    @Test
    @Transactional
    public void getCommentHistory() throws Exception {
        // Initialize the database
        commentHistoryRepository.saveAndFlush(commentHistory);

        // Get the commentHistory
        restCommentHistoryMockMvc.perform(get("/api/comment-histories/{id}", commentHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commentHistory.getId().intValue()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.dateModification").value(DEFAULT_DATE_MODIFICATION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCommentHistory() throws Exception {
        // Get the commentHistory
        restCommentHistoryMockMvc.perform(get("/api/comment-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommentHistory() throws Exception {
        // Initialize the database
        commentHistoryRepository.saveAndFlush(commentHistory);

        int databaseSizeBeforeUpdate = commentHistoryRepository.findAll().size();

        // Update the commentHistory
        CommentHistory updatedCommentHistory = commentHistoryRepository.findById(commentHistory.getId()).get();
        // Disconnect from session so that the updates on updatedCommentHistory are not directly saved in db
        em.detach(updatedCommentHistory);
        updatedCommentHistory
            .comment(UPDATED_COMMENT)
            .dateModification(UPDATED_DATE_MODIFICATION);
        CommentHistoryDTO commentHistoryDTO = commentHistoryMapper.toDto(updatedCommentHistory);

        restCommentHistoryMockMvc.perform(put("/api/comment-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the CommentHistory in the database
        List<CommentHistory> commentHistoryList = commentHistoryRepository.findAll();
        assertThat(commentHistoryList).hasSize(databaseSizeBeforeUpdate);
        CommentHistory testCommentHistory = commentHistoryList.get(commentHistoryList.size() - 1);
        assertThat(testCommentHistory.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testCommentHistory.getDateModification()).isEqualTo(UPDATED_DATE_MODIFICATION);
    }

    @Test
    @Transactional
    public void updateNonExistingCommentHistory() throws Exception {
        int databaseSizeBeforeUpdate = commentHistoryRepository.findAll().size();

        // Create the CommentHistory
        CommentHistoryDTO commentHistoryDTO = commentHistoryMapper.toDto(commentHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommentHistoryMockMvc.perform(put("/api/comment-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommentHistory in the database
        List<CommentHistory> commentHistoryList = commentHistoryRepository.findAll();
        assertThat(commentHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommentHistory() throws Exception {
        // Initialize the database
        commentHistoryRepository.saveAndFlush(commentHistory);

        int databaseSizeBeforeDelete = commentHistoryRepository.findAll().size();

        // Delete the commentHistory
        restCommentHistoryMockMvc.perform(delete("/api/comment-histories/{id}", commentHistory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CommentHistory> commentHistoryList = commentHistoryRepository.findAll();
        assertThat(commentHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
