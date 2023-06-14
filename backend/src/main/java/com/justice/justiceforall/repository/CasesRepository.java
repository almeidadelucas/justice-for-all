package com.justice.justiceforall.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.justice.justiceforall.entity.casesentity.CaseEntity;
import org.springframework.data.repository.query.Param;


public interface CasesRepository extends CrudRepository<CaseEntity, Long> {

    List<CaseEntity> findByCategory(String category);
    List<CaseEntity> findAll();
    @Query(value = "SELECT * FROM cases c WHERE " +
            "(:open is null or c.open = :open) AND " +
            "(:userId is null or c.user_id = :userId) AND " +
            "(:lawyerId is null or c.lawyer_id = :lawyerId) AND " +
            "(:category is null or c.category ilike :category) AND " +
            "(:description is null or lower(c.description) SIMILAR TO '%(' || REPLACE(lower(:description), ' ', '%') || ')%');",
            nativeQuery = true)
    Page<CaseEntity> filterCases(
            @Param("open") Boolean open,
            @Param("userId") Long userId,
            @Param("lawyerId") Long lawyerId,
            @Param("category") String category,
            @Param("description") String description,
            Pageable pageable
    );
 }
