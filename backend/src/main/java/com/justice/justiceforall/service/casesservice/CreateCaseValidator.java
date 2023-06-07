package com.justice.justiceforall.service.casesservice;

import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;
import com.justice.justiceforall.service.casesservice.casevalidator.FileFormatValidator;
import com.justice.justiceforall.service.casesservice.casevalidator.MustHaveFields;

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
