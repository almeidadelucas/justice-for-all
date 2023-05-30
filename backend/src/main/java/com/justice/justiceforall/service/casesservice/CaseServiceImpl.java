package com.justice.justiceforall.service.casesservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justice.justiceforall.controllers.CaseController;
import com.justice.justiceforall.dto.casesdto.Case;
import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;
import com.justice.justiceforall.entity.casesentity.CaseEntity;
import com.justice.justiceforall.repository.CasesRepository;

@Service
public class CaseServiceImpl implements CaseService {

  @Autowired
  private CasesRepository CasesRepository;

  private final Logger logger = LoggerFactory.getLogger(CaseController.class);

  @Override
  public Case createCase(CreateCaseCommand createCaseCommand) {
    logger.info(
        "Creating a new Case of title {} and category {}",
        createCaseCommand.title(),
        createCaseCommand.category()
    );
    CreateCaseValidator.validateNewCase(createCaseCommand);
    var savedEntity = CasesRepository.save(getCaseEntity(createCaseCommand));
    logger.info("Created a new Case with ID {}", savedEntity.getId());
    return getCaseFromEntity(savedEntity);
  }

  private CaseEntity getCaseEntity(CreateCaseCommand createCaseCommand) {
    return CaseEntity.builder()
        .title(createCaseCommand.title())
        .category(createCaseCommand.category())
        .description(createCaseCommand.description())
        .evidencesPdf(createCaseCommand.evicendesPDF())
        .alegation(createCaseCommand.alegation())
        .evidenceImage(createCaseCommand.evidenceImage())
        .caseIdentifier(createCaseCommand.caseIdentifier())
        .open(createCaseCommand.open())
        .build();
  }

  private Case getCaseFromEntity(CaseEntity entity) {
    return new Case(0, null, null, null, null, null, null, false);
  }
}

