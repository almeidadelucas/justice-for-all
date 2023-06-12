package com.justice.justiceforall.dto.matchdto;

import com.justice.justiceforall.entity.userentity.UserType;

import java.time.LocalDateTime;

public record DetailedProposal(
        long lawyerId,
        String lawyerFirstName,
        String lawyerLastName,
        LocalDateTime proposalDate,
        String oab
) {
}
