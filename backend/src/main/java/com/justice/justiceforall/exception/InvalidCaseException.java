package com.justice.justiceforall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCaseException extends ResponseStatusException {
    public InvalidCaseException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
