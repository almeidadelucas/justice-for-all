package com.justice.justiceforall.service.impl.validators;

import com.justice.justiceforall.dto.command.CreateUserCommand;

public abstract class BaseUserCreatorHandler implements Handler<CreateUserCommand> {

    protected Handler<CreateUserCommand> nextHandler;

    @Override
    public void setNext(Handler<CreateUserCommand> next) {
        this.nextHandler = next;
    }

    protected void toNext(CreateUserCommand createUserCommand) {
        if (this.nextHandler != null) {
            this.nextHandler.validate(createUserCommand);
        }
    }
}
