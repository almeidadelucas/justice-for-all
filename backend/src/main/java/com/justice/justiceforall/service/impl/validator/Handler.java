package com.justice.justiceforall.service.impl.validator;

public interface Handler<T> {

    void setNext(Handler<T> next);

    void validate(T input);
}
