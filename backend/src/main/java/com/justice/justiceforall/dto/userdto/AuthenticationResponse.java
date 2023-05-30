package com.justice.justiceforall.dto.userdto;

public record AuthenticationResponse(
    boolean isAuthorized,
    Long userId
) {

}
