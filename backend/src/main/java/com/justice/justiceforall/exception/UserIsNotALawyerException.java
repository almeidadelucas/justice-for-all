package com.justice.justiceforall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserIsNotALawyerException extends ResponseStatusException {
    public UserIsNotALawyerException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
