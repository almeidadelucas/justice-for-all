package com.justice.justiceforall.service.casesservice;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.justice.justiceforall.dto.casesdto.FilterCasesRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.justice.justiceforall.controllers.CaseController;
import com.justice.justiceforall.dto.casesdto.Case;
import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;
import com.justice.justiceforall.entity.casesentity.CaseEntity;
import com.justice.justiceforall.repository.CasesRepository;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CasesRepository casesRepository;

    private final Logger logger = LoggerFactory.getLogger(CaseController.class);

    @Override
    public Case createCase(CreateCaseCommand createCaseCommand) {
        logger.info(
                "Creating a new Case of title {} and category {}",
                createCaseCommand.title(),
                createCaseCommand.category()
        );
        CreateCaseValidator.validateNewCase(createCaseCommand);
        var savedEntity = casesRepository.save(getCaseEntity(createCaseCommand));
        logger.info("Created a new Case with ID {}", savedEntity.getId());
        return getCaseFromEntity(savedEntity);
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
        Optional<CaseEntity> caseEntityOptional = casesRepository.findById(id);
        if (caseEntityOptional.isPresent()) {
            CaseEntity caseEntity = caseEntityOptional.get();
            return getCaseFromEntity(caseEntity);
        }
        return null;
    }

    @Override
    public Case[] getAllCases() {
        List<CaseEntity> caseEntities = casesRepository.findAll();
        return caseEntities.stream()
                .map(this::getCaseFromEntity)
                .toArray(Case[]::new);
    }

    @Override
    public Case[] getCasesByCategory(String category) {
        List<CaseEntity> caseEntities = casesRepository.findByCategory(category);
        return caseEntities.stream()
                .map(this::getCaseFromEntity)
                .toArray(Case[]::new);
    }

    private CaseEntity getCaseEntity(CreateCaseCommand createCaseCommand) {
        return CaseEntity.builder()
                .userId(createCaseCommand.userId())
                .title(createCaseCommand.title())
                .category(createCaseCommand.category())
                .description(createCaseCommand.description())
                .alegation(createCaseCommand.alegation())
                .evidencesPdf(createCaseCommand.evidencesPDF())
                .evidenceImage(createCaseCommand.evidenceImage())
                .caseIdentifier(createCaseCommand.caseIdentifier())
                .open(createCaseCommand.open())
                .build();
    }

    private Case getCaseFromEntity(CaseEntity entity) {
        return new Case(
                entity.getId(),
                entity.getUserId(),
                entity.getLawyerId(),
                entity.getTitle(),
                entity.getCategory(),
                entity.getDescription(),
                entity.getAlegation(),
                entity.getEvidencesPdf(),
                entity.getEvidenceImage(),
                entity.getCaseIdentifier(),
                entity.isOpen()
        );
    }


}

