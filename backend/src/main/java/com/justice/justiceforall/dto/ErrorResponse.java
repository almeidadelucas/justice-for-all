package com.justice.justiceforall.dto;

public record ErrorResponse(
    String message,
    String httpStatus
) {

}
