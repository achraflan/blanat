package com.aa.blanat.web.rest;

import com.aa.blanat.service.DealReportService;
import com.aa.blanat.web.rest.errors.BadRequestAlertException;
import com.aa.blanat.service.dto.DealReportDTO;

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
 * REST controller for managing {@link com.aa.blanat.domain.DealReport}.
 */
@RestController
@RequestMapping("/api")
public class DealReportResource {

    private final Logger log = LoggerFactory.getLogger(DealReportResource.class);

    private static final String ENTITY_NAME = "dealReport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DealReportService dealReportService;

    public DealReportResource(DealReportService dealReportService) {
        this.dealReportService = dealReportService;
    }

    /**
     * {@code POST  /deal-reports} : Create a new dealReport.
     *
     * @param dealReportDTO the dealReportDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dealReportDTO, or with status {@code 400 (Bad Request)} if the dealReport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deal-reports")
    public ResponseEntity<DealReportDTO> createDealReport(@Valid @RequestBody DealReportDTO dealReportDTO) throws URISyntaxException {
        log.debug("REST request to save DealReport : {}", dealReportDTO);
        if (dealReportDTO.getId() != null) {
            throw new BadRequestAlertException("A new dealReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DealReportDTO result = dealReportService.save(dealReportDTO);
        return ResponseEntity.created(new URI("/api/deal-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /deal-reports} : Updates an existing dealReport.
     *
     * @param dealReportDTO the dealReportDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dealReportDTO,
     * or with status {@code 400 (Bad Request)} if the dealReportDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dealReportDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deal-reports")
    public ResponseEntity<DealReportDTO> updateDealReport(@Valid @RequestBody DealReportDTO dealReportDTO) throws URISyntaxException {
        log.debug("REST request to update DealReport : {}", dealReportDTO);
        if (dealReportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DealReportDTO result = dealReportService.save(dealReportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dealReportDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /deal-reports} : get all the dealReports.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dealReports in body.
     */
    @GetMapping("/deal-reports")
    public ResponseEntity<List<DealReportDTO>> getAllDealReports(Pageable pageable) {
        log.debug("REST request to get a page of DealReports");
        Page<DealReportDTO> page = dealReportService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /deal-reports/:id} : get the "id" dealReport.
     *
     * @param id the id of the dealReportDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dealReportDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deal-reports/{id}")
    public ResponseEntity<DealReportDTO> getDealReport(@PathVariable Long id) {
        log.debug("REST request to get DealReport : {}", id);
        Optional<DealReportDTO> dealReportDTO = dealReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dealReportDTO);
    }

    /**
     * {@code DELETE  /deal-reports/:id} : delete the "id" dealReport.
     *
     * @param id the id of the dealReportDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deal-reports/{id}")
    public ResponseEntity<Void> deleteDealReport(@PathVariable Long id) {
        log.debug("REST request to delete DealReport : {}", id);

        dealReportService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
