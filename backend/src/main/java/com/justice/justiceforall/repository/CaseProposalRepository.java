package com.justice.justiceforall.repository;

import com.justice.justiceforall.entity.proposalentity.CaseProposalEntity;
import com.justice.justiceforall.entity.proposalentity.CaseProposalId;
import org.springframework.data.repository.CrudRepository;

public interface CaseProposalRepository extends CrudRepository<CaseProposalEntity, CaseProposalId> {
}
