package com.justice.justiceforall.service.casesservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justice.justiceforall.controllers.CaseController;
import com.justice.justiceforall.dto.casesdto.Case;
import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;
import com.justice.justiceforall.repository.CasesRepository;

@Service
public class CreateCaseServiceImpl{
      @Autowired
      private CaseUtil util;
      
      @Autowired
      private CasesRepository casesRepository;
      
      private final Logger logger = LoggerFactory.getLogger(CaseController.class);

      public Case createCase(CreateCaseCommand createCaseCommand) {
            logger.info(
                  "Creating a new Case of title {} and category {}",
                  createCaseCommand.title(),
                  createCaseCommand.category()
            );
            CreateCaseValidator.validateNewCase(createCaseCommand);
            var savedEntity = casesRepository.save(util.getCaseEntity(createCaseCommand));
            logger.info("Created a new Case with ID {}", savedEntity.getId());
            return util.getCaseFromEntity(savedEntity);
      }   
}
