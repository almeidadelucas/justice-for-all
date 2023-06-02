package com.justice.justiceforall.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.justice.justiceforall.entity.casesentity.CaseEntity;


public interface CasesRepository extends CrudRepository<CaseEntity, Long> {

    CaseEntity findByCategory(String category);
    List<CaseEntity> findAll();
 }
