package com.justice.justiceforall.dto.casesdto;

import java.util.List;

public record FilteredCases(
        int totalPages,
        List<Case> cases
) {
}
