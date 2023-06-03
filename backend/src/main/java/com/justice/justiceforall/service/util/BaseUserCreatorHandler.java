package com.justice.justiceforall.service.util;

import com.justice.justiceforall.dto.userdto.CreateUserCommand;

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
