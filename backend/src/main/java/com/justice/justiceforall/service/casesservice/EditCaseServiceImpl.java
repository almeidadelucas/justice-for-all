package com.justice.justiceforall.service.casesservice;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justice.justiceforall.controllers.CaseController;
import com.justice.justiceforall.dto.casesdto.Case;
import com.justice.justiceforall.dto.casesdto.EditCaseCommand;
import com.justice.justiceforall.entity.casesentity.CaseEntity;
import com.justice.justiceforall.repository.CasesRepository;

@Service
public class EditCaseServiceImpl {
    @Autowired
    private CaseUtil util;
    
    @Autowired
    private CasesRepository casesRepository;
    
    private final Logger logger = LoggerFactory.getLogger(CaseController.class);

    public Case editCase(EditCaseCommand editCaseCommand) {
        logger.info(
                  "Editing the Case of id {}",
                  editCaseCommand
        );
        Optional<CaseEntity> originCase = casesRepository.findById(editCaseCommand.caseId());
        if( originCase.isPresent()) {
            CaseEntity caseEntity = originCase.get();
            CreateCaseValidator.validateCase(editCaseCommand);
            caseEntity = update(caseEntity, editCaseCommand);

            CaseEntity updatedCaseEntity = casesRepository.save(caseEntity);

            Case updatedCase = util.getCaseFromEntity(updatedCaseEntity);
            return updatedCase;
        }else {
            throw new IllegalArgumentException("Case not found with id: " + editCaseCommand.caseId());
        }
    }

    private CaseEntity update(CaseEntity caseEntity, EditCaseCommand editCaseCommand) {
        caseEntity.setTitle(editCaseCommand.title());
        caseEntity.setCategory(editCaseCommand.category());
        caseEntity.setDescription(editCaseCommand.description());
        caseEntity.setAlegation(editCaseCommand.alegation());
        caseEntity.setEvidencesPdf(editCaseCommand.evidencesPDF());
        caseEntity.setCaseIdentifier(editCaseCommand.caseIdentifier());

        return caseEntity;
    }
}
