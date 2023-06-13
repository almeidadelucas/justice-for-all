package com.justice.justiceforall.dto.casesdto;

import com.justice.justiceforall.entity.casesentity.Alegation;

import lombok.Getter;
import lombok.With;

@With
public record Case(
        long caseId,
        long userId,
        Long lawyerId,
        String title,
        String category,
        String description,
        Alegation alegation,
        String evidencesPDF,
        String evidencesImages,
        String caseIdentifier,
        boolean open
) {
}
