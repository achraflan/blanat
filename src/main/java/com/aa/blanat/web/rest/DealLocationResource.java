package com.aa.blanat.web.rest;

import com.aa.blanat.service.DealLocationService;
import com.aa.blanat.web.rest.errors.BadRequestAlertException;
import com.aa.blanat.service.dto.DealLocationDTO;

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
 * REST controller for managing {@link com.aa.blanat.domain.DealLocation}.
 */
@RestController
@RequestMapping("/api")
public class DealLocationResource {

    private final Logger log = LoggerFactory.getLogger(DealLocationResource.class);

    private static final String ENTITY_NAME = "dealLocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DealLocationService dealLocationService;

    public DealLocationResource(DealLocationService dealLocationService) {
        this.dealLocationService = dealLocationService;
    }

    /**
     * {@code POST  /deal-locations} : Create a new dealLocation.
     *
     * @param dealLocationDTO the dealLocationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dealLocationDTO, or with status {@code 400 (Bad Request)} if the dealLocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deal-locations")
    public ResponseEntity<DealLocationDTO> createDealLocation(@Valid @RequestBody DealLocationDTO dealLocationDTO) throws URISyntaxException {
        log.debug("REST request to save DealLocation : {}", dealLocationDTO);
        if (dealLocationDTO.getId() != null) {
            throw new BadRequestAlertException("A new dealLocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DealLocationDTO result = dealLocationService.save(dealLocationDTO);
        return ResponseEntity.created(new URI("/api/deal-locations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /deal-locations} : Updates an existing dealLocation.
     *
     * @param dealLocationDTO the dealLocationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dealLocationDTO,
     * or with status {@code 400 (Bad Request)} if the dealLocationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dealLocationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deal-locations")
    public ResponseEntity<DealLocationDTO> updateDealLocation(@Valid @RequestBody DealLocationDTO dealLocationDTO) throws URISyntaxException {
        log.debug("REST request to update DealLocation : {}", dealLocationDTO);
        if (dealLocationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DealLocationDTO result = dealLocationService.save(dealLocationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dealLocationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /deal-locations} : get all the dealLocations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dealLocations in body.
     */
    @GetMapping("/deal-locations")
    public List<DealLocationDTO> getAllDealLocations() {
        log.debug("REST request to get all DealLocations");
        return dealLocationService.findAll();
    }

    /**
     * {@code GET  /deal-locations/:id} : get the "id" dealLocation.
     *
     * @param id the id of the dealLocationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dealLocationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deal-locations/{id}")
    public ResponseEntity<DealLocationDTO> getDealLocation(@PathVariable Long id) {
        log.debug("REST request to get DealLocation : {}", id);
        Optional<DealLocationDTO> dealLocationDTO = dealLocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dealLocationDTO);
    }

    /**
     * {@code DELETE  /deal-locations/:id} : delete the "id" dealLocation.
     *
     * @param id the id of the dealLocationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deal-locations/{id}")
    public ResponseEntity<Void> deleteDealLocation(@PathVariable Long id) {
        log.debug("REST request to delete DealLocation : {}", id);

        dealLocationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
