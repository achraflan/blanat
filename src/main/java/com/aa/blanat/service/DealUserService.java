package com.aa.blanat.service;

import com.aa.blanat.domain.DealUser;
import com.aa.blanat.repository.DealUserRepository;
import com.aa.blanat.repository.UserRepository;
import com.aa.blanat.service.dto.DealUserDTO;
import com.aa.blanat.service.mapper.DealUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DealUser}.
 */
@Service
@Transactional
public class DealUserService {

    private final Logger log = LoggerFactory.getLogger(DealUserService.class);

    private final DealUserRepository dealUserRepository;

    private final DealUserMapper dealUserMapper;

    private final UserRepository userRepository;

    public DealUserService(DealUserRepository dealUserRepository, DealUserMapper dealUserMapper, UserRepository userRepository) {
        this.dealUserRepository = dealUserRepository;
        this.dealUserMapper = dealUserMapper;
        this.userRepository = userRepository;
    }

    /**
     * Save a dealUser.
     *
     * @param dealUserDTO the entity to save.
     * @return the persisted entity.
     */
    public DealUserDTO save(DealUserDTO dealUserDTO) {
        log.debug("Request to save DealUser : {}", dealUserDTO);
        DealUser dealUser = dealUserMapper.toEntity(dealUserDTO);
        Long userId = dealUserDTO.getUserId();
        userRepository.findById(userId).ifPresent(dealUser::user);
        dealUser = dealUserRepository.save(dealUser);
        return dealUserMapper.toDto(dealUser);
    }

    /**
     * Get all the dealUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DealUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DealUsers");
        return dealUserRepository.findAll(pageable)
            .map(dealUserMapper::toDto);
    }


    /**
     * Get all the dealUsers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DealUserDTO> findAllWithEagerRelationships(Pageable pageable) {
        return dealUserRepository.findAllWithEagerRelationships(pageable).map(dealUserMapper::toDto);
    }

    /**
     * Get one dealUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DealUserDTO> findOne(Long id) {
        log.debug("Request to get DealUser : {}", id);
        return dealUserRepository.findOneWithEagerRelationships(id)
            .map(dealUserMapper::toDto);
    }

    /**
     * Delete the dealUser by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DealUser : {}", id);

        dealUserRepository.deleteById(id);
    }
}
