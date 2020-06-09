package com.aa.blanat.service;

import com.aa.blanat.domain.DealLocation;
import com.aa.blanat.repository.DealLocationRepository;
import com.aa.blanat.service.dto.DealLocationDTO;
import com.aa.blanat.service.mapper.DealLocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DealLocation}.
 */
@Service
@Transactional
public class DealLocationService {

    private final Logger log = LoggerFactory.getLogger(DealLocationService.class);

    private final DealLocationRepository dealLocationRepository;

    private final DealLocationMapper dealLocationMapper;

    public DealLocationService(DealLocationRepository dealLocationRepository, DealLocationMapper dealLocationMapper) {
        this.dealLocationRepository = dealLocationRepository;
        this.dealLocationMapper = dealLocationMapper;
    }

    /**
     * Save a dealLocation.
     *
     * @param dealLocationDTO the entity to save.
     * @return the persisted entity.
     */
    public DealLocationDTO save(DealLocationDTO dealLocationDTO) {
        log.debug("Request to save DealLocation : {}", dealLocationDTO);
        DealLocation dealLocation = dealLocationMapper.toEntity(dealLocationDTO);
        dealLocation = dealLocationRepository.save(dealLocation);
        return dealLocationMapper.toDto(dealLocation);
    }

    /**
     * Get all the dealLocations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DealLocationDTO> findAll() {
        log.debug("Request to get all DealLocations");
        return dealLocationRepository.findAll().stream()
            .map(dealLocationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one dealLocation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DealLocationDTO> findOne(Long id) {
        log.debug("Request to get DealLocation : {}", id);
        return dealLocationRepository.findById(id)
            .map(dealLocationMapper::toDto);
    }

    /**
     * Delete the dealLocation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DealLocation : {}", id);

        dealLocationRepository.deleteById(id);
    }
}
