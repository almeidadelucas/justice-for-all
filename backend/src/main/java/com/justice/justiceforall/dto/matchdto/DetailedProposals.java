package com.justice.justiceforall.dto.matchdto;

import java.util.List;

public record DetailedProposals(
        long caseId,
        List<DetailedProposal> proposals
) {
}
