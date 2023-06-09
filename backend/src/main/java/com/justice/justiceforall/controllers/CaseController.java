package com.justice.justiceforall.controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.justice.justiceforall.constants.AttributeConstants;
import com.justice.justiceforall.dto.casesdto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.justice.justiceforall.annotation.EndpointAuthentication;
import com.justice.justiceforall.config.AuthenticationType;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @EndpointAuthentication(authenticationType = AuthenticationType.AUTHENTICATED)
    public final FilteredCases getCases(
            @RequestParam(name = "open", required = false) Boolean open,
            @RequestParam(name = "userId", required = false) Long userId,
            @RequestParam(name = "lawyerId", required = false) Long lawyerId,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(name = "size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(name = "sort_by", required = false, defaultValue = "case_id") String sortBy,
            @RequestParam(name = "order_by", required = false, defaultValue = "asc") String orderBy
    ) {
        var filterCasesRequest = new FilterCasesRequest(open, userId, lawyerId, category, description,
                new FilterPaging(pageNumber, pageSize, sortBy, orderBy));
        return caseService.getFilteredCases(filterCasesRequest);
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
