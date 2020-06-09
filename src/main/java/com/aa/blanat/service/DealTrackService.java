package com.aa.blanat.service;

import com.aa.blanat.domain.DealTrack;
import com.aa.blanat.repository.DealTrackRepository;
import com.aa.blanat.service.dto.DealTrackDTO;
import com.aa.blanat.service.mapper.DealTrackMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DealTrack}.
 */
@Service
@Transactional
public class DealTrackService {

    private final Logger log = LoggerFactory.getLogger(DealTrackService.class);

    private final DealTrackRepository dealTrackRepository;

    private final DealTrackMapper dealTrackMapper;

    public DealTrackService(DealTrackRepository dealTrackRepository, DealTrackMapper dealTrackMapper) {
        this.dealTrackRepository = dealTrackRepository;
        this.dealTrackMapper = dealTrackMapper;
    }

    /**
     * Save a dealTrack.
     *
     * @param dealTrackDTO the entity to save.
     * @return the persisted entity.
     */
    public DealTrackDTO save(DealTrackDTO dealTrackDTO) {
        log.debug("Request to save DealTrack : {}", dealTrackDTO);
        DealTrack dealTrack = dealTrackMapper.toEntity(dealTrackDTO);
        dealTrack = dealTrackRepository.save(dealTrack);
        return dealTrackMapper.toDto(dealTrack);
    }

    /**
     * Get all the dealTracks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DealTrackDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DealTracks");
        return dealTrackRepository.findAll(pageable)
            .map(dealTrackMapper::toDto);
    }


    /**
     * Get one dealTrack by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DealTrackDTO> findOne(Long id) {
        log.debug("Request to get DealTrack : {}", id);
        return dealTrackRepository.findById(id)
            .map(dealTrackMapper::toDto);
    }

    /**
     * Delete the dealTrack by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DealTrack : {}", id);

        dealTrackRepository.deleteById(id);
    }
}
