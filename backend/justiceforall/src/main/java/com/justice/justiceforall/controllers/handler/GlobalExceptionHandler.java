package com.justice.justiceforall.controllers.handler;

import com.justice.justiceforall.dto.ErrorResponse;
import com.justice.justiceforall.exception.InvalidUserFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ExceptionHandler(InvalidUserFieldException.class)
  public ErrorResponse handleInvalidUserFieldException(Exception e) {
    return new ErrorResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.name());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Throwable.class)
  public ErrorResponse handleGenericException(Exception e) {
    return new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.name());
  }
}
