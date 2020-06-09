package com.aa.blanat.repository;

import com.aa.blanat.domain.DealTrack;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DealTrack entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DealTrackRepository extends JpaRepository<DealTrack, Long> {
}
