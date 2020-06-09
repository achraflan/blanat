package com.aa.blanat.web.rest;

import com.aa.blanat.service.DealTrackService;
import com.aa.blanat.web.rest.errors.BadRequestAlertException;
import com.aa.blanat.service.dto.DealTrackDTO;

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
 * REST controller for managing {@link com.aa.blanat.domain.DealTrack}.
 */
@RestController
@RequestMapping("/api")
public class DealTrackResource {

    private final Logger log = LoggerFactory.getLogger(DealTrackResource.class);

    private static final String ENTITY_NAME = "dealTrack";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DealTrackService dealTrackService;

    public DealTrackResource(DealTrackService dealTrackService) {
        this.dealTrackService = dealTrackService;
    }

    /**
     * {@code POST  /deal-tracks} : Create a new dealTrack.
     *
     * @param dealTrackDTO the dealTrackDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dealTrackDTO, or with status {@code 400 (Bad Request)} if the dealTrack has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deal-tracks")
    public ResponseEntity<DealTrackDTO> createDealTrack(@Valid @RequestBody DealTrackDTO dealTrackDTO) throws URISyntaxException {
        log.debug("REST request to save DealTrack : {}", dealTrackDTO);
        if (dealTrackDTO.getId() != null) {
            throw new BadRequestAlertException("A new dealTrack cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DealTrackDTO result = dealTrackService.save(dealTrackDTO);
        return ResponseEntity.created(new URI("/api/deal-tracks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /deal-tracks} : Updates an existing dealTrack.
     *
     * @param dealTrackDTO the dealTrackDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dealTrackDTO,
     * or with status {@code 400 (Bad Request)} if the dealTrackDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dealTrackDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deal-tracks")
    public ResponseEntity<DealTrackDTO> updateDealTrack(@Valid @RequestBody DealTrackDTO dealTrackDTO) throws URISyntaxException {
        log.debug("REST request to update DealTrack : {}", dealTrackDTO);
        if (dealTrackDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DealTrackDTO result = dealTrackService.save(dealTrackDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dealTrackDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /deal-tracks} : get all the dealTracks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dealTracks in body.
     */
    @GetMapping("/deal-tracks")
    public ResponseEntity<List<DealTrackDTO>> getAllDealTracks(Pageable pageable) {
        log.debug("REST request to get a page of DealTracks");
        Page<DealTrackDTO> page = dealTrackService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /deal-tracks/:id} : get the "id" dealTrack.
     *
     * @param id the id of the dealTrackDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dealTrackDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deal-tracks/{id}")
    public ResponseEntity<DealTrackDTO> getDealTrack(@PathVariable Long id) {
        log.debug("REST request to get DealTrack : {}", id);
        Optional<DealTrackDTO> dealTrackDTO = dealTrackService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dealTrackDTO);
    }

    /**
     * {@code DELETE  /deal-tracks/:id} : delete the "id" dealTrack.
     *
     * @param id the id of the dealTrackDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deal-tracks/{id}")
    public ResponseEntity<Void> deleteDealTrack(@PathVariable Long id) {
        log.debug("REST request to delete DealTrack : {}", id);

        dealTrackService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
