package com.justice.justiceforall.dto.matchdto;

public record CreateProposalCommand(
        Long caseId,
        Long userId
) {
}
