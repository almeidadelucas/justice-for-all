package com.justice.justiceforall.service.casesservice;

import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;
import com.justice.justiceforall.dto.casesdto.EditCaseCommand;
import com.justice.justiceforall.service.casesservice.casevalidator.FileFormatValidator;
import com.justice.justiceforall.service.casesservice.casevalidator.MustHaveFields;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CreateCaseValidator {
	void validateCase(CreateCaseCommand createCaseCommand) {
        var fileFormatValidator = new FileFormatValidator();
        var mustHaveFields = new MustHaveFields();
        
        fileFormatValidator.setNext(mustHaveFields);
     
        fileFormatValidator.validate(createCaseCommand);
    }

    void validateCase(EditCaseCommand editCaseCommand) {
        CreateCaseCommand createCaseCommand = fromEditCommandToCreateCaseCommand(editCaseCommand);

        validateCase(createCaseCommand);
    }

    private CreateCaseCommand fromEditCommandToCreateCaseCommand(EditCaseCommand editCaseCommand) {
        return new CreateCaseCommand(
            editCaseCommand.userId(),
            editCaseCommand.title(),
            editCaseCommand.category(),
            editCaseCommand.description(),
            editCaseCommand.alegation(),
            editCaseCommand.evidencesPDF(),
            editCaseCommand.caseIdentifier(),
            true
        );
    }
}
