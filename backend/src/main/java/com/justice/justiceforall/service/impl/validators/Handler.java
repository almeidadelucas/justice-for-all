package com.justice.justiceforall.service.impl.validators;

public interface Handler<T> {

    void setNext(Handler<T> next);

    void validate(T input);
}
