package com.aa.blanat.repository;

import com.aa.blanat.domain.DealHistory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DealHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DealHistoryRepository extends JpaRepository<DealHistory, Long> {
}
