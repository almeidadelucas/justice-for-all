package com.justice.justiceforall.service.util;


public abstract class BaseCreatorHandler<T> implements Handler<T> {

    protected Handler<T> nextHandler;

    @Override
    public void setNext(Handler<T> next) {
        this.nextHandler = next;
    }

    protected void toNext(T input) {
        if (this.nextHandler != null) {
            this.nextHandler.validate(input);
        }
    }
}
