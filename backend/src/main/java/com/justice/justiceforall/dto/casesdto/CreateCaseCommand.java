package com.justice.justiceforall.dto.casesdto;

import com.justice.justiceforall.entity.casesentity.Alegation;

import lombok.With;

@With
public record CreateCaseCommand(
    String title,
    String category,
    String description,
    Alegation alegation,
    String evicendesPDF,
    String evidenceImage,
    String caseIdentifier,
    boolean open
) {
}
