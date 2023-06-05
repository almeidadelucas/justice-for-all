package com.justice.justiceforall.service.casesservice.casevalidator;

import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CreateCaseValidator {
	void validateNewCase(CreateCaseCommand createCaseCommand) {
        var fileFormatValidator = new FileFormatValidator();
        var mustHaveFields = new MustHaveFields();
        
        fileFormatValidator.setNext(mustHaveFields);
     
        fileFormatValidator.validate(createCaseCommand);
    }
}
