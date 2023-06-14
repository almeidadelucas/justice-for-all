package com.justice.justiceforall.dto.proposaldto;

import java.time.LocalDateTime;

public record Proposal(
        Long caseId,
        Long lawyerId,
        LocalDateTime proposalDate
) {
}
