package com.justice.justiceforall.controllers;

import com.justice.justiceforall.annotation.EndpointAuthentication;
import com.justice.justiceforall.constants.AttributeConstants;
import com.justice.justiceforall.dto.proposaldto.*;
import com.justice.justiceforall.service.proposalservice.ProposalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

    @Autowired
    private ProposalService proposalService;

    private final Logger logger = LoggerFactory.getLogger(ProposalController.class);

    @PostMapping("/case/{caseId}")
    @ResponseStatus(HttpStatus.CREATED)
    @EndpointAuthentication
    public Proposal propose(
            @RequestAttribute(AttributeConstants.AUTHENTICATED_USER_ID) Long userId,
            @PathVariable Long caseId
    ) {
        logger.info("Received a new proposal from user {} to get the case {}", userId, caseId);
        return proposalService.createProposal(new CreateProposalCommand(caseId, userId));
    }

    @GetMapping("/case/{caseId}")
    @ResponseStatus(HttpStatus.OK)
    @EndpointAuthentication
    public DetailedProposals getProposals(@PathVariable Long caseId) {
        logger.info("Fetching all proposals for the case {}", caseId);
        return proposalService.getProposalsForCase(caseId);
    }

    @PostMapping("/match")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @EndpointAuthentication
    public void matchProposal(
            @RequestAttribute(AttributeConstants.AUTHENTICATED_USER_ID) Long userId,
            @RequestBody MatchProposalRequest matchProposalRequest
    ) {
        logger.info("Received a request to match the proposal with the request {}", matchProposalRequest);
        proposalService.matchProposal(
                new MatchProposalCommand(userId, matchProposalRequest.caseId(), matchProposalRequest.lawyerId())
        );
    }
}
