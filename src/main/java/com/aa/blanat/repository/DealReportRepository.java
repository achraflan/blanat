package com.aa.blanat.repository;

import com.aa.blanat.domain.DealReport;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DealReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DealReportRepository extends JpaRepository<DealReport, Long> {
}
