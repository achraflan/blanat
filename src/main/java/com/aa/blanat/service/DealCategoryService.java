package com.aa.blanat.service;

import com.aa.blanat.domain.DealCategory;
import com.aa.blanat.repository.DealCategoryRepository;
import com.aa.blanat.service.dto.DealCategoryDTO;
import com.aa.blanat.service.mapper.DealCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DealCategory}.
 */
@Service
@Transactional
public class DealCategoryService {

    private final Logger log = LoggerFactory.getLogger(DealCategoryService.class);

    private final DealCategoryRepository dealCategoryRepository;

    private final DealCategoryMapper dealCategoryMapper;

    public DealCategoryService(DealCategoryRepository dealCategoryRepository, DealCategoryMapper dealCategoryMapper) {
        this.dealCategoryRepository = dealCategoryRepository;
        this.dealCategoryMapper = dealCategoryMapper;
    }

    /**
     * Save a dealCategory.
     *
     * @param dealCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public DealCategoryDTO save(DealCategoryDTO dealCategoryDTO) {
        log.debug("Request to save DealCategory : {}", dealCategoryDTO);
        DealCategory dealCategory = dealCategoryMapper.toEntity(dealCategoryDTO);
        dealCategory = dealCategoryRepository.save(dealCategory);
        return dealCategoryMapper.toDto(dealCategory);
    }

    /**
     * Get all the dealCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DealCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DealCategories");
        return dealCategoryRepository.findAll(pageable)
            .map(dealCategoryMapper::toDto);
    }


    /**
     * Get one dealCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DealCategoryDTO> findOne(Long id) {
        log.debug("Request to get DealCategory : {}", id);
        return dealCategoryRepository.findById(id)
            .map(dealCategoryMapper::toDto);
    }

    /**
     * Delete the dealCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DealCategory : {}", id);

        dealCategoryRepository.deleteById(id);
    }
}
