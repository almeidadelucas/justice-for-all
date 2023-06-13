package com.justice.justiceforall.service.casesservice;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.justice.justiceforall.dto.casesdto.Case;
import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;

@Service
public class CaseServiceImpl implements CaseService {

  @Autowired
  private CreateCaseServiceImpl createCaseService;

  @Autowired
  private RecoverCaseServiceImpl recoverCaseService;

  @Override
  public Case createCase(CreateCaseCommand createCaseCommand) {
    return createCaseService.createCase(createCaseCommand);
  }
    
    @Override
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
                .map(this::getCaseFromEntity)
                .toList();
    }

  @Override
  public Case getCaseById(Long id) {
    return recoverCaseService.getCaseById(id);
  }

  @Override
  public Case[] getAllCases() {
    return recoverCaseService.getAllCases();
  }

  @Override
  public Case[] getCasesByCategory(String category) {
    return recoverCaseService.getCasesByCategory(category);
  }
}

