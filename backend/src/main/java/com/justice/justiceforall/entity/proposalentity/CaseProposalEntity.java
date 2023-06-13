package com.justice.justiceforall.entity.proposalentity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "case_proposal")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class CaseProposalEntity {

    @EmbeddedId
    private CaseProposalId caseProposalId;

    @Column(name = "proposal_date", nullable = false)
    private Instant proposalDate;
}
