package com.justice.justiceforall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCaseFieldException extends ResponseStatusException{

	private static final long serialVersionUID = 1L;

	public InvalidCaseFieldException(String message) {
		super(HttpStatusCode.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()), message);
	}

}
