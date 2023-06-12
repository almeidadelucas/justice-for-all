package com.justice.justiceforall.controllers;

import com.justice.justiceforall.annotation.EndpointAuthentication;
import com.justice.justiceforall.constants.AttributeConstants;
import com.justice.justiceforall.dto.matchdto.CreateProposalCommand;
import com.justice.justiceforall.dto.matchdto.CreateProposalRequest;
import com.justice.justiceforall.dto.matchdto.Proposal;
import com.justice.justiceforall.service.matchservice.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    private final Logger logger = LoggerFactory.getLogger(MatchController.class);

    @PostMapping("/proposal")
    @ResponseStatus(HttpStatus.CREATED)
    @EndpointAuthentication
    public Proposal propose(
            @RequestAttribute(AttributeConstants.AUTHENTICATED_USER_ID) Long userId,
            @RequestBody CreateProposalRequest createProposalRequest
    ) {
        logger.info("Received a new proposal from user {} to get the case {}", userId, createProposalRequest.caseId());
        return matchService.createProposal(new CreateProposalCommand(createProposalRequest.caseId(), userId));
    }

}
