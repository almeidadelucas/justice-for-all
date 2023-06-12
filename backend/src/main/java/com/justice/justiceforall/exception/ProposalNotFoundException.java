package com.justice.justiceforall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProposalNotFoundException extends ResponseStatusException {

    public ProposalNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
