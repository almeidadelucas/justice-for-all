package com.justice.justiceforall.repository;

import com.justice.justiceforall.entity.proposalentity.CaseProposalEntity;
import com.justice.justiceforall.entity.proposalentity.CaseProposalId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CaseProposalRepository extends CrudRepository<CaseProposalEntity, CaseProposalId> {
    List<CaseProposalEntity> findByCaseProposalIdCaseId(Long caseId);
}
