package com.justice.justiceforall.service.casesservice;

import org.springframework.stereotype.Component;

import com.justice.justiceforall.dto.casesdto.Case;
import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;
import com.justice.justiceforall.entity.casesentity.CaseEntity;

@Component
public class CaseUtil {
      public CaseEntity getCaseEntity(CreateCaseCommand createCaseCommand) {
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

      public Case getCaseFromEntity(CaseEntity entity) {
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
