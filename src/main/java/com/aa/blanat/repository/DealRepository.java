package com.aa.blanat.repository;

import com.aa.blanat.domain.Deal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Deal entity.
 */
@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {

    @Query(value = "select distinct deal from Deal deal left join fetch deal.dealCategories",
        countQuery = "select count(distinct deal) from Deal deal")
    Page<Deal> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct deal from Deal deal left join fetch deal.dealCategories")
    List<Deal> findAllWithEagerRelationships();

    @Query("select deal from Deal deal left join fetch deal.dealCategories where deal.id =:id")
    Optional<Deal> findOneWithEagerRelationships(@Param("id") Long id);
}
