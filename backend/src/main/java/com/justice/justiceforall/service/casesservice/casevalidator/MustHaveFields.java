package com.justice.justiceforall.service.casesservice.casevalidator;

import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;
import com.justice.justiceforall.exception.InvalidCaseFieldException;
import com.justice.justiceforall.service.util.BaseCreatorHandler;

public class MustHaveFields extends BaseCreatorHandler<CreateCaseCommand>{

	@Override
	public void validate(CreateCaseCommand input) {
		if(input.title() == null || input.title().length() < 5) {
			throw new InvalidCaseFieldException("The case title must not be null and have atlest 5 letters");
		}
		if(input.category() == null || input.category().length() < 5) {
			throw new InvalidCaseFieldException("The case category must not be null and have atlest 5 letters");
		}
		if(input.description() == null || input.description().length() < 20) {
			throw new InvalidCaseFieldException("The case description must have atlest 5 letters and must not be null");
		}
		if(input.caseIdentifier() == null) {
			throw new InvalidCaseFieldException("The case must have an identifier");
		}
		toNext(input);
	}
}