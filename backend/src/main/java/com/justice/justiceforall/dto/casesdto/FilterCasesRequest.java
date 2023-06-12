package com.justice.justiceforall.dto.casesdto;

public record FilterCasesRequest(
        Boolean open,
        Long userId,
        Long lawyerId,
        String category,
        String description
) {
}
