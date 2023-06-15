package com.justice.justiceforall.service.casesservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.justice.justiceforall.helper.cases.FilterCasesRequestFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import com.justice.justiceforall.helper.cases.CaseFixture;
import com.justice.justiceforall.entity.casesentity.CaseEntity;
import com.justice.justiceforall.helper.cases.CaseEntityFixture;
import com.justice.justiceforall.helper.cases.CreateCaseCommandFixture;
import com.justice.justiceforall.repository.CasesRepository;

@SpringBootTest
@ActiveProfiles("test")
public class CaseServiceTests {
    @Autowired
    private CaseService caseService;

    @MockBean
    private CasesRepository casesRepository;

    @Test
    void ensureTheCasesRepositoryIsProperlyCalledWhenCreatingACase() {
        var createCaseCommand = CreateCaseCommandFixture.correctCaseCommand();

        when(casesRepository.save(any())).thenReturn(CaseEntityFixture.caseWithId());

        var response = caseService.createCase(createCaseCommand);

        verify(casesRepository, times(1)).save(CaseEntityFixture.caseWithoutId());

        assertEquals(CaseFixture.correctCase(), response);
    }

    @Test
    void ensureTheGetFilteredCasesRepositoryQueryIsCalledWithCorrectArguments() {
        Page<CaseEntity> caseEntities = new PageImpl<>(Arrays.stream(CaseEntityFixture.casesWithId()).toList());
        when(casesRepository.filterCases(any(), any(), any(), any(), any(), any())).thenReturn(caseEntities);
        var filter = FilterCasesRequestFixture.getRandom();
        var response = caseService.getFilteredCases(filter);
        verify(casesRepository, times(1)).filterCases(
                filter.open(),
                filter.userId(),
                filter.lawyerId(),
                filter.category(),
                filter.description(),
                PageRequest.of(filter.paging().pageNumber() - 1, filter.paging().pageSize(), Sort.by(Sort.Direction.ASC, "case_id"))
        );
        assertEquals(caseEntities.get().count(), response.cases().size());
    }

    @Test
    public void test_getCaseById_when_id_exist() {
        Long caseId = 1L;

        when(casesRepository.findById(caseId)).thenReturn(Optional.of(CaseEntityFixture.caseWithId()));

        var response = caseService.getCaseById(caseId);

        verify(casesRepository, times(1)).findById(caseId);

        assertNotNull(response);
        assertEquals(CaseFixture.correctCase(), response);
    }

    @Test
    public void test_getCaseById_when_id_does_not_exist() {
        Long caseId = 1L;

        when(casesRepository.findById(caseId)).thenReturn(Optional.empty());

        var response = caseService.getCaseById(caseId);

        verify(casesRepository, times(1)).findById(caseId);
        assertNull(response);
    }

    @Test
    public void test_getAllCases_when_there_are_cases() {
        List<CaseEntity> caseEntities = Arrays.asList(CaseEntityFixture.casesWithId());

        when(casesRepository.findAll()).thenReturn(caseEntities);

        var allCases = caseService.getAllCases();

        verify(casesRepository, times(1)).findAll();

        assertNotNull(allCases);
        assertArrayEquals(CaseFixture.correctCases(), allCases);
    }

    @Test
    public void test_getAllCases_when_there_are_not_cases() {
        List<CaseEntity> emptyCaseEntities = Collections.emptyList();

        when(casesRepository.findAll()).thenReturn(emptyCaseEntities);

        var allCases = caseService.getAllCases();

        verify(casesRepository, times(1)).findAll();
        assertNotNull(allCases);
        assertEquals(0, allCases.length);
    }

    @Test
    public void testGetCasesByCategory() {
        String category = "furto";
        List<CaseEntity> caseEntities = Arrays.asList(CaseEntityFixture.casesFurto());

        when(casesRepository.findByCategory(category)).thenReturn(caseEntities);

        var casesByCategory = caseService.getCasesByCategory(category);

        verify(casesRepository, times(1)).findByCategory(category);

        assertNotNull(casesByCategory);
        assertArrayEquals(CaseFixture.sameCategoryCases(), casesByCategory);
    }
}
