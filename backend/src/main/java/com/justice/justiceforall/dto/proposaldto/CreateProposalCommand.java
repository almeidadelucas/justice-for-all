package com.justice.justiceforall.dto.proposaldto;

public record CreateProposalCommand(
        Long caseId,
        Long userId
) {
}
