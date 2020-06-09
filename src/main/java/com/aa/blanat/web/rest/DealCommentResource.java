package com.aa.blanat.web.rest;

import com.aa.blanat.service.DealCommentService;
import com.aa.blanat.web.rest.errors.BadRequestAlertException;
import com.aa.blanat.service.dto.DealCommentDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.aa.blanat.domain.DealComment}.
 */
@RestController
@RequestMapping("/api")
public class DealCommentResource {

    private final Logger log = LoggerFactory.getLogger(DealCommentResource.class);

    private static final String ENTITY_NAME = "dealComment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DealCommentService dealCommentService;

    public DealCommentResource(DealCommentService dealCommentService) {
        this.dealCommentService = dealCommentService;
    }

    /**
     * {@code POST  /deal-comments} : Create a new dealComment.
     *
     * @param dealCommentDTO the dealCommentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dealCommentDTO, or with status {@code 400 (Bad Request)} if the dealComment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deal-comments")
    public ResponseEntity<DealCommentDTO> createDealComment(@Valid @RequestBody DealCommentDTO dealCommentDTO) throws URISyntaxException {
        log.debug("REST request to save DealComment : {}", dealCommentDTO);
        if (dealCommentDTO.getId() != null) {
            throw new BadRequestAlertException("A new dealComment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DealCommentDTO result = dealCommentService.save(dealCommentDTO);
        return ResponseEntity.created(new URI("/api/deal-comments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /deal-comments} : Updates an existing dealComment.
     *
     * @param dealCommentDTO the dealCommentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dealCommentDTO,
     * or with status {@code 400 (Bad Request)} if the dealCommentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dealCommentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deal-comments")
    public ResponseEntity<DealCommentDTO> updateDealComment(@Valid @RequestBody DealCommentDTO dealCommentDTO) throws URISyntaxException {
        log.debug("REST request to update DealComment : {}", dealCommentDTO);
        if (dealCommentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DealCommentDTO result = dealCommentService.save(dealCommentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dealCommentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /deal-comments} : get all the dealComments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dealComments in body.
     */
    @GetMapping("/deal-comments")
    public ResponseEntity<List<DealCommentDTO>> getAllDealComments(Pageable pageable) {
        log.debug("REST request to get a page of DealComments");
        Page<DealCommentDTO> page = dealCommentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /deal-comments/:id} : get the "id" dealComment.
     *
     * @param id the id of the dealCommentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dealCommentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deal-comments/{id}")
    public ResponseEntity<DealCommentDTO> getDealComment(@PathVariable Long id) {
        log.debug("REST request to get DealComment : {}", id);
        Optional<DealCommentDTO> dealCommentDTO = dealCommentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dealCommentDTO);
    }

    /**
     * {@code DELETE  /deal-comments/:id} : delete the "id" dealComment.
     *
     * @param id the id of the dealCommentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deal-comments/{id}")
    public ResponseEntity<Void> deleteDealComment(@PathVariable Long id) {
        log.debug("REST request to delete DealComment : {}", id);

        dealCommentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
