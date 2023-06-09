package com.justice.justiceforall.dto.casesdto;

import com.justice.justiceforall.entity.casesentity.Alegation;

import lombok.With;

@With
public record CreateCaseCommand(
        Long userId,
        String title,
        String category,
        String description,
        Alegation alegation,
        String evidencesPDF,
        String evidenceImage,
        String caseIdentifier,
        boolean open
) {
}
