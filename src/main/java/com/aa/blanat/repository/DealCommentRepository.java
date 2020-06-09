package com.aa.blanat.repository;

import com.aa.blanat.domain.DealComment;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DealComment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DealCommentRepository extends JpaRepository<DealComment, Long> {
}
