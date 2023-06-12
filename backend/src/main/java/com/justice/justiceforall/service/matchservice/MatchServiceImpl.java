package com.justice.justiceforall.service.matchservice;

import com.justice.justiceforall.dto.matchdto.CreateProposalCommand;
import com.justice.justiceforall.dto.matchdto.Proposal;
import com.justice.justiceforall.entity.proposalentity.CaseProposalEntity;
import com.justice.justiceforall.entity.proposalentity.CaseProposalId;
import com.justice.justiceforall.entity.userentity.UserType;
import com.justice.justiceforall.exception.InvalidCaseException;
import com.justice.justiceforall.exception.UserIsNotALawyerException;
import com.justice.justiceforall.exception.UserNotFoundException;
import com.justice.justiceforall.repository.CaseProposalRepository;
import com.justice.justiceforall.repository.CasesRepository;
import com.justice.justiceforall.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CasesRepository casesRepository;

    @Autowired
    private CaseProposalRepository caseProposalRepository;

    private final Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);


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
