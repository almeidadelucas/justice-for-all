package com.justice.justiceforall.dto.casesdto;

import com.justice.justiceforall.entity.casesentity.Alegation;

public record Case(
    long caseId,
    String title,
    String category,
    String description,
    Alegation alegation,
    String evicendesPDF,
    String caseIdentifier,
    boolean open
) {

}
