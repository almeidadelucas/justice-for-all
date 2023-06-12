package com.justice.justiceforall.controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.justice.justiceforall.constants.AttributeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.justice.justiceforall.annotation.EndpointAuthentication;
import com.justice.justiceforall.config.AuthenticationType;
import com.justice.justiceforall.dto.casesdto.Case;
import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;
import com.justice.justiceforall.service.casesservice.CaseService;

@RestController
@RequestMapping("/case")
public class CaseController {
    @Autowired
    private CaseService caseService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @EndpointAuthentication(authenticationType = AuthenticationType.AUTHENTICATED)
    public final Case createCase(
            @RequestAttribute(AttributeConstants.AUTHENTICATED_USER_ID) Long userId,
            @RequestBody CreateCaseCommand createCaseCommand
    ) {
        logger.info(
                "Received a request to create a new case of category {} for user {}",
                createCaseCommand.category(), userId
        );
        return caseService.createCase(createCaseCommand.withUserId(userId));
    }

    @GetMapping("/{id}")
    @EndpointAuthentication(authenticationType = AuthenticationType.AUTHENTICATED)
    public ResponseEntity<Case> getCaseById(@PathVariable Long id) {
        logger.info("Received a request to get a case of id {}", id);
        Case caseObj = caseService.getCaseById(id);
        if (caseObj != null) {
            return ResponseEntity.ok(caseObj);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    @EndpointAuthentication(authenticationType = AuthenticationType.AUTHENTICATED)
    public ResponseEntity<List<Case>> getAllCases() {
        logger.info("Received a request to get all cases");
        Case[] cases = caseService.getAllCases();
        if (cases != null) {
            return ResponseEntity.ok(Arrays.asList(cases));
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/category/{category}")
    @EndpointAuthentication(authenticationType = AuthenticationType.AUTHENTICATED)
    public ResponseEntity<List<Case>> getCasesByCategory(@PathVariable String category) {
        logger.info("Received a request to get all cases of category {}", category);
        Case[] cases = caseService.getCasesByCategory(category);
        if (cases != null) {
            return ResponseEntity.ok(Arrays.asList(cases));
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }
}
