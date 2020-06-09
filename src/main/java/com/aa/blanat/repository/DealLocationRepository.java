package com.aa.blanat.repository;

import com.aa.blanat.domain.DealLocation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DealLocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DealLocationRepository extends JpaRepository<DealLocation, Long> {
}
