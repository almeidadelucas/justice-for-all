package com.justice.justiceforall.service.proposalservice;

import com.justice.justiceforall.dto.proposaldto.CreateProposalCommand;
import com.justice.justiceforall.dto.proposaldto.DetailedProposals;
import com.justice.justiceforall.dto.proposaldto.MatchProposalCommand;
import com.justice.justiceforall.dto.proposaldto.Proposal;

public interface ProposalService {
    Proposal createProposal(CreateProposalCommand createProposalCommand);

    DetailedProposals getProposalsForCase(Long caseId);

    void matchProposal(MatchProposalCommand matchProposalCommand);
}
