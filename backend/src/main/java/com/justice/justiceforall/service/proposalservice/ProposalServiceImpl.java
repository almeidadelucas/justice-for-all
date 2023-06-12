package com.justice.justiceforall.service.proposalservice;

import com.justice.justiceforall.dto.proposaldto.*;
import com.justice.justiceforall.entity.proposalentity.CaseProposalEntity;
import com.justice.justiceforall.entity.proposalentity.CaseProposalId;
import com.justice.justiceforall.entity.userentity.UserType;
import com.justice.justiceforall.exception.InvalidCaseException;
import com.justice.justiceforall.exception.ProposalNotFoundException;
import com.justice.justiceforall.exception.UserIsNotALawyerException;
import com.justice.justiceforall.exception.UserNotFoundException;
import com.justice.justiceforall.repository.CaseProposalRepository;
import com.justice.justiceforall.repository.CasesRepository;
import com.justice.justiceforall.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class ProposalServiceImpl implements ProposalService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CasesRepository casesRepository;

    @Autowired
    private CaseProposalRepository caseProposalRepository;

    private final Logger logger = LoggerFactory.getLogger(ProposalServiceImpl.class);


    @Override
    public Proposal createProposal(CreateProposalCommand createProposalCommand) {
        var user = usersRepository.findById(createProposalCommand.userId());
        if (user.isEmpty()) {
            throw new UserNotFoundException("No user was found with the given Id");
        }

        if (user.get().getUserType() != UserType.LAWYER) {
            throw new UserIsNotALawyerException("The user must be a Lawyer to perform this action!");
        }

        var proposedCase = casesRepository.findById(createProposalCommand.caseId());
        if (proposedCase.isEmpty() || !proposedCase.get().isOpen()) {
            throw new InvalidCaseException("A proposal can't be created for this case");
        }

        logger.info(
                "Creating a Proposal among lawyer {} and case {}",
                createProposalCommand.userId(), createProposalCommand.caseId()
        );
        var entity = caseProposalRepository.save(toEntity(createProposalCommand));
        return entityToProposal(entity);
    }

    @Override
    public DetailedProposals getProposalsForCase(Long caseId) {
        var proposals = caseProposalRepository.findByCaseProposalIdCaseId(caseId);
        var detailedProposalsList = new ArrayList<DetailedProposal>();
        proposals.forEach(proposal -> {
            var lawyer = usersRepository.findById(proposal.getCaseProposalId().getLawyerId());
            lawyer.ifPresent(lawyerEntity -> detailedProposalsList.add(new DetailedProposal(
                    lawyerEntity.getId(),
                    lawyerEntity.getFirstName(),
                    lawyerEntity.getLastName(),
                    LocalDateTime.ofInstant(proposal.getProposalDate(), ZoneId.systemDefault()),
                    lawyerEntity.getOab()
            )));
        });
        return new DetailedProposals(caseId, detailedProposalsList);
    }

    @Override
    public void matchProposal(MatchProposalCommand matchProposalCommand) {
        var proposedCase = casesRepository.findById(matchProposalCommand.caseId());
        if (proposedCase.isEmpty() || !Objects.equals(proposedCase.get().getUserId(), matchProposalCommand.userId())) {
            throw new InvalidCaseException("The user Id does not match the one at the case!");
        }

        if (!proposedCase.get().isOpen()) {
            throw new InvalidCaseException("The case is not open anymore!");
        }

        var proposal = caseProposalRepository.findById(
                new CaseProposalId(matchProposalCommand.caseId(), matchProposalCommand.lawyerId())
        );

        if (proposal.isEmpty()) {
            throw new ProposalNotFoundException("A proposal with the given parameters was not found!");
        }

        var newCase = proposedCase.get();
        newCase.setLawyerId(matchProposalCommand.lawyerId());
        newCase.setOpen(false);
        casesRepository.save(newCase);
    }

    private CaseProposalEntity toEntity(CreateProposalCommand createProposalCommand) {
        return new CaseProposalEntity(
                new CaseProposalId(createProposalCommand.caseId(), createProposalCommand.userId()),
                Instant.now()
        );
    }

    private Proposal entityToProposal(CaseProposalEntity caseProposalEntity) {
        return new Proposal(
                caseProposalEntity.getCaseProposalId().getCaseId(),
                caseProposalEntity.getCaseProposalId().getLawyerId(),
                LocalDateTime.ofInstant(caseProposalEntity.getProposalDate(), ZoneId.systemDefault())
        );
    }
}
