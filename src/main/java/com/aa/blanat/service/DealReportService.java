package com.aa.blanat.service;

import com.aa.blanat.domain.DealReport;
import com.aa.blanat.repository.DealReportRepository;
import com.aa.blanat.service.dto.DealReportDTO;
import com.aa.blanat.service.mapper.DealReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DealReport}.
 */
@Service
@Transactional
public class DealReportService {

    private final Logger log = LoggerFactory.getLogger(DealReportService.class);

    private final DealReportRepository dealReportRepository;

    private final DealReportMapper dealReportMapper;

    public DealReportService(DealReportRepository dealReportRepository, DealReportMapper dealReportMapper) {
        this.dealReportRepository = dealReportRepository;
        this.dealReportMapper = dealReportMapper;
    }

    /**
     * Save a dealReport.
     *
     * @param dealReportDTO the entity to save.
     * @return the persisted entity.
     */
    public DealReportDTO save(DealReportDTO dealReportDTO) {
        log.debug("Request to save DealReport : {}", dealReportDTO);
        DealReport dealReport = dealReportMapper.toEntity(dealReportDTO);
        dealReport = dealReportRepository.save(dealReport);
        return dealReportMapper.toDto(dealReport);
    }

    /**
     * Get all the dealReports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DealReportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DealReports");
        return dealReportRepository.findAll(pageable)
            .map(dealReportMapper::toDto);
    }


    /**
     * Get one dealReport by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DealReportDTO> findOne(Long id) {
        log.debug("Request to get DealReport : {}", id);
        return dealReportRepository.findById(id)
            .map(dealReportMapper::toDto);
    }

    /**
     * Delete the dealReport by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DealReport : {}", id);

        dealReportRepository.deleteById(id);
    }
}
