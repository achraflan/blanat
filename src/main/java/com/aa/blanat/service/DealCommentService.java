package com.aa.blanat.service;

import com.aa.blanat.domain.DealComment;
import com.aa.blanat.repository.DealCommentRepository;
import com.aa.blanat.service.dto.DealCommentDTO;
import com.aa.blanat.service.mapper.DealCommentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DealComment}.
 */
@Service
@Transactional
public class DealCommentService {

    private final Logger log = LoggerFactory.getLogger(DealCommentService.class);

    private final DealCommentRepository dealCommentRepository;

    private final DealCommentMapper dealCommentMapper;

    public DealCommentService(DealCommentRepository dealCommentRepository, DealCommentMapper dealCommentMapper) {
        this.dealCommentRepository = dealCommentRepository;
        this.dealCommentMapper = dealCommentMapper;
    }

    /**
     * Save a dealComment.
     *
     * @param dealCommentDTO the entity to save.
     * @return the persisted entity.
     */
    public DealCommentDTO save(DealCommentDTO dealCommentDTO) {
        log.debug("Request to save DealComment : {}", dealCommentDTO);
        DealComment dealComment = dealCommentMapper.toEntity(dealCommentDTO);
        dealComment = dealCommentRepository.save(dealComment);
        return dealCommentMapper.toDto(dealComment);
    }

    /**
     * Get all the dealComments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DealCommentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DealComments");
        return dealCommentRepository.findAll(pageable)
            .map(dealCommentMapper::toDto);
    }


    /**
     * Get one dealComment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DealCommentDTO> findOne(Long id) {
        log.debug("Request to get DealComment : {}", id);
        return dealCommentRepository.findById(id)
            .map(dealCommentMapper::toDto);
    }

    /**
     * Delete the dealComment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DealComment : {}", id);

        dealCommentRepository.deleteById(id);
    }
}
