package com.aa.blanat.service;

import com.aa.blanat.domain.CommentHistory;
import com.aa.blanat.repository.CommentHistoryRepository;
import com.aa.blanat.service.dto.CommentHistoryDTO;
import com.aa.blanat.service.mapper.CommentHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CommentHistory}.
 */
@Service
@Transactional
public class CommentHistoryService {

    private final Logger log = LoggerFactory.getLogger(CommentHistoryService.class);

    private final CommentHistoryRepository commentHistoryRepository;

    private final CommentHistoryMapper commentHistoryMapper;

    public CommentHistoryService(CommentHistoryRepository commentHistoryRepository, CommentHistoryMapper commentHistoryMapper) {
        this.commentHistoryRepository = commentHistoryRepository;
        this.commentHistoryMapper = commentHistoryMapper;
    }

    /**
     * Save a commentHistory.
     *
     * @param commentHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    public CommentHistoryDTO save(CommentHistoryDTO commentHistoryDTO) {
        log.debug("Request to save CommentHistory : {}", commentHistoryDTO);
        CommentHistory commentHistory = commentHistoryMapper.toEntity(commentHistoryDTO);
        commentHistory = commentHistoryRepository.save(commentHistory);
        return commentHistoryMapper.toDto(commentHistory);
    }

    /**
     * Get all the commentHistories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CommentHistoryDTO> findAll() {
        log.debug("Request to get all CommentHistories");
        return commentHistoryRepository.findAll().stream()
            .map(commentHistoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one commentHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommentHistoryDTO> findOne(Long id) {
        log.debug("Request to get CommentHistory : {}", id);
        return commentHistoryRepository.findById(id)
            .map(commentHistoryMapper::toDto);
    }

    /**
     * Delete the commentHistory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CommentHistory : {}", id);

        commentHistoryRepository.deleteById(id);
    }
}
