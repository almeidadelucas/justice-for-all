package com.justice.justiceforall.service.util;

import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;

public abstract class BaseCaseCreatorHandler implements Handler<CreateCaseCommand>{

	protected Handler<CreateCaseCommand> nextHandler;
	
	@Override
	public void setNext(Handler<CreateCaseCommand> next) {
		this.nextHandler = next;
	}

	@Override
	public void validate(CreateCaseCommand createCaseCommand) {
		if (this.nextHandler != null) {
            this.nextHandler.validate(createCaseCommand);
        }
	}

}
