package com.justice.justiceforall.dto.proposaldto;

import java.util.List;

public record DetailedProposals(
        long caseId,
        List<DetailedProposal> proposals
) {
}
