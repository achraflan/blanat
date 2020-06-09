package com.aa.blanat.repository;

import com.aa.blanat.domain.DealUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the DealUser entity.
 */
@Repository
public interface DealUserRepository extends JpaRepository<DealUser, Long> {

    @Query(value = "select distinct dealUser from DealUser dealUser left join fetch dealUser.dealSaveds",
        countQuery = "select count(distinct dealUser) from DealUser dealUser")
    Page<DealUser> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct dealUser from DealUser dealUser left join fetch dealUser.dealSaveds")
    List<DealUser> findAllWithEagerRelationships();

    @Query("select dealUser from DealUser dealUser left join fetch dealUser.dealSaveds where dealUser.id =:id")
    Optional<DealUser> findOneWithEagerRelationships(@Param("id") Long id);
}
