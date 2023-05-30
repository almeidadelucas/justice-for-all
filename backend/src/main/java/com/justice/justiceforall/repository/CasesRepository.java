package com.justice.justiceforall.repository;

import org.springframework.data.repository.CrudRepository;

import com.justice.justiceforall.entity.casesentity.CaseEntity;


public interface CasesRepository extends CrudRepository<CaseEntity, Long> {

    CaseEntity findByCategory(String category);
  
  }
