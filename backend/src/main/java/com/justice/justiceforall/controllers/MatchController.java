package com.justice.justiceforall.controllers;

import com.justice.justiceforall.annotation.EndpointAuthentication;
import com.justice.justiceforall.constants.AttributeConstants;
import com.justice.justiceforall.dto.matchdto.CreateProposalCommand;
import com.justice.justiceforall.dto.matchdto.CreateProposalRequest;
import com.justice.justiceforall.dto.matchdto.DetailedProposals;
import com.justice.justiceforall.dto.matchdto.Proposal;
import com.justice.justiceforall.service.matchservice.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    private final Logger logger = LoggerFactory.getLogger(MatchController.class);

    @PostMapping("/proposal/case/{caseId}")
    @ResponseStatus(HttpStatus.CREATED)
    @EndpointAuthentication
    public Proposal propose(
            @RequestAttribute(AttributeConstants.AUTHENTICATED_USER_ID) Long userId,
            @PathVariable Long caseId
    ) {
        logger.info("Received a new proposal from user {} to get the case {}", userId, caseId);
        return matchService.createProposal(new CreateProposalCommand(caseId, userId));
    }

    @GetMapping("/proposal/case/{caseId}")
    @ResponseStatus(HttpStatus.OK)
    @EndpointAuthentication
    public DetailedProposals getProposals(@PathVariable Long caseId) {
        logger.info("Fetching all proposals for the case {}", caseId);
        return matchService.getProposalsForCase(caseId);
    }

}
