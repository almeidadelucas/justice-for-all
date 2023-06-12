package com.justice.justiceforall.dto.matchdto;

import java.time.LocalDateTime;

public record Proposal(
        Long caseId,
        Long lawyerId,
        LocalDateTime proposalDate
) {
}
