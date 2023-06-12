package com.justice.justiceforall.dto.proposaldto;

public record MatchProposalCommand(
        Long userId,
        Long caseId,
        Long lawyerId
) {
}
