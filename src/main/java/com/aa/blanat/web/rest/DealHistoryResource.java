package com.aa.blanat.web.rest;

import com.aa.blanat.service.DealHistoryService;
import com.aa.blanat.web.rest.errors.BadRequestAlertException;
import com.aa.blanat.service.dto.DealHistoryDTO;

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
 * REST controller for managing {@link com.aa.blanat.domain.DealHistory}.
 */
@RestController
@RequestMapping("/api")
public class DealHistoryResource {

    private final Logger log = LoggerFactory.getLogger(DealHistoryResource.class);

    private static final String ENTITY_NAME = "dealHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DealHistoryService dealHistoryService;

    public DealHistoryResource(DealHistoryService dealHistoryService) {
        this.dealHistoryService = dealHistoryService;
    }

    /**
     * {@code POST  /deal-histories} : Create a new dealHistory.
     *
     * @param dealHistoryDTO the dealHistoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dealHistoryDTO, or with status {@code 400 (Bad Request)} if the dealHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deal-histories")
    public ResponseEntity<DealHistoryDTO> createDealHistory(@Valid @RequestBody DealHistoryDTO dealHistoryDTO) throws URISyntaxException {
        log.debug("REST request to save DealHistory : {}", dealHistoryDTO);
        if (dealHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new dealHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DealHistoryDTO result = dealHistoryService.save(dealHistoryDTO);
        return ResponseEntity.created(new URI("/api/deal-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /deal-histories} : Updates an existing dealHistory.
     *
     * @param dealHistoryDTO the dealHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dealHistoryDTO,
     * or with status {@code 400 (Bad Request)} if the dealHistoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dealHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deal-histories")
    public ResponseEntity<DealHistoryDTO> updateDealHistory(@Valid @RequestBody DealHistoryDTO dealHistoryDTO) throws URISyntaxException {
        log.debug("REST request to update DealHistory : {}", dealHistoryDTO);
        if (dealHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DealHistoryDTO result = dealHistoryService.save(dealHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dealHistoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /deal-histories} : get all the dealHistories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dealHistories in body.
     */
    @GetMapping("/deal-histories")
    public ResponseEntity<List<DealHistoryDTO>> getAllDealHistories(Pageable pageable) {
        log.debug("REST request to get a page of DealHistories");
        Page<DealHistoryDTO> page = dealHistoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /deal-histories/:id} : get the "id" dealHistory.
     *
     * @param id the id of the dealHistoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dealHistoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deal-histories/{id}")
    public ResponseEntity<DealHistoryDTO> getDealHistory(@PathVariable Long id) {
        log.debug("REST request to get DealHistory : {}", id);
        Optional<DealHistoryDTO> dealHistoryDTO = dealHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dealHistoryDTO);
    }

    /**
     * {@code DELETE  /deal-histories/:id} : delete the "id" dealHistory.
     *
     * @param id the id of the dealHistoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deal-histories/{id}")
    public ResponseEntity<Void> deleteDealHistory(@PathVariable Long id) {
        log.debug("REST request to delete DealHistory : {}", id);

        dealHistoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
