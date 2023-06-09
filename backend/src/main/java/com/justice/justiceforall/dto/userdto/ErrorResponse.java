package com.justice.justiceforall.dto.userdto;

public record ErrorResponse(
    String message,
    String httpStatus
) {

}
