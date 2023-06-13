package com.justice.justiceforall.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.justice.justiceforall.helper.cases.FilterCasesRequestFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.justice.justiceforall.dto.casesdto.Case;
import com.justice.justiceforall.helper.cases.CaseFixture;
import com.justice.justiceforall.helper.cases.CreateCaseCommandFixture;
import com.justice.justiceforall.service.casesservice.CaseService;

@SpringBootTest
@ActiveProfiles("test")
public class CaseControllerTests {
    @Autowired
    private CaseController caseController;

    @MockBean
    private CaseService caseService;

    @Test
    void ensureTheControllerCallsTheServiceMethod() {
        var createCaseCommand = CreateCaseCommandFixture.correctCaseCommand();
        var loggedUserId = new Random().nextLong();
        var case1 = CaseFixture.correctCase();

        when(caseService.createCase(any())).thenReturn(case1);

        var response = caseController.createCase(loggedUserId, createCaseCommand);

        verify(caseService, times(1)).createCase(createCaseCommand.withUserId(loggedUserId));
        assertEquals(case1, response);
    }

    @Test
    void ensureTheFilteredSearchCallsTheServiceWithProperArguments() {
        var filter = FilterCasesRequestFixture.getRandom();
        when(caseService.getFilteredCases(filter)).thenReturn(List.of());
        var response = caseController.getCases(
                filter.open(),
                filter.userId(),
                filter.lawyerId(),
                filter.category(),
                filter.description(),
                filter.paging().pageNumber(),
                filter.paging().pageSize()
        );
        verify(caseService, times(1)).getFilteredCases(filter);
        assertEquals(List.of(), response);
    }

    @Test
    void ensure_getCaseById_returns_correct_response_for_existing_case() {
        var caseObj = CaseFixture.correctCase();
        Long id = caseObj.caseId();
        when(caseService.getCaseById(id)).thenReturn(caseObj);

        ResponseEntity<Case> response = caseController.getCaseById(id);

        verify(caseService, times(1)).getCaseById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(caseObj, response.getBody());
    }

    @Test
    void ensure_getCaseById_return_notFound_status() {
        Long id = 5555L;
        when(caseService.getCaseById(id)).thenReturn(null);

        ResponseEntity<Case> response = caseController.getCaseById(id);

        verify(caseService, times(1)).getCaseById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void ensure_getAllCases_return_correct_httpStatus_and_correct_cases() {
        Case[] cases = CaseFixture.correctCases();
        when(caseService.getAllCases()).thenReturn(cases);

        ResponseEntity<List<Case>> response = caseController.getAllCases();

        verify(caseService, times(1)).getAllCases();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(cases), response.getBody());
    }

    @Test
    void ensure_getAllCases_return_empty_list_when_there_is_no_case() {
        when(caseService.getAllCases()).thenReturn(null);

        ResponseEntity<List<Case>> response = caseController.getAllCases();

        verify(caseService, times(1)).getAllCases();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
    void ensure_getCasesByCategory_returns_correct_http_response_and_list_of_cases() {
        String category = "furto";
        Case[] cases = CaseFixture.sameCategoryCases();
        when(caseService.getCasesByCategory(category)).thenReturn(cases);

        ResponseEntity<List<Case>> response = caseController.getCasesByCategory(category);

        verify(caseService, times(1)).getCasesByCategory(category);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(cases), response.getBody());
    }

    @Test
    void getCaseByCategory_NoCases_ReturnsOkResponseWithEmptyList() {
        String category = "trabalhista";
        when(caseService.getCasesByCategory(category)).thenReturn(null);

        ResponseEntity<List<Case>> response = caseController.getCasesByCategory(category);

        verify(caseService, times(1)).getCasesByCategory(category);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }
}
