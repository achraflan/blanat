package com.aa.blanat.web.rest;

import com.aa.blanat.service.DealCategoryService;
import com.aa.blanat.web.rest.errors.BadRequestAlertException;
import com.aa.blanat.service.dto.DealCategoryDTO;

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
 * REST controller for managing {@link com.aa.blanat.domain.DealCategory}.
 */
@RestController
@RequestMapping("/api")
public class DealCategoryResource {

    private final Logger log = LoggerFactory.getLogger(DealCategoryResource.class);

    private static final String ENTITY_NAME = "dealCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DealCategoryService dealCategoryService;

    public DealCategoryResource(DealCategoryService dealCategoryService) {
        this.dealCategoryService = dealCategoryService;
    }

    /**
     * {@code POST  /deal-categories} : Create a new dealCategory.
     *
     * @param dealCategoryDTO the dealCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dealCategoryDTO, or with status {@code 400 (Bad Request)} if the dealCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deal-categories")
    public ResponseEntity<DealCategoryDTO> createDealCategory(@Valid @RequestBody DealCategoryDTO dealCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save DealCategory : {}", dealCategoryDTO);
        if (dealCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new dealCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DealCategoryDTO result = dealCategoryService.save(dealCategoryDTO);
        return ResponseEntity.created(new URI("/api/deal-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /deal-categories} : Updates an existing dealCategory.
     *
     * @param dealCategoryDTO the dealCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dealCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the dealCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dealCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deal-categories")
    public ResponseEntity<DealCategoryDTO> updateDealCategory(@Valid @RequestBody DealCategoryDTO dealCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update DealCategory : {}", dealCategoryDTO);
        if (dealCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DealCategoryDTO result = dealCategoryService.save(dealCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dealCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /deal-categories} : get all the dealCategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dealCategories in body.
     */
    @GetMapping("/deal-categories")
    public ResponseEntity<List<DealCategoryDTO>> getAllDealCategories(Pageable pageable) {
        log.debug("REST request to get a page of DealCategories");
        Page<DealCategoryDTO> page = dealCategoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /deal-categories/:id} : get the "id" dealCategory.
     *
     * @param id the id of the dealCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dealCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deal-categories/{id}")
    public ResponseEntity<DealCategoryDTO> getDealCategory(@PathVariable Long id) {
        log.debug("REST request to get DealCategory : {}", id);
        Optional<DealCategoryDTO> dealCategoryDTO = dealCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dealCategoryDTO);
    }

    /**
     * {@code DELETE  /deal-categories/:id} : delete the "id" dealCategory.
     *
     * @param id the id of the dealCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deal-categories/{id}")
    public ResponseEntity<Void> deleteDealCategory(@PathVariable Long id) {
        log.debug("REST request to delete DealCategory : {}", id);

        dealCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
