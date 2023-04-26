package com.justice.justiceforall.dto;

public record AuthenticationResponse(
    boolean isAuthorized,
    Long userId
) {

}
