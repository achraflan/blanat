package com.aa.blanat.repository;

import com.aa.blanat.domain.DealCategory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DealCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DealCategoryRepository extends JpaRepository<DealCategory, Long> {
}
