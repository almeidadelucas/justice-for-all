package com.justice.justiceforall.dto.proposaldto;

import java.time.LocalDateTime;

public record DetailedProposal(
        long lawyerId,
        String lawyerFirstName,
        String lawyerLastName,
        LocalDateTime proposalDate,
        String oab
) {
}
