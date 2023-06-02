package com.justice.justiceforall.controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public final Case createCase(@RequestBody CreateCaseCommand createCaseCommand) {
        logger.info("Received a request to create a new case of category {}", createCaseCommand.category());
        return caseService.createCase(createCaseCommand);
    }
    
    @GetMapping("/{id}")
    @EndpointAuthentication(authenticationType = AuthenticationType.AUTHENTICATED)
    public ResponseEntity<Case> getCaseById(@PathVariable Long id) {
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
        Case[] cases = caseService.getAllCases();
        if (cases != null) {
            return ResponseEntity.ok(Arrays.asList(cases));
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/category/{category}")
    @EndpointAuthentication(authenticationType = AuthenticationType.AUTHENTICATED)
    public ResponseEntity<Case> getCaseByCategory(@PathVariable String category) {
        Case caseObj = caseService.getCaseByCategory(category);
        if (caseObj != null) {
            return ResponseEntity.ok(caseObj);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
