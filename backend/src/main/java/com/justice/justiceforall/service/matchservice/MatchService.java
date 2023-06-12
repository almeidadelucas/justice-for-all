package com.justice.justiceforall.service.matchservice;

import com.justice.justiceforall.dto.matchdto.CreateProposalCommand;
import com.justice.justiceforall.dto.matchdto.DetailedProposals;
import com.justice.justiceforall.dto.matchdto.Proposal;

public interface MatchService {
    Proposal createProposal(CreateProposalCommand createProposalCommand);

    DetailedProposals getProposalsForCase(Long caseId);
}
