package com.justice.justiceforall.service.casesservice;

import org.springframework.beans.factory.annotation.Autowired;
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

