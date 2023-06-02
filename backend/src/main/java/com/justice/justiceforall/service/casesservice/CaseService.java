package com.justice.justiceforall.service.casesservice;

import com.justice.justiceforall.dto.casesdto.Case;
import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;

public interface CaseService {
    Case createCase(CreateCaseCommand createCaseCommand);
    Case getCaseById(Long id);
    Case[] getAllCases();
    Case getCaseByCategory(String category);
}
