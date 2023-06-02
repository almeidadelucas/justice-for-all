package com.justice.justiceforall.service.userservice.uservalidator;

import com.justice.justiceforall.dto.userdto.CreateUserCommand;
import com.justice.justiceforall.service.util.Handler;

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
