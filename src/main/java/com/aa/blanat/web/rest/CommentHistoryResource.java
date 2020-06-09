package com.aa.blanat.web.rest;

import com.aa.blanat.service.CommentHistoryService;
import com.aa.blanat.web.rest.errors.BadRequestAlertException;
import com.aa.blanat.service.dto.CommentHistoryDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.aa.blanat.domain.CommentHistory}.
 */
@RestController
@RequestMapping("/api")
public class CommentHistoryResource {

    private final Logger log = LoggerFactory.getLogger(CommentHistoryResource.class);

    private static final String ENTITY_NAME = "commentHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommentHistoryService commentHistoryService;

    public CommentHistoryResource(CommentHistoryService commentHistoryService) {
        this.commentHistoryService = commentHistoryService;
    }

    /**
     * {@code POST  /comment-histories} : Create a new commentHistory.
     *
     * @param commentHistoryDTO the commentHistoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commentHistoryDTO, or with status {@code 400 (Bad Request)} if the commentHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/comment-histories")
    public ResponseEntity<CommentHistoryDTO> createCommentHistory(@Valid @RequestBody CommentHistoryDTO commentHistoryDTO) throws URISyntaxException {
        log.debug("REST request to save CommentHistory : {}", commentHistoryDTO);
        if (commentHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new commentHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommentHistoryDTO result = commentHistoryService.save(commentHistoryDTO);
        return ResponseEntity.created(new URI("/api/comment-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /comment-histories} : Updates an existing commentHistory.
     *
     * @param commentHistoryDTO the commentHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commentHistoryDTO,
     * or with status {@code 400 (Bad Request)} if the commentHistoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commentHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/comment-histories")
    public ResponseEntity<CommentHistoryDTO> updateCommentHistory(@Valid @RequestBody CommentHistoryDTO commentHistoryDTO) throws URISyntaxException {
        log.debug("REST request to update CommentHistory : {}", commentHistoryDTO);
        if (commentHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommentHistoryDTO result = commentHistoryService.save(commentHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commentHistoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /comment-histories} : get all the commentHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commentHistories in body.
     */
    @GetMapping("/comment-histories")
    public List<CommentHistoryDTO> getAllCommentHistories() {
        log.debug("REST request to get all CommentHistories");
        return commentHistoryService.findAll();
    }

    /**
     * {@code GET  /comment-histories/:id} : get the "id" commentHistory.
     *
     * @param id the id of the commentHistoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commentHistoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/comment-histories/{id}")
    public ResponseEntity<CommentHistoryDTO> getCommentHistory(@PathVariable Long id) {
        log.debug("REST request to get CommentHistory : {}", id);
        Optional<CommentHistoryDTO> commentHistoryDTO = commentHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commentHistoryDTO);
    }

    /**
     * {@code DELETE  /comment-histories/:id} : delete the "id" commentHistory.
     *
     * @param id the id of the commentHistoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/comment-histories/{id}")
    public ResponseEntity<Void> deleteCommentHistory(@PathVariable Long id) {
        log.debug("REST request to delete CommentHistory : {}", id);

        commentHistoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
