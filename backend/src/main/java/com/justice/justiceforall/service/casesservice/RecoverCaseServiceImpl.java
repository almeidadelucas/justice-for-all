package com.justice.justiceforall.service.casesservice;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.justice.justiceforall.controllers.CaseController;
import com.justice.justiceforall.dto.casesdto.Case;
import com.justice.justiceforall.dto.casesdto.FilterCasesRequest;
import com.justice.justiceforall.entity.casesentity.CaseEntity;
import com.justice.justiceforall.repository.CasesRepository;

@Service
public class RecoverCaseServiceImpl{
      @Autowired
      private CasesRepository casesRepository;

      @Autowired
      private CaseUtil util;

      private final Logger logger = LoggerFactory.getLogger(CaseController.class);

      public Case getCaseById(Long id) {
            Optional<CaseEntity> caseEntityOptional = casesRepository.findById(id);
            if (caseEntityOptional.isPresent()) {
                  CaseEntity caseEntity = caseEntityOptional.get();
                  return util.getCaseFromEntity(caseEntity);
            }
            return null;
      }
      
      public Case[] getAllCases() {
            List<CaseEntity> caseEntities = casesRepository.findAll();
            return caseEntities.stream()
                  .map(util::getCaseFromEntity)
                  .toArray(Case[]::new);
      }

      public Case[] getCasesByCategory(String category) {
            List<CaseEntity> caseEntities = casesRepository.findByCategory(category);
            return caseEntities.stream()
                  .map(util::getCaseFromEntity)
                  .toArray(Case[]::new);
      }
      
      public List<Case> getFilteredCases(FilterCasesRequest filterCasesRequest) {
            logger.info("Filtering cases with filter {}", filterCasesRequest);
            return casesRepository.filterCases(
                            filterCasesRequest.open(),
                            filterCasesRequest.userId(),
                            filterCasesRequest.lawyerId(),
                            filterCasesRequest.category(),
                            filterCasesRequest.description(),
                            PageRequest.of(filterCasesRequest.paging().pageNumber() - 1, filterCasesRequest.paging().pageSize())
                    ).stream()
                    .map(util::getCaseFromEntity)
                    .toList();
      }
}
