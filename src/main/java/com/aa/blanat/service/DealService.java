package com.aa.blanat.service;

import com.aa.blanat.domain.Deal;
import com.aa.blanat.repository.DealRepository;
import com.aa.blanat.service.dto.DealDTO;
import com.aa.blanat.service.mapper.DealMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Deal}.
 */
@Service
@Transactional
public class DealService {

    private final Logger log = LoggerFactory.getLogger(DealService.class);

    private final DealRepository dealRepository;

    private final DealMapper dealMapper;

    public DealService(DealRepository dealRepository, DealMapper dealMapper) {
        this.dealRepository = dealRepository;
        this.dealMapper = dealMapper;
    }

    /**
     * Save a deal.
     *
     * @param dealDTO the entity to save.
     * @return the persisted entity.
     */
    public DealDTO save(DealDTO dealDTO) {
        log.debug("Request to save Deal : {}", dealDTO);
        Deal deal = dealMapper.toEntity(dealDTO);
        deal = dealRepository.save(deal);
        return dealMapper.toDto(deal);
    }

    /**
     * Get all the deals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DealDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Deals");
        return dealRepository.findAll(pageable)
            .map(dealMapper::toDto);
    }


    /**
     * Get all the deals with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DealDTO> findAllWithEagerRelationships(Pageable pageable) {
        return dealRepository.findAllWithEagerRelationships(pageable).map(dealMapper::toDto);
    }

    /**
     * Get one deal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DealDTO> findOne(Long id) {
        log.debug("Request to get Deal : {}", id);
        return dealRepository.findOneWithEagerRelationships(id)
            .map(dealMapper::toDto);
    }

    /**
     * Delete the deal by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Deal : {}", id);

        dealRepository.deleteById(id);
    }
}
