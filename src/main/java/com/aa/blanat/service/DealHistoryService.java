package com.aa.blanat.service;

import com.aa.blanat.domain.DealHistory;
import com.aa.blanat.repository.DealHistoryRepository;
import com.aa.blanat.service.dto.DealHistoryDTO;
import com.aa.blanat.service.mapper.DealHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DealHistory}.
 */
@Service
@Transactional
public class DealHistoryService {

    private final Logger log = LoggerFactory.getLogger(DealHistoryService.class);

    private final DealHistoryRepository dealHistoryRepository;

    private final DealHistoryMapper dealHistoryMapper;

    public DealHistoryService(DealHistoryRepository dealHistoryRepository, DealHistoryMapper dealHistoryMapper) {
        this.dealHistoryRepository = dealHistoryRepository;
        this.dealHistoryMapper = dealHistoryMapper;
    }

    /**
     * Save a dealHistory.
     *
     * @param dealHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    public DealHistoryDTO save(DealHistoryDTO dealHistoryDTO) {
        log.debug("Request to save DealHistory : {}", dealHistoryDTO);
        DealHistory dealHistory = dealHistoryMapper.toEntity(dealHistoryDTO);
        dealHistory = dealHistoryRepository.save(dealHistory);
        return dealHistoryMapper.toDto(dealHistory);
    }

    /**
     * Get all the dealHistories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DealHistoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DealHistories");
        return dealHistoryRepository.findAll(pageable)
            .map(dealHistoryMapper::toDto);
    }


    /**
     * Get one dealHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DealHistoryDTO> findOne(Long id) {
        log.debug("Request to get DealHistory : {}", id);
        return dealHistoryRepository.findById(id)
            .map(dealHistoryMapper::toDto);
    }

    /**
     * Delete the dealHistory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DealHistory : {}", id);

        dealHistoryRepository.deleteById(id);
    }
}
