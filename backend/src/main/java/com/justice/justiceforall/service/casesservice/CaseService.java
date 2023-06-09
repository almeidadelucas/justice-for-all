package com.justice.justiceforall.service.casesservice;

import com.justice.justiceforall.dto.casesdto.Case;
import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;
import com.justice.justiceforall.dto.casesdto.FilterCasesRequest;
import com.justice.justiceforall.dto.casesdto.FilteredCases;

import java.util.List;

public interface CaseService {
    Case createCase(CreateCaseCommand createCaseCommand);
    FilteredCases getFilteredCases(FilterCasesRequest filterCasesRequest);
    Case getCaseById(Long id);
    Case[] getAllCases();
    Case[] getCasesByCategory(String category);
}
